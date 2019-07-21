package com.pro.deepak.ieee_ex;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_activity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText emailET,passwordET;
    TextView headerTV,addRegistrationsTV,loginPlaceholderText;
    ProgressDialog progressDialog;
    Animation button_anim1,button_anim2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        emailET = findViewById(R.id.editTextEmail);
        passwordET = findViewById(R.id.editTextPassword);
        loginPlaceholderText = findViewById(R.id.headerPlaceHolder);
        addRegistrationsTV = findViewById(R.id.addNewRegistrationsTV);
        final Button login = findViewById(R.id.LoginButton);


        button_anim1 = AnimationUtils.loadAnimation(this, R.anim.button_anim1);
        button_anim2 = AnimationUtils.loadAnimation(this, R.anim.button_anim2);

        loginPlaceholderText.startAnimation(button_anim1);
        emailET.startAnimation(button_anim2);
        passwordET.startAnimation(button_anim2);
        login.startAnimation(button_anim2);
        addRegistrationsTV.startAnimation(button_anim2);

        headerTV = findViewById(R.id.headerPlaceHolder);
        headerTV.setText("LogIn");

        addRegistrationsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_activity.this, registrationActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInUser();

            }
        });
    }

    private void logInUser()
    {
        String email, password;
        email = emailET.getText().toString();
        password = passwordET.getText().toString();

        if (TextUtils.isEmpty(email) || !email.contains("@")) {
            Toast.makeText(getApplicationContext(), "Enter Valid Email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.setMessage("Preparing Your Dashboard");
                                progressDialog.show();
                                checkAndLogInUser();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Check E-mail/Password!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });


    }

    private void checkAndLogInUser()
    {
        FirebaseUser user = mAuth.getCurrentUser();

        if(user.isEmailVerified())
        {
            Intent intent = new Intent(login_activity.this, MainActivity.class);
            startActivity(intent);
        }

        else {
            Toast.makeText(getApplicationContext(), "Verify E-Mail " + user.getEmail(), Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }

    }

}
