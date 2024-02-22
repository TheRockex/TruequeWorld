package com.example.truequeworld.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truequeworld.Clases_RecyclerView.Main_Model;
import com.example.truequeworld.R;

import java.util.ArrayList;

public class Main_Adapter extends RecyclerView.Adapter<Main_Adapter.MyViewHolder> {
    Context context;
    ArrayList<Main_Model> mainModels;
    private LayoutInflater inflater;

    public Main_Adapter(Context context, ArrayList<Main_Model> mainModels) {
        this.context = context;
        this.mainModels = mainModels;
    }

    @NonNull
    @Override
    public Main_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_main_screen, parent, false);
        return new Main_Adapter.MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull Main_Adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //holder.productImg.setImageDrawable(mainModels.get(position).getMainImg());
        //holder.saveButton.setImageDrawable();
        holder.productName.setText(mainModels.get(position).getMainName());
        holder.productEx.setText(mainModels.get(position).getMainEx());

        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Agregar aquí 2 cosas: que si se pulsa la imagen de guardado, que es la que está como save button, que agregue
                //ese producto a favs, por ende que se uestre en el apartado de guardados, si pulsa la imagen, que vaya a info
                // del producto, layout por crear, de momento es prescindible, pero lo del guardado no, ese es necesario
            }

        });

    }

    @Override
    public int getItemCount() {
        return mainModels.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView productImg;
        ImageView saveButton;
        TextView productName;
        TextView productEx;
        CardView cvMain;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cvMain = itemView.findViewById(R.id.cvMain);
            this.productName = itemView.findViewById(R.id.main_product_cv_name);
            this.productEx = itemView.findViewById(R.id.exchange_now);
            this.productImg = itemView.findViewById(R.id.product_mainscreen_image);
            this.saveButton = itemView.findViewById(R.id.add_favs_main_cv);
        }
    }
}
