package com.pro.deepak.ieee_ex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class details_eve_work extends AppCompatActivity {

    TextView highlightTV,upcomingTV,pastTv;
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eve_work);

        final String mTitle= getIntent().getStringExtra("title");

        highlightTV = findViewById(R.id.highlightTV);
        highlightTV.setText(mTitle);

        upcomingTV = findViewById(R.id.upcomingtextview);
        upcomingTV.setText("Upcoming " + mTitle);

        pastTv = findViewById(R.id.pasttextview);
        pastTv.setText(mTitle+" in the past");

        Toast.makeText(this,"No Firebase !!!", Toast.LENGTH_SHORT).show();


        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month+1) + "/"+ year ;
                Toast.makeText(details_eve_work.this,mTitle+" on "+ date, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
