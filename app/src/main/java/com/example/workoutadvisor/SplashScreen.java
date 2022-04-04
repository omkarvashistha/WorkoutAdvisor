 package com.example.workoutadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

 public class SplashScreen extends AppCompatActivity {
     Animation up,down;
     ImageView splashimage;
     TextView splashtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashimage  = findViewById(R.id.splashlogo);
        splashtext  = findViewById(R.id.splashtext);
        up = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.up);
        splashimage.setAnimation(up);
        down = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.down);
        splashtext.setAnimation(down);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        },1500);
    }
}