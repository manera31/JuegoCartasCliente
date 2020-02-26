package com.joanmanera.juegocartascliente.utils;

import java.io.Serializable;

/**
 * Clase para almacenar los datos enumerados de la aplicación.
 * @author Joan Manera Perez
 */
public abstract class Enums {
    /**
     * Enumerado caracteristica.
     */
    public enum Caracteristica {MOTOR, POTENCIA, VELOCIDAD, CILINDROS, RPM, CONSUMO}

    /**
     * Enumerado turno.
     */
    public enum Turno {Jugador, CPU}

    /**
     * Enumerado bot.
     */
    public enum Bot implements Serializable {DESACTIVADO, ALEATORIO, INTELOGENCIA_SUPREMA, NIVEL_DIOS}
}
