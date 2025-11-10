package com.example.tp_inmobiliaria.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp_inmobiliaria.models.Contrato;
import com.example.tp_inmobiliaria.models.Inmueble;
import com.example.tp_inmobiliaria.models.Inquilino;
import com.example.tp_inmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInquilinoViewModel extends AndroidViewModel {

    private MutableLiveData<Inquilino> inquilino = new MutableLiveData<>();
    private MutableLiveData<String> mensaje = new MutableLiveData<>();
    private Context context;

    public DetalleInquilinoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inquilino> getInquilino() {
        return inquilino;
    }

    public LiveData<String> getMensaje() {
        return mensaje;
    }

    public void cargarInquilino(Bundle bundle) {
        if (bundle == null || !bundle.containsKey("idInmueble")) {
            mensaje.postValue("No se recibió el ID del inmueble");
            return;
        }

        int idInmueble = bundle.getInt("idInmueble");
        String token = ApiClient.leerToken(getApplication());
        ApiClient.InmoService api = ApiClient.getApiInmobiliaria();

        Call<Contrato> llamada = api.obtenerContratoPorInmueble("Bearer " + token, idInmueble);
        llamada.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Contrato contrato = response.body();
                    if (contrato.getInquilino() != null) {
                        inquilino.postValue(contrato.getInquilino());
                    } else {
                        mensaje.postValue("El contrato no tiene inquilino asociado");
                    }
                } else {
                    mensaje.postValue("No se encontró contrato para el inmueble");
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                mensaje.postValue("Error: " + t.getMessage());
            }
        });
    }
}



