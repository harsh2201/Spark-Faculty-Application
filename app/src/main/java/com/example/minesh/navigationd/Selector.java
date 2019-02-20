package com.example.minesh.navigationd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class Selector extends AppCompatActivity {

    private Button facultyButton,studentButton;
    private SlidrInterface slidr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);


        slidr= Slidr.attach(this);
        slidr.unlock();

        facultyButton=findViewById(R.id.FacultyResultButton);
        studentButton=findViewById(R.id.StudentResultButton);

        facultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Selector.this,Faculty_login.class));
            }
        });

        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Selector.this,Result_list.class));
            }
        });

    }
}
