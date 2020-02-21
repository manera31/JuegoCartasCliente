package com.joanmanera.juegocartascliente.fragments;

import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.joanmanera.juegocartascliente.R;

public class FragmentPreferencias extends PreferenceFragmentCompat {

    private Preference boton;
    private Preference.OnPreferenceClickListener listener;



    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        boton = findPreference(getString(R.string.preferencais_cerrar_sesion));
        boton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                return true;
            }
        });

        setPreferencesFromResource(R.xml.preference_screen, rootKey);
    }
}
