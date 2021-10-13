package com.example.proyectoprueba1quehago.Activities;

public class ActividadCultural {

    private String id;
    private String titulo;

    public ActividadCultural() {
    }

    public ActividadCultural(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
