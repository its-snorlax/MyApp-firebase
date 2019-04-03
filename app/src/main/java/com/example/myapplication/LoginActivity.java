package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private Button submit;
    private EditText email,password;
    private TextView goToSignup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        submit = findViewById(R.id.submit_login);
        goToSignup =  findViewById(R.id.openSignup);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Login Progress");

        firebaseAuth = FirebaseAuth.getInstance();

        goToSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSignupActivity = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(openSignupActivity);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


    }

    private void login() {
        progressDialog.show();
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(emailText,passwordText)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Intent openDashboard = new Intent(getApplicationContext(), DashboardActivity.class);
                            startActivity(openDashboard);
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"not Done",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
