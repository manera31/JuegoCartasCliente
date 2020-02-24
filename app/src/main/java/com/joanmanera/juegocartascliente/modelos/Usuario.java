package com.joanmanera.juegocartascliente.modelos;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nick;
    private String nombre;
    private String apellidos;
    private String pass;

    public Usuario(String nick, String nombre, String apellidos, String pass) {
        this.nick = nick;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.pass = pass;
    }

    public Usuario(String nick, String nombre, String apellidos) {
        this(nick, nombre, apellidos, "");
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
