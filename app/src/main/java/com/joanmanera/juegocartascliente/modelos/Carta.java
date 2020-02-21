package com.joanmanera.juegocartascliente.modelos;

import java.io.Serializable;

public class Carta implements Serializable {
    private int id;
    private String marca;
    private String modelo;
    private int motor;
    private int potencia;
    private int velocidad;
    private int cilindros;
    private int rpm;
    private double consumo;

    public Carta(int id, String marca, String modelo, int motor, int potencia, int velocidad, int cilindros, int rpm, double consumo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.motor = motor;
        this.potencia = potencia;
        this.velocidad = velocidad;
        this.cilindros = cilindros;
        this.rpm = rpm;
        this.consumo = consumo;
    }

    public Carta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getMotor() {
        return motor;
    }

    public void setMotor(int motor) {
        this.motor = motor;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getCilindros() {
        return cilindros;
    }

    public void setCilindros(int cilindros) {
        this.cilindros = cilindros;
    }

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }
}
