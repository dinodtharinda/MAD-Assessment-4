package com.example.mad_assessment_4.utils;

import android.content.Context;
import android.content.SharedPreferences;

 public class Helper {

    public void saveStringToSharedPref(Context context, String key, String value) {
        // Get SharedPreferences instance
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Get SharedPreferences editor
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Put the string value in SharedPreferences
        editor.putString(key, value);

        // Commit the changes
        editor.apply(); // or editor.commit();
    }

     public void saveIntToSharedPref(Context context, String key, int value) {
         // Get SharedPreferences instance
         SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

         // Get SharedPreferences editor
         SharedPreferences.Editor editor = sharedPreferences.edit();

         // Put the string value in SharedPreferences
         editor.putInt(key, value);

         // Commit the changes
         editor.apply(); // or editor.commit();
     }

     public String getStringFromSharedPref(Context context, String key) {
         // Get SharedPreferences instance
         SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

         // Retrieve the string value
         return sharedPreferences.getString(key, "default_value");
     }

     public int getIntFromSharedPref(Context context, String key) {
         // Get SharedPreferences instance
         SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

         // Retrieve the string value
         return sharedPreferences.getInt(key, 0);
     }


     public void clearSharedPreferences(Context context) {
         SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
         SharedPreferences.Editor editor = sharedPreferences.edit();
         editor.clear();
         editor.apply(); // or editor.commit(); - apply() is asynchronous, while commit() is synchronous
     }
}
