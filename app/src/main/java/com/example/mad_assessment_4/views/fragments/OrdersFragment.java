// OrdersFragment.java
package com.example.mad_assessment_4.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.mad_assessment_4.R;
import com.example.mad_assessment_4.adapters.OrderAdapter;
import com.example.mad_assessment_4.data.models.Order;
import com.example.mad_assessment_4.utils.Constants;
import com.example.mad_assessment_4.utils.Helper;
import com.example.mad_assessment_4.utils.OrderManager;
import com.example.mad_assessment_4.views.LoginActivity;

import java.util.List;

public class OrdersFragment extends Fragment {
    ConstraintLayout clScreen;
    Button btnLogin;
    RelativeLayout rlLoginMsg;
    int userId;
    Intent loginScreen;
    RecyclerView recyclerViewOrders;
    OrderAdapter orderAdapter;
    List<Order> orderList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        userId = Helper.getIntFromSharedPref(getActivity(), Constants.USER_ID);

        clScreen = view.findViewById(R.id.clScreen);
        rlLoginMsg = view.findViewById(R.id.rlLoginMsg);
        btnLogin = view.findViewById(R.id.btnLogin);
        recyclerViewOrders = view.findViewById(R.id.rvOrders);

        loginScreen = new Intent(getActivity(), LoginActivity.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(loginScreen);
                getActivity().finish();
            }
        });

        if (userId > 0) {
            clScreen.setVisibility(View.VISIBLE);
            rlLoginMsg.setVisibility(View.INVISIBLE);
            setupRecyclerView();
        } else {
            clScreen.setVisibility(View.INVISIBLE);
            rlLoginMsg.setVisibility(View.VISIBLE);
        }

        return view;
    }

    private void setupRecyclerView() {
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(userId == 1000){
            orderList = OrderManager.getInstance().getOrders();
        }else{
            orderList = OrderManager.getInstance().getOrdersByUserId(userId);
        }

        Log.e("Oder id length",String.valueOf(orderList.size()));
        orderAdapter = new OrderAdapter(orderList);
        recyclerViewOrders.setAdapter(orderAdapter);
    }
}
