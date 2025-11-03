package com.example.tp_inmobiliaria.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp_inmobiliaria.R;
import com.example.tp_inmobiliaria.databinding.FragmentInmueblesBinding;
import com.example.tp_inmobiliaria.models.Inmueble;

import java.util.List;

public class InmueblesFragment extends Fragment {
    private FragmentInmueblesBinding binding;
    private InmueblesViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(InmueblesViewModel.class);
        binding = FragmentInmueblesBinding.inflate(inflater, container, false);

        vm.getmInmueble().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {

            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                InmuebleAdapter adapter = new InmuebleAdapter(inmuebles, getContext());
                GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
                RecyclerView rv = binding.rvListaInmueble;

                rv.setAdapter(adapter);
                rv.setLayoutManager(glm);
            }
        });

        //vm.leerInmuebles();

        binding.btnAgregar.setOnClickListener(v ->{
            NavHostFragment.findNavController(this).navigate(R.id.action_nav_inmuebles_to_cargarInmuebleFragment);

        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}