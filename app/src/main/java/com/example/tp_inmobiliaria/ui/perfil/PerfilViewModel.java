package com.example.tp_inmobiliaria.ui.perfil;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp_inmobiliaria.models.Propietario;
import com.example.tp_inmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PerfilViewModel extends AndroidViewModel {

    private final MutableLiveData<Propietario> mp = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mEstado = new MutableLiveData<>();
    private final MutableLiveData<String> mPalabra = new MutableLiveData<>();

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        mPalabra.setValue("Editar");
        mEstado.setValue(false);
    }

    public LiveData<Propietario> getMp() {
        return mp;
    }

    public LiveData<Boolean> getmEstado() {
        return mEstado;
    }

    public LiveData<String> getmPalabra() {
        return mPalabra;
    }

    public void guardar(String palabra) {
        if (palabra.equalsIgnoreCase("Editar")) {
            mEstado.setValue(true);
            mPalabra.setValue("Guardar");
        } else if (palabra.equalsIgnoreCase("Guardar")) {
            mEstado.setValue(false);
            mPalabra.setValue("Editar");
            actualizarPropietario();
        }
    }

    public void setDatosEditados(String nombre, String apellido, String dniStr, String email, String telefono) {
        if (nombre.isEmpty() || apellido.isEmpty() || dniStr.isEmpty() || email.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(getApplication(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        int dni;
        try {
            dni = Integer.parseInt(dniStr);
        } catch (NumberFormatException e) {
            Toast.makeText(getApplication(), "DNI debe ser numérico", Toast.LENGTH_SHORT).show();
            return;
        }

        Propietario p = mp.getValue();
        if (p != null) {
            p.setNombre(nombre);
            p.setApellido(apellido);
            p.setDni(dni);
            p.setEmail(email);
            p.setTelefono(telefono);
            mp.setValue(p);
        }
    }

    public void leerPropietario() {
        String token = ApiClient.leerToken(getApplication());
        if (token != null) {
            token = "Bearer " + token;
        }

        Call<Propietario> llamada = ApiClient.getApiInmobiliaria().obtenerPropietario(token);
        llamada.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    mp.postValue(response.body());
                } else {
                    Toast.makeText(getApplication(), "Error al obtener el propietario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(getApplication(), "Error en el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void actualizarPropietario() {
        Propietario p = mp.getValue();
        if (p != null) {
            p.setClave(null);

            String token = ApiClient.leerToken(getApplication());
            if (token != null) token = "Bearer " + token;


            Call<Propietario> llamada = ApiClient.getApiInmobiliaria().actualizarPropietario(token, p);
            llamada.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if (response.isSuccessful()) {
                        mp.postValue(response.body());
                        Toast.makeText(getApplication(), "Perfil actualizado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplication(), "Error al actualizar", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {
                    Toast.makeText(getApplication(), "Error de conexión", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}