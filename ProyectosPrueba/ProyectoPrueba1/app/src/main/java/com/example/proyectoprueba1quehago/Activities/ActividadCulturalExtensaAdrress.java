package com.example.proyectoprueba1quehago.Activities;

import com.google.gson.annotations.SerializedName;

public class ActividadCulturalExtensaAdrress {

    @SerializedName("event-location")
    private String localizacion;

    public ActividadCulturalExtensaAdrress() {
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getLocalizacion() {
        String res;
        if (localizacion==null){
            res="";
        }else{
            res=localizacion;
        }
        return res;
    }
}
