package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class musicservice extends Service {

    public static final String ACTION_PLAY = "PLAY_MUSIC";
    public static final String MUSIC_NAME = "MUSIC_NAME";

    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && ACTION_PLAY.equals(intent.getAction())) {
            String musicName = intent.getStringExtra(MUSIC_NAME);
            playMusic(musicName);
        }
        return START_STICKY;
    }

    private void playMusic(String musicName) {
        int resId = getResIdByName(musicName);
        if (resId == 0) return; // 找不到音樂，安全檢查

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        mediaPlayer = MediaPlayer.create(this, resId);
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(1.0f,1.0f);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }

    private int getResIdByName(String name) {
        return getResources().getIdentifier(name, "raw", getPackageName());
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
