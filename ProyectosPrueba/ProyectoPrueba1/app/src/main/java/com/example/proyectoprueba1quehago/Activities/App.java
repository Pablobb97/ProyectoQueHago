package com.example.proyectoprueba1quehago.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba1quehago.API.API;
import com.example.proyectoprueba1quehago.API.APIServices.Servicios;
import com.example.proyectoprueba1quehago.Adaptadores.AdaptadorPersonalizadoServicio;
import com.example.proyectoprueba1quehago.R;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class App extends AppCompatActivity {

    private DatosServiciosActividadesCulturales datosServiciosActividadesCulturales;
    private bbdd bbdd;
    private AdaptadorPersonalizadoServicio adaptadorPersonalizadoServicio;
    private GridView gridView;
    private ArrayList<String> listaJsonServicioAC = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_app);
        gridView=findViewById(R.id.idListView);

        crearBBDD();
        crearListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu_items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Toast.makeText(this, "Aqui", Toast.LENGTH_SHORT).show();
        switch (item.getItemId()){
            case R.id.idButtonMenuFecha:
                //mostrarCalendario();
                adaptadorPersonalizadoServicio.notifyDataSetChanged();
                return true;
            case R.id.idButtonMenuGuardar:
                peticionServicio();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void mostrarCalendario() {
        Calendar calendario= Calendar.getInstance();
        int fecha_anio=calendario.get(calendario.YEAR);
        int fecha_mes=calendario.get(calendario.MONTH);
        int fecha_dia=calendario.get(calendario.DAY_OF_MONTH);
        Toast.makeText(this, "Aqui2", Toast.LENGTH_SHORT).show();

        DatePickerDialog dpd= new DatePickerDialog(App.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "-" + month + "-" + year;
                Toast.makeText(App.this, "Esta es La fecha: "+fecha, Toast.LENGTH_SHORT).show();
            }
        },fecha_dia,fecha_mes,fecha_anio);
        dpd.show();

    }

    private void crearListView() {
        //adaptadorPersonalizadoServicio=new AdaptadorPersonalizadoServicio(this,R.layout.list_item_layout_servicio,bbdd.getListaDatosServiciosActividadesCulturales());
        //gridView.setAdapter(adaptadorPersonalizadoServicio);
    }

    private void crearBBDD() {
        bbdd=new bbdd();
        bbdd.getListaDatosServiciosActividadesCulturales();
    }

    public void mostrarcalendario2(MenuItem item) {
        Calendar calendario= Calendar.getInstance();
        int fecha_anio=calendario.get(calendario.YEAR);
        int fecha_mes=calendario.get(calendario.MONTH);
        int fecha_dia=calendario.get(calendario.DAY_OF_MONTH);

        DatePickerDialog dpd= new DatePickerDialog(App.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "-" + month + "-" + year;
                Toast.makeText(App.this, "Esta es La fecha: "+fecha, Toast.LENGTH_SHORT).show();
            }
        },fecha_anio,fecha_mes,fecha_dia);
        dpd.show();
    }

    private void peticionServicio() {
        //weather servces
        Servicios service=API.getApi().create(Servicios.class);
        String nombreDistrito="ARGANZUELA";
        Toast.makeText(this, "Este distrito: "+nombreDistrito, Toast.LENGTH_SHORT).show();
        Call<DatosServiciosActividadesCulturales> distritoCall=service.getServicios("206974-0-agenda-eventos-culturales-100.json?"+nombreDistrito);
        distritoCall.enqueue(new Callback<DatosServiciosActividadesCulturales>() {
            @Override
            public void onResponse(Call<DatosServiciosActividadesCulturales> call, Response<DatosServiciosActividadesCulturales> response) {
                Toast.makeText(App.this, "Bien", Toast.LENGTH_SHORT).show();
                //datosServiciosActividadesCulturales=new DatosServiciosActividadesCulturales();
                datosServiciosActividadesCulturales =response.body();
                //System.out.println(datosServiciosActividadesCulturales.getGraph());
                //datosServiciosActividadesCulturales.parseJSON();
                //refrescarDatos();
            }

            @Override
            public void onFailure(Call<DatosServiciosActividadesCulturales> call, Throwable t) {
                Toast.makeText(App.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
