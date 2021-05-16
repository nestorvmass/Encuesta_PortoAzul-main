package com.example.encuesta_portoazul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ResultadoEncuesta extends AppCompatActivity {
    AdminSQLiteOpenHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_encuesta);

        db = new AdminSQLiteOpenHelper(this);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        List<Person> persons;
        persons = db.listapersonas();
        /*
        persons.add(new Person("1","Nestor Villafañe", "42", "SI"));
        persons.add(new Person("2","Nestor Villafañe", "32", "SI"));
        persons.add(new Person("1","Nestor fe", "422", "SI"));
        persons.add(new Person("1","Nestor efgegeg", "422", "SI"));
        persons.add(new Person("1","Nestor Villafañe", "8832", "NO"));
        persons.add(new Person("1","Nestor Villafañe", "12", "SI"));

        */




        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);





    }


}