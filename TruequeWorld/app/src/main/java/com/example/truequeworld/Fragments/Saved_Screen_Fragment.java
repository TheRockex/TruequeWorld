package com.example.truequeworld.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.truequeworld.Adapters.Main_Adapter;
import com.example.truequeworld.Adapters.Saved_adapter;
import com.example.truequeworld.Clases_RecyclerView.Main_Model;
import com.example.truequeworld.Clases_RecyclerView.Saved_Model;
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
    List<Product> productList = new ArrayList<>();
    ArrayList<Saved_Model> savedModels = new ArrayList<>();
    RecyclerView rvMain;

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
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        rvMain = view.findViewById(R.id.rvProductsFavorite);

        return view;
    }
    public void Conectar(){
        productServiceApi = RetrofitConexion.getProductServiceApi();
        userServiceApi = RetrofitConexion.getUserServiceApi();
        favoriteServiceApi = RetrofitConexion.getFavoriteServiceApi();
        Favoritos();
    }

    public void Favoritos() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UsuarioID", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        Call<List<Product>> call = favoriteServiceApi.getFavoriteProductsByUserId(userId);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList = response.body();
                    setRvMain();
                    Saved_adapter adapter = new Saved_adapter(requireContext(), savedModels,userId);
                    rvMain.setAdapter(adapter);
                    rvMain.setLayoutManager(new LinearLayoutManager(requireContext()));
                } else {
                    Toast.makeText(requireContext(), "Error no se cargan los productos", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void setRvMain() {
        Drawable mainSaveIcon = ContextCompat.getDrawable(requireContext(), R.drawable.navbar_saved_button_icon);

        for (int i = 0; i < productList.size(); i++) {
            savedModels.add(new Saved_Model(
                    productList.get(i).getNombre(),
                    productList.get(i).getValorenTP(),
                    productList.get(i).getIdUsuario(),
                    base64ToBitmap(productList.get(i).getImgProducto()),
                    mainSaveIcon,
                    productList.get(i).getId()
            ));
        }
    }

    public Bitmap base64ToBitmap(String base64Image) {
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

}