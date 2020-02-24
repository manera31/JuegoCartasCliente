package com.joanmanera.juegocartascliente.interfaces;

import com.joanmanera.juegocartascliente.respuestas.RespuestaResultadoMano;
import com.joanmanera.juegocartascliente.respuestas.RespuestaJugarCartaCPU;
import com.joanmanera.juegocartascliente.utils.Enums;

/**
 * Interfaz para controlar eventos.
 * @author Joan Manera Perez
 */
public interface IRespuestas {
    /**
     * Cuando se recibe una carta de la CPU.
     * @param respuestaJugarCartaCPU
     */
    void onCartaRecibidaCPU(RespuestaJugarCartaCPU respuestaJugarCartaCPU);

    /**
     * Cuando se recibe el ganador de una mano.
     * @param respuestaResultadoMano
     */
    void onRespuestaGanadorMano(RespuestaResultadoMano respuestaResultadoMano);

    /**
     * Cuando el jugador selecciona una carta.
     * @param idCarta
     * @param caracteristica
     */
    void onSeleccionCartaJugador(int idCarta, Enums.Caracteristica caracteristica);
}
