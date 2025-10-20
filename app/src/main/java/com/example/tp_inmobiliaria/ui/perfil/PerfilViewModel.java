package com.example.tp_inmobiliaria.ui.perfil;

import android.app.Application;
import android.util.Log;
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

    private MutableLiveData<Propietario> mp = new MutableLiveData<>();
    private MutableLiveData<Boolean> mEstado = new MutableLiveData<>();

    private MutableLiveData<String> mPalabra = new MutableLiveData<>();

    public LiveData getMp(){
        return mp;
    }

    public LiveData<Boolean> getmEstado() {
        return mEstado;
    }
    public LiveData<String> getmPalabra() {
        return mPalabra;
    }

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        mPalabra.setValue("Editar");
        mEstado.setValue(false);
    }

    public void guardar(String palabra){
        if (palabra.equalsIgnoreCase("Editar")) {
            mEstado.setValue(true);
            mPalabra.setValue("Guardar");

        } else if (palabra.equalsIgnoreCase("Guardar")) {
            mEstado.setValue(false);
            mPalabra.setValue("Editar");
            // actualizarPropietario();

    }
    }

    public void leerPropietario(){
        String token = ApiClient.leerToken(getApplication());
        Call<Propietario> llamada = ApiClient.getApiInmobiliaria().obtenerPropietario(token);
        llamada.enqueue( new Callback<Propietario>(){
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    mp.postValue(response.body());
                }
                else{
                    Toast.makeText(getApplication(), "Error al obtener el propietario", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.message());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(getApplication(), "Error en el servidor", Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());

            }
        });
    }
}