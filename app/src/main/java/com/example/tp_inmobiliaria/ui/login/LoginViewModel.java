package com.example.tp_inmobiliaria.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp_inmobiliaria.MainActivity;
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
        usuario = texto.trim();

        // Valida en tiempo real
        if (usuario.isEmpty()) {
            mensajeError.setValue("El usuario no puede estar vacío");
            mostrarError.setValue(true);
        } else {
            mostrarError.setValue(false);
        }
    }

    public void actualizarClave(String texto) {
        clave = texto.trim();

        // Valida en tiempo real
        if (clave.length() > 0 && clave.length() < 4) {
            mensajeError.setValue("La clave debe tener al menos 4 caracteres");
            mostrarError.setValue(true);
        } else {
            mostrarError.setValue(false);
        }
    }

    public void validarCampos() {
        if (usuario.isEmpty()) {
            mensajeError.setValue("Debe ingresar un usuario");
            mostrarError.setValue(true);
            return;
        }

        if (clave.isEmpty()) {
            mensajeError.setValue("Debe ingresar una clave");
            mostrarError.setValue(true);
            return;
        }

        if (clave.length() < 4) {
            mensajeError.setValue("La clave debe tener al menos 4 caracteres");
            mostrarError.setValue(true);
            return;
        }

        // si las validaciones pasan oculta el error y hace login
        mensajeError.setValue("");
        mostrarError.setValue(false);
        login();
    }


    public void login() {
        Call<String> call = ApiClient.getApiInmobiliaria().login(usuario, clave);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiClient.guardarToken(getApplication(), response.body());
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



