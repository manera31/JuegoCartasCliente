package com.joanmanera.juegocartascliente.utils;

/**
 * Clase para enumerar posibles errores y advertencias.
 * @author Joan Manera Perez
 */
public abstract class Control {
    /**
     * Controla estados de la sesión.
     */
    public abstract static class Sesion {
        public static final int ENCONTRADA = -1;
        public static final int NO_ENCONTRADA = -2;
        public static final int CADUCADA = -3;
    }

    /**
     * Cotnrola estados de la partida.
     */
    public abstract static class Partida {
        public static final int ENCONTRADA = -11;
        public static final int NO_ENCONTRADA = -12;
    }

    /**
     * Controla estados del login.
     */
    public abstract static class Login {
        public static final int ENCONTRADO = -21;
        public static final int YA_CONECTADO = -22;
        public static final int USUARIO_PASS_INCORRECTO = -23;
    }

    /**
     * Controla estados del crud.
     */
    public abstract static class CRUD {
        public static final int USUARIO_ANADIDO = -31;
        public static final int USUARIO_EXISTE = -32;
        public static final int USUARIO_FALLO_GENERAL = -33;
    }
}
