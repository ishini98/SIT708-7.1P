package com.example.lostfound;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class View_Item extends AppCompatActivity {

    // Declare variables for TextViews and database-related objects
    TextView name, description, phone, location, date, lostFound;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display
        setContentView(R.layout.activity_view_item); // Set the layout for this activity
        dbHelper = new DBHelper(this); // Create a new instance of DBHelper
        database = dbHelper.getWritableDatabase(); // Get a writable database instance

        // Initialize TextViews by finding them in the layout using their IDs
        name = findViewById(R.id.textViewName);
        description = findViewById(R.id.textViewDescription);
        phone = findViewById(R.id.textViewPhone);
        location = findViewById(R.id.textViewLocation);
        date = findViewById(R.id.textViewDate);
        lostFound = findViewById(R.id.textViewLostFound);

        // Retrieve data passed from the previous activity using an Intent
        Intent i = getIntent();
        String[] arr = i.getStringArrayExtra("data");

        // Set text for TextViews using data from the Intent's extras
        name.setText("Name: " + arr[1]);
        description.setText("Description: " + arr[2]);
        phone.setText("Phone: " + arr[3]);
        location.setText("Location: " + arr[4]);
        date.setText("Date: " + arr[5]);
        lostFound.setText("Type: " + arr[6].toUpperCase());

        // Set a click listener for the "Remove" button
        findViewById(R.id.btn_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete the item from the database based on its ID
                database.delete("my_table", "id = ?", new String[]{String.valueOf(arr[0])});
                // Show a toast message indicating successful deletion
                Toast.makeText(View_Item.this, "Item Deleted Successfully", Toast.LENGTH_SHORT).show();
                // Finish this activity
                finish();
            }
        });
    }
}