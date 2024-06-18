package com.example.mad_assessment_4.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_assessment_4.R;
import com.example.mad_assessment_4.controllers.CustomerController;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnGoRegister,btnGuest;
    EditText etEmail,etPassword;
    CustomerController customerController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        customerController = new CustomerController(this);

        btnLogin = findViewById(R.id.btnLogin);
        btnGoRegister = findViewById(R.id.btnGoRegister);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);


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
//               if(validateFields()){
//                   String email = etEmail.getText().toString();
//                   String password = etPassword.getText().toString();
//                  Boolean isLog = customerController.loginCustomer(email,password);
//
//                  if(isLog){
//                      startActivity(homeScreen);
//                  }else{
//                      Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
//                  }
//               }

                startActivity(homeScreen);
            }
        });
    }

    private boolean validateFields() {

        if (etEmail.getText().toString().trim().isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString().trim()).matches()) {
            etEmail.setError("Enter a valid email");
            etEmail.requestFocus();
            return false;
        }

        if (etPassword.getText().toString().trim().isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return false;
        }


        return true;
    }
}