package com.example.encuesta_portoazul;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.animation.AccelerateInterpolator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TratamientoDatos extends AppCompatActivity {
    AlertDialog.Builder alerta;
    Context Con;

    public TratamientoDatos(Context context){
        AlertDialog.Builder alerta = new AlertDialog.Builder(context);
        Con = context;
    }

    public boolean iniciar(){
        boolean respuesta = false;
        String texto = "Que el Artículo 15 de la Constitución Política dispone que todas las personas tienen derecho a su intimidad personal y familiar y a su buen nombre, y el Estado debe respetarlos y hacerlos respetar. De igual modo, tienen derecho a conocer, actualizar y rectificar las informaciones que se hayan recogido sobre ellas en bancos de datos y en archivos de entidades públicas y privadas.\n" +
                "Por tal motivo los datos personales de la población víctima contenidos en las herramientas tecnológicas o en cualquier mecanismo físico o digital, es el principal compromiso de todos los usuarios que accedan a ellos, toda vez que son catalogados como datos sensibles con carácter reservado y confidencial, y su tratamiento está sujeta a la normatividad aplicable.\n" +
                "\n" +
                "Se encuentra de acuerdo con el tratamiento de sus datos. ";

        alerta.setMessage("g")
                .setCancelable(false)
                .setPositiveButton("Acepto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                        //Intent intent = new Intent(Con, Encuesta.class);
                        //startActivity(intent);
                        //finish();
                    }
                })
                .setNegativeButton("No acepto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Ejemplo");
        titulo.show();

        return true;
    }
}
