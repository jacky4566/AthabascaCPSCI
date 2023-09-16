package com.sandris;

import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

public class SoundEngine {
    MediaPlayer mp =  new MediaPlayer();
    public SoundEngine(SoundEffect sound){
        //Play Game over music
        try {
            switch (sound){
                case gamestart:
                    mp.setDataSource(GameActivity.getAppContext(), Uri.parse("android.resource://" + GameActivity.PACKAGE_NAME + "/" + R.raw.game_start));
                    break;
                case gameover:
                    mp.setDataSource(GameActivity.getAppContext(), Uri.parse("android.resource://" + GameActivity.PACKAGE_NAME + "/" + R.raw.game_over));
                    break;
                case gameoverHighScore:
                    mp.setDataSource(GameActivity.getAppContext(), Uri.parse("android.resource://" + GameActivity.PACKAGE_NAME + "/" + R.raw.game_over_highscore));
                    break;
                case tetromino_drop:
                    mp.setDataSource(GameActivity.getAppContext(), Uri.parse("android.resource://" + GameActivity.PACKAGE_NAME + "/" + R.raw.tetromino_drop));
                    break;
                case tetromino_rotate:
                    mp.setDataSource(GameActivity.getAppContext(), Uri.parse("android.resource://" + GameActivity.PACKAGE_NAME + "/" + R.raw.tetromino_rotate));
                    break;
                case resolve_1:
                    mp.setDataSource(GameActivity.getAppContext(), Uri.parse("android.resource://" + GameActivity.PACKAGE_NAME + "/" + R.raw.resolve_1));
                    break;
                case resolve_2:
                    mp.setDataSource(GameActivity.getAppContext(), Uri.parse("android.resource://" + GameActivity.PACKAGE_NAME + "/" + R.raw.resolve_2));
                    break;
                case resolve_3:
                    mp.setDataSource(GameActivity.getAppContext(), Uri.parse("android.resource://" + GameActivity.PACKAGE_NAME + "/" + R.raw.resolve_3));
                    break;
                case resolve_4:
                    mp.setDataSource(GameActivity.getAppContext(), Uri.parse("android.resource://" + GameActivity.PACKAGE_NAME + "/" + R.raw.resolve_4));
                    break;
                case resolve_5:
                    mp.setDataSource(GameActivity.getAppContext(), Uri.parse("android.resource://" + GameActivity.PACKAGE_NAME + "/" + R.raw.resolve_5));
                    break;
                case resolve_6:
                    mp.setDataSource(GameActivity.getAppContext(), Uri.parse("android.resource://" + GameActivity.PACKAGE_NAME + "/" + R.raw.resolve_6));
                    break;
                case resolve_7:
                    mp.setDataSource(GameActivity.getAppContext(), Uri.parse("android.resource://" + GameActivity.PACKAGE_NAME + "/" + R.raw.resolve_7));
                    break;
                case resolve_8:
                    mp.setDataSource(GameActivity.getAppContext(), Uri.parse("android.resource://" + GameActivity.PACKAGE_NAME + "/" + R.raw.resolve_8));
                    break;
                case resolve_9:
                    mp.setDataSource(GameActivity.getAppContext(), Uri.parse("android.resource://" + GameActivity.PACKAGE_NAME + "/" + R.raw.resolve_9));
                    break;
                case resolve_10:
                    mp.setDataSource(GameActivity.getAppContext(), Uri.parse("android.resource://" + GameActivity.PACKAGE_NAME + "/" + R.raw.resolve_10));
                    break;
            }

            mp.prepareAsync();
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    // Playback completed
                    mp.reset();
                    mp.release();
                }
            });

            mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    // Handle errors here
                    Log.e("SoundEffects", "Error" + what);
                    return false;
                }
            });
        } catch (IOException e) {
        }
    }
}
