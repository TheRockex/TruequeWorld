package com.example.truequeworld;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;
import com.example.truequeworld.Interface.ProductServiceApi;
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
    ArrayList<Product> productPreference = new ArrayList<>();
    List<Product> productList = new ArrayList<>();
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        user =(User) getIntent().getSerializableExtra("usuario");

        Productos();


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


    public void Conectar(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.129.8:8086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        productServiceApi = retrofit.create(ProductServiceApi.class);
    }

    public void Productos() {
        Conectar();
        // Realiza la solicitud GET
        Call<List<Product>> call = productServiceApi.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList = response.body();
                    for(int i = 0; i < productList.size();i++) {
                        if(user.getPreferencias() != null){
                        if(user.getPreferencias().contains(productList.get(i).getCategoria()) && !user.getId().equals(productList.get(i).getIdUsuario())){
                            //Introducir cardView productPreference
                            productPreference.add(productList.get(i));
                        }
                    }else{
                            //Introducir cardView productList

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

    public void buscar(TextInputEditText buscarEditText){

        String buscarString = buscarEditText.getText().toString();

        for(int  i = 0; i < productList.size();i++){
            if(productList.get(i).getNombre().contains(buscarString) || productList.get(i).getCategoria().contains(buscarString)){
                Toast.makeText(this, productList.get(i).getNombre(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void AnadirProduct(Dialog dialog){
        Conectar();

        TextView nombreTextView = dialog.findViewById(R.id.register_name);
        TextView descripcionTextView = dialog.findViewById(R.id.register_surname);
        TextView TPTextView = dialog.findViewById(R.id.register_email);
        TextView estadoTextView = dialog.findViewById(R.id.register_pass);
        TextView usuarioIDTextView = dialog.findViewById(R.id.register_pass);
        TextView categoriaTextView = dialog.findViewById(R.id.register_pass);
        TextView imgTextView = dialog.findViewById(R.id.register_pass);

        String nombreString = nombreTextView.getText().toString();
        String descripcionString = descripcionTextView.getText().toString();
        Integer TPString = Integer.valueOf(TPTextView.getText().toString());
        String estadoString = estadoTextView.getText().toString();
        Integer usuarioIDString = Integer.valueOf(usuarioIDTextView.getText().toString());
        String categoriaString = categoriaTextView.getText().toString();
        String imgString = imgTextView.getText().toString();


        Product newProduct = new Product(null,nombreString,descripcionString,TPString,estadoString,usuarioIDString,categoriaString,imgString);

        Call<Product> call = productServiceApi.insertProduct(newProduct);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Product insertedProduct = response.body();

                    if (insertedProduct != null) {
                        Intent intent = new Intent(MainScreen.this, Preference_screen.class);
                        startActivity(intent, ActivityOptions.makeCustomAnimation(MainScreen.this, R.anim.fade_in, R.anim.fade_out).toBundle());
                    } else {
                        Toast.makeText(MainScreen.this, "Producto insertado nulo", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Manejar el error de respuesta
                    Toast.makeText(MainScreen.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                // Manejar el fallo de la llamada
                Toast.makeText(MainScreen.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
