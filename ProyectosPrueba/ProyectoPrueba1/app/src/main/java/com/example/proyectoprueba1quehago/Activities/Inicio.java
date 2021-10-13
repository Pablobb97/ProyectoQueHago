package com.example.proyectoprueba1quehago.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba1quehago.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Inicio extends AppCompatActivity {
    private Button botonInicio;
    private Button botonRegistro;
    private EditText nombre_usuario;
    private EditText contrasena;
    private String JsonCompleto;
    private boolean inicioCorrecto=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_registro);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        recogerValores();
        comporbarBotones();
        //iniciarVista();
    }

    private void recogerValores() {
        nombre_usuario=findViewById(R.id.editTextLogin);
        contrasena=findViewById(R.id.editTextContrasena);
    }

    private void comporbarBotones() {
        botonInicio=findViewById(R.id.idButtonInicioSesion);
        botonRegistro=findViewById(R.id.idButtonRegistro);

        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Inicio.this, "Has Pulsado Inicio", Toast.LENGTH_SHORT).show();
                if (comporbacionInicio()){
                    iniciarApp();
                }
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

    private void iniciarApp() {
        Intent intent= new Intent(Inicio.this,App.class);
        startActivity(intent);
    }

    private boolean comporbacionInicio(){
        /*
        if (comprobacionUsuarioExiste()){
            URL url;
            HttpURLConnection conexion = null;

            try {
                url = new URL("http://localhost/Android/AppPabloProyectoQueHago/public/login/"+nombre_usuario.getText()+"/"+contrasena.getText());
                System.out.println("************"+url+"************");
                conexion = (HttpURLConnection) url.openConnection();


                Toast.makeText(Inicio.this, "line1", Toast.LENGTH_SHORT).show();
                //BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                InputStreamReader in = new InputStreamReader(conexion.getInputStream());
                Toast.makeText(Inicio.this, "line2", Toast.LENGTH_SHORT).show();
                BufferedReader br = new BufferedReader(in);

                String line = br.readLine();
                System.out.println("------------------------------------------------"+line+"------------------------------------------------");
                Toast.makeText(Inicio.this, line, Toast.LENGTH_SHORT).show();
                Log.d("HTTP-GET", line);
                JsonCompleto=line;
                if (line=="11"){
                    inicioCorrecto=true;
                }else{
                    inicioCorrecto=false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conexion != null) {
                    conexion.disconnect();
                }
            }
        }
        */

        inicioCorrecto=true;


        return inicioCorrecto;
    }

    private boolean comprobacionUsuarioExiste() {
        boolean resultado=true;
        return resultado;
    }

    private void comprobarInicio2(){

    }
}
