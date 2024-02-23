package com.example.truequeworld.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.truequeworld.Adapters.Exchange_Adapter;
import com.example.truequeworld.Clases_RecyclerView.Exchange_Model;
import com.example.truequeworld.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class Exchanges_Screen_Fragment extends Fragment {

    ArrayList<Exchange_Model> exchangeModels = new ArrayList<>();
    RecyclerView rvExchange;
    MaterialButton requests_received;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public Exchanges_Screen_Fragment(){}

    public static Exchanges_Screen_Fragment newInstance(String param1, String param2) {
        Exchanges_Screen_Fragment fragment = new Exchanges_Screen_Fragment();
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
        View view = inflater.inflate(R.layout.f4_fragment_exchanges__screen, container, false);

        rvExchange = view.findViewById(R.id.rvRequestsSent);
        setRvExchange();
        LinearLayoutManager managerLayout = new LinearLayoutManager(requireContext());
        rvExchange.setLayoutManager(managerLayout);
        Exchange_Adapter adapter = new Exchange_Adapter(requireContext(), exchangeModels);
        rvExchange.setAdapter(adapter);

        requests_received = view.findViewById(R.id.requests_received);
        requests_received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getChildFragmentManager().beginTransaction()
                        .replace(getId(), new Exchanges_Screen_Requests_Fragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    private void setRvExchange() {

    }
}