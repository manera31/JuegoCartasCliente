package com.joanmanera.juegocartascliente.respuestas;


import com.joanmanera.juegocartascliente.utils.Enums;

public class RespuestaJugarCartaCPU {
    private int idCarta;
    private Enums.Caracteristica caracteristica;

    public RespuestaJugarCartaCPU(int idCarta, Enums.Caracteristica caracteristica) {
        this.idCarta = idCarta;
        this.caracteristica = caracteristica;
    }

    public RespuestaJugarCartaCPU() {
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
