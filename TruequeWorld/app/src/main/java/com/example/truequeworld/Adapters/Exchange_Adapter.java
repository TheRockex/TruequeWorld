package com.example.truequeworld.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Base64;
import android.util.Log;
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
        holder.myProduct.setImageBitmap(base64ToBitmap(exchangeModels.get(position).getMyImg()));
        holder.otherProduct.setImageBitmap(base64ToBitmap(exchangeModels.get(position).getOtherImg()));
        Log.d("Tag", "Esta en el adapter");
    }

    @Override
    public int getItemCount() {
        return exchangeModels.size();
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

    public Bitmap base64ToBitmap(String base64Image) {
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
