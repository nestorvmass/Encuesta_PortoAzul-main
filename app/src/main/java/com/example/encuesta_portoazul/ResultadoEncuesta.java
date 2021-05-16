package com.example.encuesta_portoazul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ResultadoEncuesta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_encuesta);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        List<Person> persons;
        persons = new ArrayList<>();
        persons.add(new Person("Nestor Villafañe", "42", "SI"));
        persons.add(new Person("Nestor Villafañe", "32", "SI"));
        persons.add(new Person("Nestor fe", "422", "SI"));
        persons.add(new Person("Nestor efgegeg", "422", "SI"));
        persons.add(new Person("Nestor Villafañe", "8832", "NO"));
        persons.add(new Person("Nestor Villafañe", "12", "SI"));

        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);





    }


}