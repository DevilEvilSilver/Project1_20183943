package com.example.test;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.test.GameView.screenRatioX;
import static com.example.test.GameView.screenRatioY;

public class Character {

    public boolean isRunLeft = false;
    public boolean isRunRight = false;
    public boolean isDown = false;
    public int isJump = 0;
    public boolean isOnGround = false;
    int x, y, width, height;
    Bitmap idle, jump, runLeft, runRight;

    Character (int screenY , Resources resources) {
        idle = BitmapFactory.decodeResource(resources, R.drawable.idle);
        jump = BitmapFactory.decodeResource(resources, R.drawable.jump);
        runLeft = BitmapFactory.decodeResource(resources, R.drawable.run_left);
        runRight = BitmapFactory.decodeResource(resources, R.drawable.run_right);

        width = idle.getWidth();
        height = idle.getHeight();
        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        idle = Bitmap.createScaledBitmap(idle, width, height, false);
        jump = Bitmap.createScaledBitmap(jump, width, height, false);
        runLeft = Bitmap.createScaledBitmap(runLeft, width, height, false);
        runRight = Bitmap.createScaledBitmap(runRight, width, height, false);

        x = (int) (64 * screenRatioX);
        y = screenY / 2;
    }

    Bitmap getCharacter() {
        if (isOnGround) {
            if (isRunRight) return runRight;
            if (isRunLeft) return runLeft;
            else return idle;
        }
        else return jump;
    }

    Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }
}
