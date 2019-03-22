package com.example.recyclerview;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.recyclerview.modelos.Persona;

public class PersonaActivity extends AppCompatActivity {

    TextView nombre, apellido, edad, telefono;
    Persona persona = null;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);

        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        edad = findViewById(R.id.edad);
        telefono = findViewById(R.id.telefono);
        btn = findViewById(R.id.llamar);

        Bundle objeto = getIntent().getExtras();


        if (objeto != null)
        {
            persona = (Persona) objeto.getSerializable("personas");
            nombre.setText(persona.getNombre());
            apellido.setText(persona.getApellido());
            edad.setText(persona.getEdad().toString());
            telefono.setText(persona.getTelefono());
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevo_telefono = persona.getTelefono();
                nuevo_telefono = nuevo_telefono.replace("-","");
                nuevo_telefono = nuevo_telefono.trim();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+nuevo_telefono));
                startActivity(i);
            }
        });
    }
}
