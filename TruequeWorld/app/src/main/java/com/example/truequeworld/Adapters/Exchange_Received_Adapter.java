package com.example.truequeworld.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequeworld.Clases_RecyclerView.Exchange_Received_Model;
import com.example.truequeworld.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.divider.MaterialDivider;

import java.util.ArrayList;

public class Exchange_Received_Adapter extends RecyclerView.Adapter<Exchange_Received_Adapter.MyViewHolder> {
    Context context;
    ArrayList<Exchange_Received_Model> exReceivedModels;
    private LayoutInflater inflater;

    public Exchange_Received_Adapter(Context context, ArrayList<Exchange_Received_Model> exReceivedModels) {
        this.context = context;
        this.exReceivedModels = exReceivedModels;
    }

    @NonNull
    @Override
    public Exchange_Received_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_exchange_received, parent, false);
        return new Exchange_Received_Adapter.MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull Exchange_Received_Adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //holder.myImg.setImageDrawable(mainModels.get(position).getMainImg());
        //holder.otherImg.setImageDrawable(mainModels.get(position).getMainImg());

        holder.cvExReceived.setOnClickListener(new View.OnClickListener() {
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
        ImageView receivedProduct;
        ImageView otherWantProduct;
        ImageView upLogoReceived;
        ImageView downLogoReceived;
        TextView userWants;
        TextView wantsEx;
        CardView cvExReceived;
        MaterialButton accept;
        MaterialButton reject;
        MaterialDivider big;
        MaterialDivider tiny;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cvExReceived = itemView.findViewById(R.id.cvExReceived);
            this.receivedProduct = itemView.findViewById(R.id.received_product);
            this.otherWantProduct = itemView.findViewById(R.id.other_wantProduct);
            this.userWants = itemView.findViewById(R.id.userWants);
            this.wantsEx = itemView.findViewById(R.id.wantsEx);
            this.upLogoReceived = itemView.findViewById(R.id.upLogoReceived);
            this.downLogoReceived = itemView.findViewById(R.id.downLogoReceived);
            this.accept = itemView.findViewById(R.id.acceptEx);
            this.reject = itemView.findViewById(R.id.rejectEx);
            this.big = itemView.findViewById(R.id.big);
            this.tiny = itemView.findViewById(R.id.tiny);
        }
    }
}
