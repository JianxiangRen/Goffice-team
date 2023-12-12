package com.example.officebooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.officebooking.adapter.BookHistoryAdapter;
import com.example.officebooking.bean.BookBean;

import java.util.ArrayList;
import java.util.List;

public class ActivityBookHistory extends AppCompatActivity {
    private BookHistoryAdapter adapter;
    private Button bookingBack;
    private RecyclerView re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);
        re = findViewById(R.id.statusList);
        bookingBack = findViewById(R.id.bookingBack);
        bookingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        adapter = new BookHistoryAdapter(R.layout.adapter_book_history);
        re.setLayoutManager(new LinearLayoutManager(this));
        re.setAdapter(adapter);

        List<BookBean> list = new ArrayList<>();
        list.add(new BookBean("Prof. John Smith","·  Course: CS101 - Introduction to \nProgramming",
                "(2023-10-16)","·  Time: 2:00 PM -2:30 PM",
                "·  Location: Room 204, Science \nBuilding","·  Purpose: Discuss CS101 \n assignment"));
        list.add(new BookBean("Dr. Sarah Johnson","·  Course: CS301- Database \n Management",
                "(2023-10-17)","·  Time: 11:30 AM12:00 PM",
                "·  Location: Room 302, Computer \n Science Department",
                "·  Purpose: Seek clarification on \n research project"));
        list.add(new BookBean("Ms. Emily Davis","·  Course: BA201-Marketing \n Strategies",
                "(2023-10-18)","·  Time: 3:00 PM3:30 PM","·  Location: Office 105, Business \n School",
                "·  Purpose: Review resume for \n internship application"));
        adapter.setNewData(list);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(ActivityBookHistory.this,ActivityHistoryInfo.class));
            }
        });

    }
}
