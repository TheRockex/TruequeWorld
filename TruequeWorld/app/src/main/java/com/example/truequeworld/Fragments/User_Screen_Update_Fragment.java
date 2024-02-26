package com.example.truequeworld.Fragments;

import static android.app.Activity.RESULT_OK;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truequeworld.Class.User;
import com.example.truequeworld.Interface.UserServiceApi;
import com.example.truequeworld.MainScreen;
import com.example.truequeworld.R;
import com.example.truequeworld.UpdateUser;
import com.example.truequeworld.retrofit.RetrofitConexion;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Screen_Update_Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    User user;
    private UserServiceApi userServiceApi;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    private RoundedImageView imageView;
    private TextInputEditText nombreInputEditText;
    private TextView nombredefault;
    private TextInputEditText apellidosInputEditText;
    private TextInputEditText contraInputEditText;
    private TextInputEditText confirmcontraInputEditText;
    private TextInputEditText telefonoInputEditText;
    private TextView nombre;
    private MaterialButton button;

    public User_Screen_Update_Fragment() {}

    public static User_Screen_Update_Fragment newInstance(String param1, String param2) {
        User_Screen_Update_Fragment fragment = new User_Screen_Update_Fragment();
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

    public void Conectar(){
        userServiceApi = RetrofitConexion.getUserServiceApi();
        getUserID();
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
                        GetUser();
                    }
                } else {
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    public void backUser(View view) {
        User_Screen_Fragment userFrag = new User_Screen_Fragment();

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.f5_fragment_user__screen__update, userFrag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f5_fragment_user__screen__update, container, false);
        imageView = (RoundedImageView) view.findViewById(R.id.imageEdit);
        nombreInputEditText = view.findViewById(R.id.updatename);
        nombredefault = view.findViewById(R.id.nombredefault);
        apellidosInputEditText = view.findViewById(R.id.updateApellidos);
        contraInputEditText = view.findViewById(R.id.updatecontra);
        confirmcontraInputEditText = view.findViewById(R.id.confirmupdatecontra);
        nombre = view.findViewById(R.id.nombredefault);
        button = view.findViewById(R.id.updatebutton);
        telefonoInputEditText = view.findViewById(R.id.updatetelefono);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageFromGallery();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModUser();
            }
        });

        Conectar();

        View closeUpdate = view.findViewById(R.id.close_edit);
        closeUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backUser(view);
            }
        });
        return view;
    }

    public void GetUser(){
        nombreInputEditText.setText(user.getName());
        nombredefault.setText(user.getName());
        apellidosInputEditText.setText(user.getApellidos());
        if(user.getImgPerfil() != null){
            Bitmap bitmap = base64ToBitmap(user.getImgPerfil());
            ((RoundedImageView) imageView).setImageBitmap(bitmap);
            ((RoundedImageView) imageView).setCornerRadius(500);
        }
    }


    public void ModUser(){
        String nombre = nombreInputEditText.getText().toString();
        String apellidos = apellidosInputEditText.getText().toString();
        String contra = contraInputEditText.getText().toString();
        String confirmcontra = confirmcontraInputEditText.getText().toString();
        String telefono = telefonoInputEditText.getText().toString();

            if(contra.equals(user.getContrasenia())){
                user.setName(nombre);
                user.setApellidos(apellidos);
                user.setContrasenia(confirmcontra);
                if(bitmap != null){
                    String IMGString = bitmapToBase64(bitmap);
                    user.setImgPerfil(IMGString);
                }
                user.setMovil(telefono);
            Call<User> call = userServiceApi.updateUser(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User insertedUser = response.body();
                        if (insertedUser != null) {
                            Toast.makeText(requireContext(), "Usuario actualizado", Toast.LENGTH_SHORT).show();

                        } else {
                            Log.d("UU","Usuario Vacio");
                        }
                    } else {
                        Log.d("UU","No Usuario Actualizado");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d("UU","Error al actualizar");
                }
            });
        }else{
                Toast.makeText(requireContext(), "La contraseña es la misma a la actual", Toast.LENGTH_SHORT).show();
        }
    }

    public String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public Bitmap base64ToBitmap(String base64Image) {
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public void selectImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}