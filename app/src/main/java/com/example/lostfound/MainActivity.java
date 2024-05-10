package com.example.lostfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);

        // Set the layout for this activity
        setContentView(R.layout.activity_main);

        // Find the "New Ad" button and set its click listener
        Button newAdButton = findViewById(R.id.newAd);
        newAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Create_Advert activity
                Intent intent = new Intent(MainActivity.this, Create_Advert.class);
                startActivity(intent);
            }
        });

        // Find the "List Ads" button and set its click listener
        Button listAdsButton = findViewById(R.id.listAds);
        listAdsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the List_Advert activity
                Intent intent = new Intent(MainActivity.this, List_Advert.class);
                startActivity(intent);
            }
        });
    }
}
