package com.pro.deepak.ieee_ex;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class details extends AppCompatActivity {

    FirebaseStorage storage = FirebaseStorage.getInstance();


    StorageReference pathReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eve_work);


        final String isEvent_or_workshop = getIntent().getStringExtra("type");
        final String mTitle= getIntent().getStringExtra("title");
        pathReference = storage.getReference(isEvent_or_workshop+"/");

        final String description = getIntent().getStringExtra("description");
        final String link = getIntent().getStringExtra("link");

        setTitle(mTitle);

        final String cont1 = getIntent().getStringExtra("cont1");
        final String cont2 = getIntent().getStringExtra("cont2");
        final String cont3 = getIntent().getStringExtra("cont3");


        TextView descTV,linkTV,cont1TV,cont2TV,cont3TV,cont4TV;
        TextView linkPlaceTV,contactPlaceTV;
        final ImageView eventIV;


        descTV = findViewById(R.id.eventDescTV);
        descTV.setText(description);

//        if(mTitle.contains("Quiz"))
//            eventIV.setImageResource(R.drawable.techqilla);
//        else if(mTitle.contains("War"))
//            eventIV.setImageResource(R.drawable.witwar);
//        else if(mTitle.contains("Robo"))
//            eventIV.setImageResource(R.drawable.robotics);
//        else
//            eventIV.setVisibility(View.GONE);

        eventIV = findViewById(R.id.eventIV);
        pathReference.child(mTitle+"/").child("eventPoster.jpeg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL
                Glide.with(details.this)
                        .load(uri)
                        .into(eventIV);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });


        linkPlaceTV = findViewById(R.id.linkPlaceholder);
        linkTV = findViewById(R.id.eventRegisterTV);
        if(link!=null) {
            linkTV.setText(link);
            linkTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent linINT = new Intent(Intent.ACTION_VIEW);
                    linINT.setData(Uri.parse(link));
                    startActivity(linINT);
                }
            });
        }

        else{
            linkPlaceTV.setVisibility(View.GONE);
            linkTV.setVisibility(View.GONE);
        }

        contactPlaceTV = findViewById(R.id.contactPlaceholder);
        cont1TV = findViewById(R.id.call1);
        cont2TV = findViewById(R.id.call2);
        cont3TV = findViewById(R.id.call3);

        if(cont1!=null && cont2!=null && cont3!=null) {

            cont1TV.setText(cont1);
            cont2TV.setText(cont2);
            cont3TV.setText(cont3);
        }
        else{
            cont1TV.setVisibility(View.GONE);
            cont2TV.setVisibility(View.GONE);
            cont3TV.setVisibility(View.GONE);
            contactPlaceTV.setVisibility(View.GONE);
        }
    }
}
