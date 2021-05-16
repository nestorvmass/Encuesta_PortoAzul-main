package com.example.encuesta_portoazul;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Person {

    String nombre;
    String valoracion;
    String cobertura;

    public Person(String nombre, String valoracion, String cobertura) {
        this.nombre = nombre;
        this.valoracion = valoracion;
        this.cobertura = cobertura;
    }

}

