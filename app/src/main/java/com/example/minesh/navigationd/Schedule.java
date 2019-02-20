package com.example.minesh.navigationd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;
import java.util.List;

public class Schedule extends AppCompatActivity {

    private static DatabaseReference ref;
    private static DatabaseReference facref;
    private RecyclerView recyclerView;
    private Schedule_adapter adapter;
    private List<Faculty_retrieve_class> facultyList;
    private ArrayAdapter<String> spinadapter;
    private Spinner spinner;
    private TextView textView;
    private Faculty_retrieve_class finfo;
    private ImageView imageView;
    private ActivityOptionsCompat optionsCompat;
    private Intent i, intent;
    private String fac_name;
    private int image;
    private String Day;
    private Animation myAnim;
    private SlidrInterface slidr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        slidr= Slidr.attach(this);
        slidr.unlock();

        imageView = findViewById(R.id.imageView3);

        i = new Intent(Schedule.this, FacultyInformation.class);

        intent = getIntent();
        fac_name = intent.getStringExtra("Faculty_retrieve_class");
        image = intent.getIntExtra("image", 0);
        imageView.setImageResource(image);
        myAnim = AnimationUtils.loadAnimation(this,R.anim.myanim2);
        imageView.startAnimation(myAnim);

        textView = findViewById(R.id.textView);
        textView.setText(fac_name);

        spinner = findViewById(R.id.spinner);
        spinadapter = new ArrayAdapter<>(this, R.layout.spinner_lay, getResources().getStringArray(R.array.Day));
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinadapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Day = String.valueOf(adapterView.getItemAtPosition(i));

                facref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        facultyList.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            finfo = ds.getValue(Faculty_retrieve_class.class);
                            String strday = finfo.gDay();
                            String strfac = finfo.gFaculty();
                            if (Day.equals("---SELECT DAY---")) {
                                facultyList.clear();
                            } else {
                                if (Day.equals(strday) && fac_name.equals(strfac)) {
                                    facultyList.add(finfo);
                                }
                            }
                            Context con = recyclerView.getContext();
                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(con, R.anim.lay_fall_down);
                            recyclerView.setLayoutAnimation(controller);
                            recyclerView.scheduleLayoutAnimation();
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        facultyList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Schedule.this));
        adapter = new Schedule_adapter(this, facultyList);
        finfo = new Faculty_retrieve_class();
        facultyList.clear();
        ref = FirebaseDatabase.getInstance().getReference();
        facref = ref.child("FacultyTimeTable");

        if (InternetError.isConnected(getBaseContext())) {

        } else {
            buildDialog(this).show();
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(Schedule.this, imageView, "myImage");
                i.putExtra("Faculty_retrieve_class", fac_name);
                i.putExtra("image", image);
                startActivity(i, optionsCompat.toBundle());
            }
        });
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this!!");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        return builder;
    }


}
