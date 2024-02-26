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

import com.example.truequeworld.Clases_RecyclerView.Products_Exchanges_Model;
import com.example.truequeworld.Clases_RecyclerView.Products_Profile_Model;
import com.example.truequeworld.Interface.TruequeServiceApi;
import com.example.truequeworld.R;
import com.example.truequeworld.retrofit.RetrofitConexion;

import java.util.ArrayList;

public class Products_Exchange_Adapter extends RecyclerView.Adapter<Products_Exchange_Adapter.MyViewHolder> {
    Context context;
    ArrayList<Products_Profile_Model> productsExchangesModels;
    private LayoutInflater inflater;
    private View.OnClickListener listener;
    public int posProduct = -1;

    public void setListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public Products_Exchange_Adapter(Context context, ArrayList<Products_Profile_Model> productsExchangesModels) {
        this.context = context;
        this.productsExchangesModels = productsExchangesModels;
    }

    @NonNull
    @Override
    public Products_Exchange_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_my_products, parent, false);
        return new Products_Exchange_Adapter.MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull Products_Exchange_Adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d("OPP", "PAN" + productsExchangesModels.size());
        holder.productName.setText(productsExchangesModels.get(position).getProductName());
        holder.productImg.setImageBitmap(productsExchangesModels.get(position).getProductImg());
        holder.cvProductsEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posProduct = position;
                listener.onClick(view);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productsExchangesModels.size();
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