package com.example.tp_inmobiliaria.ui.inquilinos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tp_inmobiliaria.databinding.FragmentDetalleInquilinoBinding;

public class DetalleInquilinoFragment extends Fragment {

    private DetalleInquilinoViewModel mViewModel;
    private FragmentDetalleInquilinoBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleInquilinoBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(DetalleInquilinoViewModel.class);

        mViewModel.getInquilino().observe(getViewLifecycleOwner(), inquilino -> {
            binding.tvInqNombre.setText("Nombre\n" + inquilino.getNombre());
            binding.tvInqApellido.setText("Apellido\n" + inquilino.getApellido());
            binding.tvInqCodigo.setText("Código\n" + inquilino.getIdInquilino());
            binding.tvInqDni.setText("DNI\n" + inquilino.getDni());
            binding.tvInqEmail.setText("Email\n" + inquilino.getEmail());
            binding.tvInqTelefono.setText("Teléfono\n" + inquilino.getTelefono());
        });

        mViewModel.getMensaje().observe(getViewLifecycleOwner(), mensaje -> {
            Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
        });

        mViewModel.cargarInquilino(getArguments());

        return binding.getRoot();
    }
}