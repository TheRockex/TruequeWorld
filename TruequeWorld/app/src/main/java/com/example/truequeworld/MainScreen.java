package com.example.truequeworld;

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
}
