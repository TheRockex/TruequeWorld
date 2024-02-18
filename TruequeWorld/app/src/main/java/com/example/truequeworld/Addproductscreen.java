package com.example.truequeworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.Interface.UserServiceApi;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Addproductscreen extends AppCompatActivity {

    private ProductServiceApi productServiceApi;
    private UserServiceApi userServiceApi;
    User user;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private Bitmap bitmap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproductscreen);
        Conectar();
        user =(User) getIntent().getSerializableExtra("usuario");
        imageView = findViewById(R.id.addphoto);
        MaterialButton button = findViewById(R.id.button_publicar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addproduct();
            }
        });
    }

    public void Conectar(){
        Retrofit retrofit = new Retrofit.Builder()
<<<<<<< Updated upstream
                .baseUrl("http://192.168.0.30:8086")
=======
                .baseUrl("http://192.168.0.41:8086")
>>>>>>> Stashed changes
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getUserID();
        // Crear instancia de la interfaz
        productServiceApi = retrofit.create(ProductServiceApi.class);
        productServiceApi = retrofit.create(ProductServiceApi.class);

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
                    }
                } else {
                    Toast.makeText(Addproductscreen.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    public void selectImageFromGallery(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
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

    public void Addproduct(){
            TextView nombreTextView = findViewById(R.id.newtitle);

            TextView descripcionTextView = findViewById(R.id.newtitle);
            TextView precioenTPTextView = findViewById(R.id.price_field);
            TextView categoriaTextView = findViewById(R.id.category_field);
            TextView estadoTextView = findViewById(R.id.estado_field);

            String nombreString = nombreTextView.getText().toString();
            String descripcionString = descripcionTextView.getText().toString();
            Integer precioenTPString = Integer.valueOf(precioenTPTextView.getText().toString());
            String categoriaString = categoriaTextView.getText().toString();
            String estadoString = estadoTextView.getText().toString();

            if(bitmap != null || nombreString == null || descripcionString == null || precioenTPString == null
                || categoriaString == null || estadoString == null || nombreString.isEmpty() || descripcionString.isEmpty() || categoriaString.isEmpty() || estadoString.isEmpty()){

            String IMGString = bitmapToBase64(bitmap);
            Product newProduct = new Product(0,nombreString,descripcionString,precioenTPString,estadoString,user.getId(),categoriaString,IMGString);

            Call<Product> call = productServiceApi.insertProduct(newProduct);
            call.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    if (response.isSuccessful()) {
                        Product insertedProduct = response.body();
                        if (insertedProduct != null) {
                            Intent intent = new Intent(Addproductscreen.this, Preference_screen.class);
                            startActivity(intent, ActivityOptions.makeCustomAnimation(Addproductscreen.this, R.anim.fade_in, R.anim.fade_out).toBundle());
                        } else {
                            Toast.makeText(Addproductscreen.this, "Producto insertado nulo", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Addproductscreen.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    // Manejar el fallo de la llamada
                    Toast.makeText(Addproductscreen.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(this, "Por favor rellene toda la información", Toast.LENGTH_SHORT).show();
        }
    }

    public String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
}