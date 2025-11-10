package com.example.tp_inmobiliaria.ui.contratos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp_inmobiliaria.R;
import com.example.tp_inmobiliaria.databinding.FragmentContratosBinding;

public class ContratosFragment extends Fragment {

    private FragmentContratosBinding binding;
    private ContratosViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentContratosBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(ContratosViewModel.class);

        mViewModel.getLista().observe(getViewLifecycleOwner(), inmuebles -> {
            ContratoAdapter adapter = new ContratoAdapter(inmuebles, getContext());
            GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
            binding.rvContratos.setLayoutManager(glm);
            binding.rvContratos.setAdapter(adapter);
        });



        mViewModel.setInmueblesAlquilados();
        return binding.getRoot();
    }
}
