package com.example.mad_assessment_4.data.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mad_assessment_4.data.models.Customer;
import com.example.mad_assessment_4.data.models.Pizza;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pizza_shop.db";
    private static final int DATABASE_VERSION = 2;


    //Customer Variables database
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


    // Pizza Table Variables
    public static final String TABLE_PIZZA = "pizza";
    public static final String COLUMN_PIZZA_ID = "id";
    public static final String COLUMN_PIZZA_NAME = "name";
    public static final String COLUMN_PIZZA_DESCRIPTION = "description";
    public static final String COLUMN_PIZZA_IMAGE_PATH = "imagePath";
    public static final String COLUMN_PIZZA_SIZE = "size";
    public static final String COLUMN_PIZZA_TOPPING = "topping";
    public static final String COLUMN_PIZZA_PRICE = "price";

    private static final String TABLE_PIZZA_CREATE =
            "CREATE TABLE " + TABLE_PIZZA + " (" +
                    COLUMN_PIZZA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PIZZA_NAME + " TEXT, " +
                    COLUMN_PIZZA_DESCRIPTION + " TEXT, " +
                    COLUMN_PIZZA_IMAGE_PATH + " TEXT, " +
                    COLUMN_PIZZA_SIZE + " TEXT, " +
                    COLUMN_PIZZA_TOPPING + " INTEGER, " +
                    COLUMN_PIZZA_PRICE + " REAL" +
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
        db.execSQL(TABLE_PIZZA_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PIZZA);
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

    public boolean insertPizza(Pizza pizza) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PIZZA_NAME, pizza.getName());
        contentValues.put(COLUMN_PIZZA_DESCRIPTION, pizza.getDescription());
        contentValues.put(COLUMN_PIZZA_IMAGE_PATH, pizza.getImagePath());
        contentValues.put(COLUMN_PIZZA_SIZE, pizza.getSize());
        contentValues.put(COLUMN_PIZZA_TOPPING, pizza.isTopping() ? 1 : 0);
        contentValues.put(COLUMN_PIZZA_PRICE, pizza.getPrice());

        long result = DB.insert(TABLE_PIZZA, null, contentValues);
        return result != -1;
    }

    public boolean updatePizza(Pizza pizza) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PIZZA_NAME, pizza.getName());
        contentValues.put(COLUMN_PIZZA_DESCRIPTION, pizza.getDescription());
        contentValues.put(COLUMN_PIZZA_IMAGE_PATH, pizza.getImagePath());
        contentValues.put(COLUMN_PIZZA_SIZE, pizza.getSize());
        contentValues.put(COLUMN_PIZZA_TOPPING, pizza.isTopping() ? 1 : 0);
        contentValues.put(COLUMN_PIZZA_PRICE, pizza.getPrice());

        int result = DB.update(TABLE_PIZZA, contentValues, COLUMN_PIZZA_ID + " = ?", new String[]{String.valueOf(pizza.getId())});
        return result > 0;
    }

    public Pizza getPizzaById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                COLUMN_PIZZA_ID,
                COLUMN_PIZZA_NAME,
                COLUMN_PIZZA_DESCRIPTION,
                COLUMN_PIZZA_IMAGE_PATH,
                COLUMN_PIZZA_SIZE,
                COLUMN_PIZZA_TOPPING,
                COLUMN_PIZZA_PRICE
        };
        String selection = COLUMN_PIZZA_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(
                TABLE_PIZZA,   // The table to query
                columns,          // The array of columns to return (pass null to get all)
                selection,        // The columns for the WHERE clause
                selectionArgs,    // The values for the WHERE clause
                null,             // Group the rows
                null,             // Filter by row groups
                null              // The sort order
        );

        if (cursor != null && cursor.moveToFirst()) {
            // Extract data from the cursor and create a Pizza object
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PIZZA_NAME));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PIZZA_DESCRIPTION));
            String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PIZZA_IMAGE_PATH));
            String size = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PIZZA_SIZE));
            boolean topping = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PIZZA_TOPPING)) == 1;
            double price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PIZZA_PRICE));

            cursor.close();
            return new Pizza(id, name, description, imagePath, size, topping, price);
        } else {
            if (cursor != null) {
                cursor.close();
            }
            return null;
        }
    }

    public List<Pizza> getAllPizzas() {
        List<Pizza> pizzas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PIZZA, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PIZZA_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PIZZA_NAME));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PIZZA_DESCRIPTION));
                String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PIZZA_IMAGE_PATH));
                String size = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PIZZA_SIZE));
                boolean topping = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PIZZA_TOPPING)) == 1;
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PIZZA_PRICE));

                pizzas.add(new Pizza(id, name, description, imagePath, size, topping, price));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return pizzas;
    }

}
