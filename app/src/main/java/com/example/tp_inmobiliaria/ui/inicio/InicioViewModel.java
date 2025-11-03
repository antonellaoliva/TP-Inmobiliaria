package com.example.tp_inmobiliaria.ui.inicio;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InicioViewModel extends AndroidViewModel {

    private final MutableLiveData<LatLng> ubicacionLiveData = new MutableLiveData<>();

    public InicioViewModel(@NonNull Application application) {
        super(application);
        cargarUbicacion();
    }

    private void cargarUbicacion(){
        LatLng ubicacion = new LatLng(-33.675064, -65.462957);
        ubicacionLiveData.setValue(ubicacion);
    }

    public LiveData<LatLng> getUbicacion(){
        return ubicacionLiveData;
    }

    public void configurarMapa(GoogleMap map){
        LatLng ubicacion = ubicacionLiveData.getValue();
        if(ubicacion != null){
            map.addMarker(new MarkerOptions().position(ubicacion).title("Inmobiliaria"));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15));
        }
    }
}