package com.joanmanera.juegocartascliente.respuestas;


import com.joanmanera.juegocartascliente.utils.Enums;

import java.io.Serializable;

public class RespuestaResultadoMano implements Serializable {
    private int idCartaJugador;
    private int idCartaCPU;
    private Enums.Caracteristica caracteristica;
    private int idCartaGanadora;
    private int contadorPuntosJugaor, contadorPuntosCPU;

    public RespuestaResultadoMano(int idCartaJugador, int idCartaCPU, Enums.Caracteristica caracteristica, int idCartaGanadora) {
        this.idCartaJugador = idCartaJugador;
        this.idCartaCPU = idCartaCPU;
        this.caracteristica = caracteristica;
        this.idCartaGanadora = idCartaGanadora;
        this.contadorPuntosJugaor = 0;
        this.contadorPuntosCPU = 0;
    }

    public RespuestaResultadoMano(int idCartaJugador, int idCartaCPU, Enums.Caracteristica caracteristica, int idCartaGanadora, int contadorPuntosJugaor, int contadorPuntosCPU) {
        this.idCartaJugador = idCartaJugador;
        this.idCartaCPU = idCartaCPU;
        this.caracteristica = caracteristica;
        this.idCartaGanadora = idCartaGanadora;
        this.contadorPuntosJugaor = contadorPuntosJugaor;
        this.contadorPuntosCPU = contadorPuntosCPU;
    }

    public int getIdCartaJugador() {
        return idCartaJugador;
    }

    public void setIdCartaJugador(int idCartaJugador) {
        this.idCartaJugador = idCartaJugador;
    }

    public int getIdCartaCPU() {
        return idCartaCPU;
    }

    public void setIdCartaCPU(int idCartaCPU) {
        this.idCartaCPU = idCartaCPU;
    }

    public Enums.Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Enums.Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    public int getIdCartaGanadora() {
        return idCartaGanadora;
    }

    public void setIdCartaGanadora(int idCartaGanadora) {
        this.idCartaGanadora = idCartaGanadora;
    }

    public int getContadorPuntosJugaor() {
        return contadorPuntosJugaor;
    }

    public void setContadorPuntosJugaor(int contadorPuntosJugaor) {
        this.contadorPuntosJugaor = contadorPuntosJugaor;
    }

    public int getContadorPuntosCPU() {
        return contadorPuntosCPU;
    }

    public void setContadorPuntosCPU(int contadorPuntosCPU) {
        this.contadorPuntosCPU = contadorPuntosCPU;
    }
}
