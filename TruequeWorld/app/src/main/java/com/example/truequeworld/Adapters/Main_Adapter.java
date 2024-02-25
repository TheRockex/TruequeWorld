package com.example.truequeworld.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequeworld.Clases_RecyclerView.Main_Model;
import com.example.truequeworld.Class.Favorito;
import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Fragments.Profile_Products_Fragment;
import com.example.truequeworld.Interface.FavoriteServiceApi;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.R;
import com.example.truequeworld.retrofit.RetrofitConexion;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main_Adapter extends RecyclerView.Adapter<Main_Adapter.MyViewHolder> {
    Context context;
    ArrayList<Main_Model> mainModels;
    private LayoutInflater inflater;
    private FavoriteServiceApi favoriteServiceApi;
    private ProductServiceApi productServiceApi;

    public Integer userId;
    private List<Favorito> savedFavorites = new ArrayList<>();
    Product product;

    private AlertDialog alertDialog;

    public interface OnAcceptClickListener {
        void onAcceptClicked();
    }

    private OnAcceptClickListener onAcceptClickListener;

    public void setOnAcceptClickListener(OnAcceptClickListener listener) {
        this.onAcceptClickListener = listener;
    }


    public Main_Adapter(Context context, ArrayList<Main_Model> mainModels, int userId, List<Favorito> savedFavorites) {
        this.context = context;
        this.mainModels = mainModels;
        this.userId = userId;
        this.savedFavorites = savedFavorites;
        Conectar();
    }

    @NonNull
    @Override
    public Main_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Conectar();
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_main_screen, parent, false);
        return new Main_Adapter.MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull Main_Adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Main_Model currentProduct = mainModels.get(position);
        holder.productName.setText(mainModels.get(position).getMainName());
        holder.productEx.setText(mainModels.get(position).getMainEx());
        holder.productImg.setImageBitmap(mainModels.get(position).getMainImg());
        holder.saveButton.setImageDrawable(mainModels.get(position).getMainSave());
        Integer productId = currentProduct.getId();

        for (Favorito favorito : savedFavorites) {
            if (favorito.getProductoId().equals(productId)) {
                holder.saveButton.setBackgroundColor(ContextCompat.getColor(context, R.color.amarillo));
                Log.d("Patata", "Se mete al if" + savedFavorites.size());
                break;
            }
        }
        holder.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean found = false;
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    Main_Model clickedItem = mainModels.get(adapterPosition);
                    Integer productId = clickedItem.getId();
                    Favorito favorito = new Favorito(null,userId, productId);
                    Log.d("PANA","Tamaña" + savedFavorites.size());
                    if(savedFavorites.size() != 0){
                        for(int i = 0; i < savedFavorites.size();i++){
                            if (savedFavorites.get(i).getProductoId().equals(productId)) {
                                deleteFavoriteById(savedFavorites.get(i).getId() ,holder.saveButton);
                                savedFavorites.remove(savedFavorites.get(i));
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            AddFavorito(favorito, holder.saveButton);
                            found = false;
                        }
                    }else{
                        Log.d("PANA","Se mete al else2" + savedFavorites.size());
                        AddFavorito(favorito, holder.saveButton);
                    }

                }
            }

        });

        holder.productImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    Main_Model clickedItem = mainModels.get(adapterPosition);
                    Integer productId = clickedItem.getId();
                    getProductosId(productId);
                }

            }

        });


        holder.productEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Para ti Johan con amor xD aqui pa truequear
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(holder.itemView.getContext());
                LayoutInflater inflater1 = LayoutInflater.from(holder.itemView.getContext());
                View dialogView = inflater1.inflate(R.layout.f1_x_alert_dialog_exchange, null);
                builder.setView(dialogView);

                MaterialButton accept = dialogView.findViewById(R.id.acceptEx);
                MaterialButton reject = dialogView.findViewById(R.id.rejectEx);

                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Lógica al hacer clic en "Aceptar"
                        if (onAcceptClickListener != null) {
                            onAcceptClickListener.onAcceptClicked();
                        }
                    }
                });

                reject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        // Lógica al hacer clic en "Aceptar"
                        /*if (onAcceptClickListener != null) {
                            onAcceptClickListener.onAcceptClicked();
                        }*/
                    }
                });
                alertDialog = builder.create();
                // Mostrar el AlertDialog
                alertDialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mainModels.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView productImg;
        ImageView saveButton;
        TextView productName;
        TextView productEx;
        CardView cvMain;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cvMain = itemView.findViewById(R.id.cvMain);
            this.productName = itemView.findViewById(R.id.main_product_cv_name);
            this.productEx = itemView.findViewById(R.id.exchange_now);
            this.productImg = itemView.findViewById(R.id.product_mainscreen_image);
            this.saveButton = itemView.findViewById(R.id.add_favs_main_cv);
        }
    }

    public void Conectar() {
        favoriteServiceApi = RetrofitConexion.getFavoriteServiceApi();
        productServiceApi = RetrofitConexion.getProductServiceApi();
    }


    public void getProductosId(Integer id) {
        Call<Product> call = productServiceApi.getproductById(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    product = response.body();
                    Log.d("CV","EL producto funciono" + product.getDescripcion());

                } else {
                    Log.d("CV","EL producto no funciono");
                }
            }
            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.d("CV","EL producto error");
            }
        });
    }

    public void AddFavorito(Favorito favorito, ImageView saveButton){
        Call<Favorito> call = favoriteServiceApi.insertFavoritos(favorito);
        call.enqueue(new Callback<Favorito>() {
            @Override
            public void onResponse(Call<Favorito> call, Response<Favorito> response) {
                if (response.isSuccessful()) {
                    Log.d("CV", "Se inserto el favorito");
                    Favorito favoritoInsertado = response.body();
                    savedFavorites.add(favoritoInsertado);
                    saveButton.setBackgroundColor(ContextCompat.getColor(context, R.color.amarillo));
                } else {
                    Log.d("CV", "No se inserto el favorito");
                    // La solicitud no fue exitosa, maneja el error aquí
                }
            }

            @Override
            public void onFailure(Call<Favorito> call, Throwable t) {
                // Hubo un error en la solicitud, maneja el error aquí
                Log.d("CV", "Error al añadir");
            }
        });
    }

    public void deleteFavoriteById(Integer id, ImageView saveButton) {
        Call<Boolean> call = favoriteServiceApi.deleteFavoriteById(id);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Log.d("CV", "Se eliminó el favorito");
                    // Si se eliminó correctamente, establece el color de fondo predeterminado
                    saveButton.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
                } else {
                    Log.d("CV", "No se pudo eliminar el favorito");
                    // Maneja el error si la solicitud no fue exitosa
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                // Maneja el error si hubo un fallo en la solicitud
                Log.e("CV", "Error al eliminar el favorito: " + t.getMessage());
            }
        });
    }

}