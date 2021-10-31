package com.example.proyectoprueba1quehago.Activities;

import java.util.ArrayList;

public class Restaurante {
    private String nombre;
    private String localizacion;
    private String precio;
    private String distrito;
    private String productos;
    private String puntuacion;

    public Restaurante(String nombre, String localizacion, String precio, String distrito,String productos, String puntuacion) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.precio = precio;
        this.distrito = distrito;
        this.productos = productos;
        this.puntuacion = puntuacion;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getPrecio() {
        return precio;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public String getProductos() {
        return productos;
    }

    public String getPuntuacion() {
        return puntuacion;
    }
}
