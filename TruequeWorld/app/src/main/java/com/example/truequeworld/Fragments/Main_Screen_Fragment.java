package com.example.truequeworld.Fragments;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truequeworld.Adapters.Main_Adapter;
import com.example.truequeworld.Clases_RecyclerView.Main_Model;
import com.example.truequeworld.Class.Favorito;
import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;
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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Main_Screen_Fragment extends Fragment {

    private ProductServiceApi productServiceApi;
    private UserServiceApi userServiceApi;
    private FavoriteServiceApi favoriteServiceApi;
    private BottomNavigationView navigationView;

    ArrayList<Product> productPreference = new ArrayList<>();
    List<Product> productList = new ArrayList<>();
    List<Product> productIDList = new ArrayList<>();
    List<Favorito> favoritoList = new ArrayList<>();

    User user;
    Product product;
    private ImageView imageview;
    private boolean permissionDenied = false;

    ArrayList<Main_Model> mainModels = new ArrayList<>();
    RecyclerView rvMain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Conectar();
        View view = inflater.inflate(R.layout.f1_fragment_main__screen, container, false);
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //recycler intento de funcionamiento
        rvMain = view.findViewById(R.id.rvProductsMain);
        setRvMain();
        LinearLayoutManager managerLayout = new LinearLayoutManager(requireContext());
        rvMain.setLayoutManager(managerLayout);
        Main_Adapter adapter = new Main_Adapter(requireContext(), mainModels);
        rvMain.setAdapter(adapter);

        /*try {

            Fragment mainScreenFragment = getChildFragmentManager().findFragmentById(R.id.main_screen);

            Button updateButton = view.findViewById(R.id.button_search);
            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToUpdate(v);
                }
            });

            if (mainScreenFragment == null) {
                mainScreenFragment = new Main_Screen_Fragment();
                getChildFragmentManager().beginTransaction().replace(R.id.main_screen, mainScreenFragment).commit();
            }*/

        imageview = view.findViewById(R.id.imageView);
        TextInputEditText buscarEditText = view.findViewById(R.id.searchEditText);

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
        return view;
    }


    /*public void onResume() {
        super.onResume();
        if (permissionDenied) {
            showMissingPermissionError();
            permissionDenied = false;
        }
    }

    private void showMissingPermissionError() {
        // Muestra un mensaje de error al usuario
    }*/

    public void Conectar(){
        productServiceApi = RetrofitConexion.getProductServiceApi();
        userServiceApi = RetrofitConexion.getUserServiceApi();
        favoriteServiceApi = RetrofitConexion.getFavoriteServiceApi();
        Productos();
    }

    public void Productos() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UsuarioID", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        Call<List<Product>> call = productServiceApi.getProducts(userId);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    Log.d("Product","Funciona");
                    List<Product> productos = response.body();
                    for (Product product : productos) {
                        // Aquí puedes hacer lo que necesites con cada producto

                    }
                } else {
                    Log.d("Product","Error");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("Product","Error2");
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

    public Bitmap base64ToBitmap(String base64Image) {
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }


    public void buscar(TextInputEditText buscarEditText){
        String buscarString = buscarEditText.getText().toString();
        for(int  i = 0; i < productList.size();i++){
            if(productList.get(i).getNombre().contains(buscarString) || productList.get(i).getCategoria().contains(buscarString)){
                Toast.makeText(requireContext(), productList.get(i).getNombre(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setRvMain() {
        //Esto es lo que rellena a las cardview, te dejo el ejemplo de lo que hice para mario, por si acaso
        /*
             private void setRvCurrency() {
        String[] curNames = getResources().getStringArray(R.array.cNames);
        String[] curValues = getResources().getStringArray(R.array.cChange);

        TypedArray typedArray = getResources().obtainTypedArray(R.array.cLogos);
        Drawable[] curLogos = new Drawable[typedArray.length()];

        for (int x = 0; x < typedArray.length(); x++) {
            int typed = typedArray.getResourceId(x, 0);
            if (typed != 0) {
                curLogos[x] = ContextCompat.getDrawable(this, typed);
            }
        }

        typedArray.recycle();

        for (int y = 0; y < curNames.length; y++) {
            currencyModels.add(new CurrencyModel(
                    curNames[y],
                    curValues[y],
                    curLogos[y]));
        }
    }
        */
    }

}