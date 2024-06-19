package com.example.mad_assessment_4.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.mad_assessment_4.R;
import com.example.mad_assessment_4.views.fragments.CartFragment;
import com.example.mad_assessment_4.views.fragments.HomeFragment;
import com.example.mad_assessment_4.views.fragments.OrdersFragment;
import com.example.mad_assessment_4.views.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {
    private BottomNavigationView bnvHome;
    private FrameLayout flHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bnvHome = findViewById(R.id.bnvHome);
        flHome = findViewById(R.id.flHome);

        bnvHome.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.navHome) {
                    loadFragment(new HomeFragment(),false);
                } else if (itemId == R.id.navCart) {
                   loadFragment(new CartFragment(),false);
                } else if (itemId == R.id.navOrders) {
                    loadFragment(new OrdersFragment(),false);
                } else {
                    loadFragment(new ProfileFragment(),false);
                }
                return true;
            }
        });

        loadFragment(new HomeFragment(),true);
    }

    private void loadFragment(Fragment fragment, boolean isAppInitialized ){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(isAppInitialized){
            fragmentTransaction.add(R.id.flHome, fragment);
        }else{
            fragmentTransaction.replace(R.id.flHome, fragment);
        }
        fragmentTransaction.commit();
    }


}