package com.example.mad_assessment_4.controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.mad_assessment_4.data.models.Customer;
import com.example.mad_assessment_4.data.repositories.CustomerRepo;

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

    public Boolean loginCustomer(String email, String password){
       Customer customer = customerRepo.loginCustomer(email);
       if(customer != null){
           Log.e("customer",customer.getPassword());
           return Objects.equals(customer.getPassword(), password);
       }else{
           Toast.makeText(context,"User Not found",Toast.LENGTH_LONG).show();
           return false;
       }

    }
}
