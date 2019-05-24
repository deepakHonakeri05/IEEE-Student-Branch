package com.pro.deepak.ieee_ex.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pro.deepak.ieee_ex.R;
import com.pro.deepak.ieee_ex.calender;
import com.pro.deepak.ieee_ex.details;
import com.pro.deepak.ieee_ex.team_all;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class frag_home extends Fragment {

    AppBarLayout Appbar;
    CollapsingToolbarLayout CoolToolbar;
    Toolbar toolbar;

    boolean ExpandedActionBar = true;

    LinearLayout calenderLL,memberLL,teamLL;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_home, container, false);

//        dateAndDayTextView=(TextView)rootView.findViewById(R.id.dateAndDay);
//        SimpleDateFormat sdf = new SimpleDateFormat("EEEE d MMMM");
//        dateAndDayTextView.setText(sdf.format(calendar.getTime()));
//
//        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


        Appbar = (AppBarLayout)rootView.findViewById(R.id.appbar);
        CoolToolbar = (CollapsingToolbarLayout)rootView.findViewById(R.id.ctolbar);
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        Appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset) > 200){
                    ExpandedActionBar = false;
                    CoolToolbar.setTitle("IEEE PESIT South Student Branch");
                    //invalidateOptionsMenu();
                } else {
                    ExpandedActionBar = true;
                    CoolToolbar.setTitle("");
                    //invalidateOptionsMenu();
                }

            }
        });

        calenderLL = rootView.findViewById(R.id.calenderLL);
        calenderLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calInt = new Intent(getActivity(), calender.class);
                startActivity(calInt);
            }
        });

        memberLL = rootView.findViewById(R.id.membershipLL);
        memberLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Membership", Toast.LENGTH_SHORT).show();
                String url = "https://www.ieee.org/membership-catalog/index.html?N=0";
                Uri uriUrl = Uri.parse(url);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

        teamLL = rootView.findViewById(R.id.teamLL);
        teamLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teamInt = new Intent(getActivity(), team_all.class);
                startActivity(teamInt);
            }
        });

        return rootView;
    }

}
