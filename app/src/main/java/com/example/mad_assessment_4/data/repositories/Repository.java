package com.example.mad_assessment_4.data.repositories;

import android.content.Context;

import com.example.mad_assessment_4.data.DB.DBHelper;
import com.example.mad_assessment_4.data.models.Pizza;

import java.util.List;

public class Repository {
    private DBHelper dbHelper;
    public Repository(Context context){
        dbHelper = new DBHelper(context);
    }


    public Boolean insertPizza(Pizza pizza){
        return dbHelper.insertPizza(pizza);
    }

    public List<Pizza> getAllPizzas(){
        return dbHelper.getAllPizzas();
    }

    public Pizza getPizzaDetailById(int id){
        return dbHelper.getPizzaById(id);
    }
}
