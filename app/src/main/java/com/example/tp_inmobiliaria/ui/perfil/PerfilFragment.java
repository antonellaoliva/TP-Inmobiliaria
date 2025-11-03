package com.example.tp_inmobiliaria.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_inmobiliaria.databinding.FragmentPerfilBinding;


public class PerfilFragment extends Fragment {

    private PerfilViewModel mViewModel;
    private FragmentPerfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        binding = FragmentPerfilBinding.inflate(inflater, container, false);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        mViewModel.getMp().observe(getViewLifecycleOwner(), propietario -> {
            binding.etNombre.setText(propietario.getNombre());
            binding.etApellido.setText(propietario.getApellido());
            binding.etDni.setText(String.valueOf(propietario.getDni()));
            binding.etEmail.setText(propietario.getEmail());
            binding.etTelefono.setText(propietario.getTelefono());
        });

        mViewModel.getmEstado().observe(getViewLifecycleOwner(), estado -> {
            binding.etNombre.setEnabled(estado);
            binding.etApellido.setEnabled(estado);
            binding.etDni.setEnabled(estado);
            binding.etEmail.setEnabled(estado);
            binding.etTelefono.setEnabled(estado);
        });

        mViewModel.getmPalabra().observe(getViewLifecycleOwner(), palabra -> {
            binding.btnEditarGuardar.setText(palabra);
        });

        mViewModel.leerPropietario();

        binding.btnEditarGuardar.setOnClickListener(v -> {
            String textoBtn = binding.btnEditarGuardar.getText().toString();
            if (textoBtn.equalsIgnoreCase("Guardar")) {
                mViewModel.setDatosEditados(
                        binding.etNombre.getText().toString(),
                        binding.etApellido.getText().toString(),
                        binding.etDni.getText().toString(),
                        binding.etEmail.getText().toString(),
                        binding.etTelefono.getText().toString()
                );
            }
            mViewModel.guardar(textoBtn);
        });

        return binding.getRoot();
    }
}


