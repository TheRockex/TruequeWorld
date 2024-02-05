package com.example.truequeworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterScreen extends AppCompatActivity {

    private UserServiceApi userServiceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        MaterialButton button = findViewById(R.id.buttonRegistrer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserExists();
            }
        });
    }

    public void Conectar(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.129.8:8086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear instancia de la interfaz
        userServiceApi = retrofit.create(UserServiceApi.class);
    }

    public void Registrer(){

        TextView nombreTextView = findViewById(R.id.register_name);
        TextView apellidosTextView = findViewById(R.id.register_surname);
        TextView emailTextView = findViewById(R.id.register_email);
        TextView contraTextView = findViewById(R.id.register_pass);

        String nombreString = nombreTextView.getText().toString();
        String apellidosString = apellidosTextView.getText().toString();
        String emailString = emailTextView.getText().toString();
        String contraString = contraTextView.getText().toString();

        Conectar();

        // Crear un nuevo usuario para la inserción
        User newUser = new User();
        newUser.setDni(nombreString);
        newUser.setName(nombreString);
        newUser.setApellidos(apellidosString);
        newUser.setEmail(emailString);
        newUser.setContrasenia(contraString);
        newUser.setTp(0);

        // Ejemplo de llamada a insertUser
        Call<User> call = userServiceApi.insertUser(newUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User insertedUser = response.body();
                    // Manejar el resultado
                    if (insertedUser != null) {
                        Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
                        startActivity(intent, ActivityOptions.makeCustomAnimation(RegisterScreen.this, R.anim.fade_in, R.anim.fade_out).toBundle());
                    } else {
                        // El usuario insertado es nulo, maneja el caso según tus necesidades
                        Toast.makeText(RegisterScreen.this, "Usuario insertado nulo", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Manejar el error de respuesta
                    Toast.makeText(RegisterScreen.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Manejar el fallo de la llamada
                Toast.makeText(RegisterScreen.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void UserExists(){
        TextView emailTextView = findViewById(R.id.register_email);
        TextView contraTextView = findViewById(R.id.register_pass);
        TextView contraconfirmTextView = findViewById(R.id.register_confpass);

        String emailString = emailTextView.getText().toString();
        String contraString = contraTextView.getText().toString();
        String contraconfirmString = contraconfirmTextView.getText().toString();

        if(contraString.equals(contraconfirmString)){
            Conectar();

            // Ejemplo de llamada a getUserId
            Call<Integer> call = userServiceApi.getUserId(emailString, contraString);
            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()) {
                        Integer userId = response.body();
                        // Manejar el resultado
                        if (userId != null) {
                            Toast.makeText(RegisterScreen.this, "El usuario existe", Toast.LENGTH_SHORT).show();
                        } else {
                            // El userId es nulo, maneja el caso según tus necesidades
                            Toast.makeText(RegisterScreen.this, "ID de Usuario nulo", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Manejar el error de respuesta
                        Toast.makeText(RegisterScreen.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    Registrer();

                }
            });

        }else{
            Toast.makeText(this, "La contraseña no es igual", Toast.LENGTH_SHORT).show();
        }

    }
}