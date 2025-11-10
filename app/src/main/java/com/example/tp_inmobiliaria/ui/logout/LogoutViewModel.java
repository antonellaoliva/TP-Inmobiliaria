package com.example.tp_inmobiliaria.ui.logout;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LogoutViewModel extends AndroidViewModel {

    private final MutableLiveData<Boolean> salir = new MutableLiveData<>();
    private final MutableLiveData<Boolean> cancelar = new MutableLiveData<>();

    public LiveData<Boolean> getSalir() {
        return salir;
    }

    public LiveData<Boolean> getCancelar() {
        return cancelar;
    }

    public LogoutViewModel(@NonNull Application application) {
        super(application);
    }

    public void confirmarSalida() {
        salir.setValue(true);
    }

    public void cancelarSalida() {
        cancelar.setValue(true);
    }
}