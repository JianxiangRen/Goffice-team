package com.example.officebooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.officebooking.adapter.BookAdapter;
import com.example.officebooking.bean.BookBean;

import java.util.ArrayList;
import java.util.List;

public class NewBookingActivity extends AppCompatActivity {
    private BookAdapter adapter;
    private RecyclerView re;
    private Button go_back,see_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_booking);
        go_back = findViewById(R.id.go_back);
        see_history = findViewById(R.id.see_history);
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

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        see_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewBookingActivity.this,ActivityBookHistory.class));
            }
        });

    }
}
