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
    private Paint dest =new Paint();
    private Paint endpaint=new Paint();
    private Bitmap bitmap;
    private Bitmap reset;
    private boolean die =false;
    private int towardPointX = 550;
    private int towardPointY = 1500;
    private boolean start =true;
    int smileX = 450;
    int smileY = 1400;
    int smileXP = 100;

    {
        backgroundPaint.setColor(Color.WHITE);
        backgroundPaint.setStyle(Paint.Style.FILL);
        endpaint.setColor(Color.RED);
        endpaint.setTextSize((float) 100);
        dest.setColor(Color.BLACK);
        dest.setTextSize(75);
    }

    public Tamagochi(Context context, SurfaceHolder surfaceHolder) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.smile12345);
        this.surfaceHolder = surfaceHolder;
        reset=BitmapFactory.decodeResource(context.getResources(), R.drawable.reset);
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
    Rect left;
    Rect top;
    Rect right;
    Rect r4;
    Rect r5;
    Rect destination;
    Rect destinationres;
    Rect srcreset;

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            int h = canvas.getHeight();
            int w = canvas.getWidth();
            destination = new Rect(smileX  , smileY, smileX + 390, smileY + 200);
            right = new Rect(w - 25, h / 2, w, h / 2 + 300);
            top = new Rect(w / 2 + 150, 0, w / 3 + 25, 25);
            r4 = new Rect(w / 2 + 150, 0, w , 500);
            r5 = new Rect(w / 2 + 150, 0 , w , 500);
            left = new Rect(0, h / 2, 25, h / 2 + 300);
            canvas.drawText("XP-" + smileXP, h - 200, w + 200, dest);
            destinationres = new Rect(w /2-100, h /2-300, w /2+100, h /2-100);
            srcreset = new Rect(0,0,reset.getWidth(),reset.getHeight());
            if(die){
                canvas.drawBitmap(reset,srcreset,destinationres,new Paint());
                canvas.drawRect(0, 0, w, h, new Paint());
                canvas.drawText("YOU DIE", (float) w / 2 - 150, (float) h / 2, endpaint);
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
        canvas.drawRect(left, paint);
        canvas.drawRect(top, paint);
        canvas.drawRect(right, paint);
        if (left.intersect(destination)) {
            room = 1;
        } else if (top.intersect(destination)) {
            room = 2;
        } else if (right.intersect(destination)) {
            room = 3;
        }
    }

    private void leftroom(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawBitmap(bitmap, smileX, smileY, backgroundPaint);
        if (smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 5;
        if (smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 5;
        if (smileY + bitmap.getHeight() / 2 < towardPointY) smileY += 5;
        if (smileY + bitmap.getHeight() / 2 > towardPointY) smileY -= 5;
        paint.setColor(Color.GRAY);
        canvas.drawRect(right, paint);
        paint.setColor(Color.RED);
        canvas.drawRect(r4, paint);
        if (right.intersect(destination)) {
            room = 0;
            smileX = 60;
        }
    }

    private void uproom(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawBitmap(bitmap, smileX, smileY, backgroundPaint);
        if (smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 5;
        if (smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 5;
        if (smileY + bitmap.getHeight() / 2 < towardPointY) smileY += 5;
        if (smileY + bitmap.getHeight() / 2 > towardPointY) smileY -= 5;
        paint.setColor(Color.GRAY);
        canvas.drawRect(r5, paint);
        if (r5.intersect(destination)) {
            room = 0;
            smileX = 1;
        }

    }

    private void rightroom(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawBitmap(bitmap, smileX, smileY, backgroundPaint);
        if (smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 5;
        if (smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 5;
        if (smileY + bitmap.getHeight() / 2 < towardPointY) smileY += 5;
        if (smileY + bitmap.getHeight() / 2 > towardPointY) smileY -= 5;
        paint.setColor(Color.GRAY);
        canvas.drawRect(left, paint);
        if (left.intersect(destination)) {
            room = 0;
            smileX = canvas.getHeight() - 110;

        }
    }
}
