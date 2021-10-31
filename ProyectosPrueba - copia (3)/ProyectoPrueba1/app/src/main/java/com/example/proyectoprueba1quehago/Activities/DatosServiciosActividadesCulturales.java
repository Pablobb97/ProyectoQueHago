package com.example.proyectoprueba1quehago.Activities;

import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DatosServiciosActividadesCulturales {

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

    private ActividadCultural actividadCultural;

    @SerializedName("@graph")
    private ArrayList<ActividadCultural> listaDatosServicios = new ArrayList<>();

    public DatosServiciosActividadesCulturales() {
        //parseJSON();
    }

    public ArrayList<ActividadCultural> getListaDatosServicios() {
        return listaDatosServicios;
    }

    public void setListaDatosServicios(ArrayList<ActividadCultural> listaDatosServicios) {
        this.listaDatosServicios = listaDatosServicios;
    }

    public String getTitle() {
        return title;
    }

    public ActividadCultural getActividadCultural() {
        return actividadCultural;
    }

    public void setActividadCultural(ActividadCultural actividadCultural) {
        this.actividadCultural = actividadCultural;
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

    public static ActividadCultural parseJson(String response){
        Gson gson=new GsonBuilder().create();
        ActividadCultural datos=gson.fromJson(response,ActividadCultural.class);
        return datos;
    }
}
