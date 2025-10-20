package com.example.tp_inmobiliaria.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_inmobiliaria.R;
import com.example.tp_inmobiliaria.databinding.FragmentInicioBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

public class InicioFragment extends Fragment implements OnMapReadyCallback {

    private FragmentInicioBinding binding;
    private GoogleMap mMap;
    private InicioViewModel viewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentInicioBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(InicioViewModel.class);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
       return binding.getRoot();
}

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        viewModel.getUbicacion().observe(getViewLifecycleOwner(), ubicacion -> {
            mMap.addMarker(new MarkerOptions().position(ubicacion).title("Inmobiliaria"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15));
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}