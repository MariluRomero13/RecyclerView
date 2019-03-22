package com.example.recyclerview.modelos;

import android.os.Parcelable;

import java.io.Serializable;

public class Persona implements Serializable
{
    private Integer id;
    private String Nombre;
    private String Apellido;
    private Integer Edad;
    private String Telefono;


    public Persona(Integer ID, String nombre, String apellido, Integer edad, String telefono)
    {
        this.id = ID;
        Nombre = nombre;
        Apellido = apellido;
        Edad = edad;
        Telefono = telefono;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer ID) {
        this.id = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public Integer getEdad() {
        return Edad;
    }

    public void setEdad(Integer edad) {
        Edad = edad;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
