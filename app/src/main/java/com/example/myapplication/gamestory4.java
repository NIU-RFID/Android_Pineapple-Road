package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class gamestory4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gamestory4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void skip(View view) {
        Intent intent = new Intent(gamestory4.this, gameinsturction.class);
        startActivity(intent);
    }

    public void next(View view) {
        Intent intent = new Intent(gamestory4.this, gamestory5.class);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(gamestory4.this, gamestory3.class);
        startActivity(intent);
    }
}