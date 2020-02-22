package com.joanmanera.juegocartascliente.utils;

import com.joanmanera.juegocartascliente.interfaces.APIUtils;
import com.joanmanera.juegocartascliente.interfaces.ICRUD;
import com.joanmanera.juegocartascliente.interfaces.IEstadistica;
import com.joanmanera.juegocartascliente.modelos.Carta;
import com.joanmanera.juegocartascliente.modelos.EstadisticaUsuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Datos {
    private static ArrayList<Carta> cartas = null;
    private static ArrayList<EstadisticaUsuario> estadisticaUsuarios = null;

    public static ArrayList<Carta> getCartas(){
        if (cartas == null){
            cargarCartas();
        }
        return cartas;
    }

    public static ArrayList<EstadisticaUsuario> getEstadisticaUsuarios(){
        if (estadisticaUsuarios == null){
            cargarEstadisticas();
        }
        return estadisticaUsuarios;
    }

    public static void cargarEstadisticas() {
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

                }
            }

            @Override
            public void onFailure(Call<List<EstadisticaUsuario>> call, Throwable t) {

            }
        });
    }

    public static void cargarCartas(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUtils.URL_CRUD)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ICRUD iCartas = retrofit.create(ICRUD.class);

        Call<List<Carta>> respuestaCartas = iCartas.getCartas();

        respuestaCartas.enqueue(new Callback<List<Carta>>() {
            @Override
            public void onResponse(Call<List<Carta>> call, Response<List<Carta>> response) {
                if (response.isSuccessful()){
                    cartas = new ArrayList<>();
                    for (Carta c: response.body()){
                        cartas.add(c);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Carta>> call, Throwable t) {

            }
        });
    }
}
