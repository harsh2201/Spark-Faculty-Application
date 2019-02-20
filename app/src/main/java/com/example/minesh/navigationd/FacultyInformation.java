package com.example.minesh.navigationd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.Map;
import java.util.TreeMap;

public class FacultyInformation extends AppCompatActivity {

    private TextView textView, textName;
    private ImageView imageView;
    private Map<String, String> facInfo = new TreeMap<>();
    private Intent i;
    private ActivityOptionsCompat optionsCompat;
    private FloatingActionButton BlogButton,EmailButton;
    private Animation myAnim;
    private SlidrInterface slidr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_information);

        textView = findViewById(R.id.text);
        textName = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView3);

        slidr= Slidr.attach(this);
        slidr.unlock();

        i = new Intent(FacultyInformation.this, Schedule.class);

        Intent intent = getIntent();
        final String Name = intent.getStringExtra("Faculty_retrieve_class");
        final int image = intent.getIntExtra("image", 0);
        facInfo.put("Aniruddhkumar G. Fataniya", "Assistant Professor\n" +
                "M.Tech. CE.\n" +
                "Area of Expertise: Algorithms, Data Structures\n" +
                "Area of Research: Mobile Adhoc networks, Information Security\n" +
                "\tAniruddhfataniya.Ce@Charusat.Ac.In\n" +
                "\tPhone No:5288.\n");
        facInfo.put("Dipsi Dave", "Assistant Professor\n" +
                "M.Tech. CE.\n" +
                "Area of Expertise: C, gcc, Artificial Intelligence, Algorithm\n" +
                "Area of Research: Stream data compression, Image Processing, gcc\n" +
                "\tDipsidave.Ce@Charusat.Ac.In\n" +
                "\tPhone No:5286.");
        facInfo.put("Padmavathi Bindulal", "Assistant Professor\n" +
                "M.Tech. CSE.| PhD Pursuing\n" +
                "Area of Expertise: Data Structures, Algorithms, Operating Systems.\n" +
                "Area of Research: Algorithms , Data Structures.\n" +
                "\tPadmavathibindulal.Ce@Charusat.Ac.In\n" +
                "\tPhone No:5122.");
        facInfo.put("Khushboo Patel", "Assistant Professor\n" +
                "M.Tech. CE. | PhD Pursuing\n" +
                "Area of Expertise: DAA, Networking, Data structure, DBMS.\n" +
                "Area of Research: Satellite Image processing.\n" +
                "\tKhushboopatel.Ce@Charusat.Ac.In\n" +
                "\tPhone No:5115.");
        facInfo.put("Kruti Dhyani", "Assistant Professor\n" +
                "M.Tech. C.E.\n" +
                "Area of Expertise: C, C++, Data Structure, OOAD.\n" +
                "Area of Research: Data Mining.\n" +
                "\tKrutidhyani.Ce@Charusat.Ac.In\n" +
                "\tPhone No:5115.");
        facInfo.put("Ronak Patel", "Assistant Professor\n" +
                "M.Tech. C.E. | PhD Pursuing\n" +
                "Area of Expertise: C, C++, Operating System, Parallel Computing, Cryptography and Network Security, Data Structure.\n" +
                "Area of Research: Recommender System, GPU Computing.\n" +
                "\tRonakpatel.Ce@Charusat.Ac.In\n" +
                "\tPhone No:5123.");
        facInfo.put("Dippal Israni", "Assistant Professor\n" +
                "M.Tech. C.E. | PhD Pursuing\n" +
                "Area of Expertise: Digital Image Processing, Parallel Computing.\n" +
                "Area of Research: Video and Image Processing.\n" +
                "\tDippalisrani.Ce@Charusat.Ac.In\n" +
                "\tPhone No: 5135.");
        facInfo.put("Hardik Mandora", "Assistant Professor\n" +
                "M.Tech. C.E. | PhD Pursuing\n" +
                "Area of Expertise: Computer Netwokring, Mobile Computing, Java.\n" +
                "Area of Research: Cloud Computing.\n" +
                "\tHardikmandora.Ce@Charusat.Ac.In\n" +
                "\tPhone No: 5113.");
        facInfo.put("Minal Maniar", "Assistant Professor\n" +
                "M.Tech(WT) | PhD Pursuing\n" +
                "Area of Expertise: C, Java, Web Technologies\n" +
                "Area of Research: Internet and Web Applications\n" +
                "\tMinalmaniar.Ce@Charusat.Ac.In\n" +
                "\tPhone No:5122.");
        facInfo.put("Chintan Bhatt", "Assistant Professor\n" +
                "M.E. C.E. | PhD Pursuing\n" +
                "Area of Expertise: Networking, Mobile Computing.\n" +
                "Area of Research: Data Minig, Networking.\n" +
                "\tChintanbhatt.Ce@Charusat.Ac.In\n" +
                "\tPhone No:5113.");
        facInfo.put("Martin Parmar", "Assistant Professor\n" +
                "M.E. C.S.E.| PhD Pursuing\n" +
                "Area of Expertise: ASP.NET with C#, PHP and MySQL,Sevlet and JSP.\n" +
                "Area of Research: Mobile Adhoc Network.\n" +
                "\tMartinparmar.Ce@Charusat.Ac.In\n" +
                "\tPhone No:5116.");
        facInfo.put("Devyani Panchal", "Assistant Professor\n" +
                "M.Tech.C.E\n" +
                "Area of Expertise: Cryptography.\n" +
                "Area of Research: Biometric security, Cryptography, Data storage security.\n" +
                "\tDevyanipanchal.It@Charusat.Ac.In\n" +
                "\tPhone No:5114.");
        facInfo.put("Vaishali Mewada", "Assistant Professor\n" +
                "M.E. C.E.| PhD Pursuing\n" +
                "Area of Expertise: C, Data Structure and Algorithm.\n" +
                "Area of Research: Mobile Adhoc Network.\n" +
                "\tVaishalimewada.Ce@Charusat.Ac.In\n" +
                "\tPhone No:5122.");
        facInfo.put("Mrugendrasinh Rahevar", "Assistant Professor\n" +
                "M.E. CSE. | PhD Pursuing\n" +
                "Area of Expertise: OOP with JAVA, PHP, Python, SOA\n" +
                "Area of Research: Information Retrieval, Deep Learning.\n" +
                "\tMrugendrarahevar.Ce@Charusat.Ac.In\n" +
                "\tPhone No:5113.");
        facInfo.put("Ashwin Makwana", "Assistant Professor\n" +
                "M.E. C.E. | Phd. Pursuing\n" +
                "Area of Expertise: Artificial Intelligence, Software Engineering, Project Management, Microsoft .Net Programming\n" +
                "Area of Research: Semantic WebResonal, AI.\n" +
                "\tAshwinmakwana.Ce@Charusat.Ac.In\n" +
                "\tPhone No: 5123/5213.");
        facInfo.put("Arpita Shah", "Assistant Professor\n" +
                "M.E. C.E. | Ph.D.\n" +
                "Area of Expertise: Operating system, Database, Computer Architecture.\n" +
                "Area of Research: In-memory Database.\n" +
                "\tArpitashah.Ce@Charusat.Ac.In\n" +
                "\tPhone No:5125.");
        facInfo.put("Ritesh Patel", "Assistant Professor\n" +
                "M.E. C.E. | Ph.D \n" +
                "Area of Expertise: Cloud Computing, Computer Network. \n" +
                "Area of Research: Cloud Computing. \n" +
                "\tRiteshpatel.Ce@Charusat.Ac.In\n" +
                "\tPhone No:5114.");
        textName.setText(Name);
        textView.setText(facInfo.get(Name));
        imageView.setImageResource(image);
        myAnim = AnimationUtils.loadAnimation(this,R.anim.myanim2);
        imageView.startAnimation(myAnim);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(FacultyInformation.this, imageView, "myImage");
                i.putExtra("Faculty_retrieve_class", Name);
                i.putExtra("image", image);
                startActivity(i, optionsCompat.toBundle());
            }
        });

        BlogButton = findViewById(R.id.BlogButton);
        EmailButton = findViewById(R.id.EmailButton);

        BlogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String site = null;
                switch (Name) {
                    case "Aniruddhkumar G. Fataniya":
                        site = "https://in.linkedin.com/in/aniruddh-fataniya-8060342a";
                        break;
                    case "Dipsi Dave":
                        site = "https://in.linkedin.com/in/dipsi-dave-7822b984";
                        break;
                    case "Padmavathi Bindulal":
                        site = "https://www.researchgate.net/profile/Padmavathi_Bindulal";
                        break;
                    case "Khushboo Patel":
                        site = "https://in.linkedin.com/in/khushboo-patel-6a819929";
                        break;
                    case "Kruti Dhyani":
                        site = "http://117.239.83.200:8110/wordpress/Faculty_retrieve_class-details/";
                        break;
                    case "Ronak Patel":
                        site = "https://sites.google.com/site/knowledgedisc4u/contact";
                        break;
                    case "Dippal Israni":
                        site = "https://www.researchgate.net/profile/Dippal_Israni";
                        break;
                    case "Hardik Mandora":
                        site = "https://sites.google.com/a/ecchanga.ac.in/hardikmandora/";
                        break;
                    case "Minal Maniar":
                        site = "https://in.linkedin.com/in/mmaniar";
                        break;
                    case "Chintan Bhatt":
                        site = "https://sites.google.com/a/ecchanga.ac.in/chintan/contact";
                        break;
                    case "Martin Parmar":
                        site = "https://in.linkedin.com/in/martin-parmar";
                        break;
                    case "Devyani Panchal":
                        site = "https://in.linkedin.com/in/devyani-panchal-ab92a38a";
                        break;
                    case "Vaishali Mewada":
                        site = "https://in.linkedin.com/in/vaishali-mewada-7a619053";
                        break;
                    case "Mrugendrasinh Rahevar":
                        site = "https://in.linkedin.com/in/mrugendrasinh-rahevar-9569b911";
                        break;
                    case "Ashwin Makwana":
                        site = "https://in.linkedin.com/in/ashwin-makwana-87402632";
                        break;
                    case "Arpita Shah":
                        site = "https://sites.google.com/a/ecchanga.ac.in/arpitashah/";
                        break;
                    case "Ritesh Patel":
                        site = "https://scholar.google.co.in/citations?user=AHODKrIAAAAJ&hl=en";
                        break;
                }
                if (InternetError.isConnected(getBaseContext())) {
                    Intent siteIntent = new Intent(Intent.ACTION_VIEW);
                    siteIntent.setData(Uri.parse(site));
                    startActivity(siteIntent);
                } else {
                    buildDialog(FacultyInformation.this).show();
                }
            }
        });

        EmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = null;
                switch (Name) {
                    case "Aniruddhkumar G. Fataniya":
                        email = "Aniruddhfataniya.Ce@Charusat.Ac.In";
                        break;
                    case "Dipsi Dave":
                        email = "Dipsidave.Ce@Charusat.Ac.In";
                        break;
                    case "Padmavathi Bindulal":
                        email = "Padmavathibindulal.Ce@Charusat.Ac.In";
                        break;
                    case "Khushboo Patel":
                        email = "Khushboopatel.Ce@Charusat.Ac.In";
                        break;
                    case "Kruti Dhyani":
                        email = "Krutidhyani.Ce@Charusat.Ac.In";
                        break;
                    case "Ronak Patel":
                        email = "Ronakpatel.Ce@Charusat.Ac.In";
                        break;
                    case "Dippal Israni":
                        email = "Dippalisrani.Ce@Charusat.Ac.In";
                        break;
                    case "Hardik Mandora":
                        email = "Hardikmandora.Ce@Charusat.Ac.In";
                        break;
                    case "Minal Maniar":
                        email = "Minalmaniar.Ce@Charusat.Ac.In";
                        break;
                    case "Chintan Bhatt":
                        email = "Chintanbhatt.Ce@Charusat.Ac.In";
                        break;
                    case "Martin Parmar":
                        email = "Martinparmar.Ce@Charusat.Ac.In";
                        break;
                    case "Devyani Panchal":
                        email = "Devyanipanchal.It@Charusat.Ac.In";
                        break;
                    case "Vaishali Mewada":
                        email = "Vaishalimewada.Ce@Charusat.Ac.In";
                        break;
                    case "Mrugendrasinh Rahevar":
                        email = "Mrugendrarahevar.Ce@Charusat.Ac.In";
                        break;
                    case "Ashwin Makwana":
                        email = "Ashwinmakwana.Ce@Charusat.Ac.In";
                        break;
                    case "Arpita Shah":
                        email = "Arpitashah.Ce@Charusat.Ac.In";
                        break;
                    case "Ritesh Patel":
                        email = "Riteshpatel.Ce@Charusat.Ac.In";
                        break;
                }
                if (InternetError.isConnected(getBaseContext())) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto: " + email));
                    if (emailIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(emailIntent);
                    }
                } else {
                    buildDialog(FacultyInformation.this).show();
                }
            }
        });
    }



    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        //builder.setTitle();
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this!!");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder;
    }
}
