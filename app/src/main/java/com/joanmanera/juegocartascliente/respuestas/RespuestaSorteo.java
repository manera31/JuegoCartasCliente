package com.joanmanera.juegocartascliente.respuestas;

import com.joanmanera.juegocartascliente.utils.Enums;


public class RespuestaSorteo {

    private Enums.Turno empieza;
    private int condigoError;

    public RespuestaSorteo(Enums.Turno empieza, int condigoError) {
        this.empieza = empieza;
        this.condigoError = condigoError;
    }

    public RespuestaSorteo(int condigoError) {
        empieza = null;
        this.condigoError = condigoError;
    }

    public RespuestaSorteo() {
    }

    public Enums.Turno getTurno() {
        return empieza;
    }

    public void setTurno(Enums.Turno empieza) {
        this.empieza = empieza;
    }

    public int getCondigoError() {
        return condigoError;
    }

    public void setCondigoError(int condigoError) {
        this.condigoError = condigoError;
    }
}
