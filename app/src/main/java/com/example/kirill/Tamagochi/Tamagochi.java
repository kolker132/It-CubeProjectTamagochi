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

    private int towardPointX = 550;
    private int towardPointY = 1500;
    private boolean start =true;
    private boolean die =false;
    int smileX = 450;
    int smileY = 1400;
    int smileXP = 100;
    Rect src;

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
        src = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
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
    Rect up;
    Rect right;
    Rect r4;
    Rect r5;
    Rect r6;
    Rect destination;

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            int h = canvas.getHeight();
            int w = canvas.getWidth();
            destination = new Rect(smileX   , smileY , smileX + 200, smileY + 200);
            right = new Rect(w - 25, h / 2, w, h / 2 + 300);
            up = new Rect(w / 3 + 150, 0, w / 2 + 25, 25);
            r4 = new Rect(w / 2 + 150, 0, w , 500);
            r6 = new Rect(w / 2 + 150, 0, w , 500);
            r5 = new Rect(w / 3 + 150, h, w / 2 + 25, h - 25);
            left = new Rect(0, h / 2, 25, h / 2 + 300);
            if (r4.contains(destination)) {
                smileXP--;
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
        canvas.drawBitmap(bitmap, src, destination, backgroundPaint);
        defRect(canvas);
        canvas.drawText("XP=" + smileXP, canvas.getWidth() - 275,canvas.getHeight() - 250 , dest);
        canvas.drawText("Eat=" + smileXP, canvas.getWidth() - 275,canvas.getHeight() - 175 , dest);
        canvas.drawText("Slp=" + smileXP, canvas.getWidth() - 275,canvas.getHeight() - 100 , dest);
        if (smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 5;
        if (smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 5;
        if (smileY + bitmap.getHeight() / 2 < towardPointY) smileY += 5;
        if (smileY + bitmap.getHeight() / 2 > towardPointY) smileY -= 5;
        paint.setColor(Color.GRAY);
        canvas.drawRect(left, paint);
        canvas.drawRect(up, paint);
        canvas.drawRect(right, paint);
        if (left.intersect(destination)) {
            room = 1;
        } else if (up.intersect(destination)) {
            room = 2;
        } else if (right.intersect(destination)) {
            room = 3;
        }
        if(die){
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), new Paint());
            canvas.drawText("СМЕРТЬ", (float) canvas.getWidth() / 2 - 150, (float) canvas.getHeight() / 2, endpaint);
        }
        if (smileXP <= 0) {
            start = false;
            die = true;
        }
    }

    private void defRect(Canvas canvas) {
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.BLUE);
        canvas.drawRect(destination,p);
    }

    private void leftroom(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawBitmap(bitmap,src,destination,backgroundPaint);
        canvas.drawText("XP=" + smileXP, canvas.getWidth() - 275,canvas.getHeight() - 250 , dest);
        canvas.drawText("Eat=" + smileXP, canvas.getWidth() - 275,canvas.getHeight() - 175 , dest);
        canvas.drawText("Sleep=" + smileXP, canvas.getWidth() - 275,canvas.getHeight() - 100 , dest);
        paint.setColor(Color.GRAY);
        canvas.drawRect(right, paint);
        paint.setColor(Color.RED);
        canvas.drawRect(r4, paint);
        if (right.intersect(destination)) {
            room = 0;
            smileX = 60;
        }
        if (smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 5;
        if (smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 5;
        if (smileY + bitmap.getHeight() / 2 < towardPointY) smileY += 5;
        if (smileY + bitmap.getHeight() / 2 > towardPointY) smileY -= 5;
        defRect(canvas);
        if(die){
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), new Paint());
            canvas.drawText("СМЕРТЬ", (float) canvas.getWidth() / 2 - 150, (float) canvas.getHeight() / 2, endpaint);
        }
        if (smileXP <= 0) {
            start = false;
            die = true;
        }
    }

    private void uproom(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawBitmap(bitmap,src,destination,backgroundPaint);
        canvas.drawText("XP=" + smileXP, canvas.getWidth() - 275,canvas.getHeight() - 250 , dest);
        canvas.drawText("Eat=" + smileXP, canvas.getWidth() - 275,canvas.getHeight() - 175 , dest);
        canvas.drawText("Sleep=" + smileXP, canvas.getWidth() - 275,canvas.getHeight() - 100 , dest);
        paint.setColor(Color.GRAY);
        canvas.drawRect(r5, paint);
        if (r5.intersect(destination)) {
            room = 0;
            smileX = 1;
        }
        if (smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 5;
        if (smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 5;
        if (smileY + bitmap.getHeight() / 2 < towardPointY) smileY += 5;
        if (smileY + bitmap.getHeight() / 2 > towardPointY) smileY -= 5;
        defRect(canvas);
        if(die){
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), new Paint());
            canvas.drawText("СМЕРТЬ", (float) canvas.getWidth() / 2 - 150, (float) canvas.getHeight() / 2, endpaint);
        }
        if (smileXP <= 0) {
            start = false;
            die = true;
        }
    }

    private void rightroom(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawBitmap(bitmap,src,destination,backgroundPaint);
        canvas.drawText("XP=" + smileXP, canvas.getWidth() - 275,canvas.getHeight() - 250 , dest);
        canvas.drawText("Eat=" + smileXP, canvas.getWidth() - 275,canvas.getHeight() - 175 , dest);
        canvas.drawText("Sleep=" + smileXP, canvas.getWidth() - 275,canvas.getHeight() - 100 , dest);
        if (smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 5;
        if (smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 5;
        if (smileY + bitmap.getHeight() / 2 < towardPointY) smileY += 5;
        if (smileY + bitmap.getHeight() / 2 > towardPointY) smileY -= 5;
        paint.setColor(Color.BLUE);
        canvas.drawRect(r4, paint);
        defRect(canvas);
        paint.setColor(Color.GRAY);
        canvas.drawRect(left, paint);
        if(die){
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), new Paint());
            canvas.drawText("СМЕРТЬ", (float) canvas.getWidth() / 2 - 150, (float) canvas.getHeight() / 2, endpaint);
        }
        if (left.intersect(destination)) {
            room = 0;
            smileX = canvas.getHeight() - 1100;
            if (smileXP <= 0) {
                start = false;
                die = true;
            }

        }
    }
}
