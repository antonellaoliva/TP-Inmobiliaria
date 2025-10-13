package com.example.tp_inmobiliaria;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.fragment.app.Fragment;

import com.example.tp_inmobiliaria.ui.contratos.ContratosFragment;
import com.example.tp_inmobiliaria.ui.inicio.InicioFragment;
import com.example.tp_inmobiliaria.ui.inmuebles.InmueblesFragment;
import com.example.tp_inmobiliaria.ui.inquilinos.InquilinosFragment;
import com.example.tp_inmobiliaria.ui.logout.LogoutFragment;
import com.example.tp_inmobiliaria.ui.perfil.PerfilFragment;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<Fragment> fragment = new MutableLiveData<>();

    public LiveData<Fragment> getFragment() {
        return fragment;
    }

    public void seleccionarOpcion(int itemId) {
        if (itemId == R.id.nav_inicio) {
            fragment.setValue(new InicioFragment());
        } else if (itemId == R.id.nav_perfil) {
            fragment.setValue(new PerfilFragment());
        } else if (itemId == R.id.nav_inmuebles) {
            fragment.setValue(new InmueblesFragment());
        } else if (itemId == R.id.nav_contratos) {
            fragment.setValue(new ContratosFragment());
        } else if (itemId == R.id.nav_inquilinos) {
            fragment.setValue(new InquilinosFragment());
        } else if (itemId == R.id.nav_logout) {
            fragment.setValue(new LogoutFragment());
        }
    }
    }

