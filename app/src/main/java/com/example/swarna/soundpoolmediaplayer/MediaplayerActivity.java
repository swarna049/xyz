package com.example.swarna.soundpoolmediaplayer;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.FileInputStream;

public class MediaplayerActivity extends AppCompatActivity {
private Button button;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer);


        mediaPlayer = MediaPlayer.create(this, R.raw.nelenele);
        mediaPlayer.setLooping(true);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                    .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build());
        }
        button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}
