package com.example.officebooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartCheckActivity extends AppCompatActivity {
    private Button bookingBack,done;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_check);
        done = findViewById(R.id.done);
        bookingBack = findViewById(R.id.bookingBack);
        bookingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartCheckActivity.this,ActivityHistoryInfo.class));
            }
        });
    }
}
