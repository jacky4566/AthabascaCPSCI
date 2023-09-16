package com.sandris;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class GameActivity extends Activity {
    // This is where the "Play" button from HomeActivity sends us
    static volatile boolean playing = false;
    private GameView gameView;
    private int difficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();

        //Load the resolution into a Point object
        Point size = new Point();
        display.getSize(size);

        //Get Difficulty
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            difficulty = extras.getInt("difficulty");
        }

        gameView = new GameView(this, size.x, size.y, difficulty);

        // Make the gameView the view for the Activity
        setContentView(gameView);
    }

    // If the Activity is paused make sure to pause the thread
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    // If the Activity is resumed, make sure to resume the thread
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}

