package com.joanmanera.juegocartascliente.interfaces;

import com.joanmanera.juegocartascliente.modelos.Carta;
import com.joanmanera.juegocartascliente.modelos.Usuario;
import com.joanmanera.juegocartascliente.respuestas.RespuestaKeyValue;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ICRUD {

    @GET("carta")
    Call<List<Carta>> getCartas();

    @POST("jugador")
    Call<RespuestaKeyValue> crearUsuario(@Body Usuario usuario);

}
