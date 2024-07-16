package com.example.mad_assessment_4.utils;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissions {

    public static final int REQUEST_CAMERA_PERMISSION = 100;
    public static final int REQUEST_STORAGE_PERMISSION = 101;
    public static final int REQUEST_MULTIPLE_PERMISSIONS = 102;

    // Method to check camera permission
    public static boolean checkCameraPermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    // Method to request camera permission
    public static void requestCameraPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
    }

    // Method to check storage permission
    public static boolean checkStoragePermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    // Method to request storage permission
    public static void requestStoragePermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
    }

    // Method to check both camera and storage permissions
    public static boolean checkAllPermissions(Context context) {
        return checkCameraPermission(context) && checkStoragePermission(context);
    }

    // Method to request both camera and storage permissions
    public static void requestAllPermissions(Activity activity) {
        List<String> permissionList = new ArrayList<>();
        if (!checkCameraPermission(activity)) {
            permissionList.add(Manifest.permission.CAMERA);
        }
        if (!checkStoragePermission(activity)) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(activity, permissionList.toArray(new String[0]), REQUEST_MULTIPLE_PERMISSIONS);
        }
    }
}
