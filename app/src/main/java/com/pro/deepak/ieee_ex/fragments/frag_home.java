package com.pro.deepak.ieee_ex.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pro.deepak.ieee_ex.R;
import com.pro.deepak.ieee_ex.details_eve_work;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class frag_home extends Fragment {

    private TextView dateAndDayTextView;
    private Calendar calendar= Calendar.getInstance();
    private CardView eventsCV,workshopsCV,memberCV;
    private String url;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_home, container, false);

        dateAndDayTextView=(TextView)rootView.findViewById(R.id.dateAndDay);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE d MMMM");
        dateAndDayTextView.setText(sdf.format(calendar.getTime()));

        eventsCV = rootView.findViewById(R.id.event_CV);
        eventsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), details_eve_work.class);
                intent.putExtra("title", "Events");
                startActivity(intent);
            }
        });

        workshopsCV = rootView.findViewById(R.id.workshop_CV);
        workshopsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), details_eve_work.class);
                intent.putExtra("title", "Workshops");
                startActivity(intent);
            }
        });


        memberCV = rootView.findViewById(R.id.member_CV);
        memberCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://www.ieee.org/membership-catalog/index.html?N=0";
                Uri uriUrl = Uri.parse(url);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

        return rootView;
    }

}
