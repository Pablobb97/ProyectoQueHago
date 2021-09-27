package com.example.proyectoprueba1quehago.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba1quehago.R;

public class Inicio extends AppCompatActivity {
    private Button botonInicio;
    private Button botonRegistro;
    private EditText nombreUsuario;
    private EditText contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_registro);
        comporbarBotones();
        //iniciarVista();
    }

    private void comporbarBotones() {
        botonInicio=findViewById(R.id.idButtonInicioSesion);
        botonRegistro=findViewById(R.id.idButtonRegistro);

        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Inicio.this, "Has Pulsado Inicio", Toast.LENGTH_SHORT).show();
            }
        });

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Inicio.this, "Has Pulsado Registro", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Inicio.this,Registro.class);
                startActivity(intent);
            }
        });
    }
    public void iniciarVista() {
    }

    /*public Inicio(Button botonInicio, Button botonRegistro, EditText nombreUsuario, EditText contrasena) {
        this.botonInicio = botonInicio;
        this.botonRegistro = botonRegistro;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }*/

    public void ComporbacionInicio(){

    }
}
