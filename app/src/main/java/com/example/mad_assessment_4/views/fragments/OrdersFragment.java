package com.example.mad_assessment_4.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.mad_assessment_4.R;
import com.example.mad_assessment_4.utils.Constants;
import com.example.mad_assessment_4.utils.Helper;
import com.example.mad_assessment_4.views.LoginActivity;

public class OrdersFragment extends Fragment {
    ConstraintLayout clScreen;

    Button btnLogin;

    RelativeLayout rlLoginMsg;
    int userId ;

    Intent loginScreen;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        userId =  Helper.getIntFromSharedPref(getActivity(), Constants.USER_ID);

        clScreen = view.findViewById(R.id.clScreen);
        rlLoginMsg = view.findViewById(R.id.rlLoginMsg);
        btnLogin = view.findViewById(R.id.btnLogin);

        loginScreen = new Intent(getActivity(), LoginActivity.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(loginScreen);
                getActivity().finish();
            }
        });


        if(userId>0){
            clScreen.setVisibility(View.VISIBLE);

            rlLoginMsg.setVisibility(View.INVISIBLE);
        }

        else{
            clScreen.setVisibility(View.INVISIBLE);
            rlLoginMsg.setVisibility(View.VISIBLE);
        }

        return view;
    }
}