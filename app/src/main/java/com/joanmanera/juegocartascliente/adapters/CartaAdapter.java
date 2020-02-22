package com.joanmanera.juegocartascliente.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.modelos.Carta;

import java.util.ArrayList;

public class CartaAdapter extends RecyclerView.Adapter<CartaAdapter.CartaHolder> {
    private ArrayList<Carta> cartas;
    private ICartaListener listener;

    public CartaAdapter (ArrayList<Carta> cartas, ICartaListener listener){
        this.cartas = cartas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carta_miniatura, parent, false);
        return new CartaHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartaHolder holder, int position) {
        holder.bindCarta(position);
    }

    @Override
    public int getItemCount() {
        return cartas.size();
    }

    public void quitarCarta(int idCarta){
        for (Carta c: cartas){
            if (c.getId() == idCarta){
                cartas.remove(c);
                return;
            }
        }
    }

    public class CartaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvMarca;
        private TextView tvModelo;
        private TextView tvMotor;
        private TextView tvCilindros;
        private TextView tvPotencia;
        private TextView tvRPM;
        private TextView tvVelocidad;
        private TextView tvConsumo;
        private ICartaListener listener;


        public CartaHolder(@NonNull View itemView, ICartaListener listener) {
            super(itemView);
            this.listener = listener;

            tvMarca = itemView.findViewById(R.id.tvMarca2);
            tvModelo = itemView.findViewById(R.id.tvModelo2);
            tvMotor = itemView.findViewById(R.id.tvMotor2);
            tvCilindros = itemView.findViewById(R.id.tvCilindros2);
            tvPotencia = itemView.findViewById(R.id.tvPotencia2);
            tvRPM = itemView.findViewById(R.id.tvRPM2);
            tvVelocidad = itemView.findViewById(R.id.tvVelocidad2);
            tvConsumo = itemView.findViewById(R.id.tvConsumo2);
            itemView.setOnClickListener(this);
        }

        public void bindCarta(int posicion){
            Carta carta = cartas.get(posicion);

            tvMarca.setText(carta.getMarca());
            tvModelo.setText(carta.getModelo());
            tvMotor.setText(String.valueOf(carta.getMotor()));
            tvCilindros.setText(String.valueOf(carta.getCilindros()));
            tvPotencia.setText(String.valueOf(carta.getPotencia()));
            tvRPM.setText(String.valueOf(carta.getRpm()));
            tvVelocidad.setText(String.valueOf(carta.getVelocidad()));
            tvConsumo.setText(String.valueOf(carta.getConsumo()));

        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onCartaSeleccionada(cartas.get(getAdapterPosition()));
            }
        }
    }

    public interface ICartaListener{
        void onCartaSeleccionada(Carta carta);
    }
}
