package com.joanmanera.juegocartascliente.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.fragments.FragmentPreferencias;

/**
 * Activity para cargar las preferencias.
 * @author Joan Manera Perez
 */
public class PreferenciasActivity extends AppCompatActivity {

    /**
     * Carga el fragment de las preferencias.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new FragmentPreferencias());
    }
}
