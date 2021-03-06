package com.pro.deepak.ieee_ex;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
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

public class registrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText emailEdit,passwordEditText;
    Button registerButton;

    TextView headerTV,addNewRegistrationsTV;
    private ProgressDialog progressDialog;

    private String email, password;
    Animation button_anim1,button_anim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        headerTV = findViewById(R.id.headerPlaceHolder);
        headerTV.setText("Register");

        addNewRegistrationsTV = findViewById(R.id.addNewRegistrationsTV);
        addNewRegistrationsTV.setVisibility(View.GONE);

        emailEdit = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        registerButton = findViewById(R.id.LoginButton);


        button_anim1 = AnimationUtils.loadAnimation(this, R.anim.button_anim1);
        button_anim2 = AnimationUtils.loadAnimation(this, R.anim.button_anim2);

        headerTV.startAnimation(button_anim1);
        emailEdit.startAnimation(button_anim2);
        passwordEditText.startAnimation(button_anim2);
        registerButton.startAnimation(button_anim2);

        registerButton.setText("Register");
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });

    }

    private void  registerNewUser() {



        email = emailEdit.getText().toString();
        password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(email) && !email.contains("@")) {
            Toast.makeText(getApplicationContext(), "Enter Vaild E-mail", Toast.LENGTH_LONG).show();
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
                            progressDialog.setMessage("Sending Email Verification");
                            progressDialog.show();
                            sendVerificationEmail();

                        } else {
                            Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    private void sendVerificationEmail()
    {
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // after email is sent just logout the user and finish this activity
                            Toast.makeText(getApplicationContext(), "Verification E-Mail sent : "+user.getEmail(), Toast.LENGTH_LONG).show();
//                            FirebaseAuth.getInstance().signOut();
                            Intent addUSerDataInt = new Intent(registrationActivity.this, addUserDetails.class);
                            addUSerDataInt.putExtra("email",email);
                            startActivity(addUSerDataInt);
//                            finish();
                        }
                        else
                        {
                            // email not sent, so display message and restart the activity or do whatever you wish to do
                            Toast.makeText(getApplicationContext(), "Failed to send E-mail!", Toast.LENGTH_LONG).show();

                            //restart this activity
                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());

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