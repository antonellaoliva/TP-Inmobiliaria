package com.example.tp_inmobiliaria.ui.pago;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tp_inmobiliaria.R;
import com.example.tp_inmobiliaria.databinding.FragmentPagosBinding;
import com.example.tp_inmobiliaria.models.Pago;

import java.util.List;public class PagosFragment extends Fragment {
    private FragmentPagosBinding binding;
    private PagosViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentPagosBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(PagosViewModel.class);

        Bundle bundle = getArguments();
        mViewModel.cargarPagos(bundle);

        mViewModel.getLista().observe(getViewLifecycleOwner(), pagos -> {
            PagoAdapter adapter = new PagoAdapter(pagos, getContext());
            binding.rvPagos.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvPagos.setAdapter(adapter);
        });

        mViewModel.getMensaje().observe(getViewLifecycleOwner(), mensaje ->
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show()
        );

        return binding.getRoot();
    }
}


