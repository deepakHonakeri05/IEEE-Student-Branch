package com.pro.deepak.ieee_ex;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class details extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eve_work);


        final String mTitle= getIntent().getStringExtra("title");
        final String description = getIntent().getStringExtra("description");
        final String link = getIntent().getStringExtra("link");

        setTitle(mTitle);

        final String cont1 = getIntent().getStringExtra("cont1");
        final String cont2 = getIntent().getStringExtra("cont2");
        final String cont3 = getIntent().getStringExtra("cont3");


        TextView descTV,linkTV,cont1TV,cont2TV,cont3TV,cont4TV;
        TextView linkPlaceTV,contactPlaceTV;
        ImageView eventIV;

        eventIV = findViewById(R.id.eventIV);
        descTV = findViewById(R.id.eventDescTV);
        linkTV = findViewById(R.id.eventRegisterTV);
        cont1TV = findViewById(R.id.call1);
        cont2TV = findViewById(R.id.call2);
        cont3TV = findViewById(R.id.call3);

        linkPlaceTV = findViewById(R.id.linkPlaceholder);
        contactPlaceTV = findViewById(R.id.contactPlaceholder);

        if(mTitle.contains("Quiz"))
            eventIV.setImageResource(R.drawable.techqilla);
        else if(mTitle.contains("War"))
            eventIV.setImageResource(R.drawable.witwar);
        else if(mTitle.contains("Robo"))
            eventIV.setImageResource(R.drawable.robotics);
        else
            eventIV.setVisibility(View.GONE);

        descTV.setText(description);

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
