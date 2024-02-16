package com.example.truequeworld;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truequeworld.Class.Favorito;
import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;
import com.example.truequeworld.Interface.FavoriteServiceApi;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.Interface.UserServiceApi;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

public class MainScreen extends AppCompatActivity {
    private ProductServiceApi productServiceApi;

    private UserServiceApi userServiceApi;

    private FavoriteServiceApi favoriteServiceApi;
    ArrayList<Product> productPreference = new ArrayList<>();
    List<Product> productList = new ArrayList<>();
    List<Product> productIDList = new ArrayList<>();
    List<Favorito> favoritoList = new ArrayList<>();
    User user;
    Product product;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Conectar();
        setContentView(R.layout.a6_activity_main_screen);
        imageview = findViewById(R.id.imageView);
        TextInputEditText buscarEditText = findViewById(R.id.searchEditText);

        // Configurar el listener para detectar cuando se presiona "Enter" en el teclado
        buscarEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    buscar(buscarEditText);
                    return true;
                }
                return false;
            }
        });
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
                        Productos();
                        Favoritos();
                    }
                } else {
                    Toast.makeText(MainScreen.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    public void Conectar(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.129.8:8086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        productServiceApi = retrofit.create(ProductServiceApi.class);
        favoriteServiceApi = retrofit.create(FavoriteServiceApi.class);
        userServiceApi = retrofit.create(UserServiceApi.class);
        getUserID();
    }

    public void Productos() {
        Call<List<Product>> call = productServiceApi.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList = response.body();
                    for(int i = 0; i < productList.size();i++) {
                        if(user.getPreferencias() != null){
                        if(user.getPreferencias().contains(productList.get(i).getCategoria()) && !user.getId().equals(productList.get(i).getIdUsuario())
                        && !productList.get(i).getEstado().equals("Truequeado")){
                            //Introducir cardView productPreference
                            productPreference.add(productList.get(i));
                            Bitmap bitmap = base64ToBitmap(productList.get(i).getImgProducto());
                            imageview.setImageBitmap(bitmap);
                        }
                    }else{
                            //Introducir cardView productList
                            Bitmap bitmap = base64ToBitmap(productList.get(i).getImgProducto());
                            imageview.setImageBitmap(bitmap);
                        }
                    }
                } else {
                    Toast.makeText(MainScreen.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        });
    }

    public void getProductosId(Integer id) {
        Call<Product> call = productServiceApi.getproductById(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    product = response.body();
                    productIDList.add(product);
                    Toast.makeText(MainScreen.this, "Es: " + product.getNombre(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainScreen.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Product> call, Throwable t) {
            }
        });
    }

    public void Favoritos() {
        Call<List<Favorito>> call = favoriteServiceApi.getFavoritos();
        call.enqueue(new Callback<List<Favorito>>() {
            @Override
            public void onResponse(Call<List<Favorito>> call, Response<List<Favorito>> response) {
                if (response.isSuccessful()) {
                    favoritoList = response.body();
                    for(int i = 0; i < favoritoList.size();i++) {
                       if(user.getId().equals(favoritoList.get(i).getUsuarioId())){
                           getProductosId(favoritoList.get(i).getProductoId());
                       }
                    }
                } else {
                    Toast.makeText(MainScreen.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Favorito>> call, Throwable t) {
            }
        });
    }

    public void deleteFavoriteById() {
        Call<Boolean> call = favoriteServiceApi.deleteFavoriteById(0);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body() != null && response.body()) {
                    // El favorito se eliminó exitosamente
                    Favoritos();
                } else {
                    Toast.makeText(MainScreen.this, "Error al eliminar el favorito", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(MainScreen.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Bitmap base64ToBitmap(String base64Image) {
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }


    public void buscar(TextInputEditText buscarEditText){
        String buscarString = buscarEditText.getText().toString();
        for(int  i = 0; i < productList.size();i++){
            if(productList.get(i).getNombre().contains(buscarString) || productList.get(i).getCategoria().contains(buscarString)){
                Toast.makeText(this, productList.get(i).getNombre(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void ToUpdate(View view){
        Intent intent = new Intent(MainScreen.this, UpdateUser.class);
        startActivity(intent, ActivityOptions.makeCustomAnimation(MainScreen.this, R.anim.fade_in, R.anim.fade_out).toBundle());
    }

    public void CerrarSesion(){
        SharedPreferences.Editor editor = getSharedPreferences("UsuarioID", MODE_PRIVATE).edit();
        editor.clear().apply();
        Intent intent = new Intent(MainScreen.this, SplashScreen.class);
        startActivity(intent, ActivityOptions.makeCustomAnimation(MainScreen.this, R.anim.fade_in, R.anim.fade_out).toBundle());
    }

    public void Trueque(){
        //Vendedor
        productList.get(0).setEstado("Truequeado");
        //Comprador
        //user.getId();
        Call<Product> call = productServiceApi.updateProduct(productList.get(0));
        call.enqueue(new Callback<Product>() {
         @Override
         public void onResponse(Call<Product> call, Response<Product> response) {
              if (response.isSuccessful()) {
                  Product insertedProduct = response.body();
                 if (insertedProduct != null) {


                    } else {
                      Toast.makeText(MainScreen.this, "Usuario insertado nulo", Toast.LENGTH_SHORT).show();
                    }
              } else {
                    Toast.makeText(MainScreen.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
              }
         }
         @Override
         public void onFailure(Call<Product> call, Throwable t) {
            Toast.makeText(MainScreen.this, "Error", Toast.LENGTH_SHORT).show();
         }
         });
    }
}

