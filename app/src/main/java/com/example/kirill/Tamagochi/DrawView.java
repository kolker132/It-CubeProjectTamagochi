package com.example.kirill.Tamagochi;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.kirill.Tamagochi.Tamagochi;

public class DrawView extends SurfaceView implements SurfaceHolder.Callback {

    private Tamagochi tamagochi;

    public DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        tamagochi = new Tamagochi(getContext(), getHolder());
        tamagochi.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        tamagochi.requestStop();
        boolean retry = true;
        while (retry) {
            try {
                tamagochi.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
     //   tamagochi.setTowardPoint((int)event.getX(),(int)event.getY());

        return false;
    }

    public Tamagochi getTamagochi() {
        return tamagochi;
    }

    public void setTamagochi(Tamagochi tamagochi) {
        this.tamagochi = tamagochi;
    }
}