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
    private ProductServiceApi productServiceApi;

    public Integer userId;
    Product product;

    public ProductsEx_Adapter(Context context, ArrayList<ProductsEx_Model> productsExModels, int userId) {
        this.context = context;
        this.productsExModels = productsExModels;
        this.userId = userId;
    }

    @NonNull
    @Override
    public ProductsEx_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Conectar();
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_my_products, parent, false);
        return new ProductsEx_Adapter.MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ProductsEx_Adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProductsEx_Model selectedProduct = productsExModels.get(position);
        holder.productName.setText(productsExModels.get(position).getProductName());
        holder.productImg.setImageBitmap(productsExModels.get(position).getProductImg());
        Integer productId = selectedProduct.getId();

        holder.productImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //para que al pulsar este producto, se añada a la cardview ya del intercambio
            }
        });
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

    public void Conectar() {
        productServiceApi = RetrofitConexion.getProductServiceApi();
    }

    public void getProductosId(Integer id) {
        Call<Product> call = productServiceApi.getproductById(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    product = response.body();
                    Log.d("CV", "En efecto, el producto va" + product.getDescripcion());
                } else {
                    Log.d("CV", "No va pringao");
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.d("CV", "Jiji fallo");
            }
        });
    }
}
