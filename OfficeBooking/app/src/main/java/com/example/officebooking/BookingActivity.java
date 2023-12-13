package com.example.officebooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.officebooking.adapter.BookAdapter;
import com.example.officebooking.bean.BookBean;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {
    private Button back,bookingNew,bookingManage;
    private BookAdapter adapter;
    private RecyclerView re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        back=findViewById(R.id.bookingBack);
        bookingNew = findViewById(R.id.bookingNew);
        bookingManage = findViewById(R.id.bookingManage);

        re = findViewById(R.id.statusList);
        adapter = new BookAdapter(R.layout.adapter_book);
        re.setLayoutManager(new LinearLayoutManager(this));
        re.setAdapter(adapter);

        List<BookBean> list = new ArrayList<>();
        list.add(new BookBean("Prof. John Smith","·  Course: CS101 - Introduction to \nProgramming",
                "·   Date: 2023-10-16","·  Time: 2:00 PM -2:30 PM",
                "·  Location: Room 204, Science \nBuilding","·  Purpose: Discuss CS101 \n assignment"));
        list.add(new BookBean("Dr. Sarah Johnson","·  Course: CS301- Database \n Management",
                "·   Date: 2023-10-17","·  Time: 11:30 AM12:00 PM",
                "·  Location: Room 302, Computer \n Science Department",
                "·  Purpose: Seek clarification on \n research project"));
        list.add(new BookBean("Ms. Emily Davis","·  Course: BA201-Marketing \n Strategies",
                "·   Date:2023-10-18","·  Time: 3:00 PM3:30 PM","·  Location: Office 105, Business \n School",
                "·  Purpose: Review resume for \n internship application"));
        adapter.setNewData(list);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bookingNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingActivity.this,ActivityManageBooking.class));
            }
        });
        bookingManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingActivity.this,NewBookingActivity.class));
            }
        });
    }
}
