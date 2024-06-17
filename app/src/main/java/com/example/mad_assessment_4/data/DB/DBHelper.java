package com.example.mad_assessment_4.data.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mad_assessment_4.data.models.Customer;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pizza_shop.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CUSTOMER = "customer";
    public static final String COLUMN_CUSTOMER_ID = "id";
    public static final String COLUMN_CUSTOMER_NAME = "name";
    public static final String COLUMN_CUSTOMER_EMAIL = "email";
    public static final String COLUMN_CUSTOMER_PHONE = "phone";
    public static final String COLUMN_CUSTOMER_ADDRESS = "address";
    public static final String COLUMN_CUSTOMER_PASSWORD = "password";

    private static final String TABLE_CUSTOMER_CREATE =
            "CREATE TABLE " + TABLE_CUSTOMER + " (" +
                    COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CUSTOMER_NAME + " TEXT, " +
                    COLUMN_CUSTOMER_EMAIL + " TEXT," +
                    COLUMN_CUSTOMER_PASSWORD + " TEXT," +
                    COLUMN_CUSTOMER_PHONE + " TEXT," +
                    COLUMN_CUSTOMER_ADDRESS + " TEXT" +
                    ")";

    private final SQLiteDatabase DB = this.getWritableDatabase();
    private Context context;

    public DBHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CUSTOMER_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        onCreate(db);
    }


    public Boolean insertCustomer(Customer customer){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CUSTOMER_NAME,customer.getName());
        contentValues.put(COLUMN_CUSTOMER_EMAIL,customer.getEmail());
        contentValues.put(COLUMN_CUSTOMER_PHONE,customer.getPhone());
        contentValues.put(COLUMN_CUSTOMER_PASSWORD,customer.getPassword());
        contentValues.put(COLUMN_CUSTOMER_ADDRESS,customer.getAddress());

        long result = DB.insert(TABLE_CUSTOMER,null,contentValues);

        return  result != -1;

    }

    public Customer getCustomerByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                COLUMN_CUSTOMER_ID,
                COLUMN_CUSTOMER_NAME,
                COLUMN_CUSTOMER_EMAIL,
                COLUMN_CUSTOMER_PHONE,
                COLUMN_CUSTOMER_PASSWORD,
                COLUMN_CUSTOMER_ADDRESS
        };
        String selection = COLUMN_CUSTOMER_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(
                TABLE_CUSTOMER,   // The table to query
                columns,          // The array of columns to return (pass null to get all)
                selection,        // The columns for the WHERE clause
                selectionArgs,    // The values for the WHERE clause
                null,             // Group the rows
                null,             // Filter by row groups
                null              // The sort order
        );

        if (cursor != null && cursor.moveToFirst()) {
            // Extract data from the cursor and create a Customer object
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_NAME));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_PHONE));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_PASSWORD));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CUSTOMER_ADDRESS));

            cursor.close();
            return new Customer(id, name, email, password, phone, address);
        } else {
            if (cursor != null) {
                cursor.close();
            }

            return null;
        }


    }



}
