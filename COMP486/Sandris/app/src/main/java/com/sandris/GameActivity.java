package com.sandris;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;

public class GameActivity extends Activity implements GameView.GameViewListener, SensorEventListener {
    // This is where the "Play" button from HomeActivity sends us
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    static volatile boolean playing = false;
    private GameView gameView;
    private int difficulty;
    private boolean useMotion;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();

        //Load the resolution into a Point object
        Point size = new Point();
        display.getSize(size);

        //Get Difficulty
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            difficulty = extras.getInt("difficulty");
            useMotion = extras.getBoolean("motion");
        }

        gameView = new GameView(this, size.x, size.y, difficulty, useMotion);
        gameView.setGameViewListener(this);

        // Make the gameView the view for the Activity
        setContentView(gameView);
    }

    // If the Activity is paused make sure to pause the thread
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
        mSensorManager.unregisterListener(this);
    }

    // If the Activity is resumed, make sure to resume the thread
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void gameViewCallback(int newScore) {
        sharedPreferences = getSharedPreferences("gamePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("highScore", newScore);
        editor.commit();

        this.finish();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            GameView.updateSensorData(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

