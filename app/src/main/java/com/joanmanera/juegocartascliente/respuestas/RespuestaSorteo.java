package com.joanmanera.juegocartascliente.respuestas;

import com.joanmanera.juegocartascliente.utils.Enums;


public class RespuestaSorteo {

    private Enums.PrimeroEnSacar empieza;
    private int condigoError;

    public RespuestaSorteo(Enums.PrimeroEnSacar empieza) {
        this.empieza = empieza;
        condigoError = 0;
    }

    public RespuestaSorteo(int condigoError) {
        empieza = null;
        this.condigoError = condigoError;
    }

    public RespuestaSorteo() {
    }

    public Enums.PrimeroEnSacar getTurno() {
        return empieza;
    }

    public void setTurno(Enums.PrimeroEnSacar empieza) {
        this.empieza = empieza;
    }

    public int getCondigoError() {
        return condigoError;
    }

    public void setCondigoError(int condigoError) {
        this.condigoError = condigoError;
    }
}
