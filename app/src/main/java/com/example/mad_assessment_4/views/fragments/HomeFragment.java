package com.example.mad_assessment_4.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mad_assessment_4.R;
import com.example.mad_assessment_4.utils.Constants;
import com.example.mad_assessment_4.utils.Helper;
import com.example.mad_assessment_4.views.DashboardActivity;

public class HomeFragment extends Fragment {

    TextView tvHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvHome = view.findViewById(R.id.tvHome);
        int userId =Helper.getIntFromSharedPref(getActivity(),Constants.USER_ID);

        Log.e("User id",Integer.toString(userId));

        if(userId > 0){
            tvHome.setText("Login in user");
        }else{
            tvHome.setText("Guest in user");
        }


        return view;


    }
}