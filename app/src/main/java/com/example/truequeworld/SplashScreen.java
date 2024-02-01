package com.example.truequeworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView logoUpSplash = findViewById(R.id.logoUpSplash);
        ImageView logoDownSplash = findViewById(R.id.logoDownSplash);
        TextView appNameSplash = findViewById(R.id.appNameSplash);

        ImageView backgroundSplash = findViewById(R.id.backgroundSplash);

        /*Glide.with(this)
                .load("https://forbes.es/wp-content/uploads/2022/02/El-mercado-de-segunda-mano-se-dispara.-Foto_-Wallapop.jpg")
                .transition(DrawableTransitionOptions.withCrossFade(2000))
                //.centerCrop()
                .into(backgroundSplash);*/

        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.go_right);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.go_left);
        Animation animationUpOut = AnimationUtils.loadAnimation(this, R.anim.exit_up);
        Animation animationDownOut = AnimationUtils.loadAnimation(this, R.anim.exit_down);
        Animation appearName = AnimationUtils.loadAnimation(this, R.anim.appear);
        Animation disappearName = AnimationUtils.loadAnimation(this, R.anim.disappear);

        final ImageView logoUp = findViewById(R.id.logoUpSplash);
        final ImageView logoDown = findViewById(R.id.logoDownSplash);
        final TextView appName = findViewById(R.id.appNameSplash);

        logoUp.setVisibility(View.INVISIBLE);
        logoDown.setVisibility(View.INVISIBLE);
        appName.setVisibility(View.INVISIBLE);

        appName.setAnimation(appearName);
        logoUp.setAnimation(animation2);
        logoDown.setAnimation(animation1);

        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                logoDown.setAnimation(animationDownOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                logoUp.setAnimation(animationUpOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        appearName.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                appName.setAnimation(disappearName);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashScreen.this, StartScreen.class);
                        startActivity(intent, ActivityOptions.makeCustomAnimation(SplashScreen.this, R.anim.fade_in, R.anim.fade_out).toBundle());
                    }
                }, 2800);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}
