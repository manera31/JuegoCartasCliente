package com.joanmanera.juegocartascliente.interfaces;

import com.joanmanera.juegocartascliente.modelos.EstadisticaUsuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IEstadistica {

    @GET("ranking")
    Call<List<EstadisticaUsuario>> getRanking();
}
