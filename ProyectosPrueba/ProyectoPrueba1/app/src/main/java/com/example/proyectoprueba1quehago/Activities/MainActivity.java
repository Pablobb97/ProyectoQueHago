package com.example.proyectoprueba1quehago.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyectoprueba1quehago.R;

public class MainActivity extends AppCompatActivity {
    private Inicio inicio;

    private int num;

    private Button botonInicio;
    private Button botonRegistro;
    private EditText nombreUsuario;
    private EditText contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ponerLogo();
        inicioAleatorio();

        //recogerElementos();
        //regogerDatosElementos();
        //crearInicio();
        //recogerDatos();

        //comporbarBotones();
    }

    private void ponerLogo() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_logo);
    }


    private void recogerDatos() {
    }

    /*private void crearInicio() {
        inicio = new Inicio(botonInicio,botonRegistro,nombreUsuario,contrasena);
    }*/

    private void regogerDatosElementos() {

    }

    private void recogerElementos() {
        botonInicio=findViewById(R.id.idButtonInicioSesion);
        botonRegistro=findViewById(R.id.idButtonRegistro);
        nombreUsuario=findViewById(R.id.editTextLogin);
        contrasena=findViewById(R.id.editTextContrasena);
    }

    private void inicioAleatorio() {
        num=1;
        if (num==1){
            //setContentView(R.layout.activity_inicio_registro);
            Intent intente= new Intent(MainActivity.this,Inicio.class);
            startActivity(intente);
        }else{
            setContentView(R.layout.activity_main);
        }
    }
}