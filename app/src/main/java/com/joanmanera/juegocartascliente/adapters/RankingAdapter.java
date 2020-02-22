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

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingHolder> {
    private ArrayList<EstadisticaUsuario> estadisticaUsuarios;

    public RankingAdapter (ArrayList<EstadisticaUsuario> estadisticaUsuarios){
        this.estadisticaUsuarios = estadisticaUsuarios;
    }

    @NonNull
    @Override
    public RankingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking, parent, false);
        return new RankingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingHolder holder, int position) {
        holder.bindRanking(position);
    }

    @Override
    public int getItemCount() {
        return estadisticaUsuarios.size();
    }

    public class RankingHolder extends RecyclerView.ViewHolder{

        private TextView tvNick, tvGanadas, tvEmpates, tvPerdidas;

        public RankingHolder(@NonNull View itemView) {
            super(itemView);

            tvNick = itemView.findViewById(R.id.tvNombreJugadorRanking);
            tvGanadas = itemView.findViewById(R.id.tvPartidasGanadas);
            tvEmpates = itemView.findViewById(R.id.tvPartidasEmpatadas);
            tvPerdidas = itemView.findViewById(R.id.tvPartidasPerdidas);

        }

        public void bindRanking(int posicion){
            EstadisticaUsuario estadisticaUsuario = estadisticaUsuarios.get(posicion);

            tvNick.setText(estadisticaUsuario.getNick());
            tvGanadas.setText(String.valueOf(estadisticaUsuario.getPartidasGanadas()));
            tvEmpates.setText(String.valueOf(estadisticaUsuario.getPartidasEmpatadas()));
            tvPerdidas.setText(String.valueOf(estadisticaUsuario.getPartidasPerdidas()));
        }
    }
}
