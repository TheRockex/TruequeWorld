package com.example.truequeworld.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequeworld.Class.User;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.Interface.UserServiceApi;
import com.example.truequeworld.R;
import com.example.truequeworld.retrofit.RetrofitConexion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Profile_Products_Fragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    User user;
    private UserServiceApi userServiceApi;
    private ProductServiceApi productServiceApi;
    private ImageView imageView;
    private TextView nombre;
    RecyclerView rvProductsEx;

    public Profile_Products_Fragment() {}

    public static  Profile_Products_Fragment newInstance(String param1, String param2) {
        Profile_Products_Fragment fragment = new Profile_Products_Fragment();
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

    public void Conectar(){
        productServiceApi = RetrofitConexion.getProductServiceApi();
        userServiceApi = RetrofitConexion.getUserServiceApi();
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
                    }
                } else {
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    public void cancelEx(View view)  {
        Main_Screen_Fragment mainFrag = new Main_Screen_Fragment();

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.f6_fragment_profile__screen, mainFrag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f6_fragment_profile_products__screen, container, false);
        imageView = view.findViewById(R.id.user_profile);
        nombre = view.findViewById(R.id.username_exchange_products);
        rvProductsEx = view.findViewById(R.id.rvMyProducts);
        Conectar();

        return view;

    }

    public Bitmap base64ToBitmap(String base64Image) {
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

}