package com.example.myapplication;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class game4iplay extends AppCompatActivity {
    private ImageView pineapple;
    private FrameLayout gameArea;
    private TextView scoreText;
    private int screenWidth;
    private int pineappleX;
    private int score = 0;
    private int missedDrops=0;
    private boolean isGameRunning = true;
    private Handler handler = new Handler();
    private Random random = new Random();
    private void endGame(){
        isGameRunning=false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4iplay);
        //skip
        Intent intent = new Intent(game4iplay.this, game4win.class);
        startActivity(intent);

        pineapple = findViewById(R.id.pineapple);
        gameArea = findViewById(R.id.game_area);
        scoreText = findViewById(R.id.score);

        gameArea.post(() -> screenWidth = gameArea.getWidth());

        startDroppingWater();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int newX = (int) event.getX() - (pineapple.getWidth() / 2);
            if (newX >= 0 && newX + pineapple.getWidth() <= screenWidth) {
                pineapple.setX(newX);
                pineappleX = newX;
            }
        }
        return true;
    }

    private void startDroppingWater() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isGameRunning) {
                    handler.postDelayed(this, 3000);
                    dropWater();
                }
            }
        }, 1000);
    }

    private void dropWater() {
        ImageView waterDrop = new ImageView(this);
        waterDrop.setImageResource(R.drawable.water_drop);
        int startX = random.nextInt(screenWidth - 100);
        waterDrop.setX(startX);
        waterDrop.setY(0);
        waterDrop.setLayoutParams(new FrameLayout.LayoutParams(100, 100));

        gameArea.addView(waterDrop);

        new Thread(() -> {
            while (waterDrop.getY() < gameArea.getHeight() && isGameRunning) {
                runOnUiThread(() -> waterDrop.setY(waterDrop.getY() + 15));

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (checkCollision(waterDrop)) {
                    runOnUiThread(() -> {
                        gameArea.removeView(waterDrop);
                        score++;
                        scoreText.setText("已接取水滴: " + score+"/10");
                        if(score==10){
                            Toast.makeText(getApplicationContext(), "過關!", Toast.LENGTH_SHORT).show();
                            //這裡跳轉
                            //這裡跳轉
                            //這裡跳轉
                            Intent intent = new Intent(game4iplay.this, game4win.class);
                            startActivity(intent);
                            //這裡跳轉
                            //這裡跳轉
                            //這裡跳轉
                        }
                    });
                    return;
                }
            }
            runOnUiThread(() -> {
                gameArea.removeView(waterDrop);
                // 這裡可以減分或增加 "未接住計數"
                scoreText.setText("已接取水滴: " + score+"/10");
//                missedDrops++;
                if (missedDrops >= 3) {
                    endGame(); // 遊戲結束
                }
            });
//            runOnUiThread(() -> gameArea.removeView(waterDrop));
        }).start();
    }

    private boolean checkCollision(ImageView waterDrop) {
        Rect pineappleRect = new Rect(
                (int) pineapple.getX(),
                (int) pineapple.getY(),
                (int) pineapple.getX() + pineapple.getWidth(),
                (int) pineapple.getY() + pineapple.getHeight()
        );

        Rect waterRect = new Rect(
                (int) waterDrop.getX(),
                (int) waterDrop.getY(),
                (int) waterDrop.getX() + waterDrop.getWidth(),
                (int) waterDrop.getY() + waterDrop.getHeight()
        );

        return Rect.intersects(pineappleRect, waterRect);
    }
}
