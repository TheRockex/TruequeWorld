package com.example.truequeworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truequeworld.Class.User;
import com.example.truequeworld.Interface.UserServiceApi;
import com.example.truequeworld.retrofit.RetrofitConexion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUser extends AppCompatActivity {

    private UserServiceApi userServiceApi;
    private static final int PICK_IMAGE_REQUEST = 1;
    User user;
    private ImageView imageView;
    TextView nombreTextView;
    TextView apellidosTextView;
    TextView contraTextView;
    TextView oldcontraTextView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        user =(User) getIntent().getSerializableExtra("usuario");
        imageView = findViewById(R.id.update_img);
        nombreTextView = findViewById(R.id.update_name);
        contraTextView = findViewById(R.id.update_pass);
        apellidosTextView = findViewById(R.id.update_surname);
        oldcontraTextView = findViewById(R.id.update_oldpass);
        Conectar();
    }

    public void Conectar(){
        // Crear instancia de la interfaz
        userServiceApi = RetrofitConexion.getUserServiceApi();
        getUserID();
    }

    public void getUserID(){
        SharedPreferences sharedPreferences = getSharedPreferences("UsuarioID", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        Call<User> call = userServiceApi.getUserById(userId);;
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User userId = response.body();
                    if (userId != null) {
                        user = userId;
                        GetUser();
                    }
                } else {
                    Toast.makeText(UpdateUser.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    public void GetUser(){
        nombreTextView.setText(user.getName());
        apellidosTextView.setText(user.getApellidos());
        if(user.getImgPerfil() != null){
            Bitmap bitmap = base64ToBitmap(user.getImgPerfil());
            imageView.setImageBitmap(bitmap);
        }
    }

    public void ModUser(View view){
        String nombreString = nombreTextView.getText().toString();
        String apellidosString = apellidosTextView.getText().toString();
        String oldcontraString = oldcontraTextView.getText().toString();
        String contraString = contraTextView.getText().toString();

        Toast.makeText(this, "Contra:" + contraString, Toast.LENGTH_SHORT).show();
        if(oldcontraString.equals(user.getContrasenia())){
            user.setName(nombreString);
            user.setApellidos(apellidosString);
            user.setContrasenia(contraString);
            user.setImgPerfil(bitmapToBase64(bitmap));

            Call<User> call = userServiceApi.updateUser(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User insertedUser = response.body();
                        if (insertedUser != null) {
                            Intent intent = new Intent(UpdateUser.this, MainScreen.class);
                            intent.putExtra("usuario", insertedUser);
                            startActivity(intent, ActivityOptions.makeCustomAnimation(UpdateUser.this, R.anim.fade_in, R.anim.fade_out).toBundle());
                        } else {
                            Toast.makeText(UpdateUser.this, "Usuario insertado nulo", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(UpdateUser.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    // Manejar el fallo de la llamada
                    Toast.makeText(UpdateUser.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this, "La contraseña anterior no coincide", Toast.LENGTH_SHORT).show();
        }
    }

    public Bitmap base64ToBitmap(String base64Image) {
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public void selectImageFromGallery(View view) {
        Intent intent = new Intent();
        intent.setType("image_yellow/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}