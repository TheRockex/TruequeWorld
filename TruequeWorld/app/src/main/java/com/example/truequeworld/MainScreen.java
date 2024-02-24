package com.example.truequeworld;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.truequeworld.Class.Favorito;
import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;
import com.example.truequeworld.Fragments.Add_Product_Fragment;
import com.example.truequeworld.Fragments.Exchanges_Screen_Fragment;
import com.example.truequeworld.Fragments.Main_Screen_Fragment;
import com.example.truequeworld.Fragments.Saved_Screen_Fragment;
import com.example.truequeworld.Fragments.User_Screen_Fragment;
import com.example.truequeworld.Interface.FavoriteServiceApi;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.Interface.UserServiceApi;
import com.example.truequeworld.databinding.A6ActivityMainScreenBinding;
import com.example.truequeworld.retrofit.RetrofitConexion;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreen extends AppCompatActivity {
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
    Fragment mainFragment;
    private boolean permissionDenied = false;

    A6ActivityMainScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = A6ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Main_Screen_Fragment());

        binding.bottomNavigationViewMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.navbar_main_button) {
                    replaceFragment(new Main_Screen_Fragment());
                } else if (itemId == R.id.navbar_saved_button) {
                    replaceFragment(new Saved_Screen_Fragment());
                } else if (itemId == R.id.navbar_addproduct_button) {
                    replaceFragment(new Add_Product_Fragment());
                } else if (itemId == R.id.navbar_messages_button) {
                    replaceFragment(new Exchanges_Screen_Fragment());
                } else if (itemId == R.id.navbar_user_button) {
                    replaceFragment(new User_Screen_Fragment());
                }

                return true;
            }
        });

        Conectar();

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public void Conectar(){
        productServiceApi = RetrofitConexion.getProductServiceApi();
        favoriteServiceApi = RetrofitConexion.getFavoriteServiceApi();
        userServiceApi = RetrofitConexion.getUserServiceApi();
        //getUserID();
    }

    public void getProductosId(Integer id) {
        Call<Product> call = productServiceApi.getproductById(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    product = response.body();
                    productIDList.add(product);
                    Toast.makeText(MainScreen.this, "Es: " + product.getNombre(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainScreen.this, "Error", Toast.LENGTH_SHORT).show();
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
}
