package com.pro.deepak.ieee_ex.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pro.deepak.ieee_ex.R;

public class frag_team extends Fragment {
    CardView annCV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_team, container, false);

        annCV = rootView.findViewById(R.id.ann_CV);
        annCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Yet to add details", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
