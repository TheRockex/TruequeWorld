package com.example.truequeworld.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.truequeworld.R;
import com.example.truequeworld.SplashScreen;

public class User_Screen_Fragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public User_Screen_Fragment(){}

    public static User_Screen_Fragment newInstance(String param1, String param2) {
        User_Screen_Fragment fragment = new User_Screen_Fragment();
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
        View rootView = inflater.inflate(R.layout.f5_fragment_user__screen, container, false);

        Button btnCerrarSesion = rootView.findViewById(R.id.button_logout);
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CerrarSesion();
            }
        });

        return rootView;
    }

    public void CerrarSesion(){
        SharedPreferences.Editor editor = requireActivity().getSharedPreferences("UsuarioID", MODE_PRIVATE).edit();
        editor.clear().apply();
        Intent intent = new Intent(requireContext(), SplashScreen.class);
        startActivity(intent, ActivityOptions.makeCustomAnimation(requireContext(), R.anim.fade_in, R.anim.fade_out).toBundle());
    }

}