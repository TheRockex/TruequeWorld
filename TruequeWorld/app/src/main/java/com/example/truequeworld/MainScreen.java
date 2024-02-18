package com.example.truequeworld;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
<<<<<<< Updated upstream
=======
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
>>>>>>> Stashed changes

import com.example.truequeworld.Class.Favorito;
import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
import com.example.truequeworld.Fragments.Main_Screen_Fragment;
import com.example.truequeworld.Interface.FavoriteServiceApi;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.Interface.UserServiceApi;
import com.google.android.gms.dynamic.SupportFragmentWrapper;
=======
import com.example.truequeworld.Fragments.Add_Product_Fragment;
import com.example.truequeworld.Fragments.Main_Screen_Fragment;
import com.example.truequeworld.Fragments.Messages_Screen_Fragment;
import com.example.truequeworld.Fragments.Saved_Screen_Fragment;
import com.example.truequeworld.Fragments.User_Screen_Fragment;
import com.example.truequeworld.Interface.FavoriteServiceApi;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.Interface.UserServiceApi;
>>>>>>> Stashed changes
=======
import com.example.truequeworld.Fragments.Add_Product_Fragment;
import com.example.truequeworld.Fragments.Main_Screen_Fragment;
import com.example.truequeworld.Fragments.Messages_Screen_Fragment;
import com.example.truequeworld.Fragments.Saved_Screen_Fragment;
import com.example.truequeworld.Fragments.User_Screen_Fragment;
import com.example.truequeworld.Interface.FavoriteServiceApi;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.Interface.UserServiceApi;
import com.example.truequeworld.databinding.A6ActivityMainScreenBinding;
>>>>>>> Stashed changes
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
<<<<<<< Updated upstream
=======

    A6ActivityMainScreenBinding binding;
>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< Updated upstream
        setContentView(R.layout.a6_activity_main_screen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

<<<<<<< Updated upstream
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new Main_Screen_Fragment())
                    .addToBackStack(null)
                    .commit();
        }
=======
=======
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
                    replaceFragment(new Messages_Screen_Fragment());
                } else if (itemId == R.id.navbar_user_button) {
                    replaceFragment(new User_Screen_Fragment());
                }

                return true;
            }
        });
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

>>>>>>> Stashed changes
        //mainFragment = getSupportFragmentManager().findFragmentById(R.id.main_screen);

        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Conectar();

        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_screen, new Main_Screen_Fragment())
                    .addToBackStack(null)
                    .commit();
        }*/
<<<<<<< Updated upstream
>>>>>>> Stashed changes

        imageview = findViewById(R.id.imageView);
=======

        /*imageview = findViewById(R.id.imageView);
>>>>>>> Stashed changes
        TextInputEditText buscarEditText = findViewById(R.id.searchEditText);

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
<<<<<<< Updated upstream
        });

        navigationView = findViewById(R.id.bottomNavigationViewMain);
=======
        });*/

        /*navigationView = findViewById(R.id.bottomNavigationViewMain);
>>>>>>> Stashed changes

        navigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.navbar_main_button) {
                fragment = new Main_Screen_Fragment();
            } else if (itemId == R.id.navbar_saved_button) {
                fragment = new Saved_Screen_Fragment();
            } else if (itemId == R.id.navbar_addproduct_button) {
                fragment = new Add_Product_Fragment();
            } else if (itemId == R.id.navbar_messages_button) {
                fragment = new Messages_Screen_Fragment();
            } else if (itemId == R.id.navbar_user_button) {
                fragment = new User_Screen_Fragment();
            }

            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(0, 0)
                        .replace(R.id.main_screen, fragment)
                        .addToBackStack(null)
                        .commit();
                return true;
            }

            return false;
<<<<<<< Updated upstream
        });
=======
        });*/
>>>>>>> Stashed changes

        /*navigationView = findViewById(R.id.bottomNavigationViewMain);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.navbar_main_button) {
                    fragment = new Main_Screen_Fragment();
                } else if (item.getItemId() == R.id.navbar_saved_button) {
                    fragment = new Saved_Screen_Fragment();
                } else if (item.getItemId() == R.id.navbar_addproduct_button) {
                    fragment = new Add_Product_Fragment();
                } else if (item.getItemId() == R.id.navbar_messages_button) {
                    fragment = new Messages_Screen_Fragment();
                } else if (item.getItemId() == R.id.navbar_user_button) {
                    fragment = new User_Screen_Fragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_screen, fragment).addToBackStack(null).commit();
                return true;
            }
        });*/
    }

<<<<<<< Updated upstream
    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (permissionDenied) {
            showMissingPermissionError();
            permissionDenied = false;
        }
    }

    private void showMissingPermissionError() {
        // Muestra un mensaje de error al usuario
=======
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
>>>>>>> Stashed changes
    }

    public void getUserID(){
        SharedPreferences sharedPreferences = getSharedPreferences("UsuarioID", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        Call<User> call = userServiceApi.getUserById(userId);;
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User userId = response.body();
                    if (userId != null) {
                        user = userId;
                        Productos();
                        Favoritos();
                    }
                } else {
                    Toast.makeText(MainScreen.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }


    public void Conectar(){
        Retrofit retrofit = new Retrofit.Builder()
<<<<<<< Updated upstream
                .baseUrl("http://192.168.0.30:8086")
=======
                .baseUrl("http://192.168.0.41:8086")
>>>>>>> Stashed changes
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        productServiceApi = retrofit.create(ProductServiceApi.class);
        favoriteServiceApi = retrofit.create(FavoriteServiceApi.class);
        userServiceApi = retrofit.create(UserServiceApi.class);
        getUserID();
    }

    public void Productos() {
        Call<List<Product>> call = productServiceApi.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList = response.body();
                    for(int i = 0; i < productList.size();i++) {
                        if(user.getPreferencias() != null){
<<<<<<< Updated upstream
<<<<<<< Updated upstream
                        if(user.getPreferencias().contains(productList.get(i).getCategoria()) && !user.getId().equals(productList.get(i).getIdUsuario())){
                            //Introducir cardView productPreference
                            productPreference.add(productList.get(i));
                        }
                    }else{
=======
=======
>>>>>>> Stashed changes
                            if(user.getPreferencias().contains(productList.get(i).getCategoria()) && !user.getId().equals(productList.get(i).getIdUsuario())){
                                //Introducir cardView productPreference
                                productPreference.add(productList.get(i));
                            }
                        }else{
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
                            //Introducir cardView productList
                            Bitmap bitmap = base64ToBitmap(productList.get(i).getImgProducto());
                            imageview.setImageBitmap(bitmap);
                        }
                    }
                } else {
                    Toast.makeText(MainScreen.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
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




    public void Favoritos() {
        Call<List<Favorito>> call = favoriteServiceApi.getFavoritos();
        call.enqueue(new Callback<List<Favorito>>() {
            @Override
            public void onResponse(Call<List<Favorito>> call, Response<List<Favorito>> response) {
                if (response.isSuccessful()) {
                    favoritoList = response.body();
                    for(int i = 0; i < favoritoList.size();i++) {
                        if(user.getId().equals(favoritoList.get(i).getUsuarioId())){
                            getProductosId(favoritoList.get(i).getProductoId());
                        }
                    }
                } else {
                    Toast.makeText(MainScreen.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Favorito>> call, Throwable t) {
            }
        });
    }

<<<<<<< Updated upstream
=======
    public Bitmap base64ToBitmap(String base64Image) {
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
/*
    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (permissionDenied) {
            showMissingPermissionError();
            permissionDenied = false;
        }
    }

    private void showMissingPermissionError() {
        // Muestra un mensaje de error al usuario
    }

    public void getUserID(){
        SharedPreferences sharedPreferences = getSharedPreferences("UsuarioID", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        Call<User> call = userServiceApi.getUserById(userId);;
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User userId = response.body();
                    if (userId != null) {
                        user = userId;
                        Productos();
                        Favoritos();
                    }
                } else {
                    Toast.makeText(MainScreen.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }


    public void Conectar(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.41:8086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        productServiceApi = retrofit.create(ProductServiceApi.class);
        favoriteServiceApi = retrofit.create(FavoriteServiceApi.class);
        userServiceApi = retrofit.create(UserServiceApi.class);
        getUserID();
    }

    public void Productos() {
        Call<List<Product>> call = productServiceApi.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList = response.body();
                    for(int i = 0; i < productList.size();i++) {
                        if(user.getPreferencias() != null){
                            if(user.getPreferencias().contains(productList.get(i).getCategoria()) && !user.getId().equals(productList.get(i).getIdUsuario())){
                                //Introducir cardView productPreference
                                productPreference.add(productList.get(i));
                            }
                        }else{
                            //Introducir cardView productList
                            Bitmap bitmap = base64ToBitmap(productList.get(i).getImgProducto());
                            imageview.setImageBitmap(bitmap);
                        }
                    }
                } else {
                    Toast.makeText(MainScreen.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
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




    public void Favoritos() {
        Call<List<Favorito>> call = favoriteServiceApi.getFavoritos();
        call.enqueue(new Callback<List<Favorito>>() {
            @Override
            public void onResponse(Call<List<Favorito>> call, Response<List<Favorito>> response) {
                if (response.isSuccessful()) {
                    favoritoList = response.body();
                    for(int i = 0; i < favoritoList.size();i++) {
                        if(user.getId().equals(favoritoList.get(i).getUsuarioId())){
                            getProductosId(favoritoList.get(i).getProductoId());
                        }
                    }
                } else {
                    Toast.makeText(MainScreen.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Favorito>> call, Throwable t) {
            }
        });
    }

>>>>>>> Stashed changes
    public Bitmap base64ToBitmap(String base64Image) {
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }


    public void buscar(TextInputEditText buscarEditText){
        String buscarString = buscarEditText.getText().toString();
        for(int  i = 0; i < productList.size();i++){
            if(productList.get(i).getNombre().contains(buscarString) || productList.get(i).getCategoria().contains(buscarString)){
                Toast.makeText(this, productList.get(i).getNombre(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void ToUpdate(View view){
        Intent intent = new Intent(MainScreen.this, RegisterScreen.class);
        startActivity(intent, ActivityOptions.makeCustomAnimation(MainScreen.this, R.anim.fade_in, R.anim.fade_out).toBundle());
<<<<<<< Updated upstream
    }
=======
    }*/
>>>>>>> Stashed changes
}
