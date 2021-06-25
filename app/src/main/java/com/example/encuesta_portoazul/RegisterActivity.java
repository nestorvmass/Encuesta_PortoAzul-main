package com.example.encuesta_portoazul;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText Register_EdT_Nombres, Register_EdT_Apellidos, Register_EdT_User, Register_EdT_Password;
    Button Register_BT_Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Register_EdT_Nombres = findViewById(R.id.Register_EdT_Nombres);
        Register_EdT_Apellidos = findViewById(R.id.Register_EdT_Apellidos);
        Register_EdT_User = findViewById(R.id.Register_EdT_User);
        Register_EdT_Password = findViewById(R.id.Register_EdT_Password);
        Register_BT_Register = findViewById(R.id.Register_BT_Register);


        Register_BT_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( Register_EdT_Apellidos.length()==0
                        || Register_EdT_Nombres.length()==0
                        || Register_EdT_User.length()==0
                        || Register_EdT_Password.length()==0
                ){
                    alerta("Todos los campos son obligatorios");
                }else {

                    crear_usuario();
                    finish();

                }
            }
        });


    }

    private void alerta(String mensaje){
        Toast.makeText(getApplicationContext(),
                mensaje , Toast.LENGTH_SHORT).show();
    }
    private String convertirEditTextString(EditText campo){
        return String.valueOf(campo.getText());
    }


    private void crear_usuario(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://android-americana.000webhostapp.com/register_user.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("Usuario_Creado")){
                            Toast.makeText( RegisterActivity.this,"Se ha registrado Exitosamente", Toast.LENGTH_SHORT).show();
                        }else{

                            Toast.makeText(RegisterActivity.this,response, Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String,String>();
                parametros.put("usuario", convertirEditTextString(Register_EdT_User));
                parametros.put("password", convertirEditTextString(Register_EdT_Password));
                parametros.put("nombres", convertirEditTextString(Register_EdT_Nombres));
                parametros.put("apellidos", convertirEditTextString(Register_EdT_Apellidos));

                return parametros;
            }
        };

        // Add the request to the RequestQueue.
        RequestQueue queue= Volley.newRequestQueue(RegisterActivity.this);
        queue.add(stringRequest);

    }
}