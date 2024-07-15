package com.example.mad_assessment_4.controllers;

import android.content.Context;

import com.example.mad_assessment_4.data.models.Pizza;
import com.example.mad_assessment_4.data.repositories.CustomerRepo;
import com.example.mad_assessment_4.data.repositories.Repository;

import java.util.List;

public class Controller {

    final Repository repository;
    Context context;

    public Controller(Context context){
        repository = new Repository(context);
        this.context = context;
    }

    public boolean insertPizza(Pizza pizza){
      return   repository.insertPizza(pizza);
    }


    public List<Pizza> getAllPizza(){
        return repository.getAllPizzas();
    }
}
