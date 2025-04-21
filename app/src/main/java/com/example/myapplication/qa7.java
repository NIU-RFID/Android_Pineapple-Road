package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class qa7 extends AppCompatActivity {
    int hp=-1;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_qa7);
        Intent intent = getIntent();
        hp = intent.getIntExtra("hp",5);
        imageView = findViewById(R.id.imageView17);
        if(hp==5){
            imageView.setImageResource(R.drawable.hp5);
        }
        else if(hp==4){
            imageView.setImageResource(R.drawable.hp4);
        }
        else if(hp==3){
            imageView.setImageResource(R.drawable.hp3);
        }
        else if(hp==2){
            imageView.setImageResource(R.drawable.hp2);
        }
        else if(hp==1){
            imageView.setImageResource(R.drawable.hp1);
        }
        else if(hp==0){
            imageView.setImageResource(R.drawable.hp0);
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void A(View view) {
        //錯誤答案
        if(hp>0){
            lose();
        }
        else{
            win();
        }
    }

    public void B(View view) {
        //正確答案
        hp-=1;
        if(hp>0){
            lose();
        }
        else{
            win();
        }
    }
    public void C(View view) {
        //錯誤答案
        if(hp>0){
            lose();
        }
        else{
            win();
        }
    }

    public void D(View view) {
        //錯誤答案
        if(hp>0){
            lose();
        }
        else{
            win();
        }
    }
    public void win(){
        //跳轉到勝利結局
        //跳轉到勝利結局
        //跳轉到勝利結局
        Toast.makeText(this, "勝利!", Toast.LENGTH_SHORT).show();
        //跳轉到勝利結局
        //跳轉到勝利結局
        //跳轉到勝利結局
    }
    public void lose(){
        //跳轉到失敗結局
        //跳轉到失敗結局
        //跳轉到失敗結局
        Toast.makeText(this, "失敗!", Toast.LENGTH_SHORT).show();
        //跳轉到失敗結局
        //跳轉到失敗結局
        //跳轉到失敗結局
    }
//    答對數>=5
//    Intent intent = new Intent(qa7.this, endgood.class);
//    startActivity(intent);
//    答對數<5
//    Intent intent = new Intent(qa7.this, endbad.class);
//    startActivity(intent);
}