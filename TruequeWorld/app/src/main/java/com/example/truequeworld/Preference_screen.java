package com.example.truequeworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_screen);
        user =(User) getIntent().getSerializableExtra("usuario");


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
        otros.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view instanceof MaterialButton) {
                textoBoton += ((MaterialButton) view).getText().toString() + ",";
                Toast.makeText(Preference_screen.this, textoBoton, Toast.LENGTH_SHORT).show();
            }
        }
    };
    public void UpdatePreferences(View view) {
        user.setPreferencias(textoBoton);
        Toast.makeText(this, user.getPreferencias(), Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.129.8:8086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userServiceApi = retrofit.create(UserServiceApi.class);
        Call<User> call = userServiceApi.updateUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    user = response.body();
                    if (user  != null) {
                        Intent intent = new Intent(Preference_screen.this, MainScreen.class);
                        intent.putExtra("usuario", user);
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