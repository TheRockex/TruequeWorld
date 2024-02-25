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

import com.example.truequeworld.Clases_RecyclerView.ProductsEx_Model;
import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Interface.ProductServiceApi;
import com.example.truequeworld.R;
import com.example.truequeworld.retrofit.RetrofitConexion;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsEx_Adapter extends RecyclerView.Adapter<ProductsEx_Adapter.MyViewHolder> {
    Context context;
    ArrayList<ProductsEx_Model> productsExModels;
    private LayoutInflater inflater;

    public ProductsEx_Adapter(Context context, ArrayList<ProductsEx_Model> productsExModels) {
        this.context = context;
        this.productsExModels = productsExModels;
    }

    @NonNull
    @Override
    public ProductsEx_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_my_products, parent, false);
        return new ProductsEx_Adapter.MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ProductsEx_Adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d("OPP","PAN " + productsExModels.size());
        holder.productName.setText(productsExModels.get(position).getProductName());
        holder.productImg.setImageBitmap(productsExModels.get(position).getProductImg());
    }

    @Override
    public int getItemCount() {
        return productsExModels.size();
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
