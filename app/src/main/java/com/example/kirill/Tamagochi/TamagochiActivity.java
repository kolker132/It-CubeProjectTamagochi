package com.example.kirill.Tamagochi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.kirill.R;

public class TamagochiActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout;
    Button up, down, right, left1;
    DrawView dv;
    int dx = 0;
    int dy = 0;
    int touchbut = 0;
    boolean start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamagochi);
        left1 = findViewById(R.id.left1);
        right = findViewById(R.id.right);
        up = findViewById(R.id.up);
        down = findViewById(R.id.down);
        constraintLayout = findViewById(R.id.constraint1);
        dv = new DrawView(this);
        constraintLayout.addView(dv);

        CountDownTimer upCountDownTimer = new CountDownTimer(Long.MAX_VALUE, 15) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (start) {
                    dv.getTamagochi().move(dx, dy, touchbut);
                }

            }

            @Override
            public void onFinish() {
            }
        };
        left1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dx = -5;
                    dy = 0;
                    upCountDownTimer.start();
                    start = true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    start = false;
                    upCountDownTimer.cancel();
                }
                return true;
            }
        });
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dx = 5;
                    dy = 0;
                    upCountDownTimer.start();
                    start = true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    start = false;
                    upCountDownTimer.cancel();
                }
                return true;
            }
        });
        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dx = 0;
                    dy = -5;
                    upCountDownTimer.start();
                    start = true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    start = false;
                    upCountDownTimer.cancel();
                }
                return true;
            }
        });
        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dx = 0;
                    dy = 5;
                    upCountDownTimer.start();
                    start = true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    start = false;
                    upCountDownTimer.cancel();
                }
                return true;
            }
        });
    }
}