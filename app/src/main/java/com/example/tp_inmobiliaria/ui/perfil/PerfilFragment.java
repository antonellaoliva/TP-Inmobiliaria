package com.example.tp_inmobiliaria.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_inmobiliaria.databinding.FragmentPerfilBinding;
import com.example.tp_inmobiliaria.models.Propietario;

public class PerfilFragment extends Fragment {

    private PerfilViewModel mViewModel;
    private FragmentPerfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        binding = FragmentPerfilBinding.inflate(inflater, container, false);

        mViewModel.getMp().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                binding.etNombre.setText(propietario.getNombre());
                binding.etApellido.setText(propietario.getApellido());
                binding.etDni.setText(propietario.getDni());
                binding.etEmail.setText(propietario.getEmail());
                binding.etTelefono.setText(propietario.getTelefono());
            }
        });

        mViewModel.getmEstado().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.etNombre.setEnabled(aBoolean);
                binding.etApellido.setEnabled(aBoolean);
                binding.etDni.setEnabled(aBoolean);
                binding.etEmail.setEnabled(aBoolean);
                binding.etTelefono.setEnabled(aBoolean);
            }
        });

        mViewModel.getmPalabra().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.btnEditarGuardar.setText(s);
            }
        });

        mViewModel.leerPropietario();
        binding.btnEditarGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoBtn = binding.btnEditarGuardar.getText().toString();
                mViewModel.guardar(textoBtn);

            }
        });
        return binding.getRoot();
    }
}