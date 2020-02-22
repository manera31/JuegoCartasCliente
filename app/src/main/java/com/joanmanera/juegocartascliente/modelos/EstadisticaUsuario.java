package com.joanmanera.juegocartascliente.modelos;

public class EstadisticaUsuario {
    private String nick;
    private int partidasGanadas;
    private int partidasPerdidas;
    private int partidasEmpatadas;

    public EstadisticaUsuario(String nick, int partidasGanadas, int partidasPerdidas, int partidasEmpatadas) {
        this.nick = nick;
        this.partidasGanadas = partidasGanadas;
        this.partidasPerdidas = partidasPerdidas;
        this.partidasEmpatadas = partidasEmpatadas;
    }

    public EstadisticaUsuario(String nick){
        this(nick, 0, 0, 0);
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas() {
        this.partidasGanadas++;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public void setPartidasPerdidas() {
        this.partidasPerdidas++;
    }

    public int getPartidasEmpatadas() {
        return partidasEmpatadas;
    }

    public void setPartidasEmpatadas() {
        this.partidasEmpatadas++;
    }
}
