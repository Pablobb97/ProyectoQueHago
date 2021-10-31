package com.example.proyectoprueba1quehago.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIActividadCultural {
    //public static final String URL="https://datos.madrid.es/";
    public static final String URL="";
    public static Retrofit retrofit=null;

    public APIActividadCultural() {
    }

    public static Retrofit getAPIActividadCultural(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
