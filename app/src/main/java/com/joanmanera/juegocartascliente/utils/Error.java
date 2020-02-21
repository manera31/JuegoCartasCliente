package com.joanmanera.juegocartascliente.utils;

public abstract class Error {
    public abstract static class Login {
        public static final int USUARIO_PASS_INCORRECTO = -1;
    }
    public abstract static class Sesion {
        public static final int NO_ENCONTRADA = -2;
        public static final int CADUCADA = -3;
    }
    public abstract static class Partida {
        public static final int NO_ENCONTRADA = -4;
    }

}
