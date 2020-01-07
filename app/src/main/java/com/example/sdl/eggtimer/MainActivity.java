package com.example.sdl.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timeSelector;
    TextView timer;
    Button textoButton;
    CountDownTimer myCountDown;
    MediaPlayer myAlarm;
    boolean startMode = true;


    public void beginStop(View n){

        textoButton = (Button) n;

        if(startMode){
            timeSelector.setEnabled(false);
            startMode = false;
            textoButton.setText(R.string.stop);


            myCountDown = new CountDownTimer(timeSelector.getProgress() * 1000 +100, 1000) {
                @Override
                public void onTick(long l) {
                    timeSelector.setProgress((int)l/1000);
                }

                @Override
                public void onFinish() {
                    timer.setText("0:00");
                    myAlarm = MediaPlayer.create(getApplicationContext(), R.raw.alert);
                    myAlarm.setLooping(true);
                    myAlarm.start();
                }//onFinish end
            }.start(); // CountDownTimer end ...

        } else {
            myAlarm.stop();
            myCountDown.cancel();
            timeSelector.setProgress(45);
            textoButton.setText(R.string.start);
            timeSelector.setEnabled(true);
            startMode = true;

        }// if else end

    } // beginStop end


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeSelector = findViewById(R.id.sbTime);
        timer = findViewById(R.id.tvTime);
        timeSelector.setMax(900);
        timeSelector.setProgress(45);

        timeSelector.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int iProgress, boolean bFromUser) {
                int minutes;
                int seconds;
                minutes = iProgress / 60;
                seconds = iProgress - minutes * 60;
                String secondString = String.valueOf(seconds);

                if(seconds <= 9){
                    secondString = "0" + seconds;
                }

                timer.setText(Integer.toString(minutes) + ":" + secondString);
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        }); // setOnSeekBarChangeListener end


    }// OnCreate end
}// MAIN end mmmmmm
