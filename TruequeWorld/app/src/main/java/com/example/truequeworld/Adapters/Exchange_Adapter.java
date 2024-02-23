package com.example.truequeworld.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequeworld.Clases_RecyclerView.Exchange_Model;
import com.example.truequeworld.R;

import java.util.ArrayList;

public class Exchange_Adapter extends RecyclerView.Adapter<Exchange_Adapter.MyViewHolder> {
    Context context;
    ArrayList<Exchange_Model> exchangeModels;
    private LayoutInflater inflater;

    public Exchange_Adapter(Context context, ArrayList<Exchange_Model> exchangeModels) {
        this.context = context;
        this.exchangeModels = exchangeModels;
    }

    @NonNull
    @Override
    public Exchange_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_exchange_sent, parent, false);
        return new Exchange_Adapter.MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull Exchange_Adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //holder.myImg.setImageDrawable(mainModels.get(position).getMainImg());
        //holder.otherImg.setImageDrawable(mainModels.get(position).getMainImg());

        holder.cvExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //En verdad este onclick no sería necesario, pero por si aca necesitas meter algo xd
            }

        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView myProduct;
        ImageView otherProduct;
        ImageView upLogo;
        ImageView downLogo;
        CardView cvExchange;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cvExchange = itemView.findViewById(R.id.cvExchange);
            this.myProduct = itemView.findViewById(R.id.offer_product);
            this.otherProduct = itemView.findViewById(R.id.wanted_product);
            this.upLogo = itemView.findViewById(R.id.upLogo);
            this.downLogo = itemView.findViewById(R.id.downLogo);
        }
    }
}
