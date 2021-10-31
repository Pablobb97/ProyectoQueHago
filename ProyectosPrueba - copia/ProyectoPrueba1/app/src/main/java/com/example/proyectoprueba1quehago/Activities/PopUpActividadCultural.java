package com.example.proyectoprueba1quehago.Activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba1quehago.R;

import java.util.ArrayList;

public class PopUpActividadCultural extends AppCompatActivity {
    private TextView nombre;
    private TextView descripcion;
    private TextView localizacion;
    private TextView precio;
    private TextView fecha;
    private Button boton;


    private String nombreString;
    private String descripcionString;
    private String localizacionString;
    private String precioString;
    private String fechaString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popmenu_actividad_cultual);

        nombre=findViewById(R.id.idTextViewPopMenuNombre);
        descripcion=findViewById(R.id.idTextViewPopMenuDescripcion);
        localizacion=findViewById(R.id.idTextViewPopMenuLocalizacion);
        precio=findViewById(R.id.idTextViewPopMenuPrecio);
        fecha=findViewById(R.id.idTextViewPopMenuFecha);
        boton=findViewById(R.id.idButtonAñadirAC);


        Bundle bundle=getIntent().getExtras();
        if (bundle!=null && bundle.getString("nombre")!=null){
            nombreString=bundle.getString("nombre");
            descripcionString=bundle.getString("descripcion");
            localizacionString=bundle.getString("localizacion");
            precioString=bundle.getString("precio");
            fechaString=bundle.getString("fecha");

            if (precioString=="1"){
                precioString="GRATIS";
            }

            nombre.setText(nombreString);
            descripcion.setText(descripcionString);
            precio.setText(precioString);
            localizacion.setText(localizacionString);
            fecha.setText(fechaString);

        }else{
            Toast.makeText(PopUpActividadCultural.this, "ERROR POPMENU", Toast.LENGTH_SHORT).show();
        }

        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho=medidasVentana.widthPixels;
        int alto=medidasVentana.heightPixels;

        getWindow().setLayout((int) (ancho*0.65),(int) (alto*0.45));
    }
}
