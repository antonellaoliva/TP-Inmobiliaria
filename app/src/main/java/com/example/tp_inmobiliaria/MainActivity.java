package com.example.tp_inmobiliaria;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.tp_inmobiliaria.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        vm = new ViewModelProvider(this).get(MainViewModel.class);

        vm.getFragment().observe(this, fragment -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(binding.fragmentContainer.getId(), fragment)
                    .commit();
            binding.drawerLayout.closeDrawers();
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(item -> {
            vm.seleccionarOpcion(item.getItemId());
            return true;
        });
    }
}