package com.example.proyectoprueba1quehago.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.proyectoprueba1quehago.Activities.ActividadCulturalExtensa;
import com.example.proyectoprueba1quehago.Activities.Restaurante;
import com.example.proyectoprueba1quehago.R;

import java.util.ArrayList;

public class AdaptadorPersonalizadoRestaurante extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Restaurante> listaRestaurantes;

    public AdaptadorPersonalizadoRestaurante(Context context, int layout, ArrayList<Restaurante> listaRestaurantes) {
        this.context = context;
        this.layout = layout;
        this.listaRestaurantes = listaRestaurantes;
    }


    @Override
    public int getCount() {
        return listaRestaurantes.size();
    }

    @Override
    public Object getItem(int position) {
        return listaRestaurantes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdaptadorPersonalizadoHolderServicio holder;
        if (convertView == null){
            LayoutInflater layoutInflater=LayoutInflater.from(this.context);
            convertView=layoutInflater.inflate(this.layout,null);
            holder=new AdaptadorPersonalizadoHolderServicio();
            holder.nombreServicio=(TextView) convertView.findViewById(R.id.idTextViewServicioNombreDistrito2);
            //holder.nombreFecha=(TextView) convertView.findViewById(R.id.idTextViewServicioFecha);
            convertView.setTag(holder);
        }else{
            holder=(AdaptadorPersonalizadoHolderServicio) convertView.getTag();
        }

        String nNombre= listaRestaurantes.get(position).getNombre();
        holder.nombreServicio.setText(nNombre);

        /*String nFecha= listaDatosServiciosActividadesCulturales.get(position).getFecha();
        holder.nombreFecha.setText(nFecha);*/

        return convertView;
    }
}

