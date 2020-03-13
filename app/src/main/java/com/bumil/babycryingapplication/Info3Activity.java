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
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class Info3Activity extends AppCompatActivity{

    MediaPlayer music;
    AudioManager audio;

    private LinearLayout playLayout;
    private LinearLayout volumeLayout;
    private SeekBar playBar;
    private SeekBar volumeBar;
    private ImageButton volumeBtn;
    private TextView playbarStart, playbarEnd, volumeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info3);

        this.playBar = (SeekBar) findViewById(R.id.playBar);
        this.volumeBar = (SeekBar) findViewById(R.id.volumeBar);

        playBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    music.seekTo(progress);

                }
                int m = progress/60000;
                int s = (progress%60000)/1000;
                playbarStart.setText(m+":"+s);

                if(progress == music.getDuration()){
                    playLayout.setVisibility(View.INVISIBLE);
                    volumeBtn.setVisibility(View.INVISIBLE);
                    volumeLayout.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audio.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                volumeText.setText("Volume : " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //버튼에 따라 다른 소리 재생
    public void soundPlay(View view) {
        //롤리팝 이상 버전일 경우

        this.playLayout = (LinearLayout) findViewById(R.id.playLayout);
        this.volumeLayout = (LinearLayout) findViewById(R.id.volumeBarLayout);
        this.volumeBtn = (ImageButton) findViewById(R.id.volumeBtn);
        this.playbarStart = (TextView) findViewById(R.id.playBarStart);
        this.playbarEnd = (TextView) findViewById(R.id.playBarEnd);
        this.volumeText = (TextView) findViewById(R.id.volumeText);

        playLayout.setVisibility(View.VISIBLE);
        volumeBtn.setVisibility(View.VISIBLE);

        audio = (AudioManager) getSystemService(AUDIO_SERVICE);

        int aMax = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int aVol = audio.getStreamVolume(AudioManager.STREAM_MUSIC);

        volumeBar.setMax(aMax);
        volumeBar.setProgress(aVol);

        volumeText.setText("Volume : " + aVol);

        if(music != null){
            music.stop();
        }

        switch (view.getId()){
            case R.id.sound1:
                music = MediaPlayer.create(Info3Activity.this, R.raw.vacuum);
                playMusic();
                break;
            case R.id.sound2:
                music = MediaPlayer.create(Info3Activity.this, R.raw.envelope);
                playMusic();
                break;
            case R.id.sound3:
                music = MediaPlayer.create(Info3Activity.this, R.raw.tv_end);
                playMusic();
                break;
            case R.id.sound4:
                music = MediaPlayer.create(Info3Activity.this, R.raw.dry);
                playMusic();
                break;
        }
    }

    // MediaPlayer는 시스템 리소스를 잡아먹는다.
    // MediaPlayer는 필요이상으로 사용하지 않도록 주의해야 한다.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // MediaPlayer 해지
        if(music != null) {
            music.release();
            music = null;
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        music.stop();
    }

    public void playMusic(){

        music.setAudioStreamType(AudioManager.STREAM_MUSIC);

        playBar.setMax(music.getDuration());  // 음악의 총 길이를 시크바 최대값에 적용

        int kk = playBar.getMax();
        int m = kk/60000;
        int s = (kk%60000)/1000;
        playbarEnd.setText(m+":"+s);

        if(music.isPlaying()){
            music.stop();
            try {
                // 음악을 재생할경우를 대비해 준비합니다
                // prepare()은 예외가 2가지나 필요합니다
                music.prepare();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            music.start();
            Thread();
        }
    }

    public void Thread(){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                while (music.isPlaying()){
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    playBar.setProgress(music.getCurrentPosition());
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }


    //스피커 버튼 클릭시 볼륨조절 화면 켜고 끄기
    public void volumeCtl(View view) {

        this.volumeLayout = (LinearLayout) findViewById(R.id.volumeBarLayout);

        if(volumeLayout.getVisibility() == View.VISIBLE){
            volumeLayout.setVisibility(View.INVISIBLE);
        }else{
            volumeLayout.setVisibility(View.VISIBLE);
        }

    }
}
