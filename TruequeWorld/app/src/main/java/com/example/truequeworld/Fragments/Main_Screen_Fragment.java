package com.example.truequeworld.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.truequeworld.Class.Favorito;
import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;
import com.example.truequeworld.Interface.FavoriteServiceApi;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.Interface.UserServiceApi;
import com.example.truequeworld.R;

import java.util.ArrayList;
import java.util.List;


public class Main_Screen_Fragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f1_fragment_main__screen, container, false);
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //navigationView = view.findViewById(R.id.bottomNavigationViewAdmin);

        return view;
    }

    public Main_Screen_Fragment() {

    }

    public static Main_Screen_Fragment newInstance(String param1, String param2) {
        Main_Screen_Fragment fragment = new Main_Screen_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }


}