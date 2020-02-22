package com.joanmanera.juegocartascliente.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.adapters.RankingAdapter;
import com.joanmanera.juegocartascliente.modelos.EstadisticaUsuario;
import com.joanmanera.juegocartascliente.modelos.Usuario;

import java.util.ArrayList;


public class FragmentMenuPrincipal extends Fragment {

    private TextView tvNick;
    private Button bNuevoJuego;
    private View.OnClickListener listener;
    private Usuario usuario;
    private ArrayList<EstadisticaUsuario> estadisticaUsuarios;
    private RecyclerView recyclerView;
    private RankingAdapter adapter;

    public FragmentMenuPrincipal(View.OnClickListener listener, Usuario usuario, ArrayList<EstadisticaUsuario> estadisticaUsuarios){
        this.listener = listener;
        this.usuario = usuario;
        this.estadisticaUsuarios = estadisticaUsuarios;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_principal, container, false);

        tvNick = view.findViewById(R.id.tvNick);
        bNuevoJuego = view.findViewById(R.id.bNuevaPartida);

        bNuevoJuego.setOnClickListener(listener);

        tvNick.setText(usuario.getNick());

        recyclerView = view.findViewById(R.id.rvRanking);
        adapter = new RankingAdapter(estadisticaUsuarios);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        return view;
    }
}
