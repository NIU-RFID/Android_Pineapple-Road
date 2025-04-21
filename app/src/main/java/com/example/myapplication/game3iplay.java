package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.Toast;

public class game3iplay extends AppCompatActivity {
    private int[][] matrix = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,3},
            {1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1},
            {1,0,0,0,0,0,1,0,1,0,0,0,1,0,1,0,1},
            {1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,0,1},
            {1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1},
            {1,1,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1},
            {1,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,1},
            {1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1},
            {1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,1},
            {1,0,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1},
            {1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,1},
            {1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1},
            {2,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    private int width_unit = 0;
    private int height_unit=0;
    private int maze_x=0;
    private int maze_y=0;
    private int maze_width=0;
    private int maze_height=0;
    private int cod_x=0;
    private int cod_y=0;

    //    private int[] start_point = {16,0};
//    private int[] end_point = {1,6};
    private boolean check(int x,int y){
        return x >= 0 && x < 17 && y >= 0 && y < 17 && matrix[x][y]!=1;
    }
    private void move(int x,int y){
        ImageView black_block = findViewById(R.id.black_block);
        cod_x=x;
        cod_y=y;
        ObjectAnimator animX = ObjectAnimator.ofFloat(black_block, "x", (maze_x+(float)maze_width/17*cod_y));
        ObjectAnimator animY = ObjectAnimator.ofFloat(black_block, "y", (maze_y+(float)maze_height/17*cod_x));
        animX.setDuration(150);
        animY.setDuration(150);
        animX.start();
        animY.start();
        if(matrix[x][y]==3){
            Toast.makeText(getApplicationContext(), "過關!", Toast.LENGTH_SHORT).show();
            //這裡跳轉
            //這裡跳轉
            //這裡跳轉
            Intent intent = new Intent(game3iplay.this, game3win.class);
            startActivity(intent);
            //這裡跳轉
            //這裡跳轉
            //這裡跳轉
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3iplay);
        //skip
        Intent intent = new Intent(game3iplay.this, game3win.class);
        startActivity(intent);
        ImageView imageView = findViewById(R.id.maze_obj);

        imageView.post(() -> {
            // 取得 ImageView 的位置 (相對於父容器)
            int[] location = new int[2];
            imageView.getLocationOnScreen(location);  // 或使用 getLocationInWindow(location)
            maze_x = location[0];
            maze_y = location[1];

            // 取得 ImageView 的寬度和高度
            maze_width = imageView.getWidth();
            maze_height = imageView.getHeight();

            // 輸出座標與尺寸
            Log.d("ImageViewInfo", "X: " + maze_x + ", Y: " + maze_y + ", Width: " + maze_width + ", Height: " + maze_height);
            ObjectAnimator animX = ObjectAnimator.ofFloat(imageView, "x", maze_x);
            ObjectAnimator animY = ObjectAnimator.ofFloat(imageView, "y", maze_y);
            animX.setDuration(0);
            animY.setDuration(0);
            animX.start();
            animY.start();

            ImageView black_block = findViewById(R.id.black_block);
            ViewGroup.LayoutParams params = black_block.getLayoutParams();
            width_unit=maze_width/17;
            height_unit=maze_height/17;
            params.width = width_unit;
            params.height = height_unit;
            black_block.setLayoutParams(params);
            cod_x=15;
            cod_y=0;
            animX = ObjectAnimator.ofFloat(black_block, "x", (maze_x+(float)maze_width/17*cod_y));
            animY = ObjectAnimator.ofFloat(black_block, "y", (maze_y+(float)maze_height/17*cod_x));
            animX.setDuration(0);
            animY.setDuration(0);
            animX.start();
            animY.start();
        });

    }

    public void left_move(View view) {
        int x = cod_x,y=cod_y-1;
        if(check(x,y)) move(x,y);
    }

    public void up_move(View view) {
        int x = cod_x-1,y=cod_y;
        if(check(x,y)) move(x,y);
    }

    public void down_move(View view) {
        int x = cod_x+1,y=cod_y;
        if(check(x,y)) move(x,y);
    }

    public void right_move(View view) {
        int x = cod_x,y=cod_y+1;
        if(check(x,y)) move(x,y);
    }
}