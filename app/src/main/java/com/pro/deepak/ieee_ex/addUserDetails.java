package com.pro.deepak.ieee_ex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class addUserDetails extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    private EditText mNameEditText, mUSNEditText, mIEEIDEditText;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();
        String Email = intent.getStringExtra("email");

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("userInfo/");

        mNameEditText = findViewById(R.id.editTextName);
        mUSNEditText = findViewById(R.id.editTextUSN);
        mIEEIDEditText = findViewById(R.id.editTextIEEID);


        registerButton = findViewById(R.id.RegisterButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameEditText.getText().toString();
                String usn = mUSNEditText.getText().toString();
                String ieeeID = mIEEIDEditText.getText().toString();

                Pattern p = Pattern.compile("1PE\\d\\d[A-Z][A-Z]\\d\\d\\d");
                Matcher m = p.matcher(usn);
                boolean b = m.matches();

                if(b){
                    DatabaseReference element = mDatabaseReference.push();

                    element.child("uid").setValue(element.getKey());
                    element.child("Name").setValue(name);
                    element.child("USN").setValue(usn);
                    element.child("ieeeID").setValue(ieeeID);

                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(addUserDetails.this, MainActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please Enter Valid Data",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
