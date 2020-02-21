package com.joanmanera.juegocartascliente.modelos;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nick;
    private String nombre;
    private String apellidos;

    public Usuario(String nick, String nombre, String apellidos) {
        this.nick = nick;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getNick() {
        return nick;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nick='" + nick + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}
