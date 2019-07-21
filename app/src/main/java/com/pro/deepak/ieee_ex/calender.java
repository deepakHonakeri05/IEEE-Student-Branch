package com.pro.deepak.ieee_ex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class calender extends AppCompatActivity {
    TextView dateTV;
    Calendar calendar= Calendar.getInstance();

    @Override
    public void onBackPressed() {
        //To support reverse transitions when user clicks the device back button
        supportFinishAfterTransition();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender);
        final SimpleDateFormat sdf = new SimpleDateFormat("EEEE d MMMM");
        setTitle(sdf.format(calendar.getTime()));

        CalendarView mCalendarView;

        dateTV = findViewById(R.id.dateCalender);

        mCalendarView =  findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month+1) + "/"+ year ;
                //Toast.makeText(calender.this,mTitle+" on "+ date, Toast.LENGTH_SHORT).show();
                dateTV.setText(date);
            }
        });

    }
}