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

public class FragmentCarta extends Fragment {

    private TextView tvMarca, tvModelo, tvMotor, tvCilindros, tvPotencia, tvRPM, tvVelocidad, tvConsumo;
    private ImageView ivFotoCarta;
    private LinearLayout llTodo;

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

    public void setCarta(Carta carta){
        llTodo.setBackgroundColor(Color.GREEN);
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
