package com.example.minesh.navigationd;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;
import java.util.List;

public class Result_list extends AppCompatActivity {

    private DatabaseReference rootref, ref;
    private Spinner spinner;
    private String subject;
    private ArrayAdapter<String> spinAdapter;
    private Schedule_adapter adapter;
    private int i = 1;
    private RecyclerView recycler;
    private List<Faculty_retrieve_class> marksList;
    private boolean b = true, t = true;
    private SlidrInterface slidr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);


        slidr = Slidr.attach(this);
        slidr.unlock();

        spinner = findViewById(R.id.SubjectSpinner);
        recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(Result_list.this));
        marksList = new ArrayList<>();
        adapter = new Schedule_adapter(this, marksList);
        marksList.clear();
        rootref = FirebaseDatabase.getInstance().getReference().child("Results").child("3CE");
        spinAdapter = new ArrayAdapter<>(Result_list.this, R.layout.spinner_lay, getResources().getStringArray(R.array.Subjects));
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subject = String.valueOf(parent.getItemAtPosition(position));
                if (!subject.equals("---SELECT SUBJECT---")) {
                    rootref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ref = rootref.child(subject);
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    marksList.clear();
                                    marksList.add(new Faculty_retrieve_class(" ", "ID No.", " ", "Marks"));
                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                        String marks = ds.getValue(String.class);
                                        marksList.add(new Faculty_retrieve_class(" ", ds.getKey(), " ", marks));
                                        Context con = recycler.getContext();
                                        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(con, R.anim.lay_fall_down);
                                        recycler.setLayoutAnimation(controller);
                                        recycler.scheduleLayoutAnimation();
                                        recycler.setAdapter(adapter);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } else {
                    marksList.clear();
                    recycler.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (!InternetError.isConnected(getBaseContext())) {
            buildDialog(this).show();
        }
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