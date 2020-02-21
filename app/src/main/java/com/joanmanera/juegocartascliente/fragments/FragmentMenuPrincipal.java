package com.joanmanera.juegocartascliente.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.modelos.Usuario;


public class FragmentMenuPrincipal extends Fragment {

    private TextView tvNick;
    private Button bNuevoJuego;
    private View.OnClickListener listener;
    private Usuario usuario;

    public FragmentMenuPrincipal(View.OnClickListener listener, Usuario usuario){
        this.listener = listener;
        this.usuario = usuario;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_principal, container, false);

        tvNick = view.findViewById(R.id.tvNick);
        bNuevoJuego = view.findViewById(R.id.bNuevaPartida);
        bNuevoJuego.setOnClickListener(listener);

        tvNick.setText(usuario.getNick());

        return view;
    }
}
