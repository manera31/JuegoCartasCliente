package com.joanmanera.juegocartascliente.utils;

import com.joanmanera.juegocartascliente.interfaces.ICRUD;
import com.joanmanera.juegocartascliente.modelos.Carta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Carga datos del servidor para cuando sea necesario utilizarlos.
 * @author Joan Manera Perez
 */
public class Datos {
    private static ArrayList<Carta> cartas = null;

    /**
     * Método estático para coger las cartas.
     * @return cartas
     */
    public static ArrayList<Carta> getCartas(){
        if (cartas == null){
            cargarCartas();
        }
        return cartas;
    }

    /**
     * Carga las cartas y la deja en memoria.
     */
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
