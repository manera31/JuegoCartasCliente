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
import com.joanmanera.juegocartascliente.utils.Datos;
import com.joanmanera.juegocartascliente.modelos.Carta;
import com.joanmanera.juegocartascliente.respuestas.RespuestaResultadoMano;

public class FragmentResultadoMano extends Fragment {
    private FragmentCarta fragmentCartaJugador, fragmentCartaCPU;
    private TextView tvCaracteristicaResultado;
    private Button bContinuarFRM;

    private RespuestaResultadoMano respuestaResultadoMano;
    private View.OnClickListener listener;

    public FragmentResultadoMano(RespuestaResultadoMano respuestaResultadoMano, View.OnClickListener listener){
        this.respuestaResultadoMano = respuestaResultadoMano;
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultado_mano, container, false);


        fragmentCartaJugador = (FragmentCarta)getChildFragmentManager().findFragmentById(R.id.fCartaJugador);
        fragmentCartaJugador.setCarta(buscarCarta(respuestaResultadoMano.getIdCartaJugador()), respuestaResultadoMano.getIdCartaGanadora());

        fragmentCartaCPU = (FragmentCarta)getChildFragmentManager().findFragmentById(R.id.fCartaCPU);
        fragmentCartaCPU.setCarta(buscarCarta(respuestaResultadoMano.getIdCartaCPU()), respuestaResultadoMano.getIdCartaGanadora());

        tvCaracteristicaResultado = view.findViewById(R.id.tvCaracteristicaResultado);
        tvCaracteristicaResultado.setText(respuestaResultadoMano.getCaracteristica().toString());

        bContinuarFRM = view.findViewById(R.id.bContinuarFRM);
        bContinuarFRM.setOnClickListener(listener);

        return view;
    }

    private Carta buscarCarta (int idCarta){
        for (Carta c: Datos.getCartas()){
            if (c.getId() == idCarta){
                return c;
            }
        }
        return null;
    }
}
