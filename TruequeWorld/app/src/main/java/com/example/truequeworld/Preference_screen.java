package com.example.truequeworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truequeworld.Class.User;
import com.example.truequeworld.Interface.UserServiceApi;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Preference_screen extends AppCompatActivity {

    private UserServiceApi userServiceApi;

    User user;

    String textoBoton = "";

    String emailString;

    String contraString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_screen);

        emailString = getIntent().getStringExtra("email");
        contraString = getIntent().getStringExtra("contra");

        MaterialButton tecnologic = findViewById(R.id.button_tecnologic);
        MaterialButton juegos = findViewById(R.id.button_toys);
        MaterialButton infantil = findViewById(R.id.button_camera);
        MaterialButton vehiculos = findViewById(R.id.button_games);
        MaterialButton deporte = findViewById(R.id.button_dolls);
        MaterialButton escolar = findViewById(R.id.button_advertisement);
        MaterialButton moda = findViewById(R.id.button_books);
        MaterialButton entretenimiento = findViewById(R.id.button_programming);
        MaterialButton alimentacion = findViewById(R.id.button_photography);
        MaterialButton coleccion = findViewById(R.id.button_sesion);
        MaterialButton anime = findViewById(R.id.button_anime);
        MaterialButton otros = findViewById(R.id.button_3d);

        tecnologic.setOnClickListener(onClickListener);
        juegos.setOnClickListener(onClickListener);
        infantil.setOnClickListener(onClickListener);
        vehiculos.setOnClickListener(onClickListener);
        deporte.setOnClickListener(onClickListener);
        escolar.setOnClickListener(onClickListener);
        moda.setOnClickListener(onClickListener);
        entretenimiento.setOnClickListener(onClickListener);
        alimentacion.setOnClickListener(onClickListener);
        coleccion.setOnClickListener(onClickListener);
        anime.setOnClickListener(onClickListener);
        otros.setOnClickListener(onClickListener);
        Conectar();
    }

    public void Conectar(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.129.8:8086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear instancia de la interfaz
        userServiceApi = retrofit.create(UserServiceApi.class);
        Login();
    }

    public void Login(){
        Call<User> call = userServiceApi.getUser(emailString, contraString);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    user = response.body();
                    if (user  != null) {
                        SharedPreferences sharedPreferences = getSharedPreferences("UsuarioID", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("userId", user.getId());
                        editor.apply();

                    } else {
                        // El userId es nulo, maneja el caso según tus necesidades
                        Toast.makeText(Preference_screen.this, "ID de Usuario nulo", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Manejar el error de respuesta
                    Toast.makeText(Preference_screen.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Manejar el fallo de la llamada
                Toast.makeText(Preference_screen.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view instanceof MaterialButton) {
                if(!textoBoton.contains(((MaterialButton) view).getText().toString())){
                    textoBoton += ((MaterialButton) view).getText().toString() + ",";
                    view.setBackgroundColor(Color.parseColor("#727272"));
                }else{
                    textoBoton = textoBoton.replace(((MaterialButton) view).getText().toString() + ",", "");
                    view.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        }
    };
    public void UpdatePreferences(View view) {
       user.setPreferencias(textoBoton);
        Call<User> call = userServiceApi.updateUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    user = response.body();
                    if (user  != null) {
                        Intent intent = new Intent(Preference_screen.this, MainScreen.class);
                        startActivity(intent, ActivityOptions.makeCustomAnimation(Preference_screen.this, R.anim.fade_in, R.anim.fade_out).toBundle());

                    } else {
                        Toast.makeText(Preference_screen.this, "ID de Usuario nulo", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Preference_screen.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Preference_screen.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}