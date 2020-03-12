package com.bumil.babycryingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class Info3Activity extends AppCompatActivity {

    MediaPlayer mp;
    AudioManager audio;

    private LinearLayout playLayout;
    private SeekBar volumeBar;
    private ImageButton volumeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info3);
    }

    //버튼에 따라 다른 소리 재생
    public void soundPlay(View view) {
        //롤리팝 이상 버전일 경우

        this.playLayout = (LinearLayout) findViewById(R.id.playLayout);
        this.volumeBtn = (ImageButton) findViewById(R.id.volumeBtn);
        this.volumeBar = (SeekBar) findViewById(R.id.volumeBar);

        playLayout.setVisibility(View.VISIBLE);
        volumeBtn.setVisibility(View.VISIBLE);

        audio = (AudioManager) getSystemService(AUDIO_SERVICE);

        int aMax = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int aVol = audio.getStreamVolume(AudioManager.STREAM_MUSIC);

        volumeBar.setMax(aMax);
        volumeBar.setProgress(aVol);


        switch (view.getId()){
            case R.id.sound1:
                mp = MediaPlayer.create(Info3Activity.this, R.raw.tv_end);
                mp.start();
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
        if(mp != null) {
            mp.release();
            mp = null;
        }
    }

    //스피커 버튼 클릭시 볼륨조절 화면 뜨기
    public void volumeCtl(View view) {
        this.volumeBar = (SeekBar) findViewById(R.id.volumeBar);
        volumeBar.setVisibility(View.VISIBLE);
    }
}
