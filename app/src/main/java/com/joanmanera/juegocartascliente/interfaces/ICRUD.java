package com.joanmanera.juegocartascliente.interfaces;

import com.joanmanera.juegocartascliente.modelos.Carta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICRUD {

    @GET("carta")
    Call<List<Carta>> getCartas();
}
