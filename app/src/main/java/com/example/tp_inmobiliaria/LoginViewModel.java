package com.example.tp_inmobiliaria;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp_inmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    private MutableLiveData<String> mensajeError = new MutableLiveData<>();
    private MutableLiveData<Boolean> mostrarError = new MutableLiveData<>();
    private MutableLiveData<Class<?>> proximaPantalla = new MutableLiveData<>();

    private String usuario = "";
    private String clave = "";

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMensajeError() {
        return mensajeError;
    }

    public LiveData<Boolean> getMostrarError() {
        return mostrarError;
    }

    public LiveData<Class<?>> getProximaPantalla() {
        return proximaPantalla;
    }

    public void actualizarUsuario(String texto) {
        usuario = texto;
    }

    public void actualizarClave(String texto) {
        clave = texto;
    }

    public void login() {
        Call<String> call = ApiClient.getApiInmobiliaria().login(usuario, clave);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiClient.guardarToken(getApplication(), "Bearer " + response.body());
                    mensajeError.postValue("");
                    mostrarError.postValue(false);
                    proximaPantalla.postValue(MainActivity.class);
                } else {
                    mensajeError.postValue("Usuario o clave incorrectos");
                    mostrarError.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mensajeError.postValue("Error de conexión: " + t.getMessage());
                mostrarError.postValue(true);
            }
        });
    }
}



