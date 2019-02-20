package com.example.minesh.navigationd;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.provider.Settings.System;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Main_screen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private boolean back = false;
    private Context context;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    public static DatabaseReference historyRef,categorizedHistoryRef,quotesRef;
    private FirebaseAuth auth;
    private TextView quotesText;
    private static FirebaseAnalytics firebaseAnalytics;
    private Animation myAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        myAnim = AnimationUtils.loadAnimation(this,R.anim.myanim);

        quotesRef=FirebaseDatabase.getInstance().getReference().child("Quotes");
        SetHistory();
        quotesText=findViewById(R.id.quotes);
        quotesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                quotesText.setText(dataSnapshot.child(""+(int)(Math.random() * ((dataSnapshot.getChildrenCount()) + 1))).getValue(String.class));
                quotesText.startAnimation(myAnim);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        auth = FirebaseAuth.getInstance();

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        toggle = new ActionBarDrawerToggle(Main_screen.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.faculty_info:
                startActivity(new Intent(Main_screen.this, Faculty_info_list.class));
                break;
            case R.id.timetable:
                startActivity(new Intent(Main_screen.this, Time_table_list.class));
                break;
            case R.id.LabOccupancy:
                startActivity(new Intent(Main_screen.this, LabOccupancy.class));
                break;
            case R.id.nav_credits:
                startActivity(new Intent(Main_screen.this, Credits.class));
                break;
            case R.id.result:
                if (auth.getCurrentUser() == null) {
                    startActivity(new Intent(Main_screen.this, Selector.class));
                }
                else{
                    startActivity(new Intent(Main_screen.this,Main_result_import.class));
                }

        }
        return false;
    }

    public void SetHistory(){
        String IMEI= System.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        String Manufacturer=Build.MANUFACTURER;
        String Model=Build.MODEL;
        String API="API_"+Build.VERSION.SDK;
        String Version=Build.VERSION.RELEASE;
        String date=java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        categorizedHistoryRef=FirebaseDatabase.getInstance().getReference().child("History").child("Categorized").child(API).child(Manufacturer).child(Model).child(IMEI).push();
        categorizedHistoryRef.child("OpenedTime").setValue(date);
        categorizedHistoryRef.child("Version").setValue(Version);
        historyRef = FirebaseDatabase.getInstance().getReference().child("History").child("AllHistory").child(IMEI).push();
        historyRef.child("Manufacturer").setValue(Manufacturer);
        historyRef.child("Model").setValue(Model);
        historyRef.child("Version").setValue(Version);
        historyRef.child("OpenedTime").setValue(date);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (back == true) {
                super.onBackPressed();
            } else {
                Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
                back = true;
            }
        }
    }

    @Override
    protected void onStop() {
        String date=java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        historyRef.child("ClosedTime").setValue(date);
        categorizedHistoryRef.child("ClosedTime").setValue(date);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        String date=java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        historyRef.child("ClosedTime").setValue(date);
        categorizedHistoryRef.child("ClosedTime").setValue(date);
        super.onDestroy();
    }
}
