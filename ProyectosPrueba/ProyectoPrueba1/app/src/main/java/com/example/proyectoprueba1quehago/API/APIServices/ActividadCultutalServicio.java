package com.example.proyectoprueba1quehago.API.APIServices;

import com.example.proyectoprueba1quehago.Activities.DatosServiciosActividadesCulturalesExtenso;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ActividadCultutalServicio {
    //@GET("egob/catalogo/tipo/evento/{link}")
    @GET("{link}")
    Call<DatosServiciosActividadesCulturalesExtenso> getActividadCulturalExtensa(@Path(value="link", encoded = true) String link_actividad_cultural);
}
