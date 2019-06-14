package com.pro.deepak.ieee_ex;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText nameEditText,usnEditText,emailEdit,passwordEditText;
    Button registerButton;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        nameEditText = findViewById(R.id.editTextName);
        usnEditText = findViewById(R.id.editTextUSN);
        emailEdit = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);

        registerButton = findViewById(R.id.RegisterButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });

    }

    private void  registerNewUser()
    {

        String name,usn,email,password;

        name = nameEditText.getText().toString();
        usn = usnEditText.getText().toString();
        email = emailEdit.getText().toString();
        password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "Enter your Name.", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(usn)) {
            Toast.makeText(getApplicationContext(), "Enter university seat no.!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email.", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_LONG).show();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.setMessage("Preparing Your Dashboard");
                            progressDialog.show();
                            Intent intent = new Intent(registrationActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });
          }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,login_activity.class);
        startActivity(i);
        finish();
    }
    }

