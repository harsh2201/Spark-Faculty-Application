package com.example.minesh.navigationd;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class Credits extends AppCompatActivity {

    private SlidrInterface slidr;
    private Animation myAnim;
    private TextView t7,t8,t9,t10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        t7=findViewById(R.id.textView7);
        t8=findViewById(R.id.textView8);
        t9=findViewById(R.id.textView9);
        t10=findViewById(R.id.textView10);
        myAnim = AnimationUtils.loadAnimation(this,R.anim.myanim);
        t7.startAnimation(myAnim);
        t8.startAnimation(myAnim);
        t9.startAnimation(myAnim);
        t10.startAnimation(myAnim);

        slidr= Slidr.attach(this);
        slidr.unlock();

    }
}
