package com.example.minesh.navigationd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;

public class LabOccupancy extends AppCompatActivity {

    private Spinner daySpinner;
    private Spinner slotSpinner;
    private ArrayAdapter<String> dayAdapter, slotAdapter;
    private Lab_info_adapter adapter;
    private RecyclerView recycler;
    private String day;
    private String slot;
    private DatabaseReference rootref, ref;
    private ArrayList<LabInfo_class> labList,templist;
    private LabInfo_class labInfoClass;
    private SlidrInterface slidr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_occupancy);

        slidr= Slidr.attach(this);
        slidr.unlock();

        rootref = FirebaseDatabase.getInstance().getReference();
        ref = rootref.child("LabOccupancy");

        day="---SELECT DAY---";
        slot="---SELECT SLOT---";

        daySpinner = findViewById(R.id.dayspinner);
        slotSpinner = findViewById(R.id.slotspinner);
        dayAdapter = new ArrayAdapter<>(LabOccupancy.this, R.layout.spinner_lay, getResources().getStringArray(R.array.Day));
        slotAdapter = new ArrayAdapter<>(LabOccupancy.this, R.layout.spinner_lay, getResources().getStringArray(R.array.Slot));
        slotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);
        slotSpinner.setAdapter(slotAdapter);
        labList = new ArrayList<>();
        templist = new ArrayList<>();
        labInfoClass =new LabInfo_class();
        labList.clear();
        recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(LabOccupancy.this));
        adapter = new Lab_info_adapter(this, templist);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    labList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    DataSnapshot d=ds;
                    labInfoClass = new LabInfo_class(d.child("Day").getValue(String.class),d.child("Lab_No").getValue(String.class),d.child("Slot").getValue(String.class));
//                    labInfoClass =ds.getValue(LabInfo_class.class);
                    labList.add(labInfoClass);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day = String.valueOf(parent.getItemAtPosition(position));
                if(!(slot.equals("---SELECT SLOT---")||day.equals("---SELECT DAY---"))){
                    templist.clear();
                    for(int i=0;i<labList.size();i++){
                        if(labList.get(i).gDay().equals(day)&&labList.get(i).gSlot().equals(slot)){
                            templist.add(labList.get(i));
                            Context con = recycler.getContext();
                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(con, R.anim.lay_fall_down);
                            recycler.setLayoutAnimation(controller);
                            recycler.scheduleLayoutAnimation();
                            recycler.setAdapter(adapter);
                        }
                    }
                }
                else{
                    templist.clear();
                    recycler.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        slotSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                slot=String.valueOf(parent.getItemAtPosition(position));
                if(!(slot.equals("---SELECT SLOT---")||day.equals("---SELECT DAY---"))){
                    templist.clear();
                    for(int i=0;i<labList.size();i++){
                        if(labList.get(i).gDay().equals(day)&&labList.get(i).gSlot().equals(slot)){
                            templist.add(labList.get(i));
                            Context con = recycler.getContext();
                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(con, R.anim.lay_fall_down);
                            recycler.setLayoutAnimation(controller);
                            recycler.scheduleLayoutAnimation();
                            recycler.setAdapter(adapter);
                        }
                    }
                }
                else{
                    templist.clear();
                    recycler.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        if (InternetError.isConnected(getBaseContext())) {

        } else {
            buildDialog(this).show();
        }
    }

    public void setList(){

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
