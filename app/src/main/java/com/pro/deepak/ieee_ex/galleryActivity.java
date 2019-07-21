package com.pro.deepak.ieee_ex;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.pro.deepak.ieee_ex.fragments.infrag_events;
import com.pro.deepak.ieee_ex.fragments.infrag_workshops;

public class galleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter
    {
        MyPagerAdapter(FragmentManager fm){
            super(fm);
        }
        @Override    public Fragment getItem(int position) {
            switch (position){
                case 0: return new infrag_events("past");
                case 1: return new infrag_workshops("past");
            }
            return null;
        }
        @Override
        public int getCount() {
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position)
            {
                case 0: return "Events";
                case 1: return "Workshops";
                default: return null;
            }
        }
    }}
