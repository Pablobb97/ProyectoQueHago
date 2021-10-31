package com.example.proyectoprueba1quehago.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba1quehago.API.API;
import com.example.proyectoprueba1quehago.API.APIServices.ActividadCultutalServicio;
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
    private DatosServiciosActividadesCulturalesExtenso datosServiciosActividadesCulturalesExtenso;
    private bbdd bbdd;
    private AdaptadorPersonalizadoServicio adaptadorPersonalizadoServicio;
    private GridView gridView;
    private ArrayList<String> listaJsonServicioAC = new ArrayList<>();
    private String distritoSeleccionado ="ARGANZUELA";
    private boolean gridviewVisible=false;
    private LinearLayout layoutServicio;
    private String linkAC="";


    private ArrayList<ActividadCultural> lista_AC_Manana;
    private ArrayList<ActividadCulturalExtensa> lista_AC_Extensa_Manana;
    private ArrayList<ActividadCultural> lista_AC_Tarde;
    private ArrayList<ActividadCulturalExtensa> lista_AC_Extensa_Tarde;

    private String url="";
    private boolean popMenuPulsado=false;
    private ActividadCulturalExtensa actividadCulturalExtensa;

    /*private String[] listaDistritos;
    private ArrayList<PopUpItem> listaPopUp;
    private PopupMenu popupMenu;*/
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_app);
        gridView=findViewById(R.id.idListView);
        crearBBDD();
        recogerDistrito();
        //seleccionaServicio();
        if (gridviewVisible){
            seleccionaServicio();
        }
    }
    private void recogerTodosLosDatosAC(){
        for (int i=0;i<datosServiciosActividadesCulturales.getListaDatosServicios().size();i++){
            popMenuPulsado=false;
            url=datosServiciosActividadesCulturales.getListaDatosServicios().get(i).getId();
            llamaActividadCultural();

        }
        //for (int i=0;i<lista_AC_Extensa_Manana.size();i++){
        //for (int i=0;i<11;i++){
         //   System.out.println("---> "+lista_AC_Extensa_Manana.get(i).getDescription());
        //}
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
                System.out.println("link ultimo: "+linkACLimpio(linkAC));
                datosServiciosActividadesCulturalesExtenso = response.body();
                //Toast.makeText(App.this, "Descripcion: "+datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getDescription(), Toast.LENGTH_SHORT).show();
                abrirPopMenu();
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
        listaDatos.add(datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getTitulo());
        listaDatos.add(datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getDescription());
        Intent intent=new Intent(App.this,PopUpActividadCultural.class);
        intent.putExtra("nombre", datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getTitulo());
        intent.putExtra("descripcion", datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getDescription());
        intent.putExtra("localizacion", datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getLocalizacion());
        intent.putExtra("precio", datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getPrecio());
        intent.putExtra("fecha", datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0).getFecha());
        startActivity(intent);
    }

    private String linkACLimpio(String linkAC) {
        String linkAClimpio=linkAC;
        System.out.println("link limpio: "+linkAClimpio);
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

    private void crearListView() {
        adaptadorPersonalizadoServicio=new AdaptadorPersonalizadoServicio(this,R.layout.list_item_layout_servicio2,datosServiciosActividadesCulturales.getListaDatosServicios());
        gridView.setAdapter(adaptadorPersonalizadoServicio);
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
