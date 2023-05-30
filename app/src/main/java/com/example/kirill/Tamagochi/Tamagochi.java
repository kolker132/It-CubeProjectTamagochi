package com.example.kirill.Tamagochi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
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

    private int towardPointX = 550;
    private int towardPointY = 1500;
    private boolean start =true;
    private boolean die =false;
    int smileX = 450;
    int smileY = 1400;
    int smileXP = 100;
    int smileSleep = 100;
    int smileEat = 100;
    int smileFun = 100;
    Rect src;

    {
        backgroundPaint.setColor(Color.WHITE);
        backgroundPaint.setStyle(Paint.Style.FILL);
        endpaint.setColor(Color.WHITE);
        endpaint.setTextSize((float) 100);
        dest.setColor(Color.BLACK);
        dest.setTextSize(75);
    }

    public Tamagochi(Context context, SurfaceHolder surfaceHolder) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.smile12345);
        src = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
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
    Rect left;
    Rect up;
    Rect right;
    Rect sleep;
    Rect down;
    Rect eat;
    Rect fun;
    Rect destination;
    CountDownTimer timer = new CountDownTimer(Long.MAX_VALUE, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            if (smileSleep >= 100) {
                smileSleep = 100;
            }
            if (smileEat >= 100) {
                smileEat = 100;
            }
            if (smileFun >= 100) {
                smileFun = 100;
            }
            if (smileFun <= 0) {
                smileFun = 0;
            }
            if (smileSleep <= 0) {
                smileSleep = 0;
            }
            if (smileEat <= 0) {
                smileEat = 0;
            }
            if (sleep.contains(destination)) {
                smileSleep++;
            } else {
                smileSleep--;
            }
            if (eat.contains(destination)) {
                smileEat++;
            } else {
                smileEat--;
            }
            if (fun.contains(destination)) {
                smileFun++;
            } else {
                smileFun--;
            }
            if (smileEat <= 0) {
                smileXP--;
            }

            if (smileFun <= 0) {
                smileXP--;
            }
            if (smileSleep <= 0) {
                smileXP--;
            }

        }

        @Override
        public void onFinish() {
        }
    };


    @Override
    public void run() {
        timer.start();
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            int h = canvas.getHeight();
            int w = canvas.getWidth();
            destination = new Rect(smileX   , smileY , smileX + 200, smileY + 200);
            right = new Rect(w - 25, h / 2, w, h / 2 + 300);
            up = new Rect(w / 3 + 25, 0, w / 2 + 150, 25);
            sleep = new Rect(0, 0, w/2 -225, 500);
            eat = new Rect(w / 2 + 150, 0, w , 500);
            down = new Rect(w / 3 + 25, h, w / 2 + 150, h - 25);
            left = new Rect(0, h / 2, 25, h / 2 + 300);
            fun = new Rect(0, h / 2, 350, h /2 + 450);


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
        canvas.drawText("XP=" + smileXP, canvas.getWidth() - 285,canvas.getHeight() - 325 , dest);
        canvas.drawText("Eat=" + smileEat, canvas.getWidth() - 285,canvas.getHeight() - 250 , dest);
        canvas.drawText("Slp=" + smileSleep, canvas.getWidth() - 285,canvas.getHeight() - 175 , dest);
        canvas.drawText("Fun=" + smileFun, canvas.getWidth() - 285,canvas.getHeight() - 100 , dest);
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
            smileX=canvas.getWidth()-300;
        } else if (up.intersect(destination)) {
            room = 2;
            smileY = canvas.getHeight()-300;
        } else if (right.intersect(destination)) {
            room = 3;
            smileX = 150;
        }
        if(die){
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), new Paint());
            canvas.drawText("GAME OVER", (float) canvas.getWidth() / 2 - 150, (float) canvas.getHeight() / 2, endpaint);
        }
        if (smileXP <= 0) {
            start = false;
            die = true;
        }

    }


    private void leftroom(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        if (smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 5;
        if (smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 5;
        if (smileY + bitmap.getHeight() / 2 < towardPointY) smileY += 5;
        if (smileY + bitmap.getHeight() / 2 > towardPointY) smileY -= 5;
        canvas.drawText("XP=" + smileXP, canvas.getWidth() - 285,canvas.getHeight() - 325 , dest);
        canvas.drawText("Eat=" + smileEat, canvas.getWidth() - 285,canvas.getHeight() - 250 , dest);
        canvas.drawText("Slp=" + smileSleep, canvas.getWidth() - 285,canvas.getHeight() - 175 , dest);
        canvas.drawText("Fun=" + smileFun, canvas.getWidth() - 285,canvas.getHeight() - 100 , dest);
        paint.setColor(Color.GRAY);
        canvas.drawRect(right, paint);
        paint.setColor(Color.RED);
        canvas.drawRect(sleep, paint);
        canvas.drawBitmap(bitmap,src,destination,backgroundPaint);
        if (right.intersect(destination)) {
            room = 0;
            smileX = 60;
        }

        if (smileXP <= 0) {
            start = false;
            die = true;
        }
        if(die){
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), new Paint());
            canvas.drawText("GAME OVER", (float) canvas.getWidth() / 2 - 150, (float) canvas.getHeight() / 2, endpaint);
        }
    }

    private void uproom(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawBitmap(bitmap,src,destination,backgroundPaint);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(fun, paint);
        canvas.drawText("XP=" + smileXP, canvas.getWidth() - 285,canvas.getHeight() - 325 , dest);
        canvas.drawText("Eat=" + smileEat, canvas.getWidth() - 285,canvas.getHeight() - 250 , dest);
        canvas.drawText("Slp=" + smileSleep, canvas.getWidth() - 285,canvas.getHeight() - 175 , dest);
        canvas.drawText("Fun=" + smileFun, canvas.getWidth() - 285,canvas.getHeight() - 100 , dest);

        paint.setColor(Color.GRAY);
        canvas.drawRect(down, paint);
        if (down.intersect(destination)) {
            room = 0;
            smileX = 1;
        }
        if (smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 5;
        if (smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 5;
        if (smileY + bitmap.getHeight() / 2 < towardPointY) smileY += 5;
        if (smileY + bitmap.getHeight() / 2 > towardPointY) smileY -= 5;
        if (smileXP <= 0) {
            start = false;
            die = true;
        }
        canvas.drawBitmap(bitmap,src,destination,backgroundPaint);
        if(die){
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), new Paint());
            canvas.drawText("GAME OVER", (float) canvas.getWidth() / 2 - 150, (float) canvas.getHeight() / 2, endpaint);
        }

    }

    private void rightroom(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawBitmap(bitmap,src,destination,backgroundPaint);
        canvas.drawText("XP=" + smileXP, canvas.getWidth() - 285,canvas.getHeight() - 325 , dest);
        canvas.drawText("Eat=" + smileEat, canvas.getWidth() - 285,canvas.getHeight() - 250 , dest);
        canvas.drawText("Slp=" + smileSleep, canvas.getWidth() - 285,canvas.getHeight() - 175 , dest);
        canvas.drawText("Fun=" + smileFun, canvas.getWidth() - 285,canvas.getHeight() - 100 , dest);
        if (smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 5;
        if (smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 5;
        if (smileY + bitmap.getHeight() / 2 < towardPointY) smileY += 5;
        if (smileY + bitmap.getHeight() / 2 > towardPointY) smileY -= 5;
        paint.setColor(Color.BLUE);
        canvas.drawRect(eat, paint);
        paint.setColor(Color.GRAY);
        canvas.drawRect(left, paint);

        if (left.intersect(destination)) {
            room = 0;
            smileX = canvas.getHeight() - 1100;
            if (smileXP <= 0) {
                start = false;
                die = true;
            }

        }
        canvas.drawBitmap(bitmap,src,destination,backgroundPaint);
        if(die){
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), new Paint());
            canvas.drawText("GAME OVER", (float) canvas.getWidth() / 2 - 150, (float) canvas.getHeight() / 2, endpaint);
        }
    }
}
