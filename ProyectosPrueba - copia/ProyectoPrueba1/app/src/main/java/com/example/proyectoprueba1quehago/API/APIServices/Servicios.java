package com.example.proyectoprueba1quehago.API.APIServices;

import com.example.proyectoprueba1quehago.Activities.DatosServiciosActividadesCulturales;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Servicios {
    @GET("206974-0-agenda-eventos-culturales-100.json")
    Call<DatosServiciosActividadesCulturales> getServicios(@Query("distrito_nombre") String distrito_nombre);
}
