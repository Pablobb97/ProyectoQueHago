package com.example.proyectoprueba1quehago.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static final String URL="https://datos.madrid.es/egob/catalogo/";
    public static Retrofit retrofit=null;

    public API() {
    }

    public static Retrofit getApi(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
