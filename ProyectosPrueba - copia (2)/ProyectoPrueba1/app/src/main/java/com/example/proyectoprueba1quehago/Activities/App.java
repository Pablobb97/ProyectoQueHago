package com.example.proyectoprueba1quehago.Activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba1quehago.API.API;
import com.example.proyectoprueba1quehago.API.APIServices.ActividadCultutalServicio;
import com.example.proyectoprueba1quehago.API.APIServices.Servicios;
import com.example.proyectoprueba1quehago.Adaptadores.AdaptadorPersonalizadoServicio;
import com.example.proyectoprueba1quehago.R;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class App extends AppCompatActivity {

    private DatosServiciosActividadesCulturales datosServiciosActividadesCulturales;
    private DatosServiciosActividadesCulturalesExtenso datosServiciosActividadesCulturalesExtenso;
    private bbdd bbdd;
    private AdaptadorPersonalizadoServicio adaptadorPersonalizadoServicio;
    private GridView gridView;
    private ArrayList<String> listaJsonServicioAC = new ArrayList<>();
    private String distritoSeleccionado ="ARGANZUELA";
    private boolean gridviewVisible=false;
    private LinearLayout layoutServicio;
    private String linkAC="";


    private ArrayList<ActividadCulturalExtensa> lista_AC_Extensa=new ArrayList<ActividadCulturalExtensa>();
    private ArrayList<ActividadCulturalExtensa> lista_GridView=new ArrayList<ActividadCulturalExtensa>();

    private String url="";
    private boolean popMenuPulsado=false;
    private ActividadCulturalExtensa actividadCulturalExtensa;
    private int num=0;

    private ImageButton botonManana;
    private ImageButton botonComida;
    private ImageButton botonTarde;
    private ImageButton botonCena;

    /*private String[] listaDistritos;
    private ArrayList<PopUpItem> listaPopUp;
    private PopupMenu popupMenu;*/
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_app);
        gridView=findViewById(R.id.idListView);
        recogerBotones();
        comprobarBotones();
        crearBBDD();
        recogerDistrito();
        //seleccionaServicio();
        if (gridviewVisible){
            seleccionaServicio();
        }
    }

    private void comprobarBotones() {
        botonManana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<lista_AC_Extensa.size();i++){
                    if (Integer.parseInt(separarHora(lista_AC_Extensa.get(i).getFecha()))<14 && Integer.parseInt(separarHora(lista_AC_Extensa.get(i).getFecha()))>0 ){
                        System.out.println("-*-> "+lista_AC_Extensa.get(i).getFecha());
                        //lista_GridView.add(datosServiciosActividadesCulturales.getListaDatosServicios().get(i));
                        lista_GridView.add(lista_AC_Extensa.get(i));
                        System.out.println("Tamaño lista GridView: "+lista_GridView.size());
                    }
                    crearListViewAdaptado(lista_GridView);
                    abrirPopMenu();
                }
            }
        });

        botonComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        botonTarde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(App.this, "TARDE", Toast.LENGTH_SHORT).show();
                for (int i=0;i<lista_AC_Extensa.size();i++){
                    //Toast.makeText(App.this, "TARDE2", Toast.LENGTH_SHORT).show();
                    if (Integer.parseInt(separarHora(lista_AC_Extensa.get(i).getFecha()))>14 || Integer.parseInt(separarHora(lista_AC_Extensa.get(i).getFecha()))==0 ){
                        //Toast.makeText(App.this, "TARDE3", Toast.LENGTH_SHORT).show();
                        System.out.println("-*-> "+lista_AC_Extensa.get(i).getFecha());
                        //lista_GridView.add(datosServiciosActividadesCulturales.getListaDatosServicios().get(i));
                        lista_GridView.add(lista_AC_Extensa.get(i));
                        System.out.println("Tamaño lista GridView TARDE: "+lista_GridView.size());
                    }
                    crearListViewAdaptado(lista_GridView);
                    abrirPopMenu();
                }
            }
        });

        botonCena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private String separarHora(String fecha) {
        String fechaDia="";
        String fechaHora="";
        String[] listaFecha = fecha.split(" ");
        fechaDia = listaFecha[0];
        fechaHora = listaFecha[1];

        String fechaHoraLimpia="";

        String[] listaFecha2 = fechaHora.split(":");
        fechaHoraLimpia = listaFecha2[0];
        return fechaHoraLimpia;
    }

    private void recogerBotones() {
        botonManana=findViewById(R.id.idButtonManana);
        botonComida=findViewById(R.id.idButtonComida);
        botonTarde=findViewById(R.id.idButtonTarde);
        botonCena=findViewById(R.id.idButtonCena);
    }

    private void recogerTodosLosDatosAC(){
        num=0;
        System.out.println("Tamaño: "+datosServiciosActividadesCulturales.getListaDatosServicios().size());
        for (int i=0;i<datosServiciosActividadesCulturales.getListaDatosServicios().size();i++){
            System.out.println("Posicion: "+i);
            popMenuPulsado=false;
            url=datosServiciosActividadesCulturales.getListaDatosServicios().get(i).getId();
            llamaActividadCultural();
        }
        System.out.println("***************************FIN******************************");
        System.out.println("Tamaño Lista Mañana: "+lista_AC_Extensa.size());
        for (int i=0;i<lista_AC_Extensa.size();i++){
        //for (int i=0;i<11;i++){
            System.out.println("****---> "+lista_AC_Extensa.get(i).getTitulo());
        }
    }

    private void llamaActividadCulturalGeneral() {
        ActividadCultutalServicio service=API.getApi().create(ActividadCultutalServicio.class);
        //String nombreDistrito="ARGANZUELA";

        Call<DatosServiciosActividadesCulturalesExtenso> actividadCulturalExtensaCallCall=service.getActividadCulturalExtensa(url+"");
        actividadCulturalExtensaCallCall.enqueue(new Callback<DatosServiciosActividadesCulturalesExtenso>() {
            @Override
            public void onResponse(Call<DatosServiciosActividadesCulturalesExtenso> call, Response<DatosServiciosActividadesCulturalesExtenso> response) {
                System.out.println("link ultimo: "+linkACLimpio(linkAC));
                datosServiciosActividadesCulturalesExtenso = response.body();
                Toast.makeText(App.this, "Descripcion: "+datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getDescription(), Toast.LENGTH_SHORT).show();
                //abrirPopMenu();
            }

            @Override
            public void onFailure(Call<DatosServiciosActividadesCulturalesExtenso> call, Throwable t) {
                Toast.makeText(App.this, "Error", Toast.LENGTH_SHORT).show();
                //gridviewVisible=false;
            }
        });
        System.out.println("hola");
        /*if (datosServiciosActividadesCulturalesExtenso.getActividadCultural().getDescription()!=null){
            return datosServiciosActividadesCulturalesExtenso.getActividadCultural();
        }else{
            return null;
        }*/
    }

    private void llamaActividadCultural() {
        ActividadCultutalServicio service=API.getApi().create(ActividadCultutalServicio.class);
        //String nombreDistrito="ARGANZUELA";
        if (popMenuPulsado){

        }
        Call<DatosServiciosActividadesCulturalesExtenso> actividadCulturalExtensaCallCall=service.getActividadCulturalExtensa(url+"");
        //Call<DatosServiciosActividadesCulturalesExtenso> actividadCulturalExtensaCallCall=service.getActividadCulturalExtensa(linkACLimpio(linkAC)+"");
        actividadCulturalExtensaCallCall.enqueue(new Callback<DatosServiciosActividadesCulturalesExtenso>() {
            @Override
            public void onResponse(Call<DatosServiciosActividadesCulturalesExtenso> call, Response<DatosServiciosActividadesCulturalesExtenso> response) {
                //System.out.println("link ultimo: "+linkACLimpio(linkAC));
                datosServiciosActividadesCulturalesExtenso = response.body();
                //Toast.makeText(App.this, "Descripcion: "+datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getDescription(), Toast.LENGTH_SHORT).show();
                lista_AC_Extensa.add(datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0));
                System.out.println();
                System.out.println("----"+num+"---> "+lista_AC_Extensa.get(num).getTitulo());
                num++;
                //abrirPopMenu();
                System.out.println("Tamaño Lista Mañana: "+lista_AC_Extensa.size());

            }

            @Override
            public void onFailure(Call<DatosServiciosActividadesCulturalesExtenso> call, Throwable t) {
                Toast.makeText(App.this, "Error", Toast.LENGTH_SHORT).show();
                //gridviewVisible=false;
            }
        });
    }

    private void abrirPopMenu() {
        ArrayList<String> listaDatos = new ArrayList<String>();
        //listaDatos.add(datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getTitulo());
        //listaDatos.add(datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getDescription());
        Intent intent=new Intent(App.this,PopUpActividadCultural.class);/*
        intent.putExtra("nombre", datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getTitulo());
        intent.putExtra("descripcion", datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getDescription());
        intent.putExtra("localizacion", datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getLocalizacion());
        intent.putExtra("precio", datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getPrecio());
        intent.putExtra("fecha", datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getFecha());*/

        //intent.putExtra("nombre", listaDatos.get());
        intent.putExtra("descripcion", datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getDescription());
        intent.putExtra("localizacion", datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getLocalizacion());
        intent.putExtra("precio", datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getPrecio());
        intent.putExtra("fecha", datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getFecha());
        startActivity(intent);
    }

    private String linkACLimpio(String linkAC) {
        String linkAClimpio=linkAC;
        //System.out.println("link limpio: "+linkAClimpio);
        return linkAClimpio;
    }

    private void seleccionaServicio() {
        final String[] idLinkServicio = {""};
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            // TODO Auto-generated method stub
            idLinkServicio[0] =(String)  String.valueOf(datosServiciosActividadesCulturales.getListaDatosServicios().get(position).getId());
            linkAC=idLinkServicio[0];
            //Toast.makeText(App.this, "Link: "+linkAC, Toast.LENGTH_SHORT).show();
            if (linkAC!=null){
                llamaActividadCultural();
            }
        });


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
            case R.id.idButtonMenuLocalizacion:
                buscarDistrito();
                //recogerDistrito();
                //peticionServicio();
                //anadirPopMenu();
                return true;
            case R.id.idButtonMenuGuardar:
                Toast.makeText(App.this, "GUARDAR", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void buscarDistrito() {
        Intent intent= new Intent(App.this,PaginaDistrito.class);
        //intent.putExtra("distrito",distrito[0]);
        startActivity(intent);
    }
    private void recogerDistrito() {
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null && bundle.getString("distrito")!=null){
            distritoSeleccionado=bundle.getString("distrito");
            Toast.makeText(App.this, distritoSeleccionado, Toast.LENGTH_SHORT).show();
            peticionServicio();
            /*System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
            if (datosServiciosActividadesCulturales!=null){
                //recogerTodosLosDatosAC();
            }else{
                System.out.println("------->"+datosServiciosActividadesCulturales.getListaDatosServicios().get(1).getTitulo());
            }*/

        }else{
            Toast.makeText(App.this, "Por favor, vuelva a seleccionar distrito.", Toast.LENGTH_SHORT).show();
        }
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
    private void crearListViewAdaptado(ArrayList<ActividadCulturalExtensa> lista) {
        adaptadorPersonalizadoServicio=new AdaptadorPersonalizadoServicio(this,R.layout.list_item_layout_servicio2,lista);
        gridView.setAdapter(adaptadorPersonalizadoServicio);
        popMenuPulsado=true;
    }

    private void crearListView() {
        //adaptadorPersonalizadoServicio=new AdaptadorPersonalizadoServicio(this,R.layout.list_item_layout_servicio2,datosServiciosActividadesCulturales.getListaDatosServicios());
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
        //String nombreDistrito="ARGANZUELA";
        //Toast.makeText(this, "Este distrito: "+nombreDistrito, Toast.LENGTH_SHORT).show();
        Call<DatosServiciosActividadesCulturales> distritoCall=service.getServicios(distritoSeleccionado+"");
        distritoCall.enqueue(new Callback<DatosServiciosActividadesCulturales>() {
            @Override
            public void onResponse(Call<DatosServiciosActividadesCulturales> call, Response<DatosServiciosActividadesCulturales> response) {
                datosServiciosActividadesCulturales = response.body();
                //crearListView();
                gridviewVisible=true;
                System.out.println("------->"+datosServiciosActividadesCulturales.getListaDatosServicios().get(1).getTitulo());
                recogerTodosLosDatosAC();
                System.out.println("Tamaño Lista Mañana: "+lista_AC_Extensa.size());
                for (int i=0;i<lista_AC_Extensa.size();i++){
                    //for (int i=0;i<11;i++){
                    System.out.println("****---> "+lista_AC_Extensa.get(i).getTitulo());
                }
            }

            @Override
            public void onFailure(Call<DatosServiciosActividadesCulturales> call, Throwable t) {
                Toast.makeText(App.this, "Error", Toast.LENGTH_SHORT).show();
                gridviewVisible=false;
            }
        });
        gridviewVisible=true;
        /*if (datosServiciosActividadesCulturales!=null){
            recogerTodosLosDatosAC();
        }*/
        /*if (datosServiciosActividadesCulturales!=null){
            recogerTodosLosDatosAC();
            System.out.println("------->"+datosServiciosActividadesCulturales.getListaDatosServicios().get(1).getTitulo());
        }*/
    }

    private void refrescarDatos() {
        System.out.println("******************"+datosServiciosActividadesCulturales.getListaDatosServicios().get(2).getTitulo()+"******************");
    }

    /*public void anadirPopMenu(){
        listaDistritos = new String[] {"ARGANZUELA","BARAJAS","CARABANCHEL","CENTRO","CHAMARTIN","CHAMBERI","CIUDAD LINEAL","FUENCARRAL-EL PARDO","HORTALEZA","LATINA","MONCLOA-ARAVACA","MORATALAZ","PUENTE DE VALLECAS","RETIRO","SALAMANCA","SAN BLAS-CANILLEJAS","TETUAN","USERA","VICALVARO","VILLAVERDE","VILLA DE VALLECAS"};

        listaPopUp = new ArrayList<>();

        for (int i=0;i<listaDistritos.length;i++){
            listaPopUp.add(new PopUpItem(1,listaDistritos[i]));
        }

        //View menuItemView = findViewById(R.id.menu_overflow);

        popupMenu = new PopupMenu(App.this, View);

        //popupMenu.getMenu().add(Menu.NONE, 1, Menu.NONE, mPopupList[0] );//the error is here
    }*/
}
