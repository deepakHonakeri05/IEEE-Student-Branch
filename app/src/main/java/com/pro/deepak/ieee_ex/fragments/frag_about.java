package com.pro.deepak.ieee_ex.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.pro.deepak.ieee_ex.R;

public class frag_about extends Fragment {

    CardView callCV,mailCV;
    Button logout,setting,feedback;
    String individual_no;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_about, container, false);

        individual_no = "+91 9456411806";

        callCV = rootView.findViewById(R.id.call_CV);
        callCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iv= new Intent(Intent.ACTION_DIAL);
                iv.setData(Uri.parse("tel:"+individual_no));
                startActivity(iv);

            }
        });

        mailCV = rootView.findViewById(R.id.mail_CV);
        mailCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"+"ieeepesitsouth@gmail.com")); // only email apps should handle this
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
                intent.setData(Uri.parse("mailto:"+"shubhams3013@gmail.com")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, "");
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                startActivity(intent);


            }
        });

        setting = rootView.findViewById(R.id.settingsButton);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Setting was Pressed!", Toast.LENGTH_SHORT).show();
            }
        });

        logout = rootView.findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        return rootView;
    }
}