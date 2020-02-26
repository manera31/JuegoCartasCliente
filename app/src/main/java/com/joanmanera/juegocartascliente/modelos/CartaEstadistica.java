package com.joanmanera.juegocartascliente.modelos;

import com.joanmanera.juegocartascliente.utils.Enums;

import java.util.HashMap;

public class CartaEstadistica extends Carta {
    private HashMap<Enums.Caracteristica, Double> mapaEstadisticas;

    public CartaEstadistica(int id, String marca, String modelo, int motor, int potencia, int velocidad, int cilindros, int rpm, double consumo, HashMap<Enums.Caracteristica, Double> mapaEstadisticas) {
        super(id, marca, modelo, motor, potencia, velocidad, cilindros, rpm, consumo);
        this.mapaEstadisticas = mapaEstadisticas;
    }

    public CartaEstadistica (Carta carta, HashMap<Enums.Caracteristica, Double> mapaEstadisticas){
        super(carta.getId(), carta.getMarca(), carta.getModelo(), carta.getMotor(), carta.getPotencia(), carta.getVelocidad(), carta.getCilindros(), carta.getRpm(), carta.getConsumo());
        this.mapaEstadisticas = mapaEstadisticas;
    }

    public HashMap<Enums.Caracteristica, Double> getMapaEstadisticas() {
        return mapaEstadisticas;
    }
}
