package com.example.speechtotext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private static int Splash=5000;
    Animation animation,tanimation,manimation;
    ImageView i1,i2;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        animation = AnimationUtils.loadAnimation(this,R.anim.animation);
        tanimation= AnimationUtils.loadAnimation(this,R.anim.tanimation);
        manimation=AnimationUtils.loadAnimation(this,R.anim.manimation);
        i1=findViewById(R.id.splash);
        i2=findViewById(R.id.splash);
        t=findViewById(R.id.t);
        i1.setAnimation(tanimation);
        i2.setAnimation(manimation);
        t.setAnimation(animation);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run(){
                Intent i =new Intent(SplashActivity.this,MainActivity.class);
                startActivity(i);
            }

        },Splash);
    }
}