package com.example.officebooking;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CheckActivity extends AppCompatActivity {
    private Button cancel_button,bookingBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        bookingBack = findViewById(R.id.bookingBack);

        int distance = getIntent().getIntExtra("distance", 588);
        TextView tv=findViewById(R.id.distance_textview);
        tv.setText(distance+"m");

        bookingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
