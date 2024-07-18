package com.example.mad_assessment_4.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mad_assessment_4.R;
import com.example.mad_assessment_4.controllers.Controller;
import com.example.mad_assessment_4.data.models.Pizza;
import com.example.mad_assessment_4.utils.Helper;
import com.example.mad_assessment_4.utils.Permissions;

import java.io.IOException;
import java.util.UUID;

public class PizzaHomeActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 22;
    private EditText etName, etDescription, etPrice;
    private Spinner spinnerSize;
    private ImageView ivPizzaImage;

    private Button btnSave;
    private Uri imageUri;

    Controller controller;

    Bitmap photo;

    int pizzaId;
    private ImageView btnBack;
    Intent adminHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_home);
        controller = new Controller(this);
        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etPrice = findViewById(R.id.etPrice);
        spinnerSize = findViewById(R.id.spinnerSize);
        ivPizzaImage = findViewById(R.id.ivPizzaImage);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivPizzaImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Permissions.checkAllPermissions(PizzaHomeActivity.this)) {
                    openGallery();
                } else {
                    Permissions.requestAllPermissions(PizzaHomeActivity.this);
                }
            }
        });
        btnSave.setOnClickListener(v->savePizza());
        adminHome = new Intent(this, AdminHomeActivity.class);



    }

    private void savePizza() {
        // Retrieve data from EditText fields and create a Pizza object
        String name = etName.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String priceText = etPrice.getText().toString().trim();

        // Validate price input
        double price = 0;
        if (!priceText.isEmpty()) {
            try {
                price = Double.parseDouble(priceText);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid price format", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            Toast.makeText(this, "Price cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate Pizza object fields
        if (name.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Name and description cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate photo bitmap
        if (photo == null) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }


        if (Permissions.checkAllPermissions(this)) {
            Helper.saveImageToExternalStorage(photo, this, name);
        } else {
            Permissions.requestAllPermissions(this);
        }



        // Optionally, save Pizza object to database or perform other operations
        Pizza pizza = new Pizza(0, name, description, name, spinnerSize.getSelectedItem().toString(), false, price);

      boolean result =  controller.insertPizza(pizza);
      if(result ){
        startActivity(adminHome);
        finish();
      }
    }



    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Handle the result here
            if (data.getData() != null) {
                // Get the URI of the selected image
                Uri selectedImageUri = data.getData();

                // Now you can use this selectedImageUri as needed, such as displaying it in an ImageView or saving it.
                try {
                     photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    ivPizzaImage.setImageBitmap(photo);
                    // Do something with the bitmap (e.g., display in ImageView, save to storage)
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Handle other result codes or cancelation here
        }
    }


    private Pizza createPizzaFromInput() {
        String name = etName.getText().toString();
        String description = etDescription.getText().toString();
        String priceString = etPrice.getText().toString();
        String size = spinnerSize.getSelectedItem().toString();
        double price = Double.parseDouble(priceString);

        // Assuming imagePath is derived from imageUri
        String imagePath = imageUri != null ? imageUri.toString() : "";

        return new Pizza(0, name, description, imagePath, size, false,price);
    }
}
