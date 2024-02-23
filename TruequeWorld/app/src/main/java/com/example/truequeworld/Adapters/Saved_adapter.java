package com.example.truequeworld.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequeworld.Clases_RecyclerView.Saved_Model;
import com.example.truequeworld.Class.Favorito;

import com.example.truequeworld.R;

import java.util.ArrayList;

public class Saved_adapter extends RecyclerView.Adapter<Saved_adapter.ViewHolder> {

    private LayoutInflater inflater;
    public Saved_Model favProduct;
    private ArrayList<Saved_Model> favProducts; // Cambiado a ArrayList<Favorito>

    public Saved_adapter(Context context , Saved_Model favProduct){
        this.favProduct = favProduct;
        this.inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public Saved_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.f2_saved_products_screen_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Saved_adapter.ViewHolder holder, int position) {



        // Establecer los datos del producto en las vistas
        //sergio
        //holder.tp_product.setImageDrawable(favProduct.get(position).getFavProduct_img());
        //Luca
        //  convierte
        //      private String favProduct_img;
        //    private Bitmap favProduct_img;
        //       holder.tp_product.setImageBitmap(favProduct.getFavProduct_img());
        //boton eliminar favorito
//        holder.tp_NoFav_Button.setImageDrawable();
        holder.tp_name.setText(favProducts.get(position).getFavProduct_name());
        holder.tp_description.setText(favProducts.get(position).getFav_Product_descripcion());

    }

    @Override
    public int getItemCount() {
        return favProducts.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView tp_product;
        ImageView tp_NoFav_Button;
        TextView tp_name;

        TextView tp_description;

        public ViewHolder(@NonNull View item) {
            super(item);
            tp_product = item.findViewById(R.id.saved_product);
            tp_NoFav_Button = item.findViewById(R.id.NocvSavedButton);
            tp_name = item.findViewById(R.id.tp_name);
            tp_description = item.findViewById(R.id.tp_description);
        }
    }
}
