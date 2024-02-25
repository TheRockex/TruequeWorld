package com.example.truequeworld.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truequeworld.Adapters.Main_Adapter;
import com.example.truequeworld.Adapters.Saved_adapter;
import com.example.truequeworld.Clases_RecyclerView.Main_Model;
import com.example.truequeworld.Class.Favorito;
import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Interface.FavoriteServiceApi;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.Interface.UserServiceApi;
import com.example.truequeworld.R;
import com.example.truequeworld.retrofit.RetrofitConexion;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Main_Screen_Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    ArrayList<Main_Model> mainModels = new ArrayList<>();
    private ProductServiceApi productServiceApi;
    private UserServiceApi userServiceApi;
    private FavoriteServiceApi favoriteServiceApi;
    private BottomNavigationView navigationView;
    List<Product> productList = new ArrayList<>();
    private List<Favorito> savedFavorites;
    Product product;
    private ImageView imageview;
    private boolean permissionDenied = false;
    RecyclerView rvMain;
    FragmentManager fragmentManager;
    Main_Adapter adapter;

    public Main_Screen_Fragment(){}

    public static Main_Screen_Fragment newInstance(String param1, String param2) {
        Main_Screen_Fragment fragment = new Main_Screen_Fragment();
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
        View view = inflater.inflate(R.layout.f1_fragment_main__screen, container, false);
        //requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        rvMain = view.findViewById(R.id.rvProductsMain);
        Conectar();

        ImageView imageView = view.findViewById(R.id.button_search);
        TextInputEditText buscar = view.findViewById(R.id.searchEditText);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar(buscar);
            }
        });

        //imageview = view.findViewById(R.id.imageView);
        TextInputEditText buscarEditText = view.findViewById(R.id.searchEditText);

        // Configurar el listener para detectar cuando se presiona "Enter" en el teclado

        return view;
    }

    public void Conectar() {
        productServiceApi = RetrofitConexion.getProductServiceApi();
        userServiceApi = RetrofitConexion.getUserServiceApi();
        favoriteServiceApi = RetrofitConexion.getFavoriteServiceApi();
        Productos();
        //toProducts();
    }

    public void Productos() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UsuarioID", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        Call<List<Product>> call = productServiceApi.getProducts(userId);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList = response.body();
                    FavoritesUser();

                } else {
                    Log.d("Product", "Error");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("Product", "Error2");
            }
        });
    }

    public void FavoritesUser(){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UsuarioID", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        Call<List<Favorito>> call = favoriteServiceApi.getFavoritosUserid(userId);
        call.enqueue(new Callback<List<Favorito>>() {
            @Override
            public void onResponse(Call<List<Favorito>> call, Response<List<Favorito>> response) {
                if (response.isSuccessful()) {
                    savedFavorites = response.body();
                    setRvMain();
                    adapter = new Main_Adapter(requireContext(), mainModels,userId,savedFavorites);
                    rvMain.setAdapter(adapter);
                    GridLayoutManager managerlayout = new GridLayoutManager(requireContext(),2);
                    rvMain.setLayoutManager(managerlayout);
                    Log.d("CVF", "Se sacaron los favoritos");
                } else {
                    Log.d("CVF", "No se sacaron los favoritos");
                }
            }
            @Override
            public void onFailure(Call<List<Favorito>> call, Throwable t) {
                // Hubo un error en la solicitud, maneja el error aquí
                Log.d("CVF", "Error al sacar favoritos");
            }
        });
        Log.d("PANA","FavoriteUser");
    }

    public void buscar(TextInputEditText buscarEditText) {
        String buscarString = buscarEditText.getText().toString();
        if(buscarString.isEmpty() || buscarString.equals(null)){
            buscarString = "all";
        }
        Call<List<Product>> call = productServiceApi.buscarProductosPorNombreOCategoria(buscarString);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList = response.body();
                    Log.d("BUS", "Buscar" + productList.size());
                    mainModels.clear();
                    setRvMain();
                    adapter.notifyDataSetChanged();
                } else {
                    Log.d("BUS", "No funciona buscar");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("BUS", "Error Busqueda");
            }
        });
    }

    private void setRvMain() {
        Drawable mainSaveIcon = ContextCompat.getDrawable(requireContext(), R.drawable.navbar_saved_button_icon);
        for (int i = 0; i < productList.size(); i++) {
            mainModels.add(new Main_Model(
                    productList.get(i).getNombre(),
                    "Truequear",
                    base64ToBitmap(productList.get(i).getImgProducto()),
                    mainSaveIcon,
                    productList.get(i).getId()
            ));
        }
    }

    public void toProducts() {
        Profile_Products_Fragment prodFrag = new Profile_Products_Fragment();

        fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.exchange_container, prodFrag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public Bitmap base64ToBitmap(String base64Image) {
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}