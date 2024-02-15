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

public class ProductsRVAdapter extends RecyclerView.Adapter<ProductsRVAdapter.ProductViewHolder> implements View.OnClickListener {
    User user;
    Context context;
    List<Product> listaProductos;
    View view;
    Product productSelected;
    private View.OnClickListener listener;

    public ProductsRVAdapter(Context context, List<Product> listaProductos, User user){
        this.context = context;
        this.listaProductos = listaProductos;
        this.user = user;
    }
    @NonNull
    @Override
    public ProductsRVAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view =inflater.inflate(R.layout.cv_row,parent,false);
        view.setOnClickListener(this);
        return new ProductsRVAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsRVAdapter.ProductViewHolder holder, int position) {
        holder.textView.setText(listaProductos.get(position).getNombre());
        final int pos = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("producto",listaProductos.get(pos));
                intent.putExtra("user",user);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public Product getProductSelected() {
        return productSelected;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textView =itemView.findViewById(R.id.textView2);
        }
    }
}
