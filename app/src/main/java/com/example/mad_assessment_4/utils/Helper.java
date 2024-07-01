package com.example.mad_assessment_4.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Helper {

    public static void saveStringToSharedPref(Context context, String key, String value) {
        // Get SharedPreferences instance
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Get SharedPreferences editor
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Put the string value in SharedPreferences
        editor.putString(key, value);

        // Commit the changes
        editor.apply(); // or editor.commit();
    }

    public static void saveIntToSharedPref(Context context, String key, int value) {
        // Get SharedPreferences instance
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Get SharedPreferences editor
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Put the string value in SharedPreferences
        editor.putInt(key, value);

        // Commit the changes
        editor.apply(); // or editor.commit();
    }

    public static String getStringFromSharedPref(Context context, String key) {
        // Get SharedPreferences instance
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Retrieve the string value
        return sharedPreferences.getString(key, "default_value");
    }

    public static int getIntFromSharedPref(Context context, String key) {
        // Get SharedPreferences instance
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Retrieve the string value
        return sharedPreferences.getInt(key, 0);
    }


    public static void clearSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply(); // or editor.commit(); - apply() is asynchronous, while commit() is synchronous
    }

    public static void saveImageToExternalStorage(Bitmap bitmap, Context context, String imgName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return; // Exit method if permission is not granted
            }
        }

        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/MyAppImages");
        if (!myDir.exists()) {
            if (!myDir.mkdirs()) {
                Log.e("saveImageToExternalStorage", "Failed to create directory: " + myDir.getAbsolutePath());
                Toast.makeText(context, "Failed to create directory", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        String fileName = imgName + ".jpg"; // Append .jpg to the image name
        File file = new File(myDir, fileName);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(context, "Image saved to " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("saveImageToExternalStorage", e.getMessage());
            e.printStackTrace();
            Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show();
        }
    }


}
