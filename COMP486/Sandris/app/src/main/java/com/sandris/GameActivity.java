package com.sandris;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import java.io.IOException;

public class GameActivity extends Activity implements GameView.GameViewListener, SensorEventListener {
    // This is where the "Play" button from HomeActivity sends us
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    static volatile boolean gameDisplay = false;
    static volatile boolean gamePause = false;
    private GameView gameView;
    private SharedPreferences sharedPreferences;
    public static String PACKAGE_NAME;
    public static Context context;
    private static boolean confirmExit = false;
    private MediaPlayer musicPlayer = new MediaPlayer();
    private boolean playMusic;
    public static int gameScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        GameActivity.context = getApplicationContext();

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();

        //Load the resolution into a Point object
        Point size = new Point();
        display.getSize(size);

        //Get options passed in
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            playMusic = extras.getBoolean("music");
        }

        gameView = new GameView(this, size.x, size.y);
        gameView.setGameViewListener(this);

        // Make the gameView the view for the Activity
        setContentView(gameView);
    }

    // If the Activity is paused make sure to pause the thread
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
        stopMusic();
        gameView.pause();
    }

    // If the Activity is resumed, make sure to resume the thread
    @Override
    protected void onResume() {
        super.onResume();
        new SoundEngine(SoundEffect.gamestart);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        if (playMusic)
            startMusic();
        gameView.resume();
    }

    private void startMusic(){
        musicPlayer = new MediaPlayer();
        try {
            musicPlayer.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.popcorn));
            musicPlayer.prepareAsync();
            musicPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });

            musicPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    // Playback completed
                    mp.start(); //Loop forever!!
                }
            });

            musicPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
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
        musicPlayer.stop();
        musicPlayer.release();
    }

    @Override
    public void gameViewCallbackEnd() {
        /*
        Callback from Game end
        Compute if high score exceed and save to sharedPreferences
        Play sound effects
        Finish acivity so we return to main acitivty
         */
        if (GameActivity.gameScore > MainActivity.highScore ) {
            MainActivity.highScore = GameActivity.gameScore;
            sharedPreferences = getSharedPreferences("gamePrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("highScore", GameActivity.gameScore);
            editor.commit();
            new SoundEngine(SoundEffect.gameoverHighScore);
            this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getBaseContext(), "New High Score!", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            new SoundEngine(SoundEffect.gameover);
            this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getBaseContext(), "Game Over", Toast.LENGTH_SHORT).show();
                }
            });
        }

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

    @Override
    public void onBackPressed() {
        if (!GameActivity.gamePause)
            GameActivity.gamePause = true;
        else {
            GameActivity.gameDisplay = false;
            gameViewCallbackEnd();
        }
    }

    public static Context getAppContext() {
        return GameActivity.context;
    }
}

