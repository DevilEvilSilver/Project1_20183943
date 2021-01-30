package com.example.test;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.test.GameView.screenRatioX;
import static com.example.test.GameView.screenRatioY;

public class Character {

    public boolean isLeft = false;
    public boolean isRight = true;
    public boolean isRunLeft = false;
    public boolean isRunRight = false;
    public boolean isDown = false;
    public int isJump = 0;
    public boolean isOnGround = false;
    int x, y, width, height, charCounter = 1;
    Bitmap il1, il2, il3, il4, ir1, ir2, ir3, ir4;
    Bitmap jl1, jl2, jl3, jl4, jl5, jl6, jr1, jr2, jr3, jr4, jr5, jr6;
    Bitmap rl1, rl2, rl3, rl4, rl5, rl6, rr1, rr2, rr3, rr4, rr5, rr6;

    Character (int screenY , Resources resources) {
        il1 = BitmapFactory.decodeResource(resources, R.drawable.il1);
        il2 = BitmapFactory.decodeResource(resources, R.drawable.il2);
        il3 = BitmapFactory.decodeResource(resources, R.drawable.il3);
        il4 = BitmapFactory.decodeResource(resources, R.drawable.il4);
        ir1 = BitmapFactory.decodeResource(resources, R.drawable.ir1);
        ir2 = BitmapFactory.decodeResource(resources, R.drawable.ir2);
        ir3 = BitmapFactory.decodeResource(resources, R.drawable.ir3);
        ir4 = BitmapFactory.decodeResource(resources, R.drawable.ir4);
        jl1 = BitmapFactory.decodeResource(resources, R.drawable.jl1);
        jl2 = BitmapFactory.decodeResource(resources, R.drawable.jl2);
        jl3 = BitmapFactory.decodeResource(resources, R.drawable.jl3);
        jl4 = BitmapFactory.decodeResource(resources, R.drawable.jl4);
        jl5 = BitmapFactory.decodeResource(resources, R.drawable.jl5);
        jl6 = BitmapFactory.decodeResource(resources, R.drawable.jl6);
        jr1 = BitmapFactory.decodeResource(resources, R.drawable.jr1);
        jr2 = BitmapFactory.decodeResource(resources, R.drawable.jr2);
        jr3 = BitmapFactory.decodeResource(resources, R.drawable.jr3);
        jr4 = BitmapFactory.decodeResource(resources, R.drawable.jr4);
        jr5 = BitmapFactory.decodeResource(resources, R.drawable.jr5);
        jr6 = BitmapFactory.decodeResource(resources, R.drawable.jr6);
        rl1 = BitmapFactory.decodeResource(resources, R.drawable.rl1);
        rl2 = BitmapFactory.decodeResource(resources, R.drawable.rl2);
        rl3 = BitmapFactory.decodeResource(resources, R.drawable.rl3);
        rl4 = BitmapFactory.decodeResource(resources, R.drawable.rl4);
        rl5 = BitmapFactory.decodeResource(resources, R.drawable.rl5);
        rl6 = BitmapFactory.decodeResource(resources, R.drawable.rl6);
        rr1 = BitmapFactory.decodeResource(resources, R.drawable.rr1);
        rr2 = BitmapFactory.decodeResource(resources, R.drawable.rr2);
        rr3 = BitmapFactory.decodeResource(resources, R.drawable.rr3);
        rr4 = BitmapFactory.decodeResource(resources, R.drawable.rr4);
        rr5 = BitmapFactory.decodeResource(resources, R.drawable.rr5);
        rr6 = BitmapFactory.decodeResource(resources, R.drawable.rr6);

        width = il1.getWidth();
        height = il1.getHeight();
        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        il1 = Bitmap.createScaledBitmap(il1, width, height, false);
        il2 = Bitmap.createScaledBitmap(il2, width, height, false);
        il3 = Bitmap.createScaledBitmap(il3, width, height, false);
        il4 = Bitmap.createScaledBitmap(il4, width, height, false);
        ir1 = Bitmap.createScaledBitmap(ir1, width, height, false);
        ir2 = Bitmap.createScaledBitmap(ir2, width, height, false);
        ir3 = Bitmap.createScaledBitmap(ir3, width, height, false);
        ir4 = Bitmap.createScaledBitmap(ir4, width, height, false);
        jl1 = Bitmap.createScaledBitmap(jl1, width, height, false);
        jl2 = Bitmap.createScaledBitmap(jl2, width, height, false);
        jl3 = Bitmap.createScaledBitmap(jl3, width, height, false);
        jl4 = Bitmap.createScaledBitmap(jl4, width, height, false);
        jl5 = Bitmap.createScaledBitmap(jl5, width, height, false);
        jl6 = Bitmap.createScaledBitmap(jl6, width, height, false);
        jr1 = Bitmap.createScaledBitmap(jr1, width, height, false);
        jr2 = Bitmap.createScaledBitmap(jr2, width, height, false);
        jr3 = Bitmap.createScaledBitmap(jr3, width, height, false);
        jr4 = Bitmap.createScaledBitmap(jr4, width, height, false);
        jr5 = Bitmap.createScaledBitmap(jr5, width, height, false);
        jr6 = Bitmap.createScaledBitmap(jr6, width, height, false);
        rl1 = Bitmap.createScaledBitmap(rl1, width, height, false);
        rl2 = Bitmap.createScaledBitmap(rl2, width, height, false);
        rl3 = Bitmap.createScaledBitmap(rl3, width, height, false);
        rl4 = Bitmap.createScaledBitmap(rl4, width, height, false);
        rl5 = Bitmap.createScaledBitmap(rl5, width, height, false);
        rl6 = Bitmap.createScaledBitmap(rl6, width, height, false);
        rr1 = Bitmap.createScaledBitmap(rr1, width, height, false);
        rr2 = Bitmap.createScaledBitmap(rr2, width, height, false);
        rr3 = Bitmap.createScaledBitmap(rr3, width, height, false);
        rr4 = Bitmap.createScaledBitmap(rr4, width, height, false);
        rr5 = Bitmap.createScaledBitmap(rr5, width, height, false);
        rr6 = Bitmap.createScaledBitmap(rr6, width, height, false);


        x = (int) (64 * screenRatioX);
        y = screenY / 2;
    }

    Bitmap getCharacter() {
        if (isOnGround) {
            if (isRunRight) return getRunRight();
            if (isRunLeft) return getRunLeft();
            if (isLeft) return getIdleLeft();
            if (isRight) return getIdleRight();
        }
        else {
            if(isRight || isRunRight) return getJumpRight();
            else return getJumpLeft();
        }

        return ir1;
    }

    Bitmap getIdleLeft() {
        if (charCounter == 1) {
            charCounter++;
            return il1;
        }
        if (charCounter <= 3) {
            charCounter++;
            return il2;
        }
        if (charCounter <= 5) {
            charCounter++;
            return il3;
        }
        charCounter = 1;
        return il4;
    }

    Bitmap getIdleRight() {
        if (charCounter == 1) {
            charCounter++;
            return ir1;
        }
        if (charCounter <= 3) {
            charCounter++;
            return ir2;
        }
        if (charCounter <= 5) {
            charCounter++;
            return ir3;
        }
        charCounter = 1;
        return ir4;
    }

    Bitmap getRunLeft() {
        if (charCounter == 1) {
            charCounter++;
            return rl1;
        }
        if (charCounter == 2) {
            charCounter++;
            return rl2;
        }
        if (charCounter == 3) {
            charCounter++;
            return rl3;
        }
        if (charCounter == 4) {
            charCounter++;
            return rl4;
        }
        if (charCounter == 5) {
            charCounter++;
            return rl5;
        }
        charCounter = 1;
        return rl6;
    }

    Bitmap getRunRight() {
        if (charCounter == 1) {
            charCounter++;
            return rr1;
        }
        if (charCounter == 2) {
            charCounter++;
            return rr2;
        }
        if (charCounter == 3) {
            charCounter++;
            return rr3;
        }
        if (charCounter == 4) {
            charCounter++;
            return rr4;
        }
        if (charCounter == 5) {
            charCounter++;
            return rr5;
        }
        charCounter = 1;
        return rr6;
    }

    Bitmap getJumpLeft() {
        if (charCounter < 4) {
            charCounter++;
            return jl1;
        }
        if (charCounter < 7) {
            charCounter++;
            return jl2;
        }
        if (charCounter < 10) {
            charCounter++;
            return jl3;
        }
        if (charCounter < 13) {
            charCounter++;
            return jl4;
        }
        if (charCounter < 16) {
            charCounter++;
            return jl5;
        }
        return jl6;
    }

    Bitmap getJumpRight() {
        if (charCounter < 4) {
            charCounter++;
            return jr1;
        }
        if (charCounter < 7) {
            charCounter++;
            return jr2;
        }
        if (charCounter < 10) {
            charCounter++;
            return jr3;
        }
        if (charCounter < 13) {
            charCounter++;
            return jr4;
        }
        if (charCounter < 16) {
            charCounter++;
            return jr5;
        }
        return jr6;
    }

    Rect getCollisionShape() {
        return new Rect(x + 3, y + 2, x + width - 3, y + height - 1);
    }
}
