package com.joanmanera.juegocartascliente.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.adapters.CartaAdapter;
import com.joanmanera.juegocartascliente.dialogos.DialogoSeleccionCaracteristica;
import com.joanmanera.juegocartascliente.interfaces.IRespuestas;
import com.joanmanera.juegocartascliente.modelos.Carta;
import com.joanmanera.juegocartascliente.utils.Enums;

import java.util.ArrayList;

public class FragmentJuego extends Fragment implements CartaAdapter.ICartaListener {

    private CartaAdapter adapter;
    private RecyclerView recyclerView;
    private Carta cartaActual;
    private TextView tvMarca, tvModelo, tvMotor, tvCilindros, tvPotencia, tvRPM, tvVelocidad, tvConsumo, tvCaracteristica, tvMano;
    private ImageView ivFotoCarta;
    private LinearLayout llCarta;

    private ArrayList<Carta> cartasJugador;
    private Enums.Caracteristica caracteristica;
    private IRespuestas listener;
    private int mano;

    public FragmentJuego(ArrayList<Carta> cartasJugador, Enums.Caracteristica caracteristica, IRespuestas lisener, int mano){
        this.cartasJugador = cartasJugador;
        this.caracteristica = caracteristica;
        this.listener = lisener;
        this.mano = mano;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_juego, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        adapter = new CartaAdapter(cartasJugador, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        ivFotoCarta = view.findViewById(R.id.ivFotoCarta);
        ivFotoCarta.setBackgroundResource(R.drawable.carta_default);
        tvMarca = view.findViewById(R.id.tvMarca);
        tvModelo = view.findViewById(R.id.tvModelo);
        tvMotor = view.findViewById(R.id.tvMotor);
        tvCilindros = view.findViewById(R.id.tvCilindros);
        tvPotencia = view.findViewById(R.id.tvPotencia);
        tvRPM = view.findViewById(R.id.tvRPM);
        tvVelocidad = view.findViewById(R.id.tvVelocidad);
        tvConsumo = view.findViewById(R.id.tvConsumo);
        tvCaracteristica = view.findViewById(R.id.tvCaracteristica);
        tvMano = view.findViewById(R.id.tvMano);

        tvMano.setText(String.valueOf(mano+1));

        if (caracteristica != null){
            tvCaracteristica.setText(caracteristica.toString());
        } else {
            tvCaracteristica.setText("Tu turno!");
        }

        llCarta = view.findViewById(R.id.llCarta);
        llCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvMarca.getText().equals("")){
                    if (caracteristica != null){
                        listener.onSeleccionCartaJugador(cartaActual.getId(), caracteristica);
                    } else {
                        DialogoSeleccionCaracteristica dialogo = new DialogoSeleccionCaracteristica(cartaActual, listener);
                        dialogo.show(getActivity().getSupportFragmentManager(), "dialogo");
                    }
                    adapter.quitarCarta(cartaActual.getId());

                } else {
                    Toast.makeText(getActivity(), "Selecciona una carta!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    @Override
    public void onCartaSeleccionada(Carta carta) {
        cartaActual = carta;
        ivFotoCarta.setBackgroundResource(R.drawable.mclaren);
        tvMarca.setText(carta.getMarca());
        tvModelo.setText(carta.getModelo());
        tvMotor.setText(String.valueOf(carta.getMotor()));
        tvCilindros.setText(String.valueOf(carta.getCilindros()));
        tvPotencia.setText(String.valueOf(carta.getPotencia()));
        tvRPM.setText(String.valueOf(carta.getRpm()));
        tvVelocidad.setText(String.valueOf(carta.getVelocidad()));
        tvConsumo.setText(String.valueOf(carta.getConsumo()));
    }
}
