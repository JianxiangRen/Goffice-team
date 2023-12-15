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
import com.example.officebooking.adapter.MarkDownAdapter;
import com.example.officebooking.bean.BookBean;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.net.URL;

public class ActivityBookHistory extends AppCompatActivity {

    private BookHistoryAdapter adapter;
    private Button bookingBack;
    private RecyclerView re;
    private MarkDownAdapter md;

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


        md = new MarkDownAdapter(this);
//        String path = downloadFile("text");
//        String mdstring = md.readMarkdownFromUrl("https://file.goffice.fun/opt/Goffice/MD_File/activity_booking.md");


        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(ActivityBookHistory.this,ActivityHistoryInfo.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        // Refresh the content every time the activity resumes
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "https://file.goffice.fun/d/opt/Goffice/MD_File/activity_booking_history-1.md"; // Replace with your Markdown file URL
                String markdownContent = md.readMarkdownFromUrl(url);
                String url2 = "https://file.goffice.fun/d/opt/Goffice/MD_File/activity_booking_history-2.md"; // Replace with your Markdown file URL
                String markdownContent2 = md.readMarkdownFromUrl(url2);
                // Since you cannot update the UI from a background thread, use runOnUiThread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        List<BookBean> list = new ArrayList<>();
                        list.add(new BookBean(markdownContent,"",
                                "","",
                                "",""));
                        list.add(new BookBean(markdownContent2,"",
                                "","",
                                "",""));
                        adapter.setNewData(list);

                    }
                });
            }
        }).start();
    }
}
