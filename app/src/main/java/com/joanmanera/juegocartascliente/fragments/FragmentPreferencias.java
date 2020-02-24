package com.joanmanera.juegocartascliente.fragments;

import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.joanmanera.juegocartascliente.R;

/**
 * Fragment para crear las preferencias.
 * @author Joan Manera Perez
 */
public class FragmentPreferencias extends PreferenceFragmentCompat {

    private Preference boton;
    private Preference.OnPreferenceClickListener listener;

    /**
     * Crea las vistas de las preferencias.
     * @param savedInstanceState
     * @param rootKey
     */
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        boton = findPreference(getString(R.string.preferencais_cerrar_sesion));
        /*boton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                return true;
            }
        });*/

        setPreferencesFromResource(R.xml.preference_screen, rootKey);
    }
}
