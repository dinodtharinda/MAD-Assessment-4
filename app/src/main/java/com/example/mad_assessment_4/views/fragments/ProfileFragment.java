package com.example.mad_assessment_4.views.fragments;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mad_assessment_4.R;
import com.example.mad_assessment_4.controllers.CustomerController;
import com.example.mad_assessment_4.utils.Constants;
import com.example.mad_assessment_4.utils.Helper;
import com.example.mad_assessment_4.utils.Permissions;
import com.example.mad_assessment_4.views.LoginActivity;

import java.io.File;

public class ProfileFragment extends Fragment {

    ImageView ivProfile;
    private Button btnLogout;
    private static final int REQUEST_CODE = 22;

    int userId ;
    CustomerController customerController;

    ConstraintLayout clScreen;

    RelativeLayout rlLoginMsg;

    Intent loginScreen;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        customerController = new CustomerController(getActivity());


        ivProfile = view.findViewById(R.id.ivProfile);
        btnLogout = view.findViewById(R.id.btnLogout);
        clScreen = view.findViewById(R.id.clScreen);
        rlLoginMsg = view.findViewById(R.id.rlLoginMsg);



        loginScreen = new Intent(getActivity(), LoginActivity.class);



        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerController.logout(getActivity());
                startActivity(loginScreen);
                getActivity().finish();
            }
        });
        userId =  Helper.getIntFromSharedPref(getActivity(),Constants.USER_ID);

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userId>0){
                    if (Permissions.checkCameraPermission(getActivity())) {
                        Intent camIntent = new
                                Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(camIntent, REQUEST_CODE);
                    } else {
                        Permissions.requestCameraPermission(getActivity());
                    }
                }

            }
        });

        if(userId>0){
            clScreen.setVisibility(View.VISIBLE);
            loadProfileImage(Integer.toString(userId));
            rlLoginMsg.setVisibility(View.INVISIBLE);
        }

        else{
            clScreen.setVisibility(View.INVISIBLE);
            rlLoginMsg.setVisibility(View.VISIBLE);
        }
        return view;
    }
    private void loadProfileImage(String imageName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Use MediaStore API for Android Q and above
            try {
                Uri imageUri = getImageUriFromMediaStore( imageName + ".jpg"); // Append .jpg to the image name
                if (imageUri != null) {
                    Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                    ivProfile.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(getActivity(), "Image not found", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Use traditional file API for Android versions below Q
            String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
            File file = new File(root + "/MyAppImages/" + imageName + ".jpg"); // Append .jpg to the image name
            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                ivProfile.setImageBitmap(bitmap);
            } else {
                Toast.makeText(getActivity(), "Image not found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Uri getImageUriFromMediaStore( String imageName) {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media._ID};
        String selection = MediaStore.Images.Media.DISPLAY_NAME + "=?";
        String[] selectionArgs = new String[]{imageName};

        Cursor cursor = getActivity().getContentResolver().query(uri, projection, selection, selectionArgs, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
                cursor.close();
                return imageUri;
            }
            cursor.close();
        }
        return null;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ivProfile.setImageBitmap(photo);
            Helper.saveImageToExternalStorage(photo, getActivity(), Integer.toString(userId));
        } else {
            Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
        }
    }


}