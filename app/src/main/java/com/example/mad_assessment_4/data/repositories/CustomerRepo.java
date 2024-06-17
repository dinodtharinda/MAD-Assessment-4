package com.example.mad_assessment_4.data.repositories;

import android.content.Context;

import com.example.mad_assessment_4.data.DB.DBHelper;
import com.example.mad_assessment_4.data.models.Customer;

public class CustomerRepo {
    private DBHelper dbHelper;

    public CustomerRepo(Context context){
        dbHelper = new DBHelper(context);
    }


    public Boolean registerCustomer(Customer customer){
      return   dbHelper.insertCustomer(customer);
    }

    public Customer loginCustomer(String email){

       return  dbHelper.getCustomerByEmail(email);

    }


}
