package com.example.tappydefender;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.content.Intent;
import android.widget.Button;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set UI layout as the view
        setContentView(R.layout.activity_main);

        // Get a reference to the button in the layout
        final Button buttonPlay = (Button)findViewById(R.id.buttonPlay);

        // Listen for clicks
        buttonPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        // must be the Play button.
        // Create a new Intent object
        Intent i = new Intent(this, GameActivity.class);

        // Start the GameActivity class via the Intent
        startActivity(i);

        // Now shut this activity down
        finish();
    }
}