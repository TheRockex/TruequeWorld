package com.example.truequeworld.Fragments;

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
        TruequeServiceApi truequeServiceApi = RetrofitConexion.getTruequeServiceApi();

        Call<List<Trueque>> call = truequeServiceApi.getTruequesByUserEstado(3,1);
        call.enqueue(new Callback<List<Trueque>>() {
            @Override
            public void onResponse(Call<List<Trueque>> call, Response<List<Trueque>> response) {

                for(Trueque t : response.body()){

                    Log.d("Alex", "onResponse:1 ");
                    exchangeModels.add(new Exchange_Model(
                            getImg(t.getProductoSolicitado()),
                            getImg(t.getProductoInteresado()),
                            uplogo,
                            downlogo
                    ));
                }
            }

            @Override
            public void onFailure(Call<List<Trueque>> call, Throwable t) {
                Toast.makeText(getContext(), "ERROR.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getImg(Integer id){

        Log.d("Alex", "onResponse:2 "+ id);
        ProductServiceApi productServiceApi = RetrofitConexion.getProductServiceApi();
        Call<String> call = productServiceApi.getImgById(id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("Alex", "onResponse: 3");
                img1 = response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //Algo
            }
        });
        return img1;
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

        rvExchange = view.findViewById(R.id.rvRequestsSent);
        setRvExchange();
        LinearLayoutManager managerLayout = new LinearLayoutManager(requireContext());
        rvExchange.setLayoutManager(managerLayout);
        Exchange_Adapter adapter = new Exchange_Adapter(requireContext(), exchangeModels);
        rvExchange.setAdapter(adapter);

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