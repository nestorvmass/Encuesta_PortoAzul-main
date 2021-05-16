package com.example.encuesta_portoazul;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    List<Person> persons;

    public RVAdapter(List<Person> persons){
        this.persons = persons;
    }
    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tarjeta, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personN.setText(persons.get(i).nombre);
        personViewHolder.personV.setText(persons.get(i).valoracion);
        personViewHolder.personC.setText(persons.get(i).cobertura);
        personViewHolder.personID.setText(persons.get(i).id);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personN;
        TextView personV;
        TextView personID;
        TextView personC;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personN = itemView.findViewById(R.id.tvnombre_paciente);
            personV = itemView.findViewById(R.id.tv_valoracion);
            personC = itemView.findViewById(R.id.tv_cobertura);
            personID = itemView.findViewById(R.id.tv_id);
        }
    }
}
