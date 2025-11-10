package com.example.tp_inmobiliaria.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp_inmobiliaria.R;
import com.example.tp_inmobiliaria.databinding.FragmentInquilinosBinding;
import com.example.tp_inmobiliaria.models.Inmueble;

import java.util.List;

public class InquilinosFragment extends Fragment {
    private FragmentInquilinosBinding binding;
    private InquilinosViewModel mViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(InquilinosViewModel.class);
        binding = FragmentInquilinosBinding.inflate(inflater, container, false);

        mViewModel.getLista().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                InquilinoAdapter adapter = new InquilinoAdapter(inmuebles, getContext());
                GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
                RecyclerView rv = binding.rvInquilinos;

                rv.setAdapter(adapter);
                rv.setLayoutManager(glm);
            }
        });

        mViewModel.getVisibilidad().observe(getViewLifecycleOwner(), visibilidad -> {
            binding.tvTitulo.setVisibility(visibilidad);
        });

        mViewModel.setInmueblesAlquilados();

        return binding.getRoot();
    }
}

