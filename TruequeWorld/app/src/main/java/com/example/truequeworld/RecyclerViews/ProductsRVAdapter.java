package com.example.truequeworld.RecyclerViews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequeworld.Class.Product;
import com.example.truequeworld.Class.User;
import com.example.truequeworld.R;
import com.example.truequeworld.chattesting.ChatActivity;

import java.util.List;

public class ProductsRVAdapter extends RecyclerView.Adapter<ProductsRVAdapter.ProductView> {
    User user;
    Context context;
    List<Product> listaProductos;
    View view;

    public ProductsRVAdapter(Context context, List<Product> listaProductos, User user){
        this.context = context;
        this.listaProductos = listaProductos;
        this.user = user;
    }
    @NonNull
    @Override
    public ProductsRVAdapter.ProductView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view =inflater.inflate(R.layout.cv_row,parent,false);
        return new ProductsRVAdapter.ProductView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsRVAdapter.ProductView holder, int position) {
        holder.textView.setText(listaProductos.get(position).getNombre());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductView extends RecyclerView.ViewHolder {
        TextView textView;
        public ProductView(@NonNull View itemView) {
            super(itemView);
            textView =itemView.findViewById(R.id.textView2);
        }
    }
}
