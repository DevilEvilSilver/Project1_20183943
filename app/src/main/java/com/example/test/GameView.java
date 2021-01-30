package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying, isGameOver = false;
    private int score = 0;
    private int screenX, screenY;
    public static float screenRatioX, screenRatioY;
    private float y1, y2;
    private static final int MIN_DISTANCE = 5;
    private Random random;
    private Paint paint;
    private SharedPreferences preferences;
    private List<Platform> platforms;
    private Platform platform;
    private List<Bird> birds;
    private int platformCount = 0, birdCount = 0, lastX, lastY, rand1, rand2;
    private Character character;
    private GameActivity activity;
    private Background background1, background2;

    public GameView (GameActivity activity, int screenX, int screenY) {
        super(activity);

        this.activity = activity;

        preferences = activity.getSharedPreferences("game", Context.MODE_PRIVATE);

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1280f / screenX;
        screenRatioY = 720f / screenY;

        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());

        random = new Random();

        paint = new Paint();
        paint.setTextSize(64);
        paint.setColor(Color.WHITE);

        character = new Character(screenY, getResources());

        background2.x = screenX;

        this.platform = new Platform(getResources());
        platforms = new ArrayList<>();
        Platform[] startPlatforms = new Platform[9];
        int startPlatformX = 0;
        for (Platform platform: startPlatforms) {
            platform = new Platform(getResources());
            platform.x = startPlatformX;
            platform.y = screenY / 2 + 200;
            platforms.add(platform);
            startPlatformX += platform.width;
        }
        lastY = screenY / 2 + 200;

        birds = new ArrayList<>();
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    private void update() {
        //background update
        background1.x -= 2 * screenRatioX;
        background2.x -= 2 * screenRatioX;

        if (background1.x + background1.background.getWidth() < 0) {
            background1.x = screenX;
        }
        if (background2.x + background2.background.getWidth() < 0) {
            background2.x = screenX;
        }

        //character update
        if (character.y > screenY - character.height / 2) {
            isGameOver = true;
            return;
        }
        if (!character.isOnGround) {
            character.y += 8 * screenRatioY;
        }
        if (character.isRunLeft) {
            character.x -= 10 * screenRatioX;
        }
        if (character.isRunRight) {
            if (character.isOnGround)
                character.x += 13 * screenRatioX;
            else
                character.x += 10 * screenRatioX;
        }
        if (character.isJump > 0) {
            character.y -= 47 * screenRatioY;
            character.isJump--;
        }
        if (character.isDown) {
            character.y += 8 * screenRatioY;
        }

        //platform update
        List<Platform> trashPlatform = new ArrayList<>();
        int groundCheck = 0;

        for (Platform platform: platforms) {
            if (platform.x < -platform.width) {
                trashPlatform.add(platform);
            }

            platform.x = (int) (platform.x - (platform.speed * screenRatioX));

            if (Rect.intersects(platform.getCollisionShape(), character.getCollisionShape())) {
                if (character.y + character.height < platform.y + platform.height
                        && character.y + character.height > platform.y) {
                    character.y = platform.y - character.height + 1;
                    character.x = (int) (character.x - (platform.speed * screenRatioX) - 3);
                    character.isOnGround = true;
                    character.isDown = false;
                    groundCheck++;
                }
                else if (character.y > platform.y) {
                    character.y = platform.y + platform.height;
                }
                else if (character.x  < platform.x) {
                    character.x = platform.x - character.width;
                }
                else if (character.x > platform.x) {
                    character.x = platform.x + platform.width;
                }
            }
        }
        if (groundCheck == 0) character.isOnGround = false;

        for (Platform platform: trashPlatform) {
            platforms.remove(platform);
        }

        //bird update
        List<Bird> trashBird = new ArrayList<>();

        for (Bird bird: birds) {
            if (bird.x + bird.width < 0) {
                trashBird.add(bird);
            }

            bird.x -= bird.speed;

            if (Rect.intersects(bird.getCollisionShape(), character.getCollisionShape())) {
                isGameOver = true;
                return;
            }
        }

        for (Bird bird: trashBird) {
            birds.remove(bird);
        }

        //map update
        if (platformCount == 0) {
            this.newTerrain();

            rand1 = random.nextInt(100);

            if (rand1 < 60) {
                platformCount = this.platform.width;
            }
            if (rand1 >= 60 && rand1 < 90) {
                platformCount = this.platform.width * 2;
            }
            if (rand1 >= 90 && rand1 < 100) {
                platformCount = this.platform.width * 4;
            }
        } else {
            platformCount -= this.platform.speed;
            if (platformCount < 0)
                platformCount = 0;
        }

        if (birdCount == 0) {
            this.newBird();

            rand2 = random.nextInt(2);

            if (rand2 == 0) {
                birdCount = 500;
            }
            if (rand2 == 1) {
                birdCount = 1000;
            }
            if (rand2 == 2) {
                birdCount = 2000;
            }
        } else {
            birdCount -= 10 * screenRatioX;
            if (birdCount < 0)
                birdCount = 0;
        }

        //score update
        score++;
    }

    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            canvas.drawText(score + "", screenX / 2, 100, paint);

            canvas.drawBitmap(character.getCharacter(), character.x, character.y, paint);

            for (Platform platform: platforms) {
                canvas.drawBitmap(platform.platform, platform.x, platform.y, paint);
            }

            for (Bird bird: birds) {
                if (bird.x < screenX + bird.width) {
                    canvas.drawBitmap(bird.getBird(), bird.x, bird.y, paint);
                }
                else {
                    canvas.drawBitmap(background1.warning, screenX - background1.warningWidth, bird.y, paint);
                }

            }

            if (isGameOver) {
                isPlaying = false;
                Bitmap gameOver = BitmapFactory.decodeResource(getResources(), R.drawable.gameover);
                int gameOverWidth = (int) (gameOver.getWidth() * screenRatioX);
                int gameOverHeight = (int) (gameOver.getHeight() * screenRatioY);
                gameOver = Bitmap.createScaledBitmap(gameOver, gameOverWidth, gameOverHeight,false);

                canvas.drawBitmap(gameOver, (screenX - gameOverWidth) / 2, (screenY - gameOverHeight) / 2, paint);
                getHolder().unlockCanvasAndPost(canvas);

                saveIfHighScore();
                waitBeforeExiting();
                return;
            }

            getHolder().unlockCanvasAndPost(canvas);
        }

    }

    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y1 = event.getY();

                if (event.getX() < screenX / 2) {
                    character.isRunLeft = true;
                    character.isRunRight = false;
                    character.isLeft = true;
                    character.isRight = false;
                }
                if (event.getX() > screenX / 2) {
                    character.isRunLeft = false;
                    character.isRunRight = true;
                    character.isLeft = false;
                    character.isRight = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                y2 = event.getY();

                float valueY = y1 - y2;

                if (event.getX() < screenX / 2) {
                    character.isRunLeft = false;
                    character.isLeft = true;
                    character.isRight = false;
                }
                if (event.getX() > screenX / 2) {
                    character.isRunRight = false;
                    character.isRight = true;
                    character.isLeft = false;
                }
                if (Math.abs(valueY) > MIN_DISTANCE) {
                    if (y2 > y1 & character.isOnGround == false) {
                        character.isDown = true;
                    }
                    else if (y2 < y1 & character.isOnGround == true) {
                        character.isJump = 9;
                        character.isOnGround = false;
                        character.charCounter = 1;
                    }
                }
                break;
        }
        return true;
    }

    public void newTerrain() {
            Platform platform = new Platform(getResources());
            platform.x = screenX;
            if (rand1 < 60) {
                rand1 = random.nextInt(250);
                if (rand1 < 100)
                    rand1 = 0;
                int tmp = random.nextInt(10);
                if (tmp > 5)
                    platform.y = lastY + rand1;
                else
                    platform.y = lastY - rand1;
                if (platform.y > screenY - platform.height - 25)
                    platform.y = screenY - platform.height -25;
                if (platform.y < character.height + 50)
                    platform.y = character.height + 50;
            }
            if (rand1 >= 60 && rand1 < 90) {
                rand1 = random.nextInt(200);
                if (rand1 < 100)
                    rand1 = 0;
                int tmp = random.nextInt(10);
                if (tmp > 5)
                    platform.y = lastY + rand1;
                else
                    platform.y = lastY - rand1;
                if (platform.y > screenY - platform.height - 25)
                    platform.y = screenY - platform.height -25;
                if (platform.y < character.height + 50)
                    platform.y = character.height + 50;
            }
            if (rand1 >= 90 && rand1 <= 100) {
                rand1 = random.nextInt(170);
                if (rand1 < 100)
                    rand1 = 0;
                int tmp = random.nextInt(10);
                if (tmp > 5)
                    platform.y = lastY + rand1;
                else
                    platform.y = lastY - rand1;
                if (platform.y > screenY - platform.height - 25)
                    platform.y = screenY - platform.height -25;
                if (platform.y < character.height + 50)
                    platform.y = character.height + 50;
            }

            platforms.add(platform);

            lastX = platform.x;
            lastY = platform.y;
    }

    public void newBird() {
        Bird bird = new Bird(getResources());
        int bound = (int) (20 * screenRatioX);
        bird.speed = random.nextInt(bound);
        if (bird.speed < 10 * screenRatioX) {
            bird.speed = (int) (10 * screenRatioX);
        }

        bird.x = screenX + bird.speed * 30;
        bird.y = random.nextInt(screenY - bird.height - 150) + 100;

        birds.add(bird);
    }

    private void saveIfHighScore() {
        if(preferences.getInt("highscore", 0) < score) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("highscore", score);
            editor.apply();
        }
    }

    private void waitBeforeExiting() {
        try {
            Thread.sleep(3000);
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
