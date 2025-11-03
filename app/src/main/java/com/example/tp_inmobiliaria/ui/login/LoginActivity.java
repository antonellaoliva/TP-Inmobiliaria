package com.example.tp_inmobiliaria.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_inmobiliaria.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.etUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                vm.actualizarUsuario(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        binding.etClave.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                vm.actualizarClave(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        binding.btnLogin.setOnClickListener(v -> vm.validarCampos());

        vm.getMensajeError().observe(this, binding.tvError::setText);

        vm.getMostrarError().observe(this, visible -> binding.tvError.setVisibility(visible ? binding.tvError.VISIBLE : binding.tvError.GONE));

        vm.getProximaPantalla().observe(this, pantalla -> {
            startActivity(new Intent(this, pantalla));
            finish();
        });
    }
}
