package com.pro.deepak.ieee_ex;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.pro.deepak.ieee_ex.fragments.frag_about;
import com.pro.deepak.ieee_ex.fragments.frag_home;
import com.pro.deepak.ieee_ex.fragments.frag_team;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_team:
                    item.setChecked(true);
                    frag_team teamTAB = new frag_team();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, teamTAB)
                            .commit();
                    return true;
                case R.id.navigation_home:
                    item.setChecked(true);
                    frag_home homeTAB = new frag_home();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, homeTAB)
                            .commit();

                    return true;
                case R.id.navigation_about:
                    item.setChecked(true);
                    frag_about aboutTAB = new frag_about();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, aboutTAB)
                            .commit();
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);
    }

}
