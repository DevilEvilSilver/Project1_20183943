package com.example.test;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.test.GameView.screenRatioX;
import static com.example.test.GameView.screenRatioY;

public class Platform {
    public int speed = (int) (3 * screenRatioX);
    int x, y, width, height;
    Bitmap platform;

    Platform (Resources resources) {
        platform = BitmapFactory.decodeResource(resources, R.drawable.snowland_platform);

        width = platform.getWidth();
        height = platform.getHeight();
        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        platform = Bitmap.createScaledBitmap(platform, width, height, false);
    }

    Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }
}
