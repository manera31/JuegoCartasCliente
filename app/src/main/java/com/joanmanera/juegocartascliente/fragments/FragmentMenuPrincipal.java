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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.adapters.RankingAdapter;
import com.joanmanera.juegocartascliente.utils.APIUtils;
import com.joanmanera.juegocartascliente.interfaces.IEstadistica;
import com.joanmanera.juegocartascliente.modelos.EstadisticaUsuario;
import com.joanmanera.juegocartascliente.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Fragment para controlar la pantalla principal.
 * @author Joan Manera Perez
 */
public class FragmentMenuPrincipal extends Fragment {

    private TextView tvNick;
    private Button bNuevoJuego;
    private View.OnClickListener listener;
    private Usuario usuario;
    private ArrayList<EstadisticaUsuario> estadisticaUsuarios;
    private RecyclerView recyclerView;
    private RankingAdapter adapter;

    /**
     * Constructor.
     * @param listener
     * @param usuario
     */
    public FragmentMenuPrincipal(View.OnClickListener listener, Usuario usuario){
        this.listener = listener;
        this.usuario = usuario;
    }

    /**
     * Carga las vistas
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_principal, container, false);

        tvNick = view.findViewById(R.id.tvNick);
        bNuevoJuego = view.findViewById(R.id.bNuevaPartida);
        recyclerView = view.findViewById(R.id.rvRanking);

        bNuevoJuego.setOnClickListener(listener);
        tvNick.setText(usuario.getNick());

         cargarRanking();

        return view;
    }

    /**
     * Hace una petición al api para cargar el ranking.
     */
    private void cargarRanking(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUtils.URL_ESTADISTICA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IEstadistica estadistica = retrofit.create(IEstadistica.class);

        Call<List<EstadisticaUsuario>> estadisticas = estadistica.getRanking();

        estadisticas.enqueue(new Callback<List<EstadisticaUsuario>>() {
            @Override
            public void onResponse(Call<List<EstadisticaUsuario>> call, Response<List<EstadisticaUsuario>> response) {
                if (response.isSuccessful()){
                    estadisticaUsuarios = new ArrayList<>();
                    for (EstadisticaUsuario e: response.body()){
                        estadisticaUsuarios.add(e);
                    }
                    adapter = new RankingAdapter(estadisticaUsuarios);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

                }
            }

            @Override
            public void onFailure(Call<List<EstadisticaUsuario>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        cargarRanking();
    }
}
