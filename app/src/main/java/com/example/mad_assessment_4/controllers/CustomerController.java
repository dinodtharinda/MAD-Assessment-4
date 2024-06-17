package com.example.mad_assessment_4.controllers;

import android.content.Context;

import com.example.mad_assessment_4.data.models.Customer;
import com.example.mad_assessment_4.data.repositories.CustomerRepo;

public class CustomerController {
    final CustomerRepo customerRepo;

   public CustomerController(Context context){
        customerRepo = new CustomerRepo(context);
    }

    public Boolean registerCustomer(Customer customer){
        return customerRepo.registerCustomer(customer);
    }
}
