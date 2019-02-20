package com.example.minesh.navigationd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.felipecsl.gifimageview.library.GifImageView;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;


public class Splash extends AppCompatActivity {

    private ImageView logo;
    private Intent intent;
    private TextView tv;
    private GifImageView gifImageView;
    private Animation myAnim;
    private static FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        gifImageView=findViewById(R.id.gifimage);
        InputStream inputStream;
        try {
            inputStream=getAssets().open("spiral.gif");
            byte[] bytes= IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        } catch (IOException e) {
            e.printStackTrace();
        }
        myAnim = AnimationUtils.loadAnimation(this,R.anim.myanim);
        Animation fall_down=AnimationUtils.loadAnimation(this,R.anim.fall_down);
        logo=findViewById(R.id.imageView);
        tv=findViewById(R.id.textView2);
        tv.startAnimation(fall_down);
        /*Thread x=new Thread(){
            public void run(){
                try{
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally{

                }
            }
        };
        x.start();*/
        logo.startAnimation(myAnim);
        logo.setImageResource(R.drawable.spark_logo);
        intent =new Intent(this,Main_screen.class);
        Thread timer=new Thread(){
          public void run(){
              try{
                  sleep(3250);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              finally {
                    startActivity(intent);
                    finish();
              }
          }
        };
        timer.start();
    }

    @Override
    public void onBackPressed() {

    }
}
