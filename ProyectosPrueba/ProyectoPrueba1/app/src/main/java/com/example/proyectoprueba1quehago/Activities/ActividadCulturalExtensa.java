package com.example.proyectoprueba1quehago.Activities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ActividadCulturalExtensa{

    @SerializedName("@id")
    private String id;
    private String title;
    private String description;
    @SerializedName("dtstart")
    private String fecha;
    @SerializedName("event-location")
    private String localizacion;
    private String price;
    private String free;

    @SerializedName("@address")
    private ActividadCulturalExtensaAdrress actividadCulturalExtensaAdrress;



    public ActividadCulturalExtensa() {
    }

    public void setPrecio(String price) {
        this.price = price;
    }

    public String getPrecio() {
        String res="";
        if (free.equals("1")){
            res="GRATIS";
        }else {
            res=price;
        }
        return res;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getLocalizacion() {
        String res="";
        if (localizacion!=null) {
            res=localizacion;
        }else{
            res="";
        }
        return res;
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
        String res="";
        if (description!=null){
            res=description;
        }else{
            res="";
        }
        return res;
    }

    public void setActividadCulturalExtensaAdrress(ActividadCulturalExtensaAdrress actividadCulturalExtensaAdrress) {
        this.actividadCulturalExtensaAdrress = actividadCulturalExtensaAdrress;
    }

    public ActividadCulturalExtensaAdrress getActividadCulturalExtensaAdrress() {
        return actividadCulturalExtensaAdrress;
    }

    public static ActividadCulturalExtensaAdrress parseJson(String response){
        Gson gson=new GsonBuilder().create();
        ActividadCulturalExtensaAdrress datos=gson.fromJson(response,ActividadCulturalExtensaAdrress.class);
        return datos;
    }
}
