package com.example.minesh.navigationd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class ResetPassword extends AppCompatActivity {

    private Button resetButton;
    private SlidrInterface slidr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        slidr= Slidr.attach(this);
        slidr.unlock();

        resetButton=findViewById(R.id.ResetPassword);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog=new ProgressDialog(ResetPassword.this);
                final TextInputLayout emailText;
                emailText=findViewById(R.id.emailLoginText);
                String email=emailText.getEditText().getText().toString().trim();
                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(ResetPassword.this, "Please Enter your Email.", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("Sending Mail...");
                progressDialog.show();
                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(ResetPassword.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ResetPassword.this, "Email is sent successfully. Check your mail box", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                                else
                                {
                                    emailText.setError("This email does not exists. Plesae enter valid email.");
                                }
                                progressDialog.dismiss();
                            }
                        });
            }
        });

    }
}
