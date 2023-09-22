package com.sandris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.slider.Slider;


/*
Jackson Wiebe 3519635
Main activity launched from app
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Saved Settings
    private SharedPreferences sharedPreferences; //Gets our saved preferences
    public static int difficulty = 1; //What difficulty did the user select
    public static int highScore = 0; //Current high score from preferences
    public static boolean useMotion = false; //Does the user want motion controls?
    //Interactive objects
    private Switch motionSwitch; //Does the user want motion controls?
    private Switch musicSwitch; //Does the user want music
    private Slider diffSlider;  //Get the control input for difficultury slider

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the button in the layout
        Button playGame = (Button) findViewById(R.id.buttonPlay);
        motionSwitch = (Switch) findViewById(R.id.switchMotion);
        musicSwitch = (Switch) findViewById(R.id.switchMusic);
        // Listen for clicks
        playGame.setOnClickListener(this);
        motionSwitch.setOnClickListener(this);
        musicSwitch.setOnClickListener(this);
        //Load Preferences
        sharedPreferences = getSharedPreferences("gamePrefs", MODE_PRIVATE);
        difficulty = sharedPreferences.getInt("diff", 1);
        highScore = sharedPreferences.getInt("highScore", 0);
        useMotion = sharedPreferences.getBoolean("motion", false);
        musicSwitch.setChecked(useMotion);
        motionSwitch.setChecked(sharedPreferences.getBoolean("motion", false));

        //Connect slider
        diffSlider = findViewById(R.id.diffslider);
        diffSlider.addOnChangeListener(new Slider.OnChangeListener() {
            //Set a custom listener for OnValue Change
            @Override
            public void onValueChange(Slider slider, float value, boolean fromUser) {
                difficulty = (int) value; //Get value from slider
                TextView tx = (TextView) findViewById(R.id.textViewDiff); //Get handle to the text object
                tx.setText(getResources().getStringArray(R.array.Difficulty)[difficulty - 1]); //Update text with new diff setting
                //Save to memory
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("diff", difficulty);
                editor.commit();
            }
        });

        //Display init text
        TextView tx = (TextView) findViewById(R.id.textViewDiff);
        tx.setText(getResources().getStringArray(R.array.Difficulty)[difficulty - 1]);
        diffSlider.setValue(difficulty);

        tx = (TextView) findViewById(R.id.textViewHighScore);
        String HS = getResources().getString(R.string.high_score) + String.valueOf(highScore);
        tx.setText(HS);

        tx = (TextView) findViewById(R.id.textcredits);
        tx.setText(getResources().getString(R.string.text_credits));
    }

    @Override
    public void onResume() {
        super.onResume();
        //Called on Resume of the Activity
        //Update High score if game resulted in a new score
        TextView tx = (TextView) findViewById(R.id.textViewHighScore);
        String HS = getResources().getString(R.string.high_score) + String.valueOf(highScore);
        tx.setText(HS);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.buttonPlay) {// must be the Play button.
            // Create a new Intent object
            Intent i = new Intent(this, GameActivity.class);

            //Pass in our variables
            i.putExtra("music", musicSwitch.isChecked());

            // Start the GameActivity class via the Intent
            GameActivity.gameDisplay = true;
            GameActivity.gamePause = false;
            GameActivity.gameScore = 0;
            startActivity(i);
        } else if (id == R.id.switchMotion) {
            //Change the motion setting
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("motion", motionSwitch.isChecked());
            editor.commit();
        } else if (id == R.id.switchMusic) {
            //Change the Music setting
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("music", musicSwitch.isChecked());
            editor.commit();
        }
    }
}