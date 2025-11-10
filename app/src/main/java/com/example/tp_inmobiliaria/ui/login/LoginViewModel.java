package com.example.tp_inmobiliaria.ui.login;

import android.app.Application;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp_inmobiliaria.MainActivity;
import com.example.tp_inmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel implements SensorEventListener {

    private final MutableLiveData<String> mensajeError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mostrarError = new MutableLiveData<>();
    private final MutableLiveData<Class<?>> proximaPantalla = new MutableLiveData<>();
    private final MutableLiveData<Boolean> llamarInmobiliaria = new MutableLiveData<>();

    private String usuario = "";
    private String clave = "";

    private float ultimaX = 0;
    private float ultimaY = 0;
    private long ultimoTiempo = 0;

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMensajeError() { return mensajeError; }
    public LiveData<Boolean> getMostrarError() { return mostrarError; }
    public LiveData<Class<?>> getProximaPantalla() { return proximaPantalla; }
    public LiveData<Boolean> getLlamarInmobiliaria() { return llamarInmobiliaria; }

    public void actualizarUsuario(String texto) {
        usuario = texto.trim();
        mensajeError.setValue(usuario.isEmpty() ? "El usuario no puede estar vacío" : "");
        mostrarError.setValue(usuario.isEmpty());
    }

    public void actualizarClave(String texto) {
        clave = texto.trim();
        mensajeError.setValue(clave.length() > 0 && clave.length() < 4 ? "La clave debe tener al menos 4 caracteres" : "");
        mostrarError.setValue(clave.length() > 0 && clave.length() < 4);
    }

    public void validarCampos() {
        boolean error = false;
        if (usuario.isEmpty()) {
            mensajeError.setValue("Debe ingresar un usuario");
            error = true;
        } else if (clave.isEmpty()) {
            mensajeError.setValue("Debe ingresar una clave");
            error = true;
        } else if (clave.length() < 4) {
            mensajeError.setValue("La clave debe tener al menos 4 caracteres");
            error = true;
        }

        mostrarError.setValue(error);
        if (!error) login();
    }

    public void login() {
        Call<String> call = ApiClient.getApiInmobiliaria().login(usuario, clave);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                boolean ok = response.isSuccessful() && response.body() != null;
                mensajeError.postValue(ok ? "" : "Usuario o clave incorrectos");
                mostrarError.postValue(!ok);
                if (ok) {
                    ApiClient.guardarToken(getApplication(), response.body());
                    proximaPantalla.postValue(MainActivity.class);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mensajeError.postValue("Error de conexión: " + t.getMessage());
                mostrarError.postValue(true);
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        long ahora = System.currentTimeMillis();

        float deltaX = Math.abs(x - ultimaX);
        float deltaY = Math.abs(y - ultimaY);

        boolean sacudida = (deltaX > 10 || deltaY > 10) && (ahora - ultimoTiempo > 1000);
        if (sacudida) {
            ultimoTiempo = ahora;
            llamarInmobiliaria.setValue(true);
        }

        ultimaX = x;
        ultimaY = y;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}




