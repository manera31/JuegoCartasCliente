package com.joanmanera.juegocartascliente.interfaces;

import com.joanmanera.juegocartascliente.respuestas.EnvioJugarCarta;
import com.joanmanera.juegocartascliente.respuestas.RespuestaJugarCartaCPU;
import com.joanmanera.juegocartascliente.respuestas.RespuestaJugarCartaJugador;
import com.joanmanera.juegocartascliente.respuestas.RespuestaLogin;
import com.joanmanera.juegocartascliente.respuestas.RespuestaNuevoJuego;
import com.joanmanera.juegocartascliente.respuestas.RespuestaSorteo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IPartida {

    @POST("login")
    Call<RespuestaLogin> login(@Body String userPass);

    @POST("crearJuego")
    Call<RespuestaNuevoJuego> crearJuego(@Body String idSesion);

    @POST("sortearInicio")
    Call<RespuestaSorteo> sortearInicio(@Body String idSesionPartida);

    @POST("jugarCarta")
    Call<RespuestaJugarCartaJugador> jugarCarta(@Body EnvioJugarCarta carta);

    @POST("clienteListo")
    Call<RespuestaJugarCartaCPU> clienteListo(@Body String idSesionPartida);
}
