package com.example.kirill.Tamagochi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import com.example.kirill.R;

public class Tamagochi extends Thread {
    private SurfaceHolder surfaceHolder;
    private volatile boolean running = true;
    private Paint backgroundPaint = new Paint();
    Paint paint = new Paint();
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

    int room = 0;
    Rect r1;
    Rect r2;
    Rect r3;
    Rect destination;

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            destination = new Rect(smileX, smileY, smileX + 200, smileY + 200);
            r3 = new Rect(canvas.getWidth() - 25, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2 + 300);
            r2 = new Rect(canvas.getWidth() / 2 + 150, 0, canvas.getWidth() / 3 + 25, 25);
            r1 = new Rect(0, canvas.getHeight() / 2, 25, canvas.getHeight() / 2 + 300);
            if (r1.intersect(destination)) {
                room = 1;
            } else if (r2.intersect(destination)) {
                room = 2;
            } else if (r3.intersect(destination)) {
                room = 3;
            }
            if (canvas != null) {
                try {
                    switch (room) {
                        case 1:
                            leftroom(canvas);
                            break;
                        case 2:
                            uproom(canvas);
                            break;
                        case 3:
                            rightroom(canvas);
                            break;
                        default:
                            mainroom(canvas);
                    }
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }

            }
        }
    }

    private void mainroom(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawBitmap(bitmap, smileX, smileY, backgroundPaint);
        if (smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 5;
        if (smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 5;
        if (smileY + bitmap.getHeight() / 2 < towardPointY) smileY += 5;
        if (smileY + bitmap.getHeight() / 2 > towardPointY) smileY -= 5;
        paint.setColor(Color.GRAY);
        canvas.drawRect(r1, paint);
        canvas.drawRect(r2, paint);
        canvas.drawRect(r3, paint);
    }

    private void leftroom(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawBitmap(bitmap, smileX, smileY, backgroundPaint);
        if (smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 5;
        if (smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 5;
        if (smileY + bitmap.getHeight() / 2 < towardPointY) smileY += 5;
        if (smileY + bitmap.getHeight() / 2 > towardPointY) smileY -= 5;
        paint.setColor(Color.GRAY);
        canvas.drawRect(r3, paint);
    }

    private void uproom(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawBitmap(bitmap, smileX, smileY, backgroundPaint);
        if (smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 5;
        if (smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 5;
        if (smileY + bitmap.getHeight() / 2 < towardPointY) smileY += 5;
        if (smileY + bitmap.getHeight() / 2 > towardPointY) smileY -= 5;
        paint.setColor(Color.GRAY);
        canvas.drawRect(r2, paint);
    }

    private void rightroom(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawBitmap(bitmap, smileX, smileY, backgroundPaint);
        if (smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 5;
        if (smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 5;
        if (smileY + bitmap.getHeight() / 2 < towardPointY) smileY += 5;
        if (smileY + bitmap.getHeight() / 2 > towardPointY) smileY -= 5;
        paint.setColor(Color.GRAY);
        canvas.drawRect(r1, paint);
    }
}
