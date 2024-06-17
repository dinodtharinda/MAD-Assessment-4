package com.example.mad_assessment_4.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mad_assessment_4.R;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnGoRegister,btnGuest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnGoRegister = findViewById(R.id.btnGoRegister);

        Intent registerScreen = new Intent(this,RegisterActivity.class);
        Intent homeScreen = new Intent(this,HomeActivity.class);

        btnGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registerScreen);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homeScreen);
                finish();
            }
        });


    }
}