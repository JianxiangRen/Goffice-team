package com.example.officebooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.officebooking.adapter.ClassAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActivityManageBooking extends AppCompatActivity {
    private Button bookingBack,add;
    private RecyclerView re;
    private EditText edt_code;
    private ClassAdapter adapter;
    private List<String> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_manage);
        list = new ArrayList<>();
        edt_code = findViewById(R.id.edt_code);
        re = findViewById(R.id.statusList);
        add = findViewById(R.id.add);
        bookingBack = findViewById(R.id.bookingBack);
        adapter = new ClassAdapter(R.layout.class_item);
        re.setLayoutManager(new LinearLayoutManager(this));
        re.setAdapter(adapter);
        bookingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_code.getText().toString().equals("CS101")){
                    list.add("CS101 - Intro To Programming");
                }
                else if(edt_code.getText().toString().equals("CS102")){
                    list.add("CS102 - Intro To Programming");
                }
                else if(edt_code.getText().toString().equals("CS103")){
                    list.add("CS103 - Intro To Programming");
                }
                else if(edt_code.getText().toString().equals("CS104")){
                    list.add("CS104 - Intro To Programming");
                }
                else if(edt_code.getText().toString().equals("CS105")){
                    list.add("CS105 - Intro To Programming");
                }

                adapter.setNewData(list);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(ActivityManageBooking.this,AddClassActivity.class));
            }
        });
    }
}
