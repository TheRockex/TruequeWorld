package com.example.truequeworld.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequeworld.Clases_RecyclerView.Main_Model;
import com.example.truequeworld.Class.Favorito;
import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Interface.FavoriteServiceApi;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.Interface.UserServiceApi;
import com.example.truequeworld.MainScreen;
import com.example.truequeworld.R;
import com.example.truequeworld.retrofit.RetrofitConexion;

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



    public Main_Adapter(Context context, ArrayList<Main_Model> mainModels,int userId) {
        this.context = context;
        this.mainModels = mainModels;
        this.userId = userId;
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
        FavoritesUser(productId, holder.saveButton);

        holder.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    Main_Model clickedItem = mainModels.get(adapterPosition);
                    Integer productId = clickedItem.getId();
                    Favorito favorito = new Favorito(null,userId, productId);
                    if(savedFavorites.size() != 0){
                        for(int i = 0; i < savedFavorites.size();i++){
                            if (savedFavorites.get(i).getProductoId().equals(productId)) {
                                deleteFavoriteById(savedFavorites.get(0).getId() ,holder.saveButton);
                                savedFavorites.remove(savedFavorites.get(i));
                            } else {
                                AddFavorito(favorito, holder.saveButton);
                            }
                        }
                    }else{
                        AddFavorito(favorito, holder.saveButton);
                    }

                }

                //Agregar aquí 2 cosas: que si se pulsa la imagen de guardado, que es la que está como save button, que agregue
                //ese producto a favs, por ende que se uestre en el apartado de guardados, si pulsa la imagen, que vaya a info
                // del producto, layout por crear, de momento es prescindible, pero lo del guardado no, ese es necesario
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
    public void FavoritesUser(Integer productId, ImageView saveButton){
        Call<List<Favorito>> call = favoriteServiceApi.getFavoritosUserid(userId);
        call.enqueue(new Callback<List<Favorito>>() {
            @Override
            public void onResponse(Call<List<Favorito>> call, Response<List<Favorito>> response) {
                if (response.isSuccessful()) {
                    savedFavorites = response.body();
                    for (Favorito favorito : savedFavorites) {
                        Log.d("CV", "ProductoID DE FAVORITOS" + favorito.getProductoId());
                        if (favorito.getProductoId().equals(productId)) {
                            saveButton.setBackgroundColor(ContextCompat.getColor(context, R.color.amarillo));
                        }
                    }
                    Log.d("CV", "Se sacaron los favoritos");
                } else {
                    Log.d("CV", "No se sacaron los favoritos");
                    // La solicitud no fue exitosa, maneja el error aquí
                }
            }
            @Override
            public void onFailure(Call<List<Favorito>> call, Throwable t) {
                // Hubo un error en la solicitud, maneja el error aquí
                Log.d("CV", "Error al sacar favoritos");
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
