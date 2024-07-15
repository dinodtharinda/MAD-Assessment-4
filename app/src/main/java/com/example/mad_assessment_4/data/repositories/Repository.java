package com.example.mad_assessment_4.data.repositories;

import android.content.Context;

import com.example.mad_assessment_4.data.DB.DBHelper;
import com.example.mad_assessment_4.data.models.Pizza;

public class Repository {
    private DBHelper dbHelper;
    public Repository(Context context){
        dbHelper = new DBHelper(context);
    }


    public Boolean insertPizza(Pizza pizza){
        return dbHelper.insertPizza(pizza);
    }
}
