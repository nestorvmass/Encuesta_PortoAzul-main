package com.example.encuesta_portoazul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultadoEncuesta extends AppCompatActivity {
    String TAG = "ResultadoEncuesta";
    AdminSQLiteOpenHelper db;
    List<Person> persons;
    RVAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_encuesta);

        db = new AdminSQLiteOpenHelper(this);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        persons = db.listapersonas();
        /*
        persons.add(new Person("1","Nestor Villafañe", "42", "SI"));
        persons.add(new Person("2","Nestor Villafañe", "32", "SI"));
        persons.add(new Person("1","Nestor fe", "422", "SI"));
        persons.add(new Person("1","Nestor efgegeg", "422", "SI"));
        persons.add(new Person("1","Nestor Villafañe", "8832", "NO"));
        persons.add(new Person("1","Nestor Villafañe", "12", "SI"));

        */

        adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);

        extraerInfo("https://android-americana.000webhostapp.com/recuperar_info.php");

    }

    private void extraerInfo(String Url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG,"Respuesta directa desde api :\n"+response);
                        Gson gson = new Gson();
                        RecuperarInfoResponse m = gson.fromJson(response, RecuperarInfoResponse.class);

                        //Toast.makeText( ResultadoEncuesta.this,response, Toast.LENGTH_LONG).show();

                        alerta("Informacion Cargada exitosamente");

                        persons.clear();
                        for(int i = 0; i < m.getPersona().size(); i++){
                            persons.add(new Person(m.getPersona().get(i).getId(),m.getPersona().get(i).getNombre(), m.getPersona().get(i).getValoracion(), m.getPersona().get(i).getCobertura()));
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ResultadoEncuesta.this,error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private void alerta(String mensaje){
        Toast.makeText(getApplicationContext(),
                mensaje , Toast.LENGTH_SHORT).show();
    }
}