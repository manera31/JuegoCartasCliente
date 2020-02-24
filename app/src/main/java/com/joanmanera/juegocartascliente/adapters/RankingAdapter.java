package com.joanmanera.juegocartascliente.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.modelos.EstadisticaUsuario;

import java.util.ArrayList;

/**
 * Adapter para cargas las posiciones del ranking en un recyclerview.
 * @author Joan Manera Perez
 */
public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingHolder> {
    private ArrayList<EstadisticaUsuario> estadisticaUsuarios;

    /**
     * Constructor del adapter.
     * @param estadisticaUsuarios
     */
    public RankingAdapter (ArrayList<EstadisticaUsuario> estadisticaUsuarios){
        this.estadisticaUsuarios = estadisticaUsuarios;
    }

    /**
     * Crea la vista.
     * @param parent
     * @param viewType
     * @return viewholder
     */
    @NonNull
    @Override
    public RankingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking, parent, false);
        return new RankingHolder(view);
    }

    /**
     * Carga todas la posiciones.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RankingHolder holder, int position) {
        holder.bindRanking(position);
    }

    /**
     * Numero de estadísticas del array
     * @return Numero de estadísticas..
     */
    @Override
    public int getItemCount() {
        return estadisticaUsuarios.size();
    }

    /**
     * View holder para mostrar una a una todas las estadísticas.
     */
    public class RankingHolder extends RecyclerView.ViewHolder{

        private TextView tvNick, tvGanadas, tvEmpates, tvPerdidas;

        /**
         * Constructor.
         * @param itemView
         */
        public RankingHolder(@NonNull View itemView) {
            super(itemView);

            tvNick = itemView.findViewById(R.id.tvNombreJugadorRanking);
            tvGanadas = itemView.findViewById(R.id.tvPartidasGanadas);
            tvEmpates = itemView.findViewById(R.id.tvPartidasEmpatadas);
            tvPerdidas = itemView.findViewById(R.id.tvPartidasPerdidas);

        }

        /**
         * Carga todos los datos de una estadística.
         * @param posicion
         */
        public void bindRanking(int posicion){
            EstadisticaUsuario estadisticaUsuario = estadisticaUsuarios.get(posicion);

            tvNick.setText(estadisticaUsuario.getNick());
            tvGanadas.setText(String.valueOf(estadisticaUsuario.getPartidasGanadas()));
            tvEmpates.setText(String.valueOf(estadisticaUsuario.getPartidasEmpatadas()));
            tvPerdidas.setText(String.valueOf(estadisticaUsuario.getPartidasPerdidas()));
        }
    }
}
