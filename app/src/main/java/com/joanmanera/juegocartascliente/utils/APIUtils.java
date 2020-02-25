package com.joanmanera.juegocartascliente.utils;

import com.joanmanera.juegocartascliente.interfaces.ICRUD;
import com.joanmanera.juegocartascliente.interfaces.IEstadistica;
import com.joanmanera.juegocartascliente.interfaces.IPartida;

public class APIUtils {
    public static final String URL_PARTIDA = "http://192.168.1.71:8080/JuegoCartasRest_war/rest/partida/";
    public static final String URL_ESTADISTICA = "http://192.168.1.71:8080/JuegoCartasRest_war/rest/estadisticas/";
    public static final String URL_CRUD = "http://192.168.1.71:8080/JuegoCartasRest_war/rest/crud/";

    public static ICRUD getCRUD(){
        return APIRestClient.getInstance(URL_CRUD).getRetrofit().create(ICRUD.class);
    }

    public static IPartida getPartida(){
        return APIRestClient.getInstance(URL_PARTIDA).getRetrofit().create(IPartida.class);
    }

    public static IEstadistica getEstadisticas(){
        return APIRestClient.getInstance(URL_ESTADISTICA).getRetrofit().create(IEstadistica.class);
    }
}
