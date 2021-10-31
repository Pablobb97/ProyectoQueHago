package com.example.proyectoprueba1quehago.Activities;

import com.google.gson.annotations.SerializedName;

public class ActividadCultural {

    @SerializedName("@id")
    private String id;
    private String title;
    private double latitude;

    public ActividadCultural() {
    }


    public ActividadCultural(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitulo(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return title;
    }
}
