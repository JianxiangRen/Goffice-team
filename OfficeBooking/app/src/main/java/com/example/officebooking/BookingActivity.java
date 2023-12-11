package com.example.officebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookingActivity extends AppCompatActivity {
private Button back,bookingNew,bookingManage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        back=findViewById(R.id.bookingBack);
        bookingNew = findViewById(R.id.bookingNew);
        bookingManage = findViewById(R.id.bookingManage);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bookingNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingActivity.this,NewBookingActivity.class));
            }
        });
        bookingManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingActivity.this,ActivityManageBooking.class));
            }
        });
    }
}