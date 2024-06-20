package com.example.mad_assessment_4.views.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mad_assessment_4.R;
import com.example.mad_assessment_4.utils.Constants;
import com.example.mad_assessment_4.utils.Helper;
import com.example.mad_assessment_4.utils.Permissions;

import java.io.File;

public class ProfileFragment extends Fragment {

    ImageView ivProfile;
    private static final int REQUEST_CODE = 22;

    int userId ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        ivProfile = view.findViewById(R.id.ivProfile);
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
            loadProfileImage(Integer.toString(userId));
        }
        return view;
    }
    private void loadProfileImage(String imageName) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File file = new File(root + "/MyAppImages/"+imageName);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            ivProfile.setImageBitmap(bitmap);
        }
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