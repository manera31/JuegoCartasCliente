package com.joanmanera.juegocartascliente.interfaces;

import com.joanmanera.juegocartascliente.modelos.Carta;
import com.joanmanera.juegocartascliente.modelos.Usuario;
import com.joanmanera.juegocartascliente.respuestas.RespuestaKeyValue;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Interfaz para hacer llamadas a la api con Retrofit.
 * @author Joan Manera Perez
 */
public interface ICRUD {

    /**
     * Lista de cartas.
     * @return lista de catas
     */
    @GET("carta")
    Call<List<Carta>> getCartas();

    /**
     * Crear un usuario.
     * @param usuario
     * @return respuesta clave valor
     */
    @POST("jugador")
    Call<RespuestaKeyValue> crearUsuario(@Body Usuario usuario);

}
