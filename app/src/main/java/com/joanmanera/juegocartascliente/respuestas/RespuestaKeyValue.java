package com.joanmanera.juegocartascliente.respuestas;

import java.util.HashMap;

public class RespuestaKeyValue {
    private HashMap<String, Integer> map;

    public RespuestaKeyValue(HashMap<String, Integer> map) {
        this.map = map;
    }

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }
}
