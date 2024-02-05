package com.example.truequeworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginScreen extends AppCompatActivity {

    private UserServiceApi userServiceApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        MaterialButton button = findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        }

        public void Login(){

            TextView emailTextView = findViewById(R.id.login_email);
            TextView contraTextView = findViewById(R.id.login_password);

            String emailString = emailTextView.getText().toString();
            String contraString = contraTextView.getText().toString();

            // Configurar Retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.129.8:8086")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            // Crear instancia de la interfaz
            userServiceApi = retrofit.create(UserServiceApi.class);

            // Ejemplo de llamada a getUserId
            Call<Integer> call = userServiceApi.getUserId(emailString, contraString);
            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()) {
                        Integer userId = response.body();
                        // Manejar el resultado
                        if (userId != null) {
                            Intent intent = new Intent(LoginScreen.this, LoginScreen.class);
                            startActivity(intent, ActivityOptions.makeCustomAnimation(LoginScreen.this, R.anim.fade_in, R.anim.fade_out).toBundle());
                        } else {
                            // El userId es nulo, maneja el caso según tus necesidades
                            Toast.makeText(LoginScreen.this, "ID de Usuario nulo", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Manejar el error de respuesta
                        Toast.makeText(LoginScreen.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    // Manejar el fallo de la llamada
                    Toast.makeText(LoginScreen.this, "porth", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


