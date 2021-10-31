package com.example.proyectoprueba1quehago.Activities;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PaginaGuardar {
    //mañana
    private String tituloManana="";
    private String descriptionManana="";
    private String fechaManana="";
    private String localizacionManana="";
    private String precioManana="";
    //comida
    private String nombreComida="";
    private String localizacionComida="";
    private String precioComida="";
    private String distritoComida="";
    private String productosComida="";
    private String puntuacionComida="";
    //tarde
    private String tituloTarde="";
    private String descriptionTarde="";
    private String fechaTarde="";
    private String localizacionTarde="";
    private String precioTarde="";
    //cena
    private String nombreCena="";
    private String localizacionCena="";
    private String precioCena="";
    private String distritoCena="";
    private String productosCena="";
    private String puntuacionCena="";

    public PaginaGuardar(String tituloManana, String descriptionManana, String fechaManana, String localizacionManana, String precioManana, String nombreComida, String localizacionComida, String precioComida, String distritoComida, String productosComida, String puntuacionComida, String tituloTarde, String descriptionTarde, String fechaTarde, String localizacionTarde, String precioTarde, String nombreCena, String localizacionCena, String precioCena, String distritoCena, String productosCena, String puntuacionCena) {
        this.tituloManana = tituloManana;
        this.descriptionManana = descriptionManana;
        this.fechaManana = fechaManana;
        this.localizacionManana = localizacionManana;
        this.precioManana = precioManana;
        this.nombreComida = nombreComida;
        this.localizacionComida = localizacionComida;
        this.precioComida = precioComida;
        this.distritoComida = distritoComida;
        this.productosComida = productosComida;
        this.puntuacionComida = puntuacionComida;
        this.tituloTarde = tituloTarde;
        this.descriptionTarde = descriptionTarde;
        this.fechaTarde = fechaTarde;
        this.localizacionTarde = localizacionTarde;
        this.precioTarde = precioTarde;
        this.nombreCena = nombreCena;
        this.localizacionCena = localizacionCena;
        this.precioCena = precioCena;
        this.distritoCena = distritoCena;
        this.productosCena = productosCena;
        this.puntuacionCena = puntuacionCena;

        comporbacionInicio();
    }

    private void comporbacionInicio(){

            URL url;
            HttpURLConnection conexion = null;

            try {
                url = new URL("http://localhost/Android/AppPabloProyectoQueHago/public/guardar/"+tituloManana +"/"+descriptionManana +"/"+fechaManana +"/"+localizacionManana +"/"+precioManana +"/"+nombreComida +"/"+localizacionComida +"/"+precioComida +"/"+distritoComida +"/"+productosComida +"/"+puntuacionComida +"/"+tituloTarde +"/"+descriptionTarde +"/"+fechaTarde +"/"+localizacionTarde +"/"+precioTarde +"/"+nombreCena +"/"+localizacionCena +"/"+precioCena +"/"+distritoCena +"/"+productosCena +"/"+puntuacionCena);
                System.out.println("************"+url+"************");
                conexion = (HttpURLConnection) url.openConnection();



                //BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                InputStreamReader in = new InputStreamReader(conexion.getInputStream());

                BufferedReader br = new BufferedReader(in);

                String line = br.readLine();
                System.out.println("------------------------------------------------"+line+"------------------------------------------------");

                Log.d("HTTP-GET", line);
                //JsonCompleto=line;
                if (line=="Guardado Registrado"){
                    //inicioCorrecto=true;
                }else{
                    //inicioCorrecto=false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conexion != null) {
                    conexion.disconnect();
                }
            }
    }
}
