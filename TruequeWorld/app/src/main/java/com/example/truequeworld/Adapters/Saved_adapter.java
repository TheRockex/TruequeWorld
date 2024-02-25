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
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequeworld.Clases_RecyclerView.Main_Model;
import com.example.truequeworld.Clases_RecyclerView.Saved_Model;
import com.example.truequeworld.Class.Favorito;
import com.example.truequeworld.Class.User;
import com.example.truequeworld.Interface.FavoriteServiceApi;
import com.example.truequeworld.Interface.UserServiceApi;
import com.example.truequeworld.R;
import com.example.truequeworld.retrofit.RetrofitConexion;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Saved_adapter extends RecyclerView.Adapter<Saved_adapter.MyViewHolder> {

    Context context;
    ArrayList<Saved_Model> savedModels;
    private LayoutInflater inflater;
    private CardView lastSelect;
    private int posLastSelect;
    private FavoriteServiceApi favoriteServiceApi;
    private UserServiceApi userServiceApi;
    public Integer userId;
    private List<Favorito> savedFavorites = new ArrayList<>();
    User user;

    public Saved_adapter(Context context , ArrayList<Saved_Model> savedModels, int userId){
        this.context = context;
        this.savedModels = savedModels;
        this.userId = userId;
    }

    @NonNull
    @Override
    public Saved_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Conectar();
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_saved_product, parent, false);
        return new Saved_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Saved_adapter.MyViewHolder holder,@SuppressLint("RecyclerView") int position) {
        Log.d("PANA","Cardview");
        Saved_Model currentProduct = savedModels.get(position);
        holder.tp_name.setText("NOMBRE: " + savedModels.get(position).getFavProduct_name());
        holder.tp_precio.setText("PRECIO: " + savedModels.get(position).getFavProduct_precio().toString() + "TP");
        holder.tp_nombrePropietario.setText("PROPIETARIO: " + user.getName());

        //getUserID(savedModels.get(position).getFavProduct_propietarioID(), holder.tp_nombrePropietario);
        holder.tp_product.setImageBitmap(savedModels.get(position).getFavProduct_img());
        holder.tp_NoFav_Button.setImageDrawable(savedModels.get(position).getMainSave());

        Integer productId = currentProduct.getId();
        Log.d("PANA","Size" + savedFavorites.size());
        if (savedFavorites.get(position).getProductoId().equals(productId)) {
            holder.tp_NoFav_Button.setBackgroundColor(ContextCompat.getColor(context, R.color.amarillo));
        }

        holder.tp_NoFav_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean found = false;
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    Saved_Model clickedItem = savedModels.get(adapterPosition);
                    Integer productId = clickedItem.getId();
                    Favorito favorito = new Favorito(null,userId, productId);
                    Log.d("PANA","Tamaña" + savedFavorites.size());
                    if(savedFavorites.size() != 0){
                        for(int i = 0; i < savedFavorites.size();i++){
                            if (savedFavorites.get(i).getProductoId().equals(productId)) {
                                deleteFavoriteById(savedFavorites.get(i).getId() ,holder.tp_NoFav_Button);
                                savedFavorites.remove(savedFavorites.get(i));
                                found = true;
                                break;
                            }
                            if (!found) {
                                AddFavorito(favorito, holder.tp_NoFav_Button);
                                found = false;
                            }
                        }
                    }else{
                        Log.d("PANA","Se mete al else2" + savedFavorites.size());
                        AddFavorito(favorito, holder.tp_NoFav_Button);
                    }

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return savedModels.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView tp_product;
        ImageView tp_NoFav_Button;
        TextView tp_name;
        TextView tp_nombrePropietario;
        TextView tp_precio;

        CardView cvSave;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cvSave = itemView.findViewById(R.id.cvSaved);
            tp_name = itemView.findViewById(R.id.tp_name);
            tp_precio = itemView.findViewById(R.id.tp_value);
            tp_nombrePropietario = itemView.findViewById(R.id.tp_location);
            tp_product = itemView.findViewById(R.id.saved_product);
            tp_NoFav_Button = itemView.findViewById(R.id.cvSavedButton);
        }
    }


    public void Conectar() {
        Log.d("PANA","Conectar");
        favoriteServiceApi = RetrofitConexion.getFavoriteServiceApi();
        userServiceApi = RetrofitConexion.getUserServiceApi();
        getUserID();
        FavoritesUser();
    }


    public void getUserID(/*Integer userId, TextView tp_nombrePropietario*/){
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
        Log.d("PANA","GetUser");
    }
    public void FavoritesUser(){
        Call<List<Favorito>> call = favoriteServiceApi.getFavoritosUserid(userId);
        call.enqueue(new Callback<List<Favorito>>() {
            @Override
            public void onResponse(Call<List<Favorito>> call, Response<List<Favorito>> response) {
                if (response.isSuccessful()) {
                    savedFavorites = response.body();
                    Log.d("CVF", "Se sacaron los favoritos");
                } else {
                    Log.d("CVF", "No se sacaron los favoritos");
                    // La solicitud no fue exitosa, maneja el error aquí
                }
            }
            @Override
            public void onFailure(Call<List<Favorito>> call, Throwable t) {
                // Hubo un error en la solicitud, maneja el error aquí
                Log.d("CVF", "Error al sacar favoritos");
            }
        });
        Log.d("PANA","FavoriteUser");
    }


    public void AddFavorito(Favorito favorito, ImageView saveButton){
        Call<Favorito> call = favoriteServiceApi.insertFavoritos(favorito);
        call.enqueue(new Callback<Favorito>() {
            @Override
            public void onResponse(Call<Favorito> call, Response<Favorito> response) {
                if (response.isSuccessful()) {
                    Log.d("CVF", "Se inserto el favorito");
                    Favorito favoritoInsertado = response.body();
                    savedFavorites.add(favoritoInsertado);
                    saveButton.setBackgroundColor(ContextCompat.getColor(context, R.color.amarillo));
                } else {
                    Log.d("CVF", "No se inserto el favorito");
                    // La solicitud no fue exitosa, maneja el error aquí
                }
            }

            @Override
            public void onFailure(Call<Favorito> call, Throwable t) {
                // Hubo un error en la solicitud, maneja el error aquí
                Log.d("CVF", "Error al añadir");
            }
        });
    }

    public void deleteFavoriteById(Integer id, ImageView saveButton) {
        Call<Boolean> call = favoriteServiceApi.deleteFavoriteById(id);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Log.d("CVF", "Se eliminó el favorito");
                    // Si se eliminó correctamente, establece el color de fondo predeterminado
                    saveButton.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
                } else {
                    Log.d("CVF", "No se pudo eliminar el favorito");
                    // Maneja el error si la solicitud no fue exitosa
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                // Maneja el error si hubo un fallo en la solicitud
                Log.e("CVF", "Error al eliminar el favorito: " + t.getMessage());
            }
        });
    }
}
