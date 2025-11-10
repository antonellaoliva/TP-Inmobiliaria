package com.example.tp_inmobiliaria.ui.login;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class PermisoLlamada {

    public static final int REQUEST_CALL_PHONE = 100;

    public static void solicitarPermiso(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
    }

    public static void manejarResultado(Activity activity, int requestCode, int[] grantResults, Runnable accionPermitida) {
        if (requestCode == REQUEST_CALL_PHONE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            accionPermitida.run();
        }
    }
}
