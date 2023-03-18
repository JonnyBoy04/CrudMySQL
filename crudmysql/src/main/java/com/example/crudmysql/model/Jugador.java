package com.example.crudmysql.model;


public class Jugador {
    int idJugador;
    String equipo;
    String posicion;
    Persona persona;

    public Jugador() {
    }

    public Jugador(int idJugador, String equipo, String posicion, Persona persona) {
        this.idJugador = idJugador;
        this.equipo = equipo;
        this.posicion = posicion;
        this.persona = persona;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
