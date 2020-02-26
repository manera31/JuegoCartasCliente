package com.joanmanera.juegocartascliente.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.respuestas.RespuestaResultadoMano;

/**
 * Fragment para mostrar el resultado de una partida.
 * @author Joan Manera Perez
 */
public class FragmentResultadoPartido extends Fragment {
    private TextView tvTitulo, tvResultadoJugador, tvResultadoCPU;
    private Button bContinuarFRP;
    private LinearLayout llResultado;

    private RespuestaResultadoMano respuestaResultadoMano;
    private View.OnClickListener listener;

    /**
     * Constructor.
     * @param respuestaResultadoMano
     * @param listener
     */
    public FragmentResultadoPartido(RespuestaResultadoMano respuestaResultadoMano, View.OnClickListener listener){
        this.respuestaResultadoMano = respuestaResultadoMano;
        this.listener = listener;
    }

    /**
     * Carga las vistas del fragment.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultado_partida, container, false);

        tvTitulo = view.findViewById(R.id.tvTitulo);
        tvResultadoJugador = view.findViewById(R.id.tvResultadoJugador);
        tvResultadoCPU = view.findViewById(R.id.tvResultadoCPU);
        bContinuarFRP = view.findViewById(R.id.bContinuarFRP);
        llResultado = view.findViewById(R.id.llResultadoPartida);

        bContinuarFRP.setOnClickListener(listener);
        if (respuestaResultadoMano.getContadorPuntosJugaor() > respuestaResultadoMano.getContadorPuntosCPU()){
            // Si el jugador ha ganado mas que la CPU
            tvTitulo.setText(R.string.partida_ganador_jugador);
            llResultado.setBackgroundColor(Color.GREEN);
        } else if (respuestaResultadoMano.getContadorPuntosJugaor() == respuestaResultadoMano.getContadorPuntosCPU()){
            // Si han ganado lo mismo
            tvTitulo.setText(R.string.partida_ganador_empate);

        } else {
            // Si la CPU ha ganado mas que el jugador
            tvTitulo.setText(R.string.partida_ganador_cpu);
            llResultado.setBackgroundColor(Color.RED);

        }
        tvResultadoJugador.setText(String.valueOf(respuestaResultadoMano.getContadorPuntosJugaor()));
        tvResultadoCPU.setText(String.valueOf(respuestaResultadoMano.getContadorPuntosCPU()));

        return view;
    }
}
