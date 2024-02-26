package com.example.truequeworld.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequeworld.Adapters.Products_Exchange_Adapter;
import com.example.truequeworld.Adapters.Products_Profile_Adapter;
import com.example.truequeworld.Clases_RecyclerView.Products_Profile_Model;
import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.Interface.UserServiceApi;
import com.example.truequeworld.R;
import com.example.truequeworld.retrofit.RetrofitConexion;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Profile_Products_Exchange_Fragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    User user;
    private UserServiceApi userServiceApi;
    private ProductServiceApi productServiceApi;
    private RoundedImageView imageView;
    private TextView nombre;
    RecyclerView rvProductsEx;
    List<Product> productList = new ArrayList<>();
    ArrayList<Products_Profile_Model> productsExModels = new ArrayList<>();
    Products_Exchange_Adapter adapter;
    RecyclerView rvMain;

    public Profile_Products_Exchange_Fragment() {}

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
                        if(user.getImgPerfil() != null){
                            Bitmap bitmap = base64ToBitmap(user.getImgPerfil());
                            ((RoundedImageView) imageView).setImageBitmap(bitmap);
                            ((RoundedImageView) imageView).setCornerRadius(500);
                        }
                        nombre.setText(user.getName());
                        Productos();
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

        fragmentTransaction.replace(R.id.f6_fragment_profile_products_toexchange__screen, mainFrag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f6_fragment_profile_products_toexchange__screen, container, false);
        imageView = (RoundedImageView) view.findViewById(R.id.user_profile);
        nombre = view.findViewById(R.id.username_exchange_products);
        rvProductsEx = view.findViewById(R.id.rvMyProducts);
        rvMain = view.findViewById(R.id.rvMyProducts);
        Conectar();

        View cancelButton = view.findViewById(R.id.cancel_exchange_products);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelEx(view);
            }
        });

        return view;
    }

    public Bitmap base64ToBitmap(String base64Image) {
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }



    public void Productos() {
        Log.d("OPP", "Hola");
        Call<List<Product>> call = productServiceApi.getProductsUserByEstado(1,user.getId());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList = response.body();
                    for (Product product : productList) {
                        Log.d("OPP", "PR:" + product.getNombre());

                    }
                    setRvMain();
                    adapter = new Products_Exchange_Adapter(requireContext(), productsExModels);
                    rvMain.setAdapter(adapter);
                    GridLayoutManager managerlayout = new GridLayoutManager(requireContext(),2);
                    rvMain.setLayoutManager(managerlayout);
                } else {
                    Log.d("OPP", "Error");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("OPP", "Error2");
            }
        });
    }

    private void setRvMain() {
        for (int i = 0; i < productList.size(); i++) {
            productsExModels.add(new Products_Profile_Model(
                    productList.get(i).getNombre(),
                    base64ToBitmap(productList.get(i).getImgProducto()),
                    productList.get(i).getId()
            ));
        }
    }

}