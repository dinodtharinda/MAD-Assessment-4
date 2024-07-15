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

import com.example.mad_assessment_4.R;
import com.example.mad_assessment_4.utils.Constants;
import com.example.mad_assessment_4.utils.Helper;
import com.example.mad_assessment_4.views.DashboardActivity;
import com.example.mad_assessment_4.views.PizzaHomeActivity;

public class HomeFragment extends Fragment {

    private TextView tvHome;
    private Button btnLogin;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((DashboardActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("Home");

        ((DashboardActivity) getActivity()).getSupportActionBar().setTitle("Home");
        setHasOptionsMenu(true);

        Intent pizzaHome = new Intent(getActivity(), PizzaHomeActivity.class);


        // Handle menu item clicks
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_settings) {
                    // Handle settings button click
                    return true;
                }else if(item.getItemId() == R.id.pizza_button){
                    startActivity(pizzaHome);
                }
                return false;
            }
        });

        tvHome = view.findViewById(R.id.tvHome);

        int userId = Helper.getIntFromSharedPref(getActivity(), Constants.USER_ID);

        Log.e("User id", Integer.toString(userId));

        if (userId > 0) {
            tvHome.setText("Logged in user");
        } else {
            tvHome.setText("Guest user");
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
