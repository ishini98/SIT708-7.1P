package com.example.lostfound;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Create_Advert extends AppCompatActivity {

    private EditText editName, editDesc, editPhoneNo, editDate, editLocation;
    private Button button;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private RadioButton lost, found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display for immersive UI experience
        EdgeToEdge.enable(this);

        // Set the layout for this activity
        setContentView(R.layout.activity_create_advert);

        // Initialize DBHelper and database objects
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        // Initialize EditText fields, Button, and RadioButtons
        editName = findViewById(R.id.editTextName);
        editDesc = findViewById(R.id.editTextDescription);
        editPhoneNo = findViewById(R.id.editTextPhone);
        editDate = findViewById(R.id.editTextDate);
        editLocation = findViewById(R.id.editTextLocation);
        button = findViewById(R.id.buttonSave);
        lost = findViewById(R.id.radioButtonLost);
        found = findViewById(R.id.radioButtonFound);

        // Set click listener for the "Save" button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToDB(); // Call method to add data to the database
            }
        });
    }

    // Method to add data to the database
    private void addDataToDB() {
        // Get input values from EditText fields
        String name = editName.getText().toString();
        String description = editDesc.getText().toString();
        String phone = editPhoneNo.getText().toString();
        String date = editDate.getText().toString();
        String location = editLocation.getText().toString();
        String type = "";

        // Determine the type (lost or found) based on RadioButton selection
        if (lost.isChecked()) type = "lost";
        else if (found.isChecked()) type = "found";

        // Validate input fields
        if (name.isEmpty() || description.isEmpty() || phone.isEmpty() || date.isEmpty() || location.isEmpty() || type.isEmpty()) {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert data into the database using ContentValues
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("phone", phone);
        values.put("date", date);
        values.put("type", type);
        values.put("location", location);
        db.insert("my_table", null, values); // Insert data into the "my_table" table
        Toast.makeText(this, "Advert created successfully", Toast.LENGTH_SHORT).show();
        finish(); // Finish the activity after adding data to the database
    }
}