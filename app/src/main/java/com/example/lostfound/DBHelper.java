package com.example.lostfound;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Database name and version constants
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Constructor to initialize the database
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL query to create the "my_table" table
        String CREATE_TABLE_QUERY =
                "CREATE TABLE my_table (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT, " +
                        "phone TEXT, " +
                        "description TEXT, " +
                        "location TEXT, " +
                        "type TEXT, " +
                        "date TEXT)";
        // Execute the SQL query to create the table
        db.execSQL(CREATE_TABLE_QUERY);
    }

    // Method called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing table if it exists
        db.execSQL("DROP TABLE IF EXISTS my_table");
        // Call onCreate method to recreate the table with new structure
        onCreate(db);
    }
}
