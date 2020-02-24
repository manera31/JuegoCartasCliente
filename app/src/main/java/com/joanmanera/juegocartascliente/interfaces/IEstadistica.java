package com.joanmanera.juegocartascliente.interfaces;

import com.joanmanera.juegocartascliente.modelos.EstadisticaUsuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interfaz para hacer llamadas a la api con Retrofit.
 * @author Joan Manera Perez
 */
public interface IEstadistica {

    /**
     * Ranking de los resultados del juego.
     * @return ranking
     */
    @GET("ranking")
    Call<List<EstadisticaUsuario>> getRanking();
}
