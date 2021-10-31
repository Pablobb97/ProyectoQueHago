package com.example.proyectoprueba1quehago.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba1quehago.API.API;
import com.example.proyectoprueba1quehago.API.APIServices.ActividadCultutalServicio;
import com.example.proyectoprueba1quehago.API.APIServices.Servicios;
import com.example.proyectoprueba1quehago.Adaptadores.AdaptadorPersonalizadoRestaurante;
import com.example.proyectoprueba1quehago.Adaptadores.AdaptadorPersonalizadoServicio;
import com.example.proyectoprueba1quehago.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class App extends AppCompatActivity {
    Handler handler = new Handler();

    private DatosServiciosActividadesCulturales datosServiciosActividadesCulturales;
    private DatosServiciosActividadesCulturalesExtenso datosServiciosActividadesCulturalesExtenso;
    private BBDDRestaurantes bbddRestaurantes;
    private AdaptadorPersonalizadoServicio adaptadorPersonalizadoServicio;
    private AdaptadorPersonalizadoRestaurante adaptadorPersonalizadoRestaurante;
    private GridView gridView;
    private ArrayList<String> listaJsonServicioAC = new ArrayList<>();
    private String distritoSeleccionado ="ARGANZUELA";
    private boolean gridviewVisible=false;
    private LinearLayout layoutServicio;
    private String linkAC="";
    private String fechaEscogida="";
    private String fechaEscogidaDia="";
    private String fechaEscogidaMes="";
    private String fechaEscogidaAnio="";
    private String nombreDistritoSeleccionado="";


    private ArrayList<ActividadCulturalExtensa> lista_AC_Extensa=new ArrayList<ActividadCulturalExtensa>();
    private ArrayList<ActividadCulturalExtensa> lista_GridView=new ArrayList<ActividadCulturalExtensa>();
    private ArrayList<Restaurante> listaRestaurantes=new ArrayList<>();

    ArrayList<ActividadCulturalExtensa> lista_GridView_Manana=new ArrayList<>();
    ArrayList<ActividadCulturalExtensa> lista_GridView_Tarde=new ArrayList<>();

    private String url="";
    private boolean popMenuPulsado=false;
    private ActividadCulturalExtensa actividadCulturalExtensa;
    private int num=0;

    private ImageButton botonManana;
    private ImageButton botonComida;
    private ImageButton botonTarde;
    private ImageButton botonCena;

    private Boolean primerInicio=true;

    private Boolean pantallaManana=true;
    private Boolean pantallaComida=false;
    private Boolean pantallaTarde=false;
    private Boolean pantallaCena=false;

    private boolean servicioCulturalSeleccionado=false;

    private String nombreRestauranteComidaSolicitado="";
    private String nombreRestauranteCenaSolicitado="";
    private String nombreServicioMananaSolicitado="";
    private String nombreServicioTardeSolicitado="";

    //Mañana
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
    private Spinner listaDesplegable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_app);
        gridView=findViewById(R.id.idGridView);
        recogerBotones();
        Toast.makeText(App.this, "POR FAVOR ESPERE UNOS SEGUNDOS HASTA QUE SE RECOJAN TODOS LOS SERVICIOS", Toast.LENGTH_LONG).show();
        Toast.makeText(App.this, "POR FAVOR SELECCIONE UNA FECHA.", Toast.LENGTH_SHORT).show();

        //ejecutarTarea();
        recogerTodosLosServicios();


        bbddRestaurantes=new BBDDRestaurantes();
        recogerRestaurantes();


        registerForContextMenu(gridView);
        comprobarBotones();
        if (primerInicio){
            botonManana.setImageResource(R.mipmap.ic_logo_manana_pulsado_foreground);
        }


        seleccionaServicio();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private final int TIEMPO = 1000;

    public void ejecutarTarea() {
        handler.postDelayed(new Runnable() {
            public void run() {

                // función a ejecutar
                actualizarGridView(); // función para refrescar la ubicación del conductor, creada en otra línea de código

                handler.postDelayed(this, TIEMPO);
            }

        }, TIEMPO);

    }

    private void recogerServiciosRestaurantesSolicitados() {
        Bundle bundle=getIntent().getExtras();
        if (pantallaManana){
            if (bundle!=null && bundle.getString("nombreServicioSolicitado")!=null){
                nombreServicioMananaSolicitado=bundle.getString("nombreServicioSolicitado");
            }
        }
        if (pantallaComida){
            if (bundle!=null && bundle.getString("nombreRestauranteSolicitado")!=null){
                nombreRestauranteComidaSolicitado=bundle.getString("nombreRestauranteSolicitado");
            }
        }
        if (pantallaTarde){
            if (bundle!=null && bundle.getString("nombreServicioSolicitado")!=null){
                nombreServicioTardeSolicitado=bundle.getString("nombreServicioSolicitado");
            }
        }
        if (pantallaCena){
            if (bundle!=null && bundle.getString("nombreRestauranteSolicitado")!=null){
                nombreRestauranteCenaSolicitado=bundle.getString("nombreRestauranteSolicitado");
            }
        }
    }

    private void recogerBotones() {
        botonManana=(ImageButton) findViewById(R.id.idButtonManana);
        botonComida=(ImageButton) findViewById(R.id.idButtonComida);
        botonTarde=(ImageButton) findViewById(R.id.idButtonTarde);
        botonCena=(ImageButton) findViewById(R.id.idButtonCena);
    }

    public void seleccionaDia(MenuItem item) {
        primerInicio=false;
        gridviewVisible=false;
        Calendar calendario= Calendar.getInstance();
        int fecha_anio=calendario.get(calendario.YEAR);
        int fecha_mes=calendario.get(calendario.MONTH);
        int fecha_dia=calendario.get(calendario.DAY_OF_MONTH);

        DatePickerDialog dpd= new DatePickerDialog(App.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fechaEscogida = dayOfMonth + "-" + month + "-" + year;
                fechaEscogidaDia=dayOfMonth+"";
                fechaEscogidaMes=month+"";
                fechaEscogidaAnio=year+"";
                Toast.makeText(App.this, "Esta es La fecha: "+fechaEscogida, Toast.LENGTH_SHORT).show();
                //recogerTodosLosServicios();
                //crearListViewAdaptado();
            }
        },fecha_anio,fecha_mes,fecha_dia);
        dpd.show();
    }

    public void recogerTodosLosServicios(){
        servicioCulturalSeleccionado=true;
        Servicios service=API.getApi().create(Servicios.class);
        Call<DatosServiciosActividadesCulturales> distritoCall=service.getServicios(""+nombreDistritoSeleccionado);
        distritoCall.enqueue(new Callback<DatosServiciosActividadesCulturales>() {
            @Override
            public void onResponse(Call<DatosServiciosActividadesCulturales> call, Response<DatosServiciosActividadesCulturales> response) {
                datosServiciosActividadesCulturales = response.body();
                recogerTodosLosDatosAC();
            }

            @Override
            public void onFailure(Call<DatosServiciosActividadesCulturales> call, Throwable t) {
                Toast.makeText(App.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void recogerTodosLosDatosAC(){
        num=0;
        for (int i=0;i<datosServiciosActividadesCulturales.getListaDatosServicios().size();i++){
            popMenuPulsado=false;
            url=datosServiciosActividadesCulturales.getListaDatosServicios().get(i).getId();
            llamaActividadCultural();
        }
    }

    private void llamaActividadCultural() {
        ActividadCultutalServicio service=API.getApi().create(ActividadCultutalServicio.class);
        Call<DatosServiciosActividadesCulturalesExtenso> actividadCulturalExtensaCallCall=service.getActividadCulturalExtensa(url+"");
        actividadCulturalExtensaCallCall.enqueue(new Callback<DatosServiciosActividadesCulturalesExtenso>() {
            @Override
            public void onResponse(Call<DatosServiciosActividadesCulturalesExtenso> call, Response<DatosServiciosActividadesCulturalesExtenso> response) {
                datosServiciosActividadesCulturalesExtenso = response.body();
                lista_AC_Extensa.add(datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0));
            }

            @Override
            public void onFailure(Call<DatosServiciosActividadesCulturalesExtenso> call, Throwable t) {
                Toast.makeText(App.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void crearGridViewAdaptado() {
        lista_GridView.clear();
        for (int i=0;i<lista_AC_Extensa.size();i++){
            String dia=separarDia(lista_AC_Extensa.get(i).getFecha());
            if (dia.equals(fechaEscogidaDia)){
                lista_GridView.add(lista_AC_Extensa.get(i));
            }
        }
        mostrarGridView();
        adaptadorPersonalizadoServicio.notifyDataSetChanged();
        popMenuPulsado=true;
    }

    private void mostrarGridView(){
        if(pantallaManana){
            lista_GridView_Manana.clear();
            for (int i=0;i<lista_GridView.size();i++){
                if (Integer.parseInt(separarHora(lista_GridView.get(i).getFecha()))<14 && Integer.parseInt(separarHora(lista_GridView.get(i).getFecha()))!=0 ){
                    lista_GridView_Manana.add(lista_GridView.get(i));
                }
            }
            adaptadorPersonalizadoServicio=new AdaptadorPersonalizadoServicio(this,R.layout.list_item_layout_servicio2,lista_GridView_Manana);
            gridView.setAdapter(adaptadorPersonalizadoServicio);
            gridviewVisible=true;
        }
        if(pantallaTarde){
            lista_GridView_Tarde.clear();
            for (int i=0;i<lista_GridView.size();i++){
                if (Integer.parseInt(separarHora(lista_GridView.get(i).getFecha()))>=14 || Integer.parseInt(separarHora(lista_GridView.get(i).getFecha()))==0 ){
                    lista_GridView_Tarde.add(lista_GridView.get(i));
                }
            }
            adaptadorPersonalizadoServicio=new AdaptadorPersonalizadoServicio(this,R.layout.list_item_layout_servicio2,lista_GridView_Tarde);
            gridView.setAdapter(adaptadorPersonalizadoServicio);
            gridviewVisible=true;
        }
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

    private String separarDia(String fecha) {
        String fechaDia="";
        String fechaHora="";
        String[] listaFecha = fecha.split(" ");
        fechaDia = listaFecha[0];
        fechaHora = listaFecha[1];

        String fechaDiaLimpia="";

        String[] listaFecha2 = fechaDia.split("-");
        fechaDiaLimpia = listaFecha2[2];
        return fechaDiaLimpia;
    }

    private void seleccionaServicio() {
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            // TODO Auto-generated method stub
            if (servicioCulturalSeleccionado){
                abrirPopMenu(position);
            }else{
                abrirPopMenuRestaurante(position);
            }
        });
        gridView.setOnItemLongClickListener((parent, view, position, id) -> {
            // TODO Auto-generated method stub
            if (servicioCulturalSeleccionado){
                //abrirPopMenu(position);
            }else{
                //abrirPopMenuRestaurante(position);
            }
            return false;
        });
    }

    private void abrirPopMenu(int position) {
        Intent intent=new Intent(App.this,PopUpActividadCultural.class);
        if(pantallaManana){
            intent.putExtra("nombre", lista_GridView_Manana.get(position).getTitulo());
            intent.putExtra("descripcion", lista_GridView_Manana.get(position).getDescription());
            intent.putExtra("localizacion", lista_GridView_Manana.get(position).getLocalizacion());
            intent.putExtra("precio", lista_GridView_Manana.get(position).getPrecio());
            intent.putExtra("fecha", lista_GridView_Manana.get(position).getFecha());
        }
        if (pantallaTarde){
            intent.putExtra("nombre", lista_GridView_Tarde.get(position).getTitulo());
            intent.putExtra("descripcion", lista_GridView_Tarde.get(position).getDescription());
            intent.putExtra("localizacion", lista_GridView_Tarde.get(position).getLocalizacion());
            intent.putExtra("precio", lista_GridView_Tarde.get(position).getPrecio());
            intent.putExtra("fecha", lista_GridView_Tarde.get(position).getFecha());
        }

        startActivity(intent);
    }

    //Restaurantes

    private void recogerRestaurantes(){
        listaRestaurantes=bbddRestaurantes.getListaRestaurantes();
    }

    private void mostrarRestaurantes(){
        mostrarGridViewComidas();
    }

    private void mostrarGridViewComidas() {
        //gridView.setVisibility(View.GONE);
        adaptadorPersonalizadoRestaurante=new AdaptadorPersonalizadoRestaurante(this,R.layout.list_item_layout_servicio2,listaRestaurantes);
        gridView.setAdapter(adaptadorPersonalizadoRestaurante);
    }



    private void abrirPopMenuRestaurante(int position) {
        Intent intent=new Intent(App.this,PopUpRestaurante.class);

        intent.putExtra("nombre", listaRestaurantes.get(position).getNombre());
        intent.putExtra("localizacion", listaRestaurantes.get(position).getLocalizacion());
        intent.putExtra("precio", listaRestaurantes.get(position).getPrecio());
        intent.putExtra("distrito", listaRestaurantes.get(position).getDistrito());
        intent.putExtra("productos", listaRestaurantes.get(position).getProductos());
        intent.putExtra("puntuacion", listaRestaurantes.get(position).getPuntuacion());
        startActivity(intent);
    }

    private void comprobarBotones() {
        botonManana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantallaManana=true;
                pantallaComida=false;
                pantallaTarde=false;
                pantallaCena=false;
                botonManana.setImageResource(R.mipmap.ic_logo_manana_pulsado_foreground);
                botonComida.setImageResource(R.mipmap.ic_logo_comida_foreground);
                botonTarde.setImageResource(R.mipmap.ic_logo_tarde1_foreground);
                botonCena.setImageResource(R.mipmap.ic_logo_cena_foreground);
                servicioCulturalSeleccionado=true;
                mostrarGridView();
                //crearListViewAdaptado();
            }
        });

        botonComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primerInicio=false;
                pantallaManana=false;
                pantallaComida=true;
                pantallaTarde=false;
                pantallaCena=false;
                botonManana.setImageResource(R.mipmap.ic_logo_manana1_foreground);
                botonComida.setImageResource(R.mipmap.ic_logo_comida_pulsado_foreground);
                botonTarde.setImageResource(R.mipmap.ic_logo_tarde1_foreground);
                botonCena.setImageResource(R.mipmap.ic_logo_cena_foreground);
                servicioCulturalSeleccionado=false;
                mostrarRestaurantes();
            }
        });

        botonTarde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primerInicio=false;
                pantallaManana=false;
                pantallaComida=false;
                pantallaTarde=true;
                pantallaCena=false;
                botonManana.setImageResource(R.mipmap.ic_logo_manana1_foreground);
                botonComida.setImageResource(R.mipmap.ic_logo_comida_foreground);
                botonTarde.setImageResource(R.mipmap.ic_logo_tarde_pulsado_foreground);
                botonCena.setImageResource(R.mipmap.ic_logo_cena_foreground);
                servicioCulturalSeleccionado=true;
                mostrarGridView();
                //crearListViewAdaptado();
            }
        });

        botonCena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primerInicio=false;
                pantallaManana=false;
                pantallaComida=false;
                pantallaTarde=false;
                pantallaCena=true;
                botonManana.setImageResource(R.mipmap.ic_logo_manana1_foreground);
                botonComida.setImageResource(R.mipmap.ic_logo_comida_foreground);
                botonTarde.setImageResource(R.mipmap.ic_logo_tarde1_foreground);
                botonCena.setImageResource(R.mipmap.ic_logo_cena_pulsado_foreground);
                servicioCulturalSeleccionado=false;
                mostrarRestaurantes();
            }
        });
    }

    private void escogerServiciosRestaurantesAutomaticos() {
        if (fechaEscogida.equals("")){
            Toast.makeText(App.this, "POR FAVOR, ESCOJA UNA FECHA", Toast.LENGTH_SHORT).show();
        }else{
            crearGridViewAdaptado();
            if (lista_GridView.size()==0){
                Toast.makeText(App.this, "NO ES POSIBLE, SELECCIONE OTRA FECHA", Toast.LENGTH_SHORT).show();
            }else{
                int numeroAleatorio=0;
                lista_GridView_Manana.clear();
                for (int j=0;j<lista_GridView.size();j++){
                    if (Integer.parseInt(separarHora(lista_GridView.get(j).getFecha()))<14 && Integer.parseInt(separarHora(lista_GridView.get(j).getFecha()))!=0 ){
                        lista_GridView_Manana.add(lista_GridView.get(j));
                    }
                }
                numeroAleatorio= (int)(Math.random()*lista_GridView_Manana.size()+0);
                nombreServicioMananaSolicitado=lista_GridView_Manana.get(numeroAleatorio).getTitulo();

                numeroAleatorio= (int)(Math.random()*listaRestaurantes.size()+0);
                nombreRestauranteComidaSolicitado=listaRestaurantes.get(numeroAleatorio).getNombre();

                lista_GridView_Tarde.clear();
                for (int j=0;j<lista_GridView.size();j++){
                    if (Integer.parseInt(separarHora(lista_GridView.get(j).getFecha()))<14 && Integer.parseInt(separarHora(lista_GridView.get(j).getFecha()))!=0 ){
                        lista_GridView_Tarde.add(lista_GridView.get(j));
                    }
                }
                numeroAleatorio= (int)(Math.random()*lista_GridView_Tarde.size()+0);
                nombreServicioTardeSolicitado=lista_GridView_Tarde.get(numeroAleatorio).getTitulo();

                numeroAleatorio= (int)(Math.random()*listaRestaurantes.size()+0);
                nombreRestauranteCenaSolicitado=listaRestaurantes.get(numeroAleatorio).getNombre();
            }


        }
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
                adaptadorPersonalizadoServicio.notifyDataSetChanged();
                return true;
            case R.id.idButtonMenuActualizar:
                //recogerTodosLosServicios();
                actualizarGridView();
                return true;
            case R.id.idButtonMenuGuardar:
                Toast.makeText(App.this, "GUARDAR", Toast.LENGTH_SHORT).show();
                recogerValoresGuardar();
                PaginaGuardar paginaGuardar=new PaginaGuardar(tituloManana, descriptionManana, fechaManana, localizacionManana, precioManana, nombreComida, localizacionComida, precioComida, distritoComida, productosComida, puntuacionComida, tituloTarde, descriptionTarde, fechaTarde, localizacionTarde, precioTarde, nombreCena, localizacionCena, precioCena, distritoCena, productosCena, puntuacionCena);
                return true;
            case R.id.idButtonMenuAutomatico:
                escogerServiciosRestaurantesAutomaticos();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void actualizarGridView() {
        crearGridViewAdaptado();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater2 =getMenuInflater();
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) menuInfo;
        inflater2.inflate(R.menu.menu_pulsacion,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.idButtonMenuPulsacionSolicitar:
                if (pantallaManana){
                    nombreServicioMananaSolicitado=lista_GridView.get(info.position).getTitulo();
                }
                if (pantallaComida){
                    nombreRestauranteComidaSolicitado=listaRestaurantes.get(info.position).getNombre();
                }
                if (pantallaTarde){
                    nombreServicioTardeSolicitado=lista_GridView.get(info.position).getTitulo();
                }
                if (pantallaComida){
                    nombreRestauranteCenaSolicitado=listaRestaurantes.get(info.position).getNombre();
                }

                return true;
        }
        return super.onContextItemSelected(item);
    }


    //Recoger Valores Guardar sirve para una vez tengamos los servicios solicitados este elimine los
    // carcateres invalidos (',(),/,...) que no se pueden añadir en la barra de busqueda a la hora de guardar
    // todos los servicios seleccionados en la base de datos.
    private void recogerValoresGuardar() {
        if (nombreServicioMananaSolicitado!=null){
            for (int i=0;i<lista_GridView.size();i++){
                if (lista_GridView.get(i).getTitulo().equals(nombreServicioMananaSolicitado)){

                    String resultado=lista_GridView.get(i).getTitulo();
                    if (!resultado.equals("") && resultado!=null){
                        tituloManana=resultado.replaceAll("\\s+","_");
                        resultado=tituloManana;
                        tituloManana=resultado.replaceAll("/","_");
                        resultado=tituloManana;
                        tituloManana=resultado.replaceAll("'","_");
                        resultado=tituloManana;
                        tituloManana=resultado.replaceAll("\\(","_");
                        resultado=tituloManana;
                        tituloManana=resultado.replaceAll("\\)","_");
                        resultado=tituloManana;
                        tituloManana=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(tituloManana, Normalizer.Form.NFD);
                        tituloManana = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        tituloManana="nada";
                    }

                    resultado=lista_GridView.get(i).getDescription();
                    if (!resultado.equals("") && resultado!=null){
                        descriptionManana=resultado.replaceAll("\\s+","_");
                        resultado=descriptionManana;
                        descriptionManana=resultado.replaceAll("/","_");
                        resultado=descriptionManana;
                        descriptionManana=resultado.replaceAll("'","_");
                        resultado=descriptionManana;
                        descriptionManana=resultado.replaceAll("\\(","_");
                        resultado=descriptionManana;
                        descriptionManana=resultado.replaceAll("\\)","_");
                        resultado=descriptionManana;
                        descriptionManana=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(descriptionManana, Normalizer.Form.NFD);
                        descriptionManana = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        descriptionManana="nada";
                    }

                    resultado=lista_GridView.get(i).getFecha();
                    if (!resultado.equals("") && resultado!=null){
                        fechaManana=resultado.replaceAll("\\s+","_");
                        resultado=fechaManana;
                        fechaManana=resultado.replaceAll("/","_");
                        resultado=fechaManana;
                        fechaManana=resultado.replaceAll("'","_");
                        resultado=fechaManana;
                        fechaManana=resultado.replaceAll("\\(","_");
                        resultado=fechaManana;
                        fechaManana=resultado.replaceAll("\\)","_");
                        resultado=fechaManana;
                        fechaManana=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(fechaManana, Normalizer.Form.NFD);
                        fechaManana = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        fechaManana="nada";
                    }

                    String resultado2=lista_GridView.get(i).getLocalizacion();
                    if (!resultado.equals("") && resultado!=null){
                        localizacionManana=resultado.replaceAll("\\s+","_");
                        resultado=localizacionManana;
                        localizacionManana=resultado.replaceAll("/","_");
                        resultado=localizacionManana;
                        localizacionManana=resultado.replaceAll("'","_");
                        resultado=localizacionManana;
                        localizacionManana=resultado.replaceAll("\\(","_");
                        resultado=localizacionManana;
                        localizacionManana=resultado.replaceAll("\\)","_");
                        resultado=localizacionManana;
                        localizacionManana=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(localizacionManana, Normalizer.Form.NFD);
                        localizacionManana = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        localizacionManana="nada";
                    }
                    if (localizacionManana.equals("")){
                        localizacionManana="nada";
                    }

                    resultado=lista_GridView.get(i).getPrecio();
                    if (!resultado.equals("") && resultado!=null){
                        precioManana=resultado.replaceAll("\\s+","_");
                        resultado=precioManana;
                        precioManana=resultado.replaceAll("/","_");
                        resultado=precioManana;
                        precioManana=resultado.replaceAll("'","_");
                        resultado=precioManana;
                        precioManana=resultado.replaceAll("\\(","_");
                        resultado=precioManana;
                        precioManana=resultado.replaceAll("\\)","_");
                        resultado=precioManana;
                        precioManana=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(precioManana, Normalizer.Form.NFD);
                        precioManana = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        precioManana="nada";
                    }
                }
            }
        }
        if (nombreRestauranteComidaSolicitado!=null){
            for (int i=0;i<listaRestaurantes.size();i++){
                if (listaRestaurantes.get(i).getNombre().equals(nombreRestauranteComidaSolicitado)){

                    String resultado=listaRestaurantes.get(i).getNombre();
                    if (!resultado.equals("") && resultado!=null){
                        nombreComida=resultado.replaceAll("\\s+","_");
                        resultado=nombreComida;
                        nombreComida=resultado.replaceAll("/","_");
                        resultado=nombreComida;
                        nombreComida=resultado.replaceAll("'","_");
                        resultado=nombreComida;
                        nombreComida=resultado.replaceAll("\\(","_");
                        resultado=nombreComida;
                        nombreComida=resultado.replaceAll("\\)","_");
                        resultado=nombreComida;
                        nombreComida=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(nombreComida, Normalizer.Form.NFD);
                        nombreComida = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        nombreComida="nada";
                    }

                    resultado=listaRestaurantes.get(i).getLocalizacion();
                    if (!resultado.equals("") && resultado!=null){
                        localizacionComida=resultado.replaceAll("\\s+","_");
                        resultado=localizacionComida;
                        localizacionComida=resultado.replaceAll("/","_");
                        resultado=localizacionComida;
                        localizacionComida=resultado.replaceAll("'","_");
                        resultado=localizacionComida;
                        localizacionComida=resultado.replaceAll("\\(","_");
                        resultado=localizacionComida;
                        localizacionComida=resultado.replaceAll("\\)","_");
                        resultado=localizacionComida;
                        localizacionComida=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(localizacionComida, Normalizer.Form.NFD);
                        localizacionComida = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        localizacionComida="nada";
                    }

                    resultado=listaRestaurantes.get(i).getPrecio();
                    if (!resultado.equals("") && resultado!=null){
                        precioComida=resultado.replaceAll("\\s+","_");
                        resultado=precioComida;
                        precioComida=resultado.replaceAll("/","_");
                        resultado=precioComida;
                        precioComida=resultado.replaceAll("'","_");
                        resultado=precioComida;
                        precioComida=resultado.replaceAll("\\(","_");
                        resultado=precioComida;
                        precioComida=resultado.replaceAll("\\)","_");
                        resultado=precioComida;
                        precioComida=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(precioComida, Normalizer.Form.NFD);
                        precioComida = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        precioComida="nada";
                    }

                    resultado=listaRestaurantes.get(i).getDistrito();
                    if (!resultado.equals("") && resultado!=null){
                        distritoComida=resultado.replaceAll("\\s+","_");
                        resultado=distritoComida;
                        distritoComida=resultado.replaceAll("/","_");
                        resultado=distritoComida;
                        distritoComida=resultado.replaceAll("'","_");
                        resultado=distritoComida;
                        distritoComida=resultado.replaceAll("\\(","_");
                        resultado=distritoComida;
                        distritoComida=resultado.replaceAll("\\)","_");
                        resultado=distritoComida;
                        distritoComida=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(distritoComida, Normalizer.Form.NFD);
                        distritoComida = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        distritoComida="nada";
                    }

                    resultado=listaRestaurantes.get(i).getProductos();
                    if (!resultado.equals("") && resultado!=null){
                        productosComida=resultado.replaceAll("\\s+","_");
                        resultado=productosComida;
                        productosComida=resultado.replaceAll("/","_");
                        resultado=productosComida;
                        productosComida=resultado.replaceAll("'","_");
                        resultado=productosComida;
                        productosComida=resultado.replaceAll("\\(","_");
                        resultado=productosComida;
                        productosComida=resultado.replaceAll("\\)","_");
                        resultado=productosComida;
                        productosComida=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(productosComida, Normalizer.Form.NFD);
                        productosComida = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        productosComida="nada";
                    }

                    resultado=listaRestaurantes.get(i).getProductos();
                    if (!resultado.equals("") && resultado!=null){
                        puntuacionComida=resultado.replaceAll("\\s+","_");
                        resultado=puntuacionComida;
                        puntuacionComida=resultado.replaceAll("/","_");
                        resultado=puntuacionComida;
                        puntuacionComida=resultado.replaceAll("'","_");
                        resultado=puntuacionComida;
                        puntuacionComida=resultado.replaceAll("\\(","_");
                        resultado=puntuacionComida;
                        puntuacionComida=resultado.replaceAll("\\)","_");
                        resultado=puntuacionComida;
                        puntuacionComida=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(puntuacionComida, Normalizer.Form.NFD);
                        puntuacionComida = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        puntuacionComida="nada";
                    }

                }
            }
        }
        if (nombreServicioTardeSolicitado!=null){
            for (int i=0;i<lista_GridView.size();i++){
                if (lista_GridView.get(i).getTitulo().equals(nombreServicioTardeSolicitado)){

                    String resultado=lista_GridView.get(i).getTitulo();
                    if (!resultado.equals("") && resultado!=null){
                        tituloTarde=resultado.replaceAll("\\s+","_");
                        resultado=tituloTarde;
                        tituloTarde=resultado.replaceAll("/","_");
                        resultado=tituloTarde;
                        tituloTarde=resultado.replaceAll("'","_");
                        resultado=tituloTarde;
                        tituloTarde=resultado.replaceAll("\\(","_");
                        resultado=tituloTarde;
                        tituloTarde=resultado.replaceAll("\\)","_");
                        resultado=tituloTarde;
                        tituloTarde=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(tituloTarde, Normalizer.Form.NFD);
                        tituloTarde = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        tituloTarde="nada";
                    }

                    resultado=lista_GridView.get(i).getDescription();
                    if (!resultado.equals("") && resultado!=null){
                        descriptionTarde=resultado.replaceAll("\\s+","_");
                        resultado=descriptionTarde;
                        descriptionTarde=resultado.replaceAll("/","_");
                        resultado=descriptionTarde;
                        descriptionTarde=resultado.replaceAll("'","_");
                        resultado=descriptionTarde;
                        descriptionTarde=resultado.replaceAll("\\(","_");
                        resultado=descriptionTarde;
                        descriptionTarde=resultado.replaceAll("\\)","_");
                        resultado=descriptionTarde;
                        descriptionTarde=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(descriptionTarde, Normalizer.Form.NFD);
                        descriptionTarde = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        descriptionTarde="nada";
                    }

                    resultado=lista_GridView.get(i).getFecha();
                    if (!resultado.equals("") && resultado!=null){
                        fechaTarde=resultado.replaceAll("\\s+","_");
                        resultado=fechaTarde;
                        fechaTarde=resultado.replaceAll("/","_");
                        resultado=fechaTarde;
                        fechaTarde=resultado.replaceAll("'","_");
                        resultado=fechaTarde;
                        fechaTarde=resultado.replaceAll("\\(","_");
                        resultado=fechaTarde;
                        fechaTarde=resultado.replaceAll("\\)","_");
                        resultado=fechaTarde;
                        fechaTarde=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(fechaTarde, Normalizer.Form.NFD);
                        fechaTarde = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        fechaTarde="nada";
                    }

                    String resultado2=lista_GridView.get(i).getLocalizacion();
                    if (!resultado.equals("") && resultado!=null){
                        localizacionTarde=resultado.replaceAll("\\s+","_");
                        resultado=localizacionTarde;
                        localizacionTarde=resultado.replaceAll("/","_");
                        resultado=localizacionTarde;
                        localizacionTarde=resultado.replaceAll("'","_");
                        resultado=localizacionTarde;
                        localizacionTarde=resultado.replaceAll("\\(","_");
                        resultado=localizacionTarde;
                        localizacionTarde=resultado.replaceAll("\\)","_");
                        resultado=localizacionTarde;
                        localizacionTarde=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(localizacionTarde, Normalizer.Form.NFD);
                        localizacionTarde = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        localizacionTarde="nada";
                    }
                    if (localizacionTarde.equals("")){
                        localizacionTarde="nada";
                    }

                    resultado=lista_GridView.get(i).getPrecio();
                    if (!resultado.equals("") && resultado!=null){
                        precioTarde=resultado.replaceAll("\\s+","_");
                        resultado=precioTarde;
                        precioTarde=resultado.replaceAll("/","_");
                        resultado=precioTarde;
                        precioTarde=resultado.replaceAll("'","_");
                        resultado=precioTarde;
                        precioTarde=resultado.replaceAll("\\(","_");
                        resultado=precioTarde;
                        precioTarde=resultado.replaceAll("\\)","_");
                        resultado=precioTarde;
                        precioTarde=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(precioTarde, Normalizer.Form.NFD);
                        precioTarde = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        precioTarde="nada";
                    }
                }
            }
        }
        if (nombreRestauranteCenaSolicitado!=null){
            for (int i=0;i<listaRestaurantes.size();i++){
                if (listaRestaurantes.get(i).getNombre().equals(nombreRestauranteCenaSolicitado)){

                    String resultado=listaRestaurantes.get(i).getNombre();
                    if (!resultado.equals("") && resultado!=null){
                        nombreCena=resultado.replaceAll("\\s+","_");
                        resultado=nombreCena;
                        nombreCena=resultado.replaceAll("/","_");
                        resultado=nombreCena;
                        nombreCena=resultado.replaceAll("'","_");
                        resultado=nombreCena;
                        nombreCena=resultado.replaceAll("\\(","_");
                        resultado=nombreCena;
                        nombreCena=resultado.replaceAll("\\)","_");
                        resultado=nombreCena;
                        nombreCena=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(nombreCena, Normalizer.Form.NFD);
                        nombreCena = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        nombreCena="nada";
                    }

                    resultado=listaRestaurantes.get(i).getLocalizacion();
                    if (!resultado.equals("") && resultado!=null){
                        localizacionCena=resultado.replaceAll("\\s+","_");
                        resultado=localizacionCena;
                        localizacionCena=resultado.replaceAll("/","_");
                        resultado=localizacionCena;
                        localizacionCena=resultado.replaceAll("'","_");
                        resultado=localizacionCena;
                        localizacionCena=resultado.replaceAll("\\(","_");
                        resultado=localizacionCena;
                        localizacionCena=resultado.replaceAll("\\)","_");
                        resultado=localizacionCena;
                        localizacionCena=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(localizacionCena, Normalizer.Form.NFD);
                        localizacionCena = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        localizacionCena="nada";
                    }

                    resultado=listaRestaurantes.get(i).getPrecio();
                    if (!resultado.equals("") && resultado!=null){
                        precioCena=resultado.replaceAll("\\s+","_");
                        resultado=precioCena;
                        precioCena=resultado.replaceAll("/","_");
                        resultado=precioCena;
                        precioCena=resultado.replaceAll("'","_");
                        resultado=precioCena;
                        precioCena=resultado.replaceAll("\\(","_");
                        resultado=precioCena;
                        precioCena=resultado.replaceAll("\\)","_");
                        resultado=precioCena;
                        precioCena=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(precioCena, Normalizer.Form.NFD);
                        precioCena = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        precioCena="nada";
                    }

                    resultado=listaRestaurantes.get(i).getDistrito();
                    if (!resultado.equals("") && resultado!=null){
                        distritoCena=resultado.replaceAll("\\s+","_");
                        resultado=distritoCena;
                        distritoCena=resultado.replaceAll("/","_");
                        resultado=distritoCena;
                        distritoCena=resultado.replaceAll("'","_");
                        resultado=distritoCena;
                        distritoCena=resultado.replaceAll("\\(","_");
                        resultado=distritoCena;
                        distritoCena=resultado.replaceAll("\\)","_");
                        resultado=distritoCena;
                        distritoCena=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(distritoCena, Normalizer.Form.NFD);
                        distritoCena = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        distritoCena="nada";
                    }

                    resultado=listaRestaurantes.get(i).getProductos();
                    if (!resultado.equals("") && resultado!=null){
                        productosCena=resultado.replaceAll("\\s+","_");
                        resultado=productosCena;
                        productosCena=resultado.replaceAll("/","_");
                        resultado=productosCena;
                        productosCena=resultado.replaceAll("'","_");
                        resultado=productosCena;
                        productosCena=resultado.replaceAll("\\(","_");
                        resultado=productosCena;
                        productosCena=resultado.replaceAll("\\)","_");
                        resultado=productosCena;
                        productosCena=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(productosCena, Normalizer.Form.NFD);
                        productosCena = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        productosCena="nada";
                    }

                    resultado=listaRestaurantes.get(i).getProductos();
                    if (!resultado.equals("") && resultado!=null){
                        puntuacionCena=resultado.replaceAll("\\s+","_");
                        resultado=puntuacionCena;
                        puntuacionCena=resultado.replaceAll("/","_");
                        resultado=puntuacionCena;
                        puntuacionCena=resultado.replaceAll("'","_");
                        resultado=puntuacionCena;
                        puntuacionCena=resultado.replaceAll("\\(","_");
                        resultado=puntuacionCena;
                        puntuacionCena=resultado.replaceAll("\\)","_");
                        resultado=puntuacionCena;
                        puntuacionCena=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(puntuacionCena, Normalizer.Form.NFD);
                        puntuacionCena = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        puntuacionCena="nada";
                    }
                }
            }
        }
    }
}
