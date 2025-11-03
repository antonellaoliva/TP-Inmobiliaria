package com.example.tp_inmobiliaria;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.tp_inmobiliaria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel vm;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflar el layout con ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar la Toolbar
        setSupportActionBar(binding.toolbar);

        // Inicializar ViewModel (si lo necesitás para otras cosas)
        vm = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Obtener el NavController del NavHostFragment
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        // Configurar los destinos de nivel superior (sin flecha atrás)
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio,
                R.id.nav_perfil,
                R.id.nav_inmuebles,
                R.id.nav_contratos,
                R.id.nav_inquilinos,
                R.id.nav_logout
        )
                .setOpenableLayout(binding.drawerLayout) // sincroniza con el Drawer
                .build();

        // Vincular Toolbar con NavController
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Vincular Drawer con NavController (para que funcione el menú)
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}




