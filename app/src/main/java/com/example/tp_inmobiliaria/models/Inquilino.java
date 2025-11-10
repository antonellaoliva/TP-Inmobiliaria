package com.example.tp_inmobiliaria.models;

import java.io.Serializable;

public class Inquilino implements Serializable {
    private int idInquilino;
    private String nombre;
    private String apellido;
    private int dni;
    private String telefono;
    private String email;

    public Inquilino() {}

    public Inquilino(int idInquilino, String nombre, String apellido, int dni, String telefono, String email) {
        this.idInquilino = idInquilino;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
