package com.example.tp_inmobiliaria.ui.pago;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_inmobiliaria.models.Contrato;
import com.example.tp_inmobiliaria.models.Inmueble;
import com.example.tp_inmobiliaria.models.Pago;
import com.example.tp_inmobiliaria.request.ApiClient;

import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel {
    private MutableLiveData<List<Pago>> lista = new MutableLiveData<>();
    private MutableLiveData<String> mensaje = new MutableLiveData<>();

    public PagosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Pago>> getLista() {
        return lista;
    }

    public LiveData<String> getMensaje() {
        return mensaje;
    }

    public void cargarPagos(Bundle bundle) {
        if (bundle == null) {
            mensaje.postValue("No se recibió el contrato");
            return;
        }

        Contrato contrato = (Contrato) bundle.getSerializable("contrato", Contrato.class);
        if (contrato == null) {
            mensaje.postValue("No se pudo obtener el contrato");
            return;
        }

        String token = ApiClient.leerToken(getApplication());
        ApiClient.InmoService api = ApiClient.getApiInmobiliaria();

        Call<List<Pago>> llamada = api.obtenerPagosPorContrato("Bearer " + token, contrato.getIdContrato());
        llamada.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    lista.postValue(response.body());
                } else {
                    mensaje.postValue("No hay pagos disponibles");
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                mensaje.postValue("Error: " + t.getMessage());
            }
        });
    }

}
