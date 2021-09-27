package com.example.proyectoprueba1quehago.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba1quehago.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Registro extends AppCompatActivity {
    private EditText et_nombre_y_apellidos;
    private EditText et_nombre_usuario;
    private EditText et_contrasena;
    private EditText et_email;
    private EditText et_fecha_nacimiento;

    private Editable nombre_y_apellidos;
    private Editable nombre_usuario;
    private Editable contrasena;
    private Editable email;
    private Editable fecha_nacimiento;

    private Button boton_Registrarse;

    private String JsonCompleto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        recoger_datos();
        pulsarBotonRegistro();
        enviar_BBDD();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    private void pulsarBotonRegistro() {
        boton_Registrarse = findViewById(R.id.idBotonRegistrarseRegistro);

        boton_Registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_nombre_usuario = findViewById(R.id.editTextTextNombreUsuario);
                nombre_usuario = et_nombre_usuario.getText();
                Toast.makeText(Registro.this, nombre_usuario, Toast.LENGTH_SHORT).show();
                boton_Registrarse.setTextColor(Color.parseColor("#9E9E9E"));
                et_nombre_usuario.setText(nombre_usuario);
            }
        });
    }

    private void enviar_BBDD() {
        //CREAR JSON
        //JsonCompleto = nombre_y_apellidos+nombre_usuario+contrasena+email+fecha_nacimiento;
        URL url;
        HttpURLConnection conexion = null;

        try {
            url = new URL("http://10.0.2.2:80/Android/AppAndroid1/public/info/"+nombre_usuario+"/"+nombre_usuario+"/"+contrasena+"/"+email+"/"+fecha_nacimiento);
            System.out.println("************"+url+"************");
            conexion = (HttpURLConnection) url.openConnection();


            InputStreamReader in = new InputStreamReader(conexion.getInputStream());
            BufferedReader br = new BufferedReader(in);

            String line = br.readLine();
            System.out.println("------------------------------------------------"+line+"------------------------------------------------");
            Log.d("HTTP-GET", line);
            JsonCompleto=line;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                conexion.disconnect();
            }
        }
    }

    private void recoger_datos() {
        et_nombre_y_apellidos = findViewById(R.id.editTextTextNombreApellido);
        et_nombre_usuario = findViewById(R.id.editTextTextNombreUsuario);
        et_contrasena = findViewById(R.id.editTextContrasena);
        et_email = findViewById(R.id.editTextTextEmailAddress);
        et_fecha_nacimiento = findViewById(R.id.editTextDate);
        boton_Registrarse = findViewById(R.id.idBotonRegistrarseRegistro);

        nombre_usuario = et_nombre_usuario.getText();


    }
}
