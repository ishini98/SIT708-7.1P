package com.example.lostfound;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.database.Cursor;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class List_Advert extends AppCompatActivity {

    private RecyclerView rv;
    private Item_Adapter adapter;
    private List<String[]> dataList;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display for immersive UI experience
        EdgeToEdge.enable(this);

        // Set the layout for this activity
        setContentView(R.layout.activity_list_advert);

        // Initialize DBHelper and database objects
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        // Initialize RecyclerView and its layout manager
        rv = findViewById(R.id.rv_list_advert);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // Initialize data list and adapter for RecyclerView
        dataList = new ArrayList<>();
        adapter = new Item_Adapter(this, dataList);

        // Call method to fetch and display data from the database
        getAllDataSortedByNameDesc();
    }

    // Method to retrieve data from the database and populate RecyclerView
    public void getAllDataSortedByNameDesc() {
        dataList.clear(); // Clear existing data list

        // Query the database to fetch data sorted by date in descending order
        Cursor cursor = database.query(
                "my_table",
                null,
                null,
                null,
                null,
                null,
                "date DESC"
        );

        // Check if cursor is not null and move to the first row
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Extract data from the cursor and add it to the data list
                @SuppressLint("Range") String id = String.valueOf(cursor.getLong(cursor.getColumnIndex("id")));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
                @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex("phone"));
                @SuppressLint("Range") String location = cursor.getString(cursor.getColumnIndex("location"));
                @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex("type"));
                dataList.add(new String[]{id, name, description, phone, location, date, type});
            } while (cursor.moveToNext()); // Move to the next row
            cursor.close(); // Close the cursor
        }

        // Set the adapter for the RecyclerView to display the data
        rv.setAdapter(adapter);
    }
}