package com.example.minesh.navigationd;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Fade;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.codekidlabs.storagechooser.Content;
import com.codekidlabs.storagechooser.StorageChooser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Main_result_import extends AppCompatActivity {

    private String subName;
    private Spinner spinner;
    private Button ImportButton,LogoutButton;
    private int STORAGE_PERMISSION_CODE = 23;
    private String[][] data;
    private DatabaseReference dataref,rootref;
    private FirebaseAuth auth;
    private ArrayAdapter<String> spinAdapter;
    private SlidrInterface slidr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_result_import);


        slidr= Slidr.attach(this);
        slidr.unlock();

        rootref= FirebaseDatabase.getInstance().getReference();
        auth=FirebaseAuth.getInstance();


        ImportButton=findViewById(R.id.ImprtButton);
        LogoutButton=findViewById(R.id.LogoutButton);
        Fade fade = new Fade();
        fade.setDuration(400);
        getWindow().setAllowEnterTransitionOverlap(false);
        getWindow().setEnterTransition(fade);

        spinner=findViewById(R.id.SubjectSpinnerImport);
        spinAdapter = new ArrayAdapter<>(Main_result_import.this, R.layout.spinner_lay, getResources().getStringArray(R.array.Subjects));
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subName=String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ImportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(subName)||subName.equals("---SELECT SUBJECT---")){
                    Toast.makeText(Main_result_import.this, "Please Select the Subject Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{

                }
                dataref=rootref.child("Results").child("3CE").child(subName);
                if (!isStorageReadable()) {
                    requestStoragePermission();
                } else {
                    Toast.makeText(Main_result_import.this, "Please Select The Storage!!!", Toast.LENGTH_SHORT).show();
                    chooser();
                }

            }
        });

        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main_result_import.this, "Logged Out", Toast.LENGTH_SHORT).show();
                finish();
                auth.signOut();
            }
        });

    }

    private boolean isStorageReadable() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        }

        return false;
    }

    private void requestStoragePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(getApplicationContext(), "Permission Required to Access Storage", Toast.LENGTH_SHORT).show();
        }

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                chooser();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the Storage permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void chooser() {
        Content c = new Content();
        c.setInternalStorageText("Internal Storage");
        c.setCancelLabel("Cancel");
        c.setSelectLabel("Choose");
        c.setOverviewHeading("Choose Drive");

        StorageChooser chooser = new StorageChooser.Builder()
                .withActivity(Main_result_import.this)
                .withFragmentManager(getFragmentManager())
                .withMemoryBar(true)
                .setType(StorageChooser.FILE_PICKER)   //.setType(StorageChooser.FILE_PICKER) to Pick a Path of a file
                .showHidden(false) //.showHidden(true)
                .allowCustomPath(true)
                .withPredefinedPath(Environment.getExternalStorageDirectory().toString())
                .setDialogTitle("Choose Directory")
                .withContent(c)
                .build();

        chooser.show();

        chooser.setOnSelectListener(new StorageChooser.OnSelectListener() {
            @Override
            public void onSelect(String path) {
                File f=new File(path);
                Workbook wb = null;
                try {
                    wb = Workbook.getWorkbook(f);
                } catch (IOException | BiffException e) {
                    e.printStackTrace();
                }
                Sheet s=wb.getSheet("Sheet1");
                int row =s.getRows();
                int col=s.getColumns();
                data=new String[row][col];
                String str="";
                for(int i=1;i<row;i++)
                {
                    for (int c=0;c<col;c++)
                    {
                        Cell z=s.getCell(c,i);
                        data[i][c]=z.getContents();
                        str=str+data[i][c]+"  ";
                    }
                    str=str+"\n";
                    dataref.child(data[i][0]).setValue(data[i][1]);
                }
                Toast.makeText(Main_result_import.this, "Marks uploaded Successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
