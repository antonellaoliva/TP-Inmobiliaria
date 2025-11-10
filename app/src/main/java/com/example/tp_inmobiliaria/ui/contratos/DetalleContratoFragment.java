package com.example.tp_inmobiliaria.ui.contratos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tp_inmobiliaria.R;
import com.example.tp_inmobiliaria.databinding.FragmentDetalleContratoBinding;
import com.example.tp_inmobiliaria.models.Contrato;

public class DetalleContratoFragment extends Fragment {
    private DetalleContratoViewModel mViewModel;
    private FragmentDetalleContratoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleContratoBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(DetalleContratoViewModel.class);

        mViewModel.getContrato().observe(getViewLifecycleOwner(), contrato -> {
            binding.tvCodigo.setText("Código: " +  contrato.getIdContrato());
            binding.tvFechaInicio.setText("Fecha de inicio: " + mViewModel.formatoFecha(contrato.getFechaIncio()));
            binding.tvFechaFin.setText("Fecha de finalización: " + mViewModel.formatoFecha(contrato.getFechaFinalizacion()));
            binding.tvMonto.setText("Monto mensual: $" + contrato.getMontoAlquiler());
            binding.tvInquilino.setText("Nombre del inquilino: " + contrato.getInquilino().getNombre() + " "
                    + contrato.getInquilino().getApellido());
            binding.tvInmueble.setText("Direccion del inmueble: "+ contrato.getInmueble().getDireccion());
        });

        mViewModel.getMensaje().observe(getViewLifecycleOwner(),
                mensaje -> Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show()
        );

        mViewModel.cargarContrato(getArguments());

        binding.btnPagos.setOnClickListener(v -> {
            Contrato contrato = mViewModel.getContrato().getValue(); // Simple, sin lógica
            if (contrato != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("contrato", contrato);
                Navigation.findNavController(v)
                        .navigate(R.id.action_detalleContratoFragment_to_pagosFragment, bundle);
            }
        });



        return binding.getRoot();
    }
}
