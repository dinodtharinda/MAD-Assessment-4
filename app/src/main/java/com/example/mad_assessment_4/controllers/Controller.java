package com.example.mad_assessment_4.controllers;

import android.content.Context;

import com.example.mad_assessment_4.data.repositories.CustomerRepo;
import com.example.mad_assessment_4.data.repositories.Repository;

public class Controller {

    final Repository repository;
    Context context;

    public Controller(Context context){
        repository = new Repository(context);
        this.context = context;
    }
}
