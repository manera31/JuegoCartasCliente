package com.joanmanera.juegocartascliente.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.modelos.Carta;

import java.util.ArrayList;

/**
 * Adapter para mostrar las cartas en un recyclerview.
 * @author Joan Manera Perez
 */
public class CartaAdapter extends RecyclerView.Adapter<CartaAdapter.CartaHolder> {
    private ArrayList<Carta> cartas;
    private ICartaListener listener;
    private Context context;

    /**
     * Constructor del adapter.
     * @param cartas
     * @param listener
     */
    public CartaAdapter (ArrayList<Carta> cartas, ICartaListener listener, Context context){
        this.cartas = cartas;
        this.listener = listener;
        this.context = context;
    }

    /**
     * Define la vista.
     * @param parent
     * @param viewType
     * @return viewholder
     */
    @NonNull
    @Override
    public CartaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carta_miniatura, parent, false);
        return new CartaHolder(view, listener, context);
    }

    /**
     * Carga todas las cartas.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull CartaHolder holder, int position) {
        holder.bindCarta(position);
    }

    /**
     * Numero de cartas de la baraja.
     * @return Numero de cartas.
     */
    @Override
    public int getItemCount() {
        return cartas.size();
    }

    /**
     * Método para quitar una carta de la baraja cuando ya haya salido.
     * @param idCarta
     */
    public void quitarCarta(int idCarta){
        for (Carta c: cartas){
            if (c.getId() == idCarta){
                cartas.remove(c);
                return;
            }
        }
    }

    /**
     * View holder para mostrar una a una todas las cartas.
     * @author Joan Manera Perez
     */
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
        private ImageView ivFotoCarta;
        private Context context;


        /**
         * Constructor.
         * @param itemView
         * @param listener
         */
        public CartaHolder(@NonNull View itemView, ICartaListener listener, Context context) {
            super(itemView);
            this.listener = listener;
            this.context = context;

            ivFotoCarta = itemView.findViewById(R.id.ivFotoCarta2);
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

        /**
         * Método para cargar los datos de una carta.
         * @param posicion
         */
        public void bindCarta(int posicion){
            Carta carta = cartas.get(posicion);

            int id = context.getResources().getIdentifier("_"+carta.getId(), "drawable", context.getPackageName());
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

        /**
         * Listener para controlar el click sobre la carta.
         * @param v
         */
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
