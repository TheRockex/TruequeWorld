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
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequeworld.Clases_RecyclerView.Products_Profile_Model;
import com.example.truequeworld.R;

import java.util.ArrayList;

public class Products_Profile_Adapter extends RecyclerView.Adapter<Products_Profile_Adapter.MyViewHolder> {
    Context context;
    ArrayList<Products_Profile_Model> productsModels;
    private LayoutInflater inflater;

    public Products_Profile_Adapter(Context context, ArrayList<Products_Profile_Model> productsExModels) {
        this.context = context;
        this.productsModels = productsExModels;
    }

    @NonNull
    @Override
    public Products_Profile_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_my_products, parent, false);
        return new Products_Profile_Adapter.MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull Products_Profile_Adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d("OPP","PAN " + productsModels.size());
        holder.productName.setText(productsModels.get(position).getProductName());
        holder.productImg.setImageBitmap(productsModels.get(position).getProductImg());
    }

    @Override
    public int getItemCount() {
        return productsModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView productImg;
        TextView productName;
        CardView cvProductsEx;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cvProductsEx = itemView.findViewById(R.id.cvProductsEx);
            this.productName = itemView.findViewById(R.id.product_toexchange_name);
            this.productImg = itemView.findViewById(R.id.product_toexchange_img);
        }
    }
}
