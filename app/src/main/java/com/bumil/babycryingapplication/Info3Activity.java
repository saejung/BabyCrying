package com.bumil.babycryingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class Info3Activity extends AppCompatActivity {

    SeekBar seekBar;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info3);
    }

    public void soundPlay(View view) {
        //롤리팝 이상 버전일 경우

        switch (view.getId()){
            case R.id.sound1:
                mediaPlayer = MediaPlayer.create(Info3Activity.this, R.raw.tv_end);
                mediaPlayer.start();
                break;
            case R.id.sound2:
                break;
            case R.id.sound3:
                break;
            case R.id.sound4:
                break;
        }
    }

    // MediaPlayer는 시스템 리소스를 잡아먹는다.
    // MediaPlayer는 필요이상으로 사용하지 않도록 주의해야 한다.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // MediaPlayer 해지
        if(mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
