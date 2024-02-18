package com.example.truequeworld.Fragments;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.truequeworld.Class.Favorito;
import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;
import com.example.truequeworld.Interface.FavoriteServiceApi;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.Interface.UserServiceApi;
import com.example.truequeworld.R;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Conectar();
        View view = inflater.inflate(R.layout.f1_fragment_main__screen, container, false);
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

        //navigationView = view.findViewById(R.id.bottomNavigationViewMain);

            /*navigationView.setOnNavigationItemSelectedListener(item -> {
                Fragment fragment = null;
                int itemId = item.getItemId();

                if (itemId == R.id.navbar_saved_button) {
                    fragment = new Saved_Screen_Fragment();
                } else if (itemId == R.id.navbar_addproduct_button) {
                    fragment = new Add_Product_Fragment();
                } else if (itemId == R.id.navbar_messages_button) {
                    fragment = new Messages_Screen_Fragment();
                } else if (itemId == R.id.navbar_user_button) {
                    fragment = new User_Screen_Fragment();
                }

                if (fragment != null) {
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(0, 0)
                            .replace(R.id.main_screen, fragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
                }

                return false;
            });*/

            /*navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    if (item.getItemId() == R.id.navbar_saved_button) {
                        fragment = new Saved_Screen_Fragment();
                    } else if (item.getItemId() == R.id.navbar_addproduct_button) {
                        fragment = new Add_Product_Fragment();
                    } else if (item.getItemId() == R.id.navbar_messages_button) {
                        fragment = new Messages_Screen_Fragment();
                    } else if (item.getItemId() == R.id.navbar_user_button) {
                        fragment = new User_Screen_Fragment();
                    }
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_screen, fragment).addToBackStack(null).commit();
                    return true;
                }
            });*/

        return view;
        /*} catch (Exception e) {
            e.printStackTrace();
            Log.e("FragmentError", "Error in onCreateView: " + e.getMessage());
            return null;
        }*/
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
                        Productos();
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.129.8:8086")
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
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Favorito>> call, Throwable t) {
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

    public void ToUpdate (View v){
        Intent intent = new Intent(requireActivity(), User_Screen_Fragment.class);
        startActivity(intent, ActivityOptions.makeCustomAnimation(requireContext(), R.anim.fade_in, R.anim.fade_out).toBundle());
    }
}