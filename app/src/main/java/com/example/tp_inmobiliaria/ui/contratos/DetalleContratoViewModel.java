package com.example.tp_inmobiliaria.ui.contratos;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp_inmobiliaria.models.Contrato;
import com.example.tp_inmobiliaria.models.Inmueble;
import com.example.tp_inmobiliaria.request.ApiClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleContratoViewModel extends AndroidViewModel {

    private MutableLiveData<Contrato> contrato = new MutableLiveData<>();
    private MutableLiveData<String> mensaje = new MutableLiveData<>();


    public DetalleContratoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Contrato> getContrato() {
        return contrato;
    }

    public LiveData<String> getMensaje() {
        return mensaje;
    }


    public void cargarContrato(Bundle bundle) {
        if (bundle == null) {
            mensaje.postValue("No se recibieron datos del inmueble");
            return;
        }

        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble", Inmueble.class);
        if (inmueble == null) {
            mensaje.postValue("No se pudo cargar el inmueble");
            return;
        }

        String token = ApiClient.leerToken(getApplication());
        ApiClient.InmoService api = ApiClient.getApiInmobiliaria();

        Call<Contrato> llamada = api.obtenerContratoPorInmueble("Bearer " + token, inmueble.getIdInmueble());
        llamada.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if (response.isSuccessful() && response.body() != null) {
                    contrato.postValue(response.body());
                } else {
                    mensaje.postValue("No se encontró contrato para este inmueble");
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                mensaje.postValue("Error: " + t.getMessage());
            }
        });


    }

    public String formatoFecha(String fechaIso) {
        if (fechaIso == null || fechaIso.trim().isEmpty()) {
            return "Sin fecha";
        }

        try {
            LocalDate fecha = LocalDate.parse(fechaIso);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return fecha.format(formatter);
        } catch (DateTimeParseException e) {
            return fechaIso;
        }
    }


}
