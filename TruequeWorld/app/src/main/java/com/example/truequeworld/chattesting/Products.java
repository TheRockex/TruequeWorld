package com.example.truequeworld.chattesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;
import com.example.truequeworld.HiloCliente;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.R;
import com.example.truequeworld.RecyclerViews.ProductsRVAdapter;
import com.example.truequeworld.StartScreen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Products extends AppCompatActivity {

    User user;
    ProductServiceApi productServiceApi;
    List<Product> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.128.235:8086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        productServiceApi = retrofit.create(ProductServiceApi.class);

        user = (User) getIntent().getSerializableExtra("usuario");

        HiloCliente hiloCliente = new HiloCliente(user);

        hiloCliente.start();
        Call<List<Product>> call = productServiceApi.getProducts();
        call.enqueue(new Callback<List<Product>>() {

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    RecyclerView recyclerView =findViewById(R.id.recicler);
                    ProductsRVAdapter adapter = new ProductsRVAdapter(Products.this,response.body(),user);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Products.this));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}