package com.example.proyectoprueba1quehago.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba1quehago.Adaptadores.AdaptadorPersonalizadoNombreDistrito;
import com.example.proyectoprueba1quehago.Adaptadores.AdaptadorPersonalizadoServicio;
import com.example.proyectoprueba1quehago.R;

import java.util.ArrayList;

public class PaginaDistrito extends AppCompatActivity {

    private ListView listView;
    private String[] listaDistritos;
    private ArrayList<String> listaDistritos2;
    private AdaptadorPersonalizadoNombreDistrito adaptadorPersonalizadoNombreDistrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distrito);

        listView=findViewById(R.id.id_listvew_pagDistrito);
        listaDistritos = new String[] {"ARGANZUELA","BARAJAS","CARABANCHEL","CENTRO","CHAMARTIN","CHAMBERI","CIUDAD LINEAL","FUENCARRAL-EL PARDO","HORTALEZA","LATINA","MONCLOA-ARAVACA","MORATALAZ","PUENTE DE VALLECAS","RETIRO","SALAMANCA","SAN BLAS-CANILLEJAS","TETUAN","USERA","VICALVARO","VILLAVERDE","VILLA DE VALLECAS"};
        listaDistritos2=new ArrayList<String>();
        for (int i=0;i<listaDistritos.length;i++){
            listaDistritos2.add(listaDistritos[i]);
        }
        crearListView();
        seleccionaDistrito();

    }

    public void seleccionaDistrito(){
        final String[] distrito = {""};
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // TODO Auto-generated method stub
            distrito[0] =(String)  listaDistritos2.get(position).toString();
            Intent intent= new Intent(PaginaDistrito.this,App.class);
            intent.putExtra("distrito",distrito[0]);
            startActivity(intent);
        });
    }
    private void crearListView() {
        adaptadorPersonalizadoNombreDistrito=new AdaptadorPersonalizadoNombreDistrito(this,R.layout.list_item_layout_distrito,listaDistritos2);
        listView.setAdapter(adaptadorPersonalizadoNombreDistrito);
    }


}
