package com.joanmanera.juegocartascliente.interfaces;

import com.joanmanera.juegocartascliente.respuestas.RespuestaResultadoMano;
import com.joanmanera.juegocartascliente.respuestas.RespuestaJugarCartaCPU;
import com.joanmanera.juegocartascliente.utils.Enums;

public interface IRespuestas {
    void onCartaRecibidaCPU(RespuestaJugarCartaCPU respuestaJugarCartaCPU);
    void onRespuestaGanadorMano(RespuestaResultadoMano respuestaResultadoMano);
    void onSeleccionCartaJugador(int idCarta, Enums.Caracteristica caracteristica);
}
