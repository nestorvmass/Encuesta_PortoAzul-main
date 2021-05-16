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
import android.widget.Toast;

public class EncuestaActivity extends AppCompatActivity {

    //campos de layout
    EditText nombre, apellido, telefono, direccion, profesion, cargo, id;
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

        id = findViewById(R.id.et_id_paciente);
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

                    paciente = new Paciente();
                    paciente.setId(convertirEditTextString(id));
                    paciente.setNombre(convertirEditTextString(nombre));
                    paciente.setApellido(convertirEditTextString(apellido));
                    paciente.setTelefono(convertirEditTextString(telefono));
                    paciente.setDireccion(convertirEditTextString(direccion));
                    paciente.setCargo(convertirEditTextString(cargo));
                    paciente.setProfesion(convertirEditTextString(profesion));
                    paciente.setEstado_civil(estado_civil.getSelectedItem().toString());
                    paciente.setEstrato(estrato.getSelectedItem().toString());
                    paciente.setNivel_estudio(niveleducativo.getSelectedItem().toString());

                    db = new AdminSQLiteOpenHelper(EncuestaActivity.this);
                    boolean p = db.addPaciente(paciente);
                    if (p){
                        //alerta("Se ha creado exitosamente el paciente");
                        cuestionario = new Cuestionario();
                        cuestionario.setId_paciente(paciente.getId());
                        cuestionario.setPregunta1(convertirRadioButtonToInt(respuesta1));
                        cuestionario.setPregunta2(convertirRadioButtonToInt(respuesta2));
                        cuestionario.setPregunta3(convertirRadioButtonToInt(respuesta3));
                        cuestionario.setPregunta4(String.valueOf(respuesta4.getText()));
                        cuestionario.setPregunta5(convertirRadioButtonToInt(respuesta5));

                        boolean c = db.addCuestionario(cuestionario);
                        if (c){
                            alerta("Cuestionario guardo exitosamente");
                            finish();
                        }else{
                            alerta("Error al guar cuestionario");

                        }
                    }else {
                        alerta("Error al crear el paciente, ya existe");
                    }
                    /*
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

        AlertDialog.Builder alerta = new AlertDialog.Builder(EncuestaActivity.this);
        alerta.setMessage(texto)
                .setCancelable(false)
                .setPositiveButton("Acepto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                        Intent intent = new Intent(EncuestaActivity.this, PrincipalActivity.class);
                        intent.putExtra("EncuestaToPrincipal", "NoAcepto");
                        dialog.cancel();
                        //finish();
                    }
                })
                .setNegativeButton("No acepto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(EncuestaActivity.this, PrincipalActivity.class);
                        intent.putExtra("EncuestaToPrincipal", "Acepto");
                        startActivity(intent);
                        finish();
                    }
                });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Anexo 1");
        titulo.show();
    }

    private void alerta(String mensaje){
        Toast.makeText(getApplicationContext(),
                mensaje , Toast.LENGTH_SHORT).show();
    }

    private String convertirEditTextString(EditText campo){
        return String.valueOf(campo.getText());
    }

    private int convertirRadioButtonToInt(RadioButton radioButton){
        return Integer.parseInt(String.valueOf(radioButton.getText()));
    }
}