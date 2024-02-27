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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.truequeworld.Adapters.Exchange_Adapter;
import com.example.truequeworld.Clases_RecyclerView.Exchange_Model;
import com.example.truequeworld.Clases_RecyclerView.Main_Model;
import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.Trueque;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.Interface.TruequeServiceApi;
import com.example.truequeworld.R;
import com.example.truequeworld.retrofit.RetrofitConexion;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Exchanges_Screen_Fragment extends Fragment {

    ArrayList<Exchange_Model> exchangeModels = new ArrayList<>();
    RecyclerView rvExchange;
    MaterialButton requests_received;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private String img1;
    private TruequeServiceApi truequeServiceApi;
    private ProductServiceApi productServiceApi;
    Integer userId;
    Product product;
    List<Product> productos = new ArrayList<>();

    public Exchanges_Screen_Fragment(){}

    public static Exchanges_Screen_Fragment newInstance(String param1, String param2) {
        Exchanges_Screen_Fragment fragment = new Exchanges_Screen_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    private void setRvExchange(){
        Drawable uplogo = ContextCompat.getDrawable(requireContext(), R.drawable.elegido_arriba_2);
        Drawable downlogo = ContextCompat.getDrawable(requireContext(), R.drawable.elegido_abajo_2);
        Log.d("Tag", "Se mete aqui");
        exchangeModels.add(new Exchange_Model(
                productos.get(0).getImgProducto(),
                productos.get(1).getImgProducto(),
                uplogo,
                downlogo
        ));
    }

    public void Conectar() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UsuarioID", Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", 0);
        productServiceApi = RetrofitConexion.getProductServiceApi();
        truequeServiceApi = RetrofitConexion.getTruequeServiceApi();
        Productos();
    }

    public void Productos(){
        Call<List<Trueque>> call = truequeServiceApi.getTruequesByUserEstado(userId,1);
        call.enqueue(new Callback<List<Trueque>>() {
            @Override
            public void onResponse(Call<List<Trueque>> call, Response<List<Trueque>> response) {
                for(Trueque t : response.body()){
                    Log.d("Tag", "ID" + t.getProductoSolicitado());
                    getImg(t.getProductoSolicitado(), t.getProductoInteresado());
                }
            }

            @Override
            public void onFailure(Call<List<Trueque>> call, Throwable t) {
                Toast.makeText(getContext(), "ERROR.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getImg(Integer id, Integer id2){
        Call<List<Product>> call = productServiceApi.getImgById(id, id2);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productos = response.body();
                setRvExchange();
                productos.clear();
                LinearLayoutManager managerLayout = new LinearLayoutManager(requireContext());
                rvExchange.setLayoutManager(managerLayout);
                Exchange_Adapter adapter = new Exchange_Adapter(requireContext(), exchangeModels);
                rvExchange.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("Alex", "onResponse: 4");
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void changeToReceived(View view) {
        Exchanges_Screen_Requests_Fragment recFrag = new Exchanges_Screen_Requests_Fragment();

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.sent_container, recFrag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f4_fragment_exchanges__screen, container, false);
        Conectar();
        rvExchange = view.findViewById(R.id.rvRequestsSent);
        View requests_received = view.findViewById(R.id.requests_received);
        requests_received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SI", "jijijija");
                changeToReceived(view);
            }
        });

        return view;
    }
}