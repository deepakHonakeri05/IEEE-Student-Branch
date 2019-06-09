package com.pro.deepak.ieee_ex.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pro.deepak.ieee_ex.R;
import com.pro.deepak.ieee_ex.calender;
import com.pro.deepak.ieee_ex.data.adapterLV;
import com.pro.deepak.ieee_ex.data.dataElement;
import com.pro.deepak.ieee_ex.details;

import java.util.ArrayList;
import java.util.List;

public class infrag_workshops extends Fragment {

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;

    String isPast=null;

    ArrayList<String> listKeys = new ArrayList<String>();
    ListView eventLV;

    List<dataElement> eventList;
    adapterLV eventAdapter;

    public infrag_workshops() {}

    @SuppressLint("ValidFragment")
    public infrag_workshops(String isPast)
    {
        this.isPast = isPast;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.infrag_workshops, container, false);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("Workshops/"+isPast);

        eventLV = rootView.findViewById(R.id.upcomingWorkLV);
        eventList = new ArrayList<>();
        eventAdapter = new adapterLV(getActivity(), R.layout.model,eventList);
        eventLV.setAdapter(eventAdapter);

        TextView placeHolder = rootView.findViewById(R.id.placeHolderWorkshop);
        placeHolder.setText(isPast+" Workshops");


        if (isPast.equals("past")) {
            addChildEventListener();
            eventLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), details.class);

                    //startActivity(intent);
                }
            });
        }
        else {
            addChildEventListenerUPComing();
            eventLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), details.class);
                    intent.putExtra("type","Workshops");
                    intent.putExtra("title",eventList.get(position).getTitle());
                    intent.putExtra("description",eventList.get(position).getDesc());
                    intent.putExtra("link",eventList.get(position).getLink());
                    intent.putExtra("cont1",eventList.get(position).getCont1());
                    intent.putExtra("cont2",eventList.get(position).getCont2());
                    intent.putExtra("cont3",eventList.get(position).getCont3());

                    startActivity(intent);
                }
            });
        }


        return rootView;
    }
    private void addChildEventListener()
    {

        ChildEventListener childListener = new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                dataElement model = dataSnapshot.getValue(dataElement.class);
                model.setTitleD((String)dataSnapshot.child("Title").getValue());
                model.setDesc((String)dataSnapshot.child("Desc").getValue());
                listKeys.add(dataSnapshot.getKey());
                eventAdapter.add(model);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int index = listKeys.indexOf(key);

                if (index != -1) {
                    eventList.remove(index);
                    listKeys.remove(index);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabaseReference.addChildEventListener(childListener);

    }

    private void addChildEventListenerUPComing()
    {

        ChildEventListener childListener = new ChildEventListener() {


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                dataElement model = dataSnapshot.getValue(dataElement.class);
                model.setTitleD((String)dataSnapshot.child("title").getValue());
                model.setDesc((String)dataSnapshot.child("desc").getValue());
                model.setLink((String)dataSnapshot.child("link").getValue());
                model.setCont1((String)dataSnapshot.child("cont1").getValue());
                model.setCont2((String)dataSnapshot.child("cont2").getValue());
                model.setCont3((String)dataSnapshot.child("cont3").getValue());

                listKeys.add(dataSnapshot.getKey());
                eventAdapter.add(model);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int index = listKeys.indexOf(key);

                if (index != -1) {
                    eventList.remove(index);
                    listKeys.remove(index);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabaseReference.addChildEventListener(childListener);

    }
}