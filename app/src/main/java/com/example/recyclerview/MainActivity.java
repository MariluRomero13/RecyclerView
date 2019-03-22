package com.example.recyclerview;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.recyclerview.adaptadores.adaptadorPersona;
import com.example.recyclerview.modelos.Persona;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    adaptadorPersona adaptador;
    Button btncargar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);
        btncargar = findViewById(R.id.cargar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonArrayRequest jar = new JsonArrayRequest(Request.Method.GET, "http://nuevo.rnrsiilge-org.mx/lista", null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try
                                {
                                    Log.d("respuesta", response.toString());

                                    Gson gson = new Gson();
                                    Type type = new TypeToken<List<Persona>>(){}.getType(); //construyendo un tipo de lista // crea un tipo de lista de personas
                                    final ArrayList<Persona> lp  = gson.fromJson(response.toString(), type);
                                    adaptador = new adaptadorPersona(lp);
                                    recyclerView.setAdapter(adaptador);
                                    runAnimation(recyclerView);

                                    /*adaptador = new adaptadorPersona(lp, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //Bundle bundle = new Bundle();
                                            //bundle.putSerializable("personas",  lp.get(recyclerView.getChildAdapterPosition(v)));

                                            //Intent intent = new Intent(v.getContext(), PersonaActivity.class);
                                            //intent.putExtras(bundle);
                                           // v.getContext().startActivity(intent);
                                            //startActivity(v.getContext(), intent);

                                            Intent i = new Intent(Intent.ACTION_CALL);
                                            i.setData(Uri.parse("tel:"+lp.get(recyclerView.getChildAdapterPosition(v)).getTelefono()));
                                           if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                                           {
                                               //return;
                                               ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 225);
                                           }
                                            startActivity(i);
                                        }
                                    });*/


                                }
                                catch(Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }

                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance(MainActivity.this).getRequestQueue().add(jar);
            }
        };

        btncargar.setOnClickListener(click);
    }





    private void runAnimation(RecyclerView recyclerView)
    {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = null;
        controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_items_right);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}
