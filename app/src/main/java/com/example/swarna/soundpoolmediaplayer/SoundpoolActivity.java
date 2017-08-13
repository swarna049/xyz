package com.example.swarna.soundpoolmediaplayer;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoundpoolActivity extends AppCompatActivity {
private Button button,button1;

    private SoundPool soundPool;

    private int soundId, alreadyPlayingStreamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundpool);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setMaxStreams(1);
            builder.setAudioAttributes(new AudioAttributes.Builder()
                    .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .setUsage(AudioAttributes.USAGE_GAME).build());
            soundPool = builder.build();
        } else {
            //noinspection deprecation
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        soundId = soundPool.load(this, R.raw.hare, 1);

        button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
                float actualVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                float maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                float volume = actualVolume / maxVolume;
                alreadyPlayingStreamId = soundPool.play(soundId, volume, volume, 1, 0, 1f);
            }
        });
        button1 = (Button)findViewById(R.id.btn1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.stop(alreadyPlayingStreamId);
                Intent intent = new Intent(SoundpoolActivity.this,MediaplayerActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.stop(alreadyPlayingStreamId);
    }
}
