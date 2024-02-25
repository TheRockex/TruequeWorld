package com.example.truequeworld.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;
import com.example.truequeworld.Interface.UserServiceApi;
import com.example.truequeworld.R;
import com.example.truequeworld.SplashScreen;
import com.example.truequeworld.retrofit.RetrofitConexion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Screen_Fragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ImageView imageView;
    private TextView UserNametextView;
    private TextView UserIDtextView;
    private TextView UserTPtextView;

    private TextView MisProductostextView;
    User user;
    private UserServiceApi userServiceApi;

    public User_Screen_Fragment(){}

    public static User_Screen_Fragment newInstance(String param1, String param2) {
        User_Screen_Fragment fragment = new User_Screen_Fragment();
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

    public void changeToUpdate(View view) {
        User_Screen_Update_Fragment upFrag = new User_Screen_Update_Fragment();

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.user_container, upFrag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void changeToMiproduto(View view) {
        Profile_Products_Fragment upFragProduct = new Profile_Products_Fragment();

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.user_container, upFragProduct);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.f5_fragment_user__screen, container, false);
        imageView = rootView.findViewById(R.id.imageButton);
        UserNametextView = rootView.findViewById(R.id.userName);
        UserIDtextView = rootView.findViewById(R.id.userID);
        UserTPtextView = rootView.findViewById(R.id.userWalletpoints);
        MisProductostextView = rootView.findViewById(R.id.misproductos);
        Conectar();

        View updateUserButton = rootView.findViewById(R.id.updateUserButton);

        updateUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToUpdate(view);
            }
        });

        View MisproductosButton = rootView.findViewById(R.id.profile_products_button);
        MisproductosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToMiproduto(v);
            }
        });

        Button btnCerrarSesion = rootView.findViewById(R.id.button_logout);
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CerrarSesion();
            }
        });

        return rootView;
    }

    public void Conectar(){
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
                        UserINFO();
                    }
                } else {
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    public void UserINFO(){
        if(user.getImgPerfil() != null){
            Bitmap bitmap = base64ToBitmap(user.getImgPerfil());
            imageView.setImageBitmap(bitmap);
        }
        UserNametextView.setText(user.getName());
        UserIDtextView.setText("ID: " + user.getId());
        UserTPtextView.setText(user.getTruquepoints().toString() +"TP");

    }

    public Bitmap base64ToBitmap(String base64Image) {
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public void CerrarSesion(){
        SharedPreferences.Editor editor = requireActivity().getSharedPreferences("UsuarioID", MODE_PRIVATE).edit();
        editor.clear().apply();
        Intent intent = new Intent(requireContext(), SplashScreen.class);
        startActivity(intent, ActivityOptions.makeCustomAnimation(requireContext(), R.anim.fade_in, R.anim.fade_out).toBundle());
    }

}