package com.example.encuesta_portoazul;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AdminSQLiteOpenHelper_BAK extends SQLiteOpenHelper {
    private static final String DB_NAME = "sqli_portoazul";
    private static final int  DB_VERSION = 1;

    public AdminSQLiteOpenHelper_BAK(Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String pacientes = "CREATE TABLE paciente(id VARCHAR PRIMARY KEY, " +
                "nombre VARCHAR, apellido VARCHAR, telefono VARCHAR, direccion VARCHAR," +
                "estado_civil VARCHAR, profesion VARCHAR, estrato VARCHAR, cargo VARCHAR," +
                "ultimo_neducativo VARCHAR);";

        String cuestionario = "CREATE TABLE cuestionario(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_paciente VARCHAR, pregunta1 INTEGER, pregunta2 INTEGER, pregunta3 INTEGER, " +
                "pregunta4 INTEGER, pregunta5 INTEGER, " +
                "promedio REAL, FOREIGN KEY(id_paciente) REFERENCES paciente(id)  );";

        db.execSQL(pacientes);
        db.execSQL(cuestionario);

    }

    public boolean addPaciente(Paciente paciente){
        long respuesta;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", paciente.getId());
        contentValues.put("nombre", paciente.getNombre());
        contentValues.put("apellido", paciente.getApellido());
        contentValues.put("telefono", paciente.getTelefono());
        contentValues.put("direccion", paciente.getDireccion());
        contentValues.put("estado_civil", paciente.getEstado_civil());
        contentValues.put("profesion", paciente.getProfesion());
        contentValues.put("estrato", paciente.getEstrato());
        contentValues.put("cargo", paciente.getCargo());
        contentValues.put("ultimo_neducativo", paciente.getNivel_estudio());


        respuesta = db.insert("paciente", null, contentValues);
        db.close();
        if (respuesta!=-1){
            return true;
        }
        return false;


    }


    public boolean addCuestionario(Cuestionario cuestionario){
        long respuesta;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_paciente", cuestionario.getId_paciente());
        contentValues.put("pregunta1", cuestionario.getPregunta1());
        contentValues.put("pregunta2", cuestionario.getPregunta2());
        contentValues.put("pregunta3", cuestionario.getPregunta3());
        contentValues.put("pregunta4", cuestionario.getPregunta4());
        contentValues.put("pregunta5", cuestionario.getPregunta5());
        contentValues.put("promedio", cuestionario.getPromedio());

        respuesta = db.insert("cuestionario", null, contentValues);
        db.close();
        if (respuesta!=-1){
            return true;
        }
        return false;

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String pacientes = "DROP TABLE IF EXISTS paciente";
        String respuestas = "DROP TABLE IF EXISTS respuesta";
        db.execSQL(pacientes);
        db.execSQL(pacientes);
        onCreate(db);
    }

    public String buscarpaciente(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String resultado = "";
        String query = "select * from paciente where id="+"'"+id+"'";
        Cursor f = db.rawQuery(query, null);
        if(f.moveToFirst()) {
            resultado=f.getString(5);
        }

        return resultado;
    }

    public List<Person> listapersonas(){
        Person persona;
        List<Person> persons;
        persons = new ArrayList<>();
        //ArrayList<Person> listapersonas =  new ArrayList<Person>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "select id, nombre, apellido from paciente";

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            persona = new Person();
            persona.setId(cursor.getString(0));
            persona.setNombre(cursor.getString(1) + " "+ cursor.getString(2));
            String query1 = "select pregunta4, promedio from cuestionario where id_paciente="+"'"+persona.getId()+"'";
            Cursor f = db.rawQuery(query1, null);
            if(f.moveToFirst()) {
                persona.setCobertura(f.getString(0));
                persona.setValoracion(f.getString(1));

            }

            System.out.println("PERSONA HELPER"+ persona.getId());
            System.out.println("PERSONA HELPER"+ persona.getNombre());
            System.out.println("PERSONA HELPER"+ persona.getCobertura());
            System.out.println("PERSONA HELPER"+ persona.getValoracion());
            System.out.println("PERSONA HELPER");
            persons.add(persona);
        }

        return persons;
    }


}
