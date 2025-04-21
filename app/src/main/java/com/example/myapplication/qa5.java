package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class qa5 extends AppCompatActivity {
    int hp=-1;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_qa5);
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
        Intent intent = new Intent(qa5.this, qa6.class);
        intent.putExtra("hp", hp);
        startActivity(intent);
        //錯誤答案
    }

    public void B(View view) {
        Intent intent = new Intent(qa5.this, qa6.class);
        intent.putExtra("hp", hp);
        startActivity(intent);
        //錯誤答案
    }
    public void C(View view) {
        Intent intent = new Intent(qa5.this, qa6.class);
        intent.putExtra("hp", hp);
        startActivity(intent);
        //錯誤答案
    }

    public void D(View view) {
        Intent intent = new Intent(qa5.this, qa6.class);
        if(hp>0) intent.putExtra("hp", hp-1);
        else{
            intent.putExtra("hp", 0);
        }
        startActivity(intent);
        //正確答案
    }
}