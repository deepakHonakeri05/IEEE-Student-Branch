package com.pro.deepak.ieee_ex.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pro.deepak.ieee_ex.R;

public class frag_about extends Fragment {

    LinearLayout callLV,mailLV,feedback,rateLL;
    Button logout;
    String individual_no,clgMailID,feedbackEmail,reviewURL;
    TextView deleteAccTV,resetAccTv,callTV,clgMailTv,reviewTV;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_about, container, false);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("Aboutpage/");

        //individual_no = "+91 9456411806";
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

            }
        });

        deleteAccTV = rootView.findViewById(R.id.deleteAccountTV);
        deleteAccTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        logout = rootView.findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        addChildEventListener();


        return rootView;
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