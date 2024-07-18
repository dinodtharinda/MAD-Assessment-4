package com.example.mad_assessment_4.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_assessment_4.R;
import com.example.mad_assessment_4.adapters.MyGridAdapter;
import com.example.mad_assessment_4.controllers.Controller;
import com.example.mad_assessment_4.data.models.Pizza;
import com.example.mad_assessment_4.utils.Constants;
import com.example.mad_assessment_4.utils.Helper;
import com.example.mad_assessment_4.views.DashboardActivity;
import com.example.mad_assessment_4.views.PizzaHomeActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private TextView tvHome;
    private Button btnLogin;

    private RecyclerView recyclerView;
    private MyGridAdapter adapter;
    private List<Pizza> pizzaList = new ArrayList<>();; // Replace with your actual data list

    Controller controller;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        controller = new Controller(getActivity());
        loadPizzaData();
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((DashboardActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("Home");

        ((DashboardActivity) getActivity()).getSupportActionBar().setTitle("Home");





            recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2)); // 2 columns in grid
            adapter = new MyGridAdapter(getActivity(), pizzaList); // Initialize your custom adapter
            recyclerView.setAdapter(adapter);





        // Handle menu item clicks


//        tvHome = view.findViewById(R.id.tvHome);

        int userId = Helper.getIntFromSharedPref(getActivity(), Constants.USER_ID);

        Log.e("User id", Integer.toString(userId));

        if (userId > 0) {
//            tvHome.setText("Logged in user");
        } else {
//            tvHome.setText("Guest user");
        }

        return view;
    }

    void loadPizzaData(){
        pizzaList = controller.getAllPizza();
    }


}
