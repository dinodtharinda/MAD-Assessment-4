package com.example.mad_assessment_4.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mad_assessment_4.R;
import com.example.mad_assessment_4.adapters.CartItemAdapter;
import com.example.mad_assessment_4.data.models.CartItem;
import com.example.mad_assessment_4.data.models.Order;
import com.example.mad_assessment_4.data.models.Pizza;
import com.example.mad_assessment_4.utils.CartManager;
import com.example.mad_assessment_4.utils.Constants;
import com.example.mad_assessment_4.utils.Helper;
import com.example.mad_assessment_4.utils.OrderManager;
import com.example.mad_assessment_4.views.DashboardActivity;
import com.example.mad_assessment_4.views.LoginActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CartFragment extends Fragment {
    ConstraintLayout clScreen;

    RelativeLayout rlLoginMsg;

    Button btnLogin;
    int userId ;

    RecyclerView recyclerView;

    CartItemAdapter cartItemAdapter;
    List<CartItem> cartItemList;
    Intent loginScreen;

    Button btnPlaceOrder;

    TextView tvTotalPrice;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        clScreen = view.findViewById(R.id.clScreen);
        rlLoginMsg = view.findViewById(R.id.rlLoginMsg);
        btnLogin = view.findViewById(R.id.btnLogin);
        loginScreen = new Intent(getActivity(), LoginActivity.class);
        recyclerView = view.findViewById(R.id.recyclerViewCart);
        btnPlaceOrder = view.findViewById(R.id.buttonPlaceOrder);
        userId =  Helper.getIntFromSharedPref(getActivity(), Constants.USER_ID);
        tvTotalPrice = view.findViewById(R.id.tvTotalPrice);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((DashboardActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("Cart");
        toolbar.setTitleTextColor(ContextCompat.getColor(getActivity(), R.color.white));
        ((DashboardActivity) getActivity()).getSupportActionBar().setTitle("Cart");
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               placeOrder();
            }
        });

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

        recyclerView = view.findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Assuming cartItemList is populated with data
        cartItemList = CartManager.getInstance().getCartItems(); // Method to create dummy data

        cartItemAdapter = new CartItemAdapter(cartItemList,this::updateTotalPrice);
        recyclerView.setAdapter(cartItemAdapter);

        tvTotalPrice.setText("Total Amount Rs "+String.valueOf(CartManager.getInstance().getTotalPrice()));

        return view;
    }



    private void placeOrder() {
         // Replace with the actual user ID
        List<CartItem> cartItems = CartManager.getInstance().getCartItems();
        double totalPrice = CartManager.getInstance().getTotalPrice();

        OrderManager.getInstance().placeOrder(userId,cartItems,totalPrice);

        // Clear the cart
        CartManager.getInstance().clearCart();

        // Update the UI
        updateTotalPrice();
        cartItemAdapter.notifyDataSetChanged();
        // Notify the RecyclerView adapter to update the UI
        // recyclerViewCart.getAdapter().notifyDataSetChanged();

        // Show a confirmation message or navigate to another screen
        // ...
    }

    private void updateTotalPrice() {
        double totalPrice = CartManager.getInstance().getTotalPrice();
        tvTotalPrice.setText("Total Price: Rs" + totalPrice);
    }

    private List<CartItem> createDummyCartItems() {
        List<CartItem> dummyList = new ArrayList<>();

        // Add dummy CartItems to the list
        // Replace this with your actual data logic
        for (int i = 0; i < 100000; i++) {
            Pizza pizza = new Pizza(
                    1,
                    "Margherita",
                    "Classic Italian pizza topped with tomato sauce, mozzarella, and basil leaves.",
                    "https://example.com/images/pizza1.jpg",
                    "Medium",
                    true,
                    12.99
            );
            CartItem cartItem = new CartItem(pizza, i + 1); // Assuming quantity is i + 1
            dummyList.add(cartItem);
        }

        return dummyList;
    }
}