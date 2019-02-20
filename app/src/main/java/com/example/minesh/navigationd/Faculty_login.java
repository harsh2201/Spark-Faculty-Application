package com.example.minesh.navigationd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Faculty_login extends AppCompatActivity {

    private TextInputLayout emailText;
    private TextInputLayout passwordText;
    private TextInputEditText pass;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseauth;
    private Button loginButton,resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);

        loginButton=findViewById(R.id.loginButton);
        resetButton=findViewById(R.id.resetButton);
        emailText=findViewById(R.id.emailLoginText);
        passwordText=findViewById(R.id.passwordLoginText);
        pass=findViewById(R.id.pass);
        progressDialog=new ProgressDialog(this);
        firebaseauth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Faculty_login.this,ResetPassword.class));
            }
        });

    }
    private void login() {
        String email=emailText.getEditText().getText().toString().trim();
        String password=passwordText.getEditText().getText().toString().trim();

        if(TextUtils.isEmpty(email)){

            if(TextUtils.isEmpty(password)){
                Toast.makeText(Faculty_login.this, "Please Enter your Email and Password", Toast.LENGTH_SHORT).show();
                pass.setText(null);
                return;
            }
            else{
                Toast.makeText(Faculty_login.this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
                pass.setText(null);
                return;
            }
        }
        else{
            if(TextUtils.isEmpty(password)){
                Toast.makeText(Faculty_login.this, "Please Enter the Password", Toast.LENGTH_SHORT).show();
                pass.setText(null);
                return;
            }
            else{

            }
        }
        progressDialog.setMessage("Logging In");
        progressDialog.show();
        firebaseauth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(Faculty_login.this, "You have logged In Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(Faculty_login.this, Main_result_import.class));
                        }
                        else
                        {
                            Toast.makeText(Faculty_login.this,"Failed to Login!",Toast.LENGTH_SHORT).show();
                            pass.setText(null);
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}
