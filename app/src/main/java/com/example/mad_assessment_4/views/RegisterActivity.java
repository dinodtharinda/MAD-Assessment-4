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
import com.example.mad_assessment_4.data.models.Customer;

public class RegisterActivity extends AppCompatActivity {
    Button btnGoLogin, btnRegister, btnGuest;
    EditText etName, etEmail, etAddress, etPassword, etPhone;
    CustomerController customerController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        customerController = new CustomerController(this);

        btnGoLogin = findViewById(R.id.btnGoLogin);
        btnRegister = findViewById(R.id.btnRegister);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etPassword = findViewById(R.id.etPassword);
        etPhone = findViewById(R.id.etPhone);

        Intent loginScreen = new Intent(this, LoginActivity.class);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    String name = etName.getText().toString();
                    String email = etEmail.getText().toString();
                    String address = etAddress.getText().toString();
                    String password = etPassword.getText().toString();
                    String phone = etPhone.getText().toString();

                    customerController.registerCustomer(new Customer(name,email,password,phone,address));
                    Toast.makeText(RegisterActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(loginScreen);
            }
        });
    }

    private boolean validateFields() {
        if (etName.getText().toString().trim().isEmpty()) {
            etName.setError("Name is required");
            etName.requestFocus();
            return false;
        }
        if (etPhone.getText().toString().trim().isEmpty()) {
            etPhone.setError("Phone number is required");
            etPhone.requestFocus();
            return false;
        }
        if (!android.util.Patterns.PHONE.matcher(etPhone.getText().toString().trim()).matches()) {
            etPhone.setError("Enter a valid phone number");
            etPhone.requestFocus();
            return false;
        }
        if (etAddress.getText().toString().trim().isEmpty()) {
            etAddress.setError("Address is required");
            etAddress.requestFocus();
            return false;
        }
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