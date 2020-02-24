package com.joanmanera.juegocartascliente.interfaces;

import com.joanmanera.juegocartascliente.respuestas.EnvioJugarCarta;
import com.joanmanera.juegocartascliente.respuestas.RespuestaResultadoMano;
import com.joanmanera.juegocartascliente.respuestas.RespuestaJugarCartaCPU;
import com.joanmanera.juegocartascliente.respuestas.RespuestaLogin;
import com.joanmanera.juegocartascliente.respuestas.RespuestaNuevoJuego;
import com.joanmanera.juegocartascliente.respuestas.RespuestaSorteo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Interfaz para hacer llamadas a la api con Retrofit.
 * @author Joan Manera Perez
 */
public interface IPartida {

    /**
     * Intenta iniciar sesion en el servidor.
     * @param userPass
     * @return respuesta login
     */
    @POST("login")
    Call<RespuestaLogin> login(@Body String userPass);


    /**
     * Crea uana nueva partida en el servidor.
     * @param idSesion
     * @return respuesta nuevo juego
     */
    @POST("crearJuego")
    Call<RespuestaNuevoJuego> crearJuego(@Body String idSesion);


    /**
     * Sortea el inicio entre el jugador y la cpu.
     * @param idSesionPartida
     * @return respuesta sorteo
     */
    @POST("sortearInicio")
    Call<RespuestaSorteo> sortearInicio(@Body String idSesionPartida);


    /**
     * Juega una carta del jugador.
     * @param carta
     * @return respuesta resultado mano
     */
    @POST("jugarCarta")
    Call<RespuestaResultadoMano> jugarCarta(@Body EnvioJugarCarta carta);


    /**
     * Envia la señal de que el cliente esta listo para recibir una carta de la CPU.
     * @param idSesionPartida
     * @return respuesta jugar carta cpu
     */
    @POST("clienteListo")
    Call<RespuestaJugarCartaCPU> clienteListo(@Body String idSesionPartida);


    /**
     * Cierra sesion en el servidor.
     * @param idSesion
     */
    @POST("cerrarSesion")
    void cerrarSesion(@Body String idSesion);
}
