package com.example.truequeworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        ImageView backgroundStart = findViewById(R.id.backgroundStart);

        Glide.with(this)
                .load("https://forbes.es/wp-content/uploads/2022/02/El-mercado-de-segunda-mano-se-dispara.-Foto_-Wallapop.jpg")
                //.centerCrop()
                .into(backgroundStart);

    }
}