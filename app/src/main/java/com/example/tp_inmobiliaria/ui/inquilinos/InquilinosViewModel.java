package com.example.tp_inmobiliaria.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp_inmobiliaria.models.Inmueble;
import com.example.tp_inmobiliaria.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinosViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> lista = new MutableLiveData<>();
    private MutableLiveData<Integer> visibilidad = new MutableLiveData<>();

    private Context context;

    public InquilinosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Inmueble>> getLista() {
        return lista;
    }

    public MutableLiveData<Integer> getVisibilidad() {
        return visibilidad;
    }

    public void setInmueblesAlquilados(){
        String token = ApiClient.leerToken(getApplication());
        ApiClient.InmoService api = ApiClient.getApiInmobiliaria();
        Call<List<Inmueble>> llamada = api.obtenerInmuebles("Bearer "+token) ;
        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    if(response.body().size()>0){
                        visibilidad.setValue(View.INVISIBLE);
                        lista.postValue(response.body());
                    }else{
                        visibilidad.setValue(View.VISIBLE);
                    }
                }else{
                    visibilidad.setValue(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}