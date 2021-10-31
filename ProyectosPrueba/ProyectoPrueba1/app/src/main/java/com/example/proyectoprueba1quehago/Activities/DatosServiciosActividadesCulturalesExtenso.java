package com.example.proyectoprueba1quehago.Activities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DatosServiciosActividadesCulturalesExtenso {

    //Conjunto de servicios por Distrito
    //private String nombre;
    //private String fecha;
    //private Button boton_añadir;

    //private JsonObject graph;
    @SerializedName("@id")
    private int id;
    private String c;
    private String link;
    private String title;

    private ActividadCulturalExtensa actividadCulturalExtensa;

    @SerializedName("@graph")
    private ArrayList<ActividadCulturalExtensa> listaDatosServicios = new ArrayList<>();

    public DatosServiciosActividadesCulturalesExtenso() {
        //parseJSON();
    }

    public ArrayList<ActividadCulturalExtensa> getListaDatosServicios() {
        return listaDatosServicios;
    }

    public void setListaDatosServicios(ArrayList<ActividadCulturalExtensa> listaDatosServicios) {
        this.listaDatosServicios = listaDatosServicios;
    }

    public String getTitle() {
        return title;
    }

    public ActividadCulturalExtensa getActividadCultural() {
        return actividadCulturalExtensa;
    }

    public void setActividadCultural(ActividadCulturalExtensa actividadCultural) {
        this.actividadCulturalExtensa = actividadCulturalExtensa;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getC() {
        return c;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public static ActividadCulturalExtensa parseJson(String response){
        Gson gson=new GsonBuilder().create();
        ActividadCulturalExtensa datos=gson.fromJson(response,ActividadCulturalExtensa.class);
        return datos;
    }
}
