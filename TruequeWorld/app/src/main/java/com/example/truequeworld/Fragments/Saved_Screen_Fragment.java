package com.example.truequeworld.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.truequeworld.Class.Favorito;
import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;
import com.example.truequeworld.Interface.FavoriteServiceApi;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.Interface.UserServiceApi;
import com.example.truequeworld.R;
import com.example.truequeworld.retrofit.RetrofitConexion;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Saved_Screen_Fragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public Saved_Screen_Fragment(){}
    private FavoriteServiceApi favoriteServiceApi;
    private ProductServiceApi productServiceApi;
    private UserServiceApi userServiceApi;
    User user;
    Product product;
    List<Product> productIDList = new ArrayList<>();
    List<Favorito> favoritoList = new ArrayList<>();

    public static Saved_Screen_Fragment newInstance(String param1, String param2) {
        Saved_Screen_Fragment fragment = new Saved_Screen_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Conectar();
        View view = inflater.inflate(R.layout.f2_fragment_saved__screen, container, false);
        return view;
    }
    public void Conectar(){
        productServiceApi = RetrofitConexion.getProductServiceApi();
        userServiceApi = RetrofitConexion.getUserServiceApi();
        favoriteServiceApi = RetrofitConexion.getFavoriteServiceApi();
        getUserID();
    }
    public void getUserID(){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UsuarioID", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);

        Call<User> call = userServiceApi.getUserById(userId);;
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User userId = response.body();
                    if (userId != null) {
                        user = userId;
                        Favoritos();
                    }
                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
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
                    Toast.makeText(requireContext(), "Es: " + product.getNombre(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Product> call, Throwable t) {
            }
        });
    }
    public void Favoritos() {
        Call<List<Product>> call = favoriteServiceApi.getFavoriteProductsByUserId(user.getId());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> productList = response.body();
                    Toast.makeText(requireContext(), "Alex es gei", Toast.LENGTH_SHORT).show();
                    // Haz algo con la lista de productos...
                } else {
                    Toast.makeText(requireContext(), "Error me cago en tus muertos", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    /*
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

     */

}