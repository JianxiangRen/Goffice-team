package com.example.officebooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CheckActivity2 extends AppCompatActivity {
    private Button start,cancel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check2);

        int distance = getIntent().getIntExtra("distance", 588);
        TextView tv=findViewById(R.id.distance_textview);
        tv.setText(distance+"m");

        cancel = findViewById(R.id.cancel);
        start = findViewById(R.id.start);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CheckActivity2.this,StartCheckActivity.class));
            }
        });
    }
}
