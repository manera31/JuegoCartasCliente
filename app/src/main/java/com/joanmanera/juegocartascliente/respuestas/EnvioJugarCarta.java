package com.joanmanera.juegocartascliente.respuestas;

import com.joanmanera.juegocartascliente.utils.Enums;


public class EnvioJugarCarta {
    private String idSesion;
    private String idPartida;
    private int idCarta;
    private Enums.Caracteristica caracteristica;

    public EnvioJugarCarta(String idSesion, String idPartida, int idCarta, Enums.Caracteristica caracteristica) {
        this.idSesion = idSesion;
        this.idPartida = idPartida;
        this.idCarta = idCarta;
        this.caracteristica = caracteristica;
    }

    public EnvioJugarCarta() {
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public String getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
    }

    public int getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }

    public Enums.Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Enums.Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }
}
