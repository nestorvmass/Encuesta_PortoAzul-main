package com.example.encuesta_portoazul;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Encuesta extends AppCompatActivity {

    //campos de layout
    EditText nombre, apellido, telefono, direccion, profesion, cargo;
    Spinner estado_civil, estrato, niveleducativo;
    RadioGroup pregunta1, pregunta2, pregunta3, pregunta4, pregunta5;
    RadioButton respuesta1, respuesta2, respuesta3, respuesta4, respuesta5;
    Button bguardar;



    AdminSQLiteOpenHelper db;
    Paciente paciente;
    Cuestionario cuestionario;


    String estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
        bguardar = findViewById(R.id.guardar);

        nombre = findViewById(R.id.et_nom_paciente);
        apellido = findViewById(R.id.et_apell_paciente);
        telefono = findViewById(R.id.et_tel_paciente);
        direccion = findViewById(R.id.et_dir_paciente);
        profesion = findViewById(R.id.et_profesion_paciente);
        cargo = findViewById(R.id.et_cargo_paciente);

        estado_civil = findViewById(R.id.sp_estadocivil_paciente);
        estrato= findViewById(R.id.sp_estrato_paciente);
        niveleducativo = findViewById(R.id.sp_nivelestudio_paciente);

        pregunta1 = findViewById(R.id.RG1);
        pregunta2 = findViewById(R.id.RG2);
        pregunta3 = findViewById(R.id.RG3);
        pregunta4 = findViewById(R.id.RG4);
        pregunta5 = findViewById(R.id.RG5);

        Bundle datos = this.getIntent().getExtras();

        try {
            estado = datos.getString("PrincipalToEncuesta");
            System.out.println("Valor de respuesta en el Try: "+estado);
        }catch (Exception e){
            estado = null;

        }
        System.out.println("Valor de respuesta: "+estado);
        if(estado == null){
            tratamiento();
        }

        bguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( nombre.getText().length() == 0
                        || apellido.getText().length() == 0
                        || telefono.getText().length() == 0
                        || direccion.getText().length() == 0
                        || profesion.getText().length() == 0
                        || cargo.getText().length() == 0
                        || estado_civil.getSelectedItemPosition()==0
                        || niveleducativo.getSelectedItemPosition()==0
                        || estrato.getSelectedItemPosition()==0
                        ||pregunta1.getCheckedRadioButtonId() == -1
                        || pregunta2.getCheckedRadioButtonId() == -1
                        || pregunta2.getCheckedRadioButtonId() == -1
                        || pregunta3.getCheckedRadioButtonId() == -1
                        || pregunta4.getCheckedRadioButtonId() == -1
                        || pregunta5.getCheckedRadioButtonId() == -1){

                    alerta("Ups! Todos los campos son obligatorios.");
                }else{
                    respuesta1 = findViewById(pregunta1.getCheckedRadioButtonId());
                    respuesta2 = findViewById(pregunta2.getCheckedRadioButtonId());
                    respuesta3 = findViewById(pregunta3.getCheckedRadioButtonId());
                    respuesta4 = findViewById(pregunta4.getCheckedRadioButtonId());
                    respuesta5 = findViewById(pregunta5.getCheckedRadioButtonId());

                    /*
                    evaluaciones.setNombre(String.valueOf(tvnombre.getText()));
                    evaluaciones.setPregunta1(Integer.parseInt(
                            String.valueOf(respuesta1.getText())));
                    evaluaciones.setPregunta2(Integer.parseInt(
                            String.valueOf(respuesta2.getText())));
                    evaluaciones.setPregunta3(Integer.parseInt(
                            String.valueOf(respuesta3.getText())));
                    evaluaciones.setPregunta4(Integer.parseInt(
                            String.valueOf(respuesta4.getText())));
                    evaluaciones.setPregunta5(Integer.parseInt(
                            String.valueOf(respuesta5.getText())));
                    evaluaciones.setPregunta6(Integer.parseInt(
                            String.valueOf(respuesta6.getText())));
                    evaluaciones.setPregunta7(Integer.parseInt(
                            String.valueOf(respuesta7.getText())));
                    evaluaciones.setPregunta8(Integer.parseInt(
                            String.valueOf(respuesta8.getText())));
                    evaluaciones.setPregunta9(Integer.parseInt(
                            String.valueOf(respuesta9.getText())));
                    evaluaciones.setPregunta10(Integer.parseInt(
                            String.valueOf(respuesta10.getText())));
                    evaluaciones.setPregunta11(Integer.parseInt(
                            String.valueOf(respuesta11.getText())));
                    evaluaciones.setPregunta12(Integer.parseInt(
                            String.valueOf(respuesta12.getText())));

                    imprimir( "Resultado de la  " + evaluaciones.getPregunta1());
                    imprimir( "Resultado de la operacion " + evaluaciones.resultado());
                    if (archivo.escribirTxt(  getApplicationContext()  , evaluaciones.toString())){
                        alerta("Se ha guardado en TXT");
                        alerta("El archivo se encuentra en la ruta: \n" +
                                "/data/data/com.example.medidordeestreslaboral/" +
                                "files/datosguardados.txt");
                    }else {
                        alerta("No ha guardado en TXT");
                    }


                    Intent i = new Intent(EncuestaActivity.this, resultadoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("resultado", String.valueOf(evaluaciones.resultado()));
                    i.putExtras(bundle);
                    startActivity(i);
                    finish();
                    */


                }


            }
        });


    }



    private void tratamiento(){
        String texto = "Que el Artículo 15 de la Constitución Política dispone que todas las personas tienen derecho a su intimidad personal y familiar y a su buen nombre, y el Estado debe respetarlos y hacerlos respetar. De igual modo, tienen derecho a conocer, actualizar y rectificar las informaciones que se hayan recogido sobre ellas en bancos de datos y en archivos de entidades públicas y privadas.\n" +
                "Por tal motivo los datos personales de la población víctima contenidos en las herramientas tecnológicas o en cualquier mecanismo físico o digital, es el principal compromiso de todos los usuarios que accedan a ellos, toda vez que son catalogados como datos sensibles con carácter reservado y confidencial, y su tratamiento está sujeta a la normatividad aplicable.\n" +
                "\n" +
                "Se encuentra de acuerdo con el tratamiento de sus datos. ";

        AlertDialog.Builder alerta = new AlertDialog.Builder(Encuesta.this);
        alerta.setMessage(texto)
                .setCancelable(false)
                .setPositiveButton("Acepto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                        Intent intent = new Intent(Encuesta.this, PrincipalActivity.class);
                        intent.putExtra("EncuestaToPrincipal", "NoAcepto");
                        dialog.cancel();
                        //finish();
                    }
                })
                .setNegativeButton("No acepto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Encuesta.this, PrincipalActivity.class);
                        intent.putExtra("EncuestaToPrincipal", "Acepto");
                        startActivity(intent);
                        finish();
                    }
                });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Ejemplo");
        titulo.show();
    }

    private void alerta(String mensaje){
        Toast.makeText(getApplicationContext(),
                mensaje , Toast.LENGTH_SHORT).show();
    }
}