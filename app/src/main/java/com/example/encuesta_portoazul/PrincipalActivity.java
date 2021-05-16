package com.example.encuesta_portoazul;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    Button botondialogo;
    public String estado;

    AdminSQLiteOpenHelper db;
    Paciente paciente;
    ListView menu;
    List<String> opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Bundle datos = this.getIntent().getExtras();
        /*
        paciente = new Paciente();
        paciente.setId("1045730795");
        paciente.setNombre("andrea");
        paciente.setApellido("Villafane");
        paciente.setTelefono("300");
        paciente.setDireccion("Calle 123");
        paciente.setCargo("Soporte");
        paciente.setEstado_civil("Soltero");
        paciente.setEstrato("2");
        paciente.setNivel_estudio("Profesional");
        paciente.setProfesion("Ingeniero");
        db = new AdminSQLiteOpenHelper(this);
        boolean r = db.addPaciente(paciente);
        Log.d(String.valueOf(r), "REsultado de addpaciente");

        Cuestionario cuestionario = new Cuestionario();
        cuestionario.setId_paciente(paciente.getId());
        cuestionario.setPregunta1(1);
        cuestionario.setPregunta2(3);
        cuestionario.setPregunta3(4);
        cuestionario.setPregunta4(5);
        cuestionario.setPregunta5(5);
        System.out.print("Variable de promedio: "+cuestionario.getPromedio()+" \n");
        db.addCuestionario(cuestionario);
        String ejemplo = db.buscarpaciente("1045730795");
        System.out.print("Variable de retorno: "+ejemplo);

        try {
            estado = datos.getString("EncuestaToPrincipal");
            System.out.println("Respuesta Valor de estado desde principal: "+estado);
        }catch (Exception e){
            estado = null;

        }
        */

        menu = findViewById(R.id.listamenu);
        opciones = new ArrayList<String>();
        opciones.add("Cuestionario de satisfacción");
        opciones.add("Resultado de cuestionario pacientes");
        opciones.add("Sobre esta app");
        opciones.add("Medidor de Estres Laboral");
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, opciones );
        menu.setAdapter(adaptador);
        menu.setOnItemClickListener((parent, view, position, id) -> {

            if(position==0){
                Intent i = new Intent(this, EncuestaActivity.class);
                System.out.println("respu, antes de enviar al encuesta: "+estado);
                if(estado =="Acepto"){
                    i.putExtra("PrincipalToEncuesta", "Acepto");
                }

                Bundle bundle = new Bundle();
                i.putExtras(bundle);
                startActivity(i);
            }
            if(position==1){
                Intent i = new Intent(this, ResultadoEncuesta.class);
                Bundle bundle = new Bundle();
                i.putExtras(bundle);
                startActivity(i);
            }
            if(position==2){
                Intent i = new Intent(this, InfoDevsActivity.class);
                Bundle bundle = new Bundle();
                i.putExtras(bundle);
                startActivity(i);
            }
            if(position==3){
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setComponent(new ComponentName("com.example.medidordeestreslaboral","com.example.medidordeestreslaboral.MainActivity"));
                startActivity(intent);

            }

            /*
            if(position==3){
                Intent i = new Intent(this, Encuesta.class);
                Bundle bundle = new Bundle();
                i.putExtras(bundle);
                startActivity(i);
            }
            */
        });





        if(estado == "NoAcepto" || estado == null){
            tratamiento();
        }


    }



    private void tratamiento(){
        String texto = "Que el Artículo 15 de la Constitución Política dispone que todas las personas tienen derecho a su intimidad personal y familiar y a su buen nombre, y el Estado debe respetarlos y hacerlos respetar. De igual modo, tienen derecho a conocer, actualizar y rectificar las informaciones que se hayan recogido sobre ellas en bancos de datos y en archivos de entidades públicas y privadas.\n" +
                "Por tal motivo los datos personales de la población víctima contenidos en las herramientas tecnológicas o en cualquier mecanismo físico o digital, es el principal compromiso de todos los usuarios que accedan a ellos, toda vez que son catalogados como datos sensibles con carácter reservado y confidencial, y su tratamiento está sujeta a la normatividad aplicable.\n" +
                "\n" +
                "Se encuentra de acuerdo con el tratamiento de sus datos. ";

        AlertDialog.Builder alerta = new AlertDialog.Builder(PrincipalActivity.this);
        alerta.setMessage(texto)
                .setCancelable(false)
                .setPositiveButton("Acepto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                        Intent intent = new Intent(PrincipalActivity.this, EncuestaActivity.class);
                        intent.putExtra("PrincipalToEncuesta", "Acepto");
                        startActivity(intent);
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
        titulo.setTitle("Anexo 1");
        titulo.show();
    }


    private void alerta(String mensaje){
        Toast.makeText(getApplicationContext(),
                mensaje , Toast.LENGTH_SHORT).show();
    }
}