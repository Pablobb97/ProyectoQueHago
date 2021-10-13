package com.example.proyectoprueba1quehago.Activities;

import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DatosServiciosActividadesCulturales {

    //Conjunto de servicios por Distrito
    //private String nombre;
    //private String fecha;
    //private Button boton_añadir;
    private String graph;

    private ActividadCultural actividadCultural;
    private ArrayList<ActividadCultural> listaDatosServicios = new ArrayList<>();

    public DatosServiciosActividadesCulturales() {
        //parseJSON();
    }

    public String getGraph() {
        return graph;
    }

    public void setGraph(String graph) {
        graph = graph;
    }

    public void parseJSON() {
        System.out.println("********************AQUI********************");
        Gson gson = new Gson();
        Type type = new TypeToken<List<ActividadCultural>>(){}.getType();
        List<ActividadCultural> ListaServicios = gson.fromJson(graph, type);
        for (int i=0;i<ListaServicios.size();i++){
            //System.out.println(servicio.getId()+"-"+servicio.getTitulo());
            System.out.println(ListaServicios.get(i).getTitulo());
        }
    }
}
