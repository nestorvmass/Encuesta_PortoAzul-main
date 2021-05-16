package com.example.encuesta_portoazul;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "sqli_portoazul";
    private static final int  DB_VERSION = 1;

    public AdminSQLiteOpenHelper(Context context){
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
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_paciente", cuestionario.getId_paciente());
        contentValues.put("pregunta1", cuestionario.getPregunta1());
        contentValues.put("pregunta2", cuestionario.getPregunta2());
        contentValues.put("pregunta3", cuestionario.getPregunta3());
        contentValues.put("pregunta4", cuestionario.getPregunta4());
        contentValues.put("pregunta5", cuestionario.getPregunta5());
        contentValues.put("promedio", cuestionario.getPromedio());

        try {
            db.insert("cuestionario", null, contentValues);
            db.close();
            return  true;
        }catch (Exception e){
            db.close();
            Log.d(String.valueOf(e), "Error al insertar en la tabla Cuestionario");
            return false;
        }

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
}
