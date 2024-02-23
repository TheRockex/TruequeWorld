package com.example.truequeworld.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.truequeworld.Adapters.Exchange_Received_Adapter;
import com.example.truequeworld.Clases_RecyclerView.Exchange_Model;
import com.example.truequeworld.Clases_RecyclerView.Exchange_Received_Model;
import com.example.truequeworld.R;

import java.util.ArrayList;

public class Exchanges_Screen_Requests_Fragment extends Fragment {

    ArrayList<Exchange_Received_Model> exReceivedModels = new ArrayList<>();
    RecyclerView rvExReceived;
    ImageView close;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public Exchanges_Screen_Requests_Fragment() {}

    public static Exchanges_Screen_Requests_Fragment newInstance(String param1, String param2) {
        Exchanges_Screen_Requests_Fragment fragment = new Exchanges_Screen_Requests_Fragment();
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

    public void backSent(View view) {
        Exchanges_Screen_Fragment sentFrag = new Exchanges_Screen_Fragment();

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.f4_fragment_exchanges__screen__requests, sentFrag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f4_fragment_exchanges__screen__requests, container, false);
        Log.d("SI", "nuh uhhhhhhhhhhh");

        rvExReceived = view.findViewById(R.id.rvRequestsReceived);
        setRvExReceived();
        LinearLayoutManager managerLayout = new LinearLayoutManager(requireContext());
        rvExReceived.setLayoutManager(managerLayout);
        Exchange_Received_Adapter adapter = new Exchange_Received_Adapter(requireContext(), exReceivedModels);
        rvExReceived.setAdapter(adapter);

        View close = view.findViewById(R.id.close_received);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backSent(view);
            }
        });

        return view;
    }

    private void setRvExReceived() {

    }
}