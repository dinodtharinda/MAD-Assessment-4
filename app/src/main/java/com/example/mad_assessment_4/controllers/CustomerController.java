package com.example.mad_assessment_4.controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.mad_assessment_4.data.models.Customer;
import com.example.mad_assessment_4.data.repositories.CustomerRepo;
import com.example.mad_assessment_4.utils.Constants;
import com.example.mad_assessment_4.utils.Helper;

import java.util.Objects;

public class CustomerController {
    final CustomerRepo customerRepo;
    Context context;

   public CustomerController(Context context){
        customerRepo = new CustomerRepo(context);
        this.context = context;
    }

    public Boolean registerCustomer(Customer customer){
        return customerRepo.registerCustomer(customer);
    }

    public boolean loginCustomer(String email, String password, Context context) {
        // Attempt to retrieve customer information based on email
        Customer customer = customerRepo.loginCustomer(email);

        if (customer != null) {
            Log.e("Customer", "Password: " + customer.getPassword());

            // Check if the provided password matches the customer's stored password
            if (Objects.equals(customer.getPassword(), password)) {
                // Save the customer's ID to SharedPreferences upon successful login
               Helper.saveIntToSharedPref(context, Constants.USER_ID, customer.getUserId());
                return true; // Return true indicating successful login
            } else {
                // Password does not match
                Toast.makeText(context, "Incorrect password", Toast.LENGTH_LONG).show();
                return false; // Return false indicating failed login
            }
        } else {
            // Customer with the given email not found
            Toast.makeText(context, "User not found", Toast.LENGTH_LONG).show();
            return false; // Return false indicating failed login
        }
    }

    public void logout(Context context){
       Helper.clearSharedPreferences(context);
    }


}
