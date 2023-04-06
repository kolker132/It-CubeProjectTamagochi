package com.example.kirill;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class Tamagochi extends Thread {
    private SurfaceHolder surfaceHolder;
    private volatile boolean running = true;
    private Paint backgroundPaint = new Paint();
    private Bitmap bitmap;
    private int towardPointX = 550;
    private int towardPointY = 1500;
    int smileX = 450;
    int smileY = 1400;
    {
        backgroundPaint.setColor(Color.WHITE);
        backgroundPaint.setStyle(Paint.Style.FILL);
    }
    public Tamagochi(Context context, SurfaceHolder surfaceHolder) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.smile12345);
        this.surfaceHolder = surfaceHolder;
    }

    public void requestStop() {
        running = false;
    }

    public void setTowardPoint(int x, int y) {
        towardPointX = x;
        towardPointY = y;
    }
    public void move(int dx, int dy) {
        towardPointX += dx;
        towardPointY += dy;

    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
                    canvas.drawBitmap(bitmap, smileX, smileY, backgroundPaint);
                    if (smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 5;
                    if (smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 5;
                    if (smileY + bitmap.getHeight() / 2 < towardPointY) smileY += 5;
                    if (smileY + bitmap.getHeight() / 2 > towardPointY) smileY -= 5;
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }

            }
        }
    }
}
