package com.example.truequeworld;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truequeworld.Class.Product;
        import com.example.truequeworld.Interface.ProductServiceApi;
        import java.util.List;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

public class MainScreen extends AppCompatActivity {
    private ProductServiceApi productServiceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Productos();
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
                    List<Product> productList = response.body();
                    Toast.makeText(MainScreen.this, "Hola", Toast.LENGTH_SHORT).show();
                    for(int i = 0; i < productList.size();i++) {
                        Toast.makeText(MainScreen.this, productList.get(i).getNombre(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(MainScreen.this, "Adios", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        });
    }
}
