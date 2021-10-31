package com.example.proyectoprueba1quehago.Activities;

import java.util.ArrayList;

public class bbdd {
    private DatosServiciosActividadesCulturales datosServiciosActividadesCulturales;
    private ArrayList<DatosServiciosActividadesCulturales> listaDatosServiciosActividadesCulturales;

    public bbdd(DatosServiciosActividadesCulturales datosServiciosActividadesCulturales, ArrayList<DatosServiciosActividadesCulturales> listaDatosServiciosActividadesCulturales) {
        this.datosServiciosActividadesCulturales = datosServiciosActividadesCulturales;
        this.listaDatosServiciosActividadesCulturales = listaDatosServiciosActividadesCulturales;
        //crearLista();
    }

    public bbdd() {
        crearLista();
    }

    private void crearLista() {
        listaDatosServiciosActividadesCulturales =new ArrayList<>();
        for (int i = 0;i<100;i++){
           // datosServiciosActividadesCulturales =new DatosServiciosActividadesCulturales(String.valueOf(i),String.valueOf(i));
            listaDatosServiciosActividadesCulturales.add(datosServiciosActividadesCulturales);
        }
    }

    public ArrayList<DatosServiciosActividadesCulturales> getListaDatosServiciosActividadesCulturales() {
        return listaDatosServiciosActividadesCulturales;
    }
}
