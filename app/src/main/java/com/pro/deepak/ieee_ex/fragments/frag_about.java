package com.pro.deepak.ieee_ex.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pro.deepak.ieee_ex.R;
import com.pro.deepak.ieee_ex.changePassword;
import com.pro.deepak.ieee_ex.login_activity;

import static android.support.constraint.Constraints.TAG;

public class frag_about extends Fragment {

    LinearLayout callLV,mailLV,feedback,rateLL;
    Button logout;
    String individual_no,clgMailID,feedbackEmail,reviewURL;
    TextView deleteAccTV,resetAccTv,callTV,clgMailTv,reviewTV,whoIsLoggedInTV;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;

    private ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_about, container, false);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("Aboutpage/");
        progressDialog = new ProgressDialog(getContext());
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        whoIsLoggedInTV = rootView.findViewById(R.id.loggedInAs);
        String userName = "Logged in as : "+user.getEmail();
        whoIsLoggedInTV.setText(userName);

        clgMailID = "null";
        feedbackEmail ="null";
        reviewURL = "null";

        callTV = rootView.findViewById(R.id.callTV);
        clgMailTv = rootView.findViewById(R.id.clgEmailTV);
        reviewTV = rootView.findViewById(R.id.feedBackTV);

        callLV = rootView.findViewById(R.id.call_LV);
        callLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iv= new Intent(Intent.ACTION_DIAL);
                iv.setData(Uri.parse("tel:"+individual_no));
                startActivity(iv);

            }
        });

        mailLV = rootView.findViewById(R.id.mail_CV);
        mailLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"+clgMailID)); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, "");
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                    startActivity(intent);


            }
        });

        feedback = rootView.findViewById(R.id.feedbackButton);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"+feedbackEmail)); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, "");
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                startActivity(intent);


            }
        });

        rateLL = rootView.findViewById(R.id.rateButton);
        rateLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Intent linINT = new Intent(Intent.ACTION_VIEW);
                        linINT.setData(Uri.parse(reviewURL));
                        startActivity(linINT);
            }
        });

        resetAccTv = rootView.findViewById(R.id.resetPwdTV);
        resetAccTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linINT = new Intent(getActivity(), changePassword.class);
                startActivity(linINT);
            }
        });

        deleteAccTV = rootView.findViewById(R.id.deleteAccountTV);
        deleteAccTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get auth credentials from the user for re-authentication. The example below shows
                // email and password credentials but there are multiple possible providers,

                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setTitle("Delete Account?");
                builder1.setMessage("\nAre you sure you want to delete?\n\n\nEnter Password : ");
                builder1.setCancelable(true);

                final EditText edittext = new EditText(getContext());
                builder1.setView(edittext);


                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String passwordEntered = edittext.getText().toString();
                                deleteUSER(passwordEntered);
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();


            }
        });

        logout = rootView.findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), login_activity.class));
                getActivity().finish();
                return;
            }
        });

        addChildEventListener();


        return rootView;
    }

    private void deleteUSER(String passwordEntered)
    {
        progressDialog.setMessage("Deleting Your account!");
        progressDialog.show();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        AuthCredential credential = EmailAuthProvider
                .getCredential(user.getEmail(), passwordEntered);

        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    FirebaseAuth.getInstance().signOut();
                                    startActivity(new Intent(getActivity(), login_activity.class));
                                    getActivity().finish();
                                    return;
                                }
                                else {
                                    progressDialog.dismiss();
                                }
                            }
                        });

                    }
                });
    }

    private void addChildEventListener()
    {

        ChildEventListener childListener = new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                individual_no = (String)dataSnapshot.child("phone").getValue();
                clgMailID = (String)dataSnapshot.child("clg_mail").getValue();
                feedbackEmail = (String)dataSnapshot.child("feedback").getValue();
                reviewURL = (String)dataSnapshot.child("rate").getValue();

                callTV.setText("Call us : " + individual_no);
                clgMailTv.setText("Email : "+clgMailID);
                reviewTV.setText("FeedBack : "+feedbackEmail);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabaseReference.addChildEventListener(childListener);

    }
}