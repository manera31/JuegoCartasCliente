package com.joanmanera.juegocartascliente.respuestas;

import com.joanmanera.juegocartascliente.modelos.Carta;


import java.util.ArrayList;

public class RespuestaNuevoJuego {
    private String idPartida;
    private ArrayList<Carta> cartasJugador;
    private int codigoError;

    public RespuestaNuevoJuego(String idPartida, ArrayList<Carta> cartasJugador, int codigoError) {
        this.idPartida = idPartida;
        this.cartasJugador = cartasJugador;
        this.codigoError = codigoError;
    }

    public RespuestaNuevoJuego(int codigoError) {
        idPartida = "";
        cartasJugador = new ArrayList<>();
        this.codigoError = codigoError;
    }

    public RespuestaNuevoJuego() {
    }

    public String getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
    }

    public ArrayList<Carta> getCartasJugador() {
        return cartasJugador;
    }

    public void setCartasJugador(ArrayList<Carta> cartasJugador) {
        this.cartasJugador = cartasJugador;
    }

    public int getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(int codigoError) {
        this.codigoError = codigoError;
    }
}
