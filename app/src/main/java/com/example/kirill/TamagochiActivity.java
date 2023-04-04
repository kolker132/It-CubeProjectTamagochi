package com.example.kirill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class TamagochiActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout;
    Button up,down,right,left1;
    DrawView dv;
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
                if(start) {
                    dv.getTamagochi().move(-5, 0);
                }
            }
            @Override
            public void onFinish() {}
        };
        left1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ) {
                    upCountDownTimer.start();
                    start = true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP ) {
                    start = false;
                    upCountDownTimer.cancel();
                }
                return true;
            }
        });
    }
}