package com.example.proyectoprueba1quehago.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.proyectoprueba1quehago.Activities.ActividadCultural;
import com.example.proyectoprueba1quehago.R;

import java.util.ArrayList;

public class AdaptadorPersonalizadoNombreDistrito extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<String> listaNombres;

    public AdaptadorPersonalizadoNombreDistrito(Context context, int layout, ArrayList<String> listaNombres) {
        this.context = context;
        this.layout = layout;
        this.listaNombres = listaNombres;
    }

    @Override
    public int getCount() {
        return listaNombres.size();
    }

    @Override
    public Object getItem(int position) {
        return listaNombres.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdaptadorPersonalizadoHolderNombreDistrito holder;
        if (convertView == null){
            LayoutInflater layoutInflater=LayoutInflater.from(this.context);
            convertView=layoutInflater.inflate(this.layout,null);
            holder=new AdaptadorPersonalizadoHolderNombreDistrito();
            holder.nombreDistrito=(TextView) convertView.findViewById(R.id.idTextViewServicioNombreDistrito);
            convertView.setTag(holder);
        }else{
            holder=(AdaptadorPersonalizadoHolderNombreDistrito) convertView.getTag();
        }

        String nNombre= listaNombres.get(position);
        holder.nombreDistrito.setText(nNombre);


        return convertView;
    }
}

