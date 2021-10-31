package com.example.proyectoprueba1quehago.Activities;

import com.google.gson.annotations.SerializedName;

public class ActividadCulturalExtensa {

    @SerializedName("@id")
    private String id;
    private String title;
    private String description;
    private double latitude;
    @SerializedName("dtstart")
    private String fecha;
    @SerializedName("street-address")
    private String localizacion;
    //@SerializedName("price")
    private String price;

    public ActividadCulturalExtensa() {
    }

    public void setPrecio(String price) {
        this.price = price;
    }

    public String getPrecio() {
        return price;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public ActividadCulturalExtensa(String id, String title) {
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
