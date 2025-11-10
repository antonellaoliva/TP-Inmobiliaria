package com.example.tp_inmobiliaria.ui.logout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tp_inmobiliaria.R;
import com.example.tp_inmobiliaria.databinding.FragmentLogoutBinding;

public class LogoutFragment extends Fragment {

    private LogoutViewModel mViewModel;
    private FragmentLogoutBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentLogoutBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(LogoutViewModel.class);

        mViewModel.getSalir().observe(getViewLifecycleOwner(), salir -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_nav_logout_to_loginActivity);
        });

        mViewModel.getCancelar().observe(getViewLifecycleOwner(), cancelar -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.nav_inicio);
        });

        binding.btSi.setOnClickListener(v -> mViewModel.confirmarSalida());
        binding.btNo.setOnClickListener(v -> mViewModel.cancelarSalida());

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}