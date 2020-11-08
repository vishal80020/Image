package com.example.android.image;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the View that shows the numbers category
        TextView numbers = (TextView) findViewById(R.id.vertical);
        // Set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new intent to open the {@Link NumbersActivity}
                Intent intentNumbers = new Intent(MainActivity.this, VerticalActivity.class);
                //Start the new activity
                startActivity(intentNumbers);
            }

        });

        // Find the View that shows the family members category
        TextView family = (TextView) findViewById(R.id.horizontal);
        // Set a click listener on that View
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new intent to open the {@Link FamilyActivity}
                Intent intentFamily = new Intent(MainActivity.this, HorizontalActivity.class);
                //Start the new activity
                startActivity(intentFamily);
            }

        });



    }
}