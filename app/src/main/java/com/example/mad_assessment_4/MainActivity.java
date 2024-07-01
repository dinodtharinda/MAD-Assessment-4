package com.example.mad_assessment_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mad_assessment_4.utils.Constants;
import com.example.mad_assessment_4.utils.Helper;
import com.example.mad_assessment_4.views.DashboardActivity;
import com.example.mad_assessment_4.views.LoginActivity;

public class MainActivity extends AppCompatActivity {
    Intent loginScreen, homeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginScreen = new Intent(this, LoginActivity.class);
        homeScreen = new Intent(this, DashboardActivity.class);


        int userId = Helper.getIntFromSharedPref(this,Constants.USER_ID);

        if(userId>0){
            startActivity(homeScreen);
        }else{
            startActivity(loginScreen);
        }
        finish();

    }
}