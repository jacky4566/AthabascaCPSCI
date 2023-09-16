package com.sandris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.slider.Slider;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Slider slider;
    private static int difficulty = 1;
    private static int highScore = 0;
    private Button playGame;
    private Switch diffSwitch;
    private Switch musicSwitch;
    MediaPlayer mediaPlayer;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the button in the layout
        playGame = (Button)findViewById(R.id.buttonPlay);
        diffSwitch = (Switch)findViewById(R.id.switchMotion);
        musicSwitch = (Switch)findViewById(R.id.switchMusic);
        // Listen for clicks
        playGame.setOnClickListener(this);
        diffSwitch.setOnClickListener(this);
        musicSwitch.setOnClickListener(this);
        //Load Preference
        sharedPreferences = getSharedPreferences("gamePrefs", MODE_PRIVATE);
        difficulty = sharedPreferences.getInt("diff", 1);
        highScore = sharedPreferences.getInt("highScore", 0);
        diffSwitch.setChecked(sharedPreferences.getBoolean("motion", false));
        musicSwitch.setChecked(sharedPreferences.getBoolean("music", true));

        //Connect slider
        slider = findViewById(R.id.diffslider);
        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(Slider slider, float value, boolean fromUser) {
                difficulty = (int)value;
                TextView tx = (TextView) findViewById(R.id.textViewDiff);
                tx.setText(getResources().getStringArray(R.array.Difficulty)[difficulty-1]);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("diff", difficulty);
                editor.commit();
            }
        });

        //Display init text
        TextView tx = (TextView) findViewById(R.id.textViewDiff);
        tx.setText(getResources().getStringArray(R.array.Difficulty)[difficulty-1]);
        slider.setValue(difficulty);

        tx = (TextView) findViewById(R.id.textViewHighScore);
        String HS = getResources().getString(R.string.high_score) + String.valueOf(highScore);
        tx.setText(HS);

        tx = (TextView) findViewById(R.id.textcredits);
        tx.setText(getResources().getString(R.string.text_credits) );

        //Start music
        if (musicSwitch.isChecked()){
            startMusic();
        }else{
            stopMusic();
        }
    }

    private void startMusic(){
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.popcorn));
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    // Playback completed
                    mp.start(); //Loop forever!!
                }
            });

            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    // Handle errors here
                    return false;
                }
            });
        } catch (IOException e) {
        }
    }
    private void stopMusic(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onResume() {
        super.onResume();
        difficulty = sharedPreferences.getInt("diff", 1);
        TextView tx = (TextView) findViewById(R.id.textViewHighScore);
        String HS = getResources().getString(R.string.high_score) + String.valueOf(highScore);
        tx.setText(HS);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    public void onClick(View v){
        int id = v.getId();
        if (id == R.id.buttonPlay) {// must be the Play button.
            // Create a new Intent object
            Intent i = new Intent(this, GameActivity.class);

            //Pass in our variables
            i.putExtra("difficulty", difficulty);
            i.putExtra("motion", diffSwitch.isChecked());

            // Start the GameActivity class via the Intent
            startActivity(i);
        } else if (id == R.id.switchMotion) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("motion", diffSwitch.isChecked());
            editor.commit();
        }else if (id == R.id.switchMusic) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("music", musicSwitch.isChecked());
            editor.commit();
            if (musicSwitch.isChecked()){
                startMusic();
            }else{
                stopMusic();
            }
        }
    }
}