package com.example.mad_assessment_4.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mad_assessment_4.R;
import com.example.mad_assessment_4.controllers.Controller;
import com.example.mad_assessment_4.data.models.Pizza;
import com.example.mad_assessment_4.utils.Helper;

public class PizzaDetailsActivity extends AppCompatActivity {

    private ImageView imageViewPizza;
    private TextView textViewPizzaName;
    private TextView textViewPizzaDescription;
    private TextView textViewPizzaSize;
    private TextView textViewPizzaPrice;
    private TextView textViewQuantity;
    private Button buttonAddToCart;
    private TextView buttonIncreaseQuantity;
    private TextView buttonDecreaseQuantity;

    private ImageView btnBack;

    private int quantity = 1;

    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);


        // Enable the Up button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // Initialize UI elements
        imageViewPizza = findViewById(R.id.imageViewPizza);
        textViewPizzaName = findViewById(R.id.textViewPizzaName);
        textViewPizzaDescription = findViewById(R.id.textViewPizzaDescription);
        textViewPizzaSize = findViewById(R.id.textViewPizzaSize);
        textViewPizzaPrice = findViewById(R.id.textViewPizzaPrice);
        textViewQuantity = findViewById(R.id.textViewQuantity);
        buttonAddToCart = findViewById(R.id.buttonAddToCart);
        buttonIncreaseQuantity = findViewById(R.id.buttonIncreaseQuantity);
        buttonDecreaseQuantity = findViewById(R.id.buttonDecreaseQuantity);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        controller = new Controller(this);

        // Set pizza details (dummy data for demonstration)
        textViewPizzaName.setText("Pepperoni Pizza");
        textViewPizzaDescription.setText("Delicious pepperoni pizza with cheese and tomato sauce.");
        textViewPizzaSize.setText("Size: Large");
        textViewPizzaPrice.setText("Price: $12.99");

        Intent intent = getIntent();
        int pizzaId = intent.getIntExtra("pizza_id", -1);

        loadData(pizzaId);
        // Set image from drawable
//        imageViewPizza.setImageResource(R.drawable.image_placeholder);

        // Set button click listeners
        buttonIncreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseQuantity();
            }
        });


        buttonDecreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseQuantity();
            }
        });

        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle add to cart action
                // Example: add the pizza to the cart with the specified quantity
            }
        });
    }

    private void loadData(int id){
       Pizza pizza = controller.getPizzaDetailsById(id);

        textViewPizzaName.setText(pizza.getName());
        textViewPizzaDescription.setText(pizza.getDescription());
        textViewPizzaSize.setText("Size: "+pizza.getSize());
        textViewPizzaPrice.setText("Price: Rs"+pizza.getPrice());
        Bitmap pizzaImage = Helper.getImageFromExternalStorage(pizza.getName());
        imageViewPizza.setImageBitmap(pizzaImage);
    }

    private void increaseQuantity() {
        quantity++;
        textViewQuantity.setText(String.valueOf(quantity));
    }

    private void decreaseQuantity() {
        if (quantity > 1) {
            quantity--;
            textViewQuantity.setText(String.valueOf(quantity));
        }
    }
}
