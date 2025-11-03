package com.example.tp_inmobiliaria.models;

import java.io.Serializable;

public class Propietario implements Serializable {
    private int idPropietario;
    private int dni;
    private String apellido;
    private String nombre;
    private String telefono;
    private String email;
    private String clave;

    public Propietario() {
    }

    public Propietario(int idPropietario, int dni, String apellido, String nombre, String telefono, String email, String clave) {
        this.idPropietario = idPropietario;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public int getDni() {
        return dni;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getClave() {
        return clave;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
