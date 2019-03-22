package com.example.recyclerview.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclerview.PersonaActivity;
import com.example.recyclerview.R;
import com.example.recyclerview.modelos.Persona;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class adaptadorPersona extends RecyclerView.Adapter<adaptadorPersona.ViewHolder> implements View.OnClickListener {
    final private List<Persona> personas;
    private View.OnClickListener listener;

    @Override
    public void onClick(View v) {
        if (listener != null)
        {
            listener.onClick(v);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tv_id;
        public TextView tv_nombre;
        public TextView tv_apellido;
        public TextView tv_edad;
        public TextView tv_telefono;
        public ViewHolder(View vista)
        {
            super(vista); //Nos referimos a las caracteristicas de nuestros padres, necesito herencia para usar este métodos
            tv_id = vista.findViewById(R.id.tv_id);
            tv_nombre = vista.findViewById(R.id.tv_name);
            tv_apellido = vista.findViewById(R.id.tv_apellido);
            tv_edad = vista.findViewById(R.id.tv_edad);
            tv_telefono = vista.findViewById(R.id.tv_telefono);

        }
    }

    public adaptadorPersona(List<Persona> personas) {
        this.personas = personas;
        //this.listener = escuchador;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        // Creamos una nueva vista
        View vista =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_persona, viewGroup, false);
        vista.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(vista);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv_id.setText(personas.get(i).getID().toString());
        viewHolder.tv_nombre.setText(personas.get(i).getNombre());
        viewHolder.tv_apellido.setText(personas.get(i).getApellido());
        viewHolder.tv_edad.setText(personas.get(i).getEdad().toString());
        viewHolder.tv_telefono.setText(personas.get(i).getTelefono());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("personas",  personas.get(i));

                Intent intent = new Intent(v.getContext(), PersonaActivity.class);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
                //startActivity(v.getContext(), intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        if(personas!=null){
            return personas.size();
        } else{
            return 0;
        }
    }
}
