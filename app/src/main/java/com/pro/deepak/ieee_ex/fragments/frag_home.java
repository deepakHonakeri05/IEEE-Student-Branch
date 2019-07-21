package com.pro.deepak.ieee_ex.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pro.deepak.ieee_ex.R;
import com.pro.deepak.ieee_ex.calender;
import com.pro.deepak.ieee_ex.details;
import com.pro.deepak.ieee_ex.galleryActivity;
import com.pro.deepak.ieee_ex.team_all;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class frag_home extends Fragment {

    AppBarLayout Appbar;
    CollapsingToolbarLayout CoolToolbar;
    Toolbar toolbar;

    boolean ExpandedActionBar = true;
    ImageView galleryIV,calenderIV;

    LinearLayout memberLL,teamLL;  //calenderCV, galleryLL;
    CardView galleryLL,calenderLL;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_home, container, false);
        Animation pulse = AnimationUtils.loadAnimation(getActivity(), R.anim.pulse);

        calenderIV = rootView.findViewById(R.id.calenderIV);
        calenderLL = rootView.findViewById(R.id.calenderCV);
        calenderLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calInt = new Intent(getActivity(), calender.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(), calenderIV, "calender");
                startActivity(calInt,options.toBundle());
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

        galleryIV = rootView.findViewById(R.id.galleryIV);
        galleryLL = rootView.findViewById(R.id.galleryCV);
        galleryLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryInt = new Intent(getActivity(), galleryActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(), galleryIV, "gallery");
                startActivity(galleryInt,options.toBundle());
            }
        });

        return rootView;
    }

}
