package com.pro.deepak.ieee_ex.fragments;

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
import com.pro.deepak.ieee_ex.data.adapterLV;
import com.pro.deepak.ieee_ex.data.dataElement;
import com.pro.deepak.ieee_ex.details;

import java.util.ArrayList;
import java.util.List;


public class infrag_events extends Fragment {

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReferenceUP,mDatabaseReferencePAST;

    ArrayList<String> listKeysPAST = new ArrayList<String>();
    ArrayList<String> listKeysUP = new ArrayList<String>();
    ListView upComingLV,pastLV;

    List<dataElement> pastList,upComList;
    adapterLV adapterPAST,adapterUP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.infrag_events, container, false);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReferenceUP = mFirebaseDatabase.getReference("Events/upcoming");
        mDatabaseReferencePAST = mFirebaseDatabase.getReference("Events/past/");
        pastLV = rootView.findViewById(R.id.pastEventLV);
        upComingLV = rootView.findViewById(R.id.upcomingEventLV);

        pastList = new ArrayList<>();
        upComList = new ArrayList<>();

        adapterPAST = new adapterLV(getActivity(), R.layout.model,pastList);
        pastLV.setAdapter(adapterPAST);

        adapterUP = new adapterLV(getActivity(), R.layout.model,upComList);
        upComingLV.setAdapter(adapterUP);

        addChildEventListener();
        addChildEventListenerUP();

        upComingLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), details.class);
                intent.putExtra("type","Events");
                intent.putExtra("title",upComList.get(position).getTitle());
                intent.putExtra("description",upComList.get(position).getDesc());
                intent.putExtra("link",upComList.get(position).getLink());
                intent.putExtra("cont1",upComList.get(position).getCont1());
                intent.putExtra("cont2",upComList.get(position).getCont2());
                intent.putExtra("cont3",upComList.get(position).getCont3());

                startActivity(intent);
            }
        });

        pastLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), details.class);
                intent.putExtra("title",pastList.get(position).getTitle());
                intent.putExtra("description",pastList.get(position).getDesc());
                startActivity(intent);
                //Toast.makeText(vainglory.this, "Position : "+position, Toast.LENGTH_SHORT).show();//
            }
        });


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
                listKeysPAST.add(dataSnapshot.getKey());
                adapterPAST.add(model);

                //model.setLink((String)dataSnapshot.child("link").getValue());


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
                int index = listKeysPAST.indexOf(key);

                if (index != -1) {
                    pastList.remove(index);
                    listKeysPAST.remove(index);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabaseReferencePAST.addChildEventListener(childListener);

    }

    private void addChildEventListenerUP()
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

                listKeysUP.add(dataSnapshot.getKey());
                adapterUP.add(model);

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
                int index = listKeysUP.indexOf(key);

                if (index != -1) {
                    pastList.remove(index);
                    listKeysUP.remove(index);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabaseReferenceUP.addChildEventListener(childListener);

    }
}
