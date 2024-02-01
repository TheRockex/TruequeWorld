package com.example.truequeworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

public class StartScreen extends AppCompatActivity {
    Button LoginDesplegable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        /**Luca**/
        LoginDesplegable = findViewById(R.id.loginGoogle);
        /****/

        ImageView logoUpStart = findViewById(R.id.logoUpStart);
        ImageView logoDownStart = findViewById(R.id.logoDownStart);
        TextView appNameStart = findViewById(R.id.appNameStart);
        TextView appSlogan = findViewById(R.id.sloganStart);
        MaterialButton google = findViewById(R.id.loginGoogle);
        MaterialButton login = findViewById(R.id.loginTW);
        MaterialButton register = findViewById(R.id.registerTW);
        ImageView googleImg = findViewById(R.id.logoGoogle);
        ImageView twImg1 = findViewById(R.id.logoLoginTW);
        ImageView twImg2 = findViewById(R.id.logoRegisterTW);

        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.go_right_start);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.go_left_start);
        Animation apName = AnimationUtils.loadAnimation(this, R.anim.appear_start);
        Animation upButton1 = AnimationUtils.loadAnimation(this, R.anim.go_up);
        Animation upButton2 = AnimationUtils.loadAnimation(this, R.anim.go_up);
        Animation upButton3 = AnimationUtils.loadAnimation(this, R.anim.go_up);

        google.setVisibility(View.INVISIBLE);
        googleImg.setVisibility(View.INVISIBLE);
        login.setVisibility(View.INVISIBLE);
        twImg1.setVisibility(View.INVISIBLE);
        register.setVisibility(View.INVISIBLE);
        twImg2.setVisibility(View.INVISIBLE);

        appNameStart.setAnimation(apName);
        appSlogan.setAnimation(apName);
        logoUpStart.setAnimation(anim2);
        logoDownStart.setAnimation(anim1);
        google.setVisibility(View.VISIBLE);
        googleImg.setVisibility(View.VISIBLE);
        google.setAnimation(upButton1);
        googleImg.setAnimation(upButton1);

        upButton1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                login.setVisibility(View.VISIBLE);
                login.setAnimation(upButton2);
                twImg1.setVisibility(View.VISIBLE);
                twImg1.setAnimation(upButton2);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        upButton2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                register.setVisibility(View.VISIBLE);
                register.setAnimation(upButton3);
                twImg2.setVisibility(View.VISIBLE);
                twImg2.setAnimation(upButton3);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        /**Luca**/
        LoginDesplegable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showContent();
            }
        });

        /****/

        /*google.postDelayed(new Runnable() {
            @Override
            public void run() {
                google.setVisibility(View.VISIBLE);
                google.setAnimation(upButton);
                googleImg.setVisibility(View.VISIBLE);
                googleImg.setAnimation(upButton);

            }
        }, 0);

        login.postDelayed(new Runnable() {
            @Override
            public void run() {
                login.setVisibility(View.VISIBLE);
                login.setAnimation(upButton);
                twImg1.setVisibility(View.VISIBLE);
                twImg1.setAnimation(upButton);
                register.setVisibility(View.VISIBLE);
                register.setAnimation(upButton);
                twImg2.setVisibility(View.VISIBLE);
                twImg2.setAnimation(upButton);
            }
        }, 1000);

        /*register.postDelayed(new Runnable() {
            @Override
            public void run() {
                register.setVisibility(View.VISIBLE);
                register.setAnimation(upButton);
                twImg2.setVisibility(View.VISIBLE);
                twImg2.setAnimation(upButton);
            }
        }, 2000);

        /*google.setVisibility(View.VISIBLE);
        googleImg.setVisibility(View.VISIBLE);
        google.setAnimation(upButton);
        googleImg.setAnimation(upButton);


        login.setVisibility(View.VISIBLE);
        twImg1.setVisibility(View.VISIBLE);
        Animation upButtonWithDelay = AnimationUtils.loadAnimation(this, R.anim.go_up);
        upButtonWithDelay.setStartOffset(1000); // 1 second delay
        login.setAnimation(upButtonWithDelay);
        twImg1.setAnimation(upButtonWithDelay);

        register.setVisibility(View.VISIBLE);
        twImg2.setVisibility(View.VISIBLE);
        upButtonWithDelay = AnimationUtils.loadAnimation(this, R.anim.go_up);
        upButtonWithDelay.setStartOffset(2000); // 2 seconds delay
        register.setAnimation(upButtonWithDelay);
        twImg2.setAnimation(upButtonWithDelay);*/
    }
    /**Luca**/
    private void showContent() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_login_screen);


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFF9EB")));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
    /**Luca**/
}