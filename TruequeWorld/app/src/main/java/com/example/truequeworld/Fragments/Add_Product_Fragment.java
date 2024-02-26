package com.example.truequeworld.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.Interface.UserServiceApi;
import com.example.truequeworld.R;
import com.example.truequeworld.retrofit.RetrofitConexion;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Product_Fragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public Add_Product_Fragment(){}
    private ProductServiceApi productServiceApi;
    private UserServiceApi userServiceApi;
    User user;
    int userId;
    View view;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private Bitmap bitmap;

    private AlertDialog alertDialog;


    public static Add_Product_Fragment newInstance(String param1, String param2) {
        Add_Product_Fragment fragment = new Add_Product_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Conectar();
        view = inflater.inflate(R.layout.f3_fragment_add_product__screen, container, false);
        Button btnAddproduct = view.findViewById(R.id.button_add);
        btnAddproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addproduct();
            }
        });
        imageView = view.findViewById(R.id.add_tp_photo); // Aquí asigna la variable miembro imageView
        // Configurar el clic programáticamente
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageFromGallery();
            }
        });
        MaterialButton buttonPrice = view.findViewById(R.id.price_field);
        MaterialButton buttonCategory = view.findViewById(R.id.category_field);
        MaterialButton buttonEstado = view.findViewById(R.id.status_field);

// Asignar un OnClickListener al botón
        buttonPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View precio) {
                // Crear el constructor del MaterialAlertDialogBuilder
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
                // Inflar el diseño personalizado para el contenido del diálogo
                LayoutInflater inflater = requireActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.f3_x_alert_dialog_price, null);
                builder.setView(dialogView);
                // Encuentra los botones dentro del diseño personalizado
                MaterialButton set_Price_Button = dialogView.findViewById(R.id.set_price_button);
                MaterialButton cancel_button = dialogView.findViewById(R.id.cancel_button_price);
                TextInputEditText price_textField = dialogView.findViewById(R.id.price_edittext);
                TextView textviewprice = view.findViewById(R.id.textview_price);



                // Configura otros atributos del AlertDialog
                set_Price_Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View precio) {
                        // Hacer algo cuando se hace clic en el botón "FIJAR PRECIO"
                        String price_string = price_textField.getText().toString();
                        textviewprice.setText(price_string);




                        alertDialog.dismiss();
                    }
                });
                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View precio) {
                        // Hacer algo cuando se hace clic en el botón "CANCELAR"
                        alertDialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                // Mostrar el AlertDialog
                alertDialog.show();
            }

        });
        buttonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View category) {
                // Crear el constructor del MaterialAlertDialogBuilder
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
                // Inflar el diseño personalizado para el contenido del diálogo
                LayoutInflater inflater = requireActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.f3_x_alert_dialog_category, null);
                builder.setView(dialogView);
                // Encuentra los botones dentro del diseño personalizado
                MaterialButton set_category_button = dialogView.findViewById(R.id.set_category_button);
                MaterialButton cancel_button_category = dialogView.findViewById(R.id.cancel_button_category);
                TextInputEditText category_textfield = dialogView.findViewById(R.id.category_edittext);
                TextView textviewcategory = view.findViewById(R.id.textView_category);


                // Configura otros atributos del AlertDialog
                set_category_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View category) {
                        String category_string = category_textfield.getText().toString();

                        textviewcategory.setText(category_string);

                        // Hacer algo cuando se hace clic en el botón "FIJAR PRECIO"
                        alertDialog.dismiss();
                    }
                });
                cancel_button_category.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View category) {
                        // Hacer algo cuando se hace clic en el botón "CANCELAR"
                        alertDialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                // Mostrar el AlertDialog
                alertDialog.show();
            }

        });
        buttonEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View estado) {
                // Crear el constructor del MaterialAlertDialogBuilder
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
                // Inflar el diseño personalizado para el contenido del diálogo
                LayoutInflater inflater = requireActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.f3_x_alert_dialog_estado, null);
                builder.setView(dialogView);
                // Encuentra los botones dentro del diseño personalizado
                MaterialButton set_estado_button = dialogView.findViewById(R.id.set_estado_button);
                MaterialButton cancel_button_estado = dialogView.findViewById(R.id.cancel_button_estado);
                TextInputEditText estado_textField = dialogView.findViewById(R.id.estado_edittext);
                TextView textviewestado = view.findViewById(R.id.textView_status);

                // Configura otros atributos del AlertDialog
                set_estado_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View estado) {
                        // Hacer algo cuando se hace clic en el botón "FIJAR PRECIO"
                        String estado_string = estado_textField.getText().toString();
                        textviewestado.setText(estado_string);
                        alertDialog.dismiss();
                    }
                });
                cancel_button_estado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View estado) {
                        // Hacer algo cuando se hace clic en el botón "CANCELAR"
                        alertDialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                // Mostrar el AlertDialog
                alertDialog.show();
            }

        });

        return view;
    }


    public void Conectar(){
        userServiceApi = RetrofitConexion.getUserServiceApi();
        productServiceApi = RetrofitConexion.getProductServiceApi();
        getUserID();
    }

    public void getUserID(){
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UsuarioID", Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", 0);
        Call<User> call = userServiceApi.getUserById(userId);;
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User userId = response.body();
                    if (userId != null) {
                        user = userId;
                    }
                } else {

                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });

        Log.d("DEBUG", "Id de usuario: " + userId);
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


    public String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public void Addproduct(){
        TextInputLayout nombreInputLayout = view.findViewById(R.id.title_add_tp);
        EditText nombreEditText = nombreInputLayout.getEditText();
        EditText descripcionEditText = view.findViewById(R.id.descripcion);
        TextView textviewprice = view.findViewById(R.id.textview_price);
        TextView textviewcategory = view.findViewById(R.id.textView_category);
        TextView textviewestado = view.findViewById(R.id.textView_status);

        String nombreString = nombreEditText.getText().toString();
        String descripcionString = descripcionEditText.getText().toString();
        String textviewcategoryString = textviewcategory.getText().toString();
        String textviewestadoString = textviewestado.getText().toString();

        // parte del precio
        String textviewPriceString = textviewprice.getText().toString();
        String textviewPriceOnlyNumbers = textviewPriceString.replaceAll("[^0-9]", "");

        // Convert the numeric part to an integer
        int textviewPriceInt = Integer.parseInt(textviewPriceOnlyNumbers);

        Log.d("DEBUG", "Estos son tus datos: idActual " + userId + ", nombreString " + nombreString
                + ", descripcionString " + descripcionString + ", truequepuntos " + textviewPriceInt + ", categoria "
                + textviewcategoryString + ", estado " + textviewestadoString);
        //Integer textviewpriceString = Integer.parseInt(textviewprice.getText().toString());


        if(bitmap != null || nombreString == null || descripcionString == null || textviewPriceOnlyNumbers == null
                || textviewcategoryString == null || textviewestadoString == null || nombreString.isEmpty() || descripcionString.isEmpty()
                || textviewcategoryString.isEmpty() || textviewestadoString.isEmpty() || textviewPriceOnlyNumbers.isEmpty()){

            String IMGString = bitmapToBase64(bitmap);
            Product newProduct = new Product(null,nombreString,descripcionString,textviewPriceInt,1,userId,IMGString, textviewcategoryString);
            Call<Product> call = productServiceApi.insertProduct(newProduct);
            call.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    if (response.isSuccessful()) {
                        Product insertedProduct = response.body();
                        Log.e("Add_Product_Fragment", "Se insertó");
                        if (insertedProduct != null) {
                            Toast.makeText(requireContext(), "Producto Añadido", Toast.LENGTH_SHORT).show();
                            nombreEditText.setText("");
                            descripcionEditText.setText("");
                            textviewprice.setText("0 TP");
                            textviewcategory.setText("-");
                            textviewestado.setText("-");
                        } else {
                            Log.e("Add_Product_Fragment", "No se inserto");
                        }
                    } else {
                        Log.e("Add_Product_Fragment", "Error tontin");
                    }
                }
                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    // Manejar el fallo de la llamada
                }
            });
        }else {
            Log.d("DEBUG", "Imbécil");
        }
    }
}