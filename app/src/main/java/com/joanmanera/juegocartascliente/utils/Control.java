package com.joanmanera.juegocartascliente.utils;

public abstract class Control {
    public abstract static class Sesion {
        public static final int ENCONTRADA = -1;
        public static final int NO_ENCONTRADA = -2;
        public static final int CADUCADA = -3;
    }
    public abstract static class Partida {
        public static final int ENCONTRADA = -11;
        public static final int NO_ENCONTRADA = -12;
    }
    public abstract static class Login {
        public static final int ENCONTRADO = -21;
        public static final int YA_CONECTADO = -22;
        public static final int USUARIO_PASS_INCORRECTO = -23;
    }
}
