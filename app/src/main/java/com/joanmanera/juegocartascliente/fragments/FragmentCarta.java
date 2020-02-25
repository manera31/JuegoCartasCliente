package com.joanmanera.juegocartascliente.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.modelos.Carta;

/**
 * Fragment para carga una carta.
 */
public class FragmentCarta extends Fragment {

    private TextView tvMarca, tvModelo, tvMotor, tvCilindros, tvPotencia, tvRPM, tvVelocidad, tvConsumo;
    private ImageView ivFotoCarta;
    private LinearLayout llTodo;

    /**
     * Carga todas las vistas del fragment.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_carta_miniatura, container, false);

        ivFotoCarta = view.findViewById(R.id.ivFotoCarta2);
        tvMarca = view.findViewById(R.id.tvMarca2);
        tvModelo = view.findViewById(R.id.tvModelo2);
        tvMotor = view.findViewById(R.id.tvMotor2);
        tvCilindros = view.findViewById(R.id.tvCilindros2);
        tvPotencia = view.findViewById(R.id.tvPotencia2);
        tvRPM = view.findViewById(R.id.tvRPM2);
        tvVelocidad = view.findViewById(R.id.tvVelocidad2);
        tvConsumo = view.findViewById(R.id.tvConsumo2);
        llTodo = view.findViewById(R.id.llTodo);

        return view;
    }

    /**
     * Carga una carta en las vistas.
     * @param carta
     * @param idCartaGanadora
     */
    public void setCarta(Carta carta, int idCartaGanadora){
        if (idCartaGanadora == carta.getId() || idCartaGanadora == 0){
            llTodo.setBackgroundColor(Color.GREEN);
        }

        int id = getContext().getResources().getIdentifier("_"+carta.getId(), "drawable", getContext().getPackageName());
        ivFotoCarta.setImageResource(id);
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
