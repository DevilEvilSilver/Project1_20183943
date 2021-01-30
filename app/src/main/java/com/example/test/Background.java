package com.example.test;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.example.test.GameView.screenRatioX;
import static com.example.test.GameView.screenRatioY;

public class Background {

    int x = 0, y = 0;
    Bitmap background;
    Bitmap warning;
    int warningWidth, warningHeight;;

    Background  (int screenX, int screenY, Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.motion_background);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);

        warning = BitmapFactory.decodeResource(res, R.drawable.warning);
        warningWidth = (int) (warning.getWidth() * screenRatioX);
        warningHeight = (int) (warning.getHeight() * screenRatioY);
        warning = Bitmap.createScaledBitmap(warning, warningWidth, warningHeight,false);
    }
}
