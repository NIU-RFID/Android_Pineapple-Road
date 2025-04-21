package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class game1iplay extends AppCompatActivity {

    private GridLayout puzzleGrid;
    Timer timer = new Timer();

    private int rowCount = 3;
    private int columnCount = 4;
    private List<ImageView> puzzlePieces = new ArrayList<>();
    private List<Integer> puzzleOrder = new ArrayList<>();
    private List<Integer> correctOrder = new ArrayList<>();

    private Integer firstSelected = null;
    private Bitmap fullBitmap;
    TextView textView;
    int time=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1iplay);

        puzzleGrid = findViewById(R.id.puzzle_grid);
        fullBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sample);
        textView = findViewById(R.id.textView);
        puzzleGrid.setRowCount(rowCount);
        puzzleGrid.setColumnCount(columnCount);

        generatePuzzle();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }, 0, 200);
//        findViewById(R.id.shuffle_button).setOnClickListener(v -> {
//            Collections.shuffle(puzzleOrder);
//            updatePuzzleUI();
//        });
    }
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if (msg.what == 1) {
                setTitle("剩餘遊戲時間：" + (90-(time++) / 5) + " s ");
                textView.setText("剩餘遊戲時間：" + (90-(time) / 5) + " s ");

            }
            if((time/5)>=90){
                //跳轉到失敗畫面
                //跳轉到失敗畫面
                //跳轉到失敗畫面
                Toast.makeText(game1iplay.this, "失敗!", Toast.LENGTH_SHORT).show();
                //跳轉到失敗畫面
                timer.cancel();
                time=0;
                Intent intent2 = new Intent(game1iplay.this, game1fail.class);
                startActivity(intent2);
                //跳轉到失敗畫面
                //跳轉到失敗畫面
            }

        }

    };

    private void generatePuzzle() {
        puzzleGrid.removeAllViews();
        puzzlePieces.clear();
        puzzleOrder.clear();
        correctOrder.clear();  // 初始化正確順序

        int pieceWidth = fullBitmap.getWidth() / columnCount;
        int pieceHeight = fullBitmap.getHeight() / rowCount;

        for (int i = 0; i < rowCount * columnCount; i++) {
            int row = i / columnCount;
            int col = i % columnCount;

            // 切割圖片
            Bitmap pieceBitmap = Bitmap.createBitmap(fullBitmap, col * pieceWidth, row * pieceHeight, pieceWidth, pieceHeight);

            // 建立拼圖片塊
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(pieceBitmap);

            // 設定拼圖片塊大小
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = getResources().getDisplayMetrics().widthPixels / columnCount - 10;
            params.height = getResources().getDisplayMetrics().widthPixels / columnCount - 10;
            params.setMargins(2, 2, 2, 2);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            int finalI = i;
            imageView.setOnClickListener(v -> onPieceClick(finalI));

            puzzlePieces.add(imageView);
            puzzleOrder.add(i);
            correctOrder.add(i);  // 存入正確的順序
        }

        Collections.shuffle(puzzleOrder);  // 打亂拼圖
        updatePuzzleUI();
    }


    // 更新拼圖 UI（只改變位置，不重新生成拼圖塊）
    private void updatePuzzleUI() {
        puzzleGrid.removeAllViews();
        for (int i = 0; i < puzzleOrder.size(); i++) {
            int index = puzzleOrder.get(i);
            ImageView imageView = puzzlePieces.get(index);

            // 更新拼圖塊的位置
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.rowSpec = GridLayout.spec(i / columnCount);
            params.columnSpec = GridLayout.spec(i % columnCount);
            params.width = getResources().getDisplayMetrics().widthPixels / columnCount - 10;
            params.height = getResources().getDisplayMetrics().widthPixels / columnCount - 10;
            params.setMargins(2, 2, 2, 2);
            imageView.setLayoutParams(params);

            puzzleGrid.addView(imageView);
        }
    }

    // 處理拼圖塊點擊
    private void onPieceClick(int clickedIndex) {
        int firstIndex, secondIndex;

        if (firstSelected == null) {
            firstSelected = clickedIndex;
        } else {
            secondIndex = clickedIndex;
            firstIndex = firstSelected;

            int firstPos = puzzleOrder.indexOf(firstIndex);
            int secondPos = puzzleOrder.indexOf(secondIndex);

            if (isAdjacent(firstPos, secondPos)) {
                Collections.swap(puzzleOrder, firstPos, secondPos);
                updatePuzzleUI();

                if (isPuzzleSolved()) {
                    Toast.makeText(this, "拼圖完成！", Toast.LENGTH_SHORT).show();
                    // 這裡跳轉
                    // 這裡跳轉
                    // 這裡跳轉
                    timer.cancel();
                    time=0;
                    Intent intent = new Intent(game1iplay.this, game1win.class);
                    startActivity(intent);
                    // 這裡跳轉
                    // 這裡跳轉
                    // 這裡跳轉
                }
            }
            firstSelected = null;
        }
    }


    // 判斷兩個拼圖塊是否相鄰
    private boolean isAdjacent(int index1, int index2) {

        int r1 = index1 / columnCount, c1 = index1 % columnCount;
        int r2 = index2 / columnCount, c2 = index2 % columnCount;
        if(r1==r2 && c1==c2) return false;
        return true;
//        return (Math.abs(r1 - r2) == 1 && c1 == c2) || (Math.abs(c1 - c2) == 1 && r1 == r2);
    }

    // 檢查是否完成拼圖
    private boolean isPuzzleSolved() {
        return puzzleOrder.equals(correctOrder);
    }
}
