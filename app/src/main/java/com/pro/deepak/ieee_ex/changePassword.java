package com.pro.deepak.ieee_ex;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class changePassword extends AppCompatActivity {

    EditText currentEditText,newPassEditText,re_EnterEditText;
    Button saveNewPassButton;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        progressDialog = new ProgressDialog(this);

        currentEditText = findViewById(R.id.textInputCurrent);
        newPassEditText = findViewById(R.id.textInputNew);
        re_EnterEditText = findViewById(R.id.editTextReEnterNew);

        saveNewPassButton = findViewById(R.id.changePasswordButton);
        saveNewPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    progressDialog.setMessage("Updating Password!");
                    progressDialog.show();
                    changepassword();
            }
        });
    }


    private  void changepassword()
    {
      final  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final String currentPassword = currentEditText.getText().toString();

        final String newPassword = newPassEditText.getText().toString();

        final String reEnterPassword = re_EnterEditText.getText().toString();

        if (TextUtils.isEmpty(currentPassword)) {
            Toast.makeText(getApplicationContext(), "Enter Current password!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(newPassword)) {
            Toast.makeText(getApplicationContext(), "Enter New password!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(reEnterPassword)) {
            Toast.makeText(getApplicationContext(), "Re-Enter Password!", Toast.LENGTH_LONG).show();
            return;
        }
        if(newPassword.equals(reEnterPassword))
        {
            AuthCredential credential = EmailAuthProvider
                    .getCredential(user.getEmail(), currentPassword);


            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            finish();
                                            return;
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Sorry Passwords not updated !", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(getApplicationContext(), "Retry with correct password!", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                    });

        }

        else {
            Toast.makeText(getApplicationContext(), "New passwords do not match!", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }
}
