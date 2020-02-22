package com.joanmanera.juegocartascliente.interfaces;

import com.joanmanera.juegocartascliente.respuestas.EnvioJugarCarta;
import com.joanmanera.juegocartascliente.respuestas.RespuestaGanadorMano;
import com.joanmanera.juegocartascliente.respuestas.RespuestaJugarCartaCPU;

public interface IRespuestas {
    void onCartaRecibidaCPU(RespuestaJugarCartaCPU respuestaJugarCartaCPU);
    void onRespuestaGanadorMano(RespuestaGanadorMano respuestaGanadorMano);
    void onSeleccionCartaJugador(EnvioJugarCarta envioJugarCarta);
}
