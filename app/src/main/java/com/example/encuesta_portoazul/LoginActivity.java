package com.example.encuesta_portoazul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {

    EditText Login_EdT_User,Login_EdT_Password;
    Button Login_BT_Login, Login_BT_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login_EdT_User = findViewById(R.id.Login_EdT_User);
        Login_EdT_Password = findViewById(R.id.Login_EdT_Password);
        Login_BT_Login = findViewById(R.id.Login_BT_Login);
        Login_BT_Register = findViewById(R.id.Login_BT_Register);


        Login_BT_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //https://android-americana.000webhostapp.com/validar_usuario.php
                validarUsuario("https://android-americana.000webhostapp.com/validar_usuario.php");

                //validarUsuario("https://pmovil1cua.000webhostapp.com/validar_usuario.php");
            }
        });


        Login_BT_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void validarUsuario(String Url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //textView.setText("Response is: "+ response.substring(0,500));

                        System.out.println("Respuesta de API"+ response);
                        if (response.contains("{")  ){
                            Intent intent= new Intent(getApplicationContext(),PrincipalActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText( LoginActivity.this,"usuario o contraseñas o son incorrectas", Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String,String>();
                parametros.put("usuario",Login_EdT_User.getText().toString());
                parametros.put("password",Login_EdT_Password.getText().toString());
                return parametros;
            }
        };

        // Add the request to the RequestQueue.
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}