package com.example.tp_inmobiliaria.ui.login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(vm, acelerometro, SensorManager.SENSOR_DELAY_UI);

        binding.etUsuario.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                vm.actualizarUsuario(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        binding.etClave.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                vm.actualizarClave(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        binding.btnLogin.setOnClickListener(v -> vm.validarCampos());

        vm.getMensajeError().observe(this, binding.tvError::setText);
        vm.getMostrarError().observe(this, visible -> binding.tvError.setVisibility(visible ? View.VISIBLE : View.GONE));
        vm.getProximaPantalla().observe(this, pantalla -> {
            startActivity(new Intent(this, pantalla));
            finish();
        });

        vm.getLlamarInmobiliaria().observe(this, llamar -> {
            PermisoLlamada.solicitarPermiso(this);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermisoLlamada.manejarResultado(this, requestCode, grantResults, () -> {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:2657246205"));
            startActivity(intent);
        });
    }
}


