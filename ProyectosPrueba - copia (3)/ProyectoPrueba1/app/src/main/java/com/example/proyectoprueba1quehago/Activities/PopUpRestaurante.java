package com.example.proyectoprueba1quehago.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba1quehago.R;

public class PopUpRestaurante extends AppCompatActivity {
    private TextView nombre;
    private TextView localizacion;
    private TextView precio;
    private TextView distrito;
    private TextView productos;
    private TextView puntuacion;
    private Button boton;



    private String nombreString;
    private String localizacionString;
    private String distritoString;
    private String precioString;
    private String productosString;
    private String puntuacionString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popmenu_restaurante);

        nombre=findViewById(R.id.idTextViewPopMenuNombreRes);
        localizacion=findViewById(R.id.idTextViewPopMenuLocalizacionRes);
        distrito=findViewById(R.id.idTextViewServicioNombreDistritoRes);
        precio=findViewById(R.id.idTextViewPopMenuPrecioRes);
        productos=findViewById(R.id.idTextViewPopMenuProductosRes);
        puntuacion=findViewById(R.id.idTextViewPopMenuPuntuacionRes);
        //boton=findViewById(R.id.idButtonAñadirACRes);


        Bundle bundle=getIntent().getExtras();
        if (bundle!=null && bundle.getString("nombre")!=null){
            nombreString=bundle.getString("nombre");
            localizacionString=bundle.getString("localizacion");
            distritoString=bundle.getString("distrito");
            precioString=bundle.getString("precio");
            puntuacionString=bundle.getString("productos");
            puntuacionString=bundle.getString("puntuacion");


            nombre.setText("Nombre: "+nombreString);
            localizacion.setText("Localizacion: "+localizacionString);
            distrito.setText("Distrito: "+distritoString);
            precio.setText("Precio: "+precioString);
            productos.setText("Productos: "+productosString);
            puntuacion.setText("Puntuacion: "+puntuacionString);

        }else{
            Toast.makeText(PopUpRestaurante.this, "ERROR POPMENU", Toast.LENGTH_SHORT).show();
        }

        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho=medidasVentana.widthPixels;
        int alto=medidasVentana.heightPixels;

        getWindow().setLayout((int) (ancho*0.65),(int) (alto*0.45));
    }

    public void solicitarRestaurante(View view) {
        Intent intent=new Intent(PopUpRestaurante.this,App.class);
        intent.putExtra("nombreRestauranteSolicitado",nombreString);
        startActivity(intent);
    }

    public void regresarAppRestaurante(View view) {
        Intent intent=new Intent(PopUpRestaurante.this,App.class);
        startActivity(intent);
    }
}
