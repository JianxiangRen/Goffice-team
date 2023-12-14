package com.example.officebooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.officebooking.adapter.BookAdapter;
import com.example.officebooking.adapter.MarkDownAdapter;
import com.example.officebooking.bean.BookBean;

import java.util.ArrayList;
import java.util.List;

public class NewBookingActivity extends AppCompatActivity {
    private BookAdapter adapter;
    private RecyclerView re;
    private Button go_back, see_history;
    private MarkDownAdapter md;

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

        md = new MarkDownAdapter(this);

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        see_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewBookingActivity.this, ActivityBookHistory.class));
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
                String url = "https://file.goffice.fun/d/opt/Goffice/MD_File/activity_new_booking.md"; // Replace with your Markdown file URL
                String markdownContent = md.readMarkdownFromUrl(url);

                // Since you cannot update the UI from a background thread, use runOnUiThread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Update your UI here with the markdownContent

                        List<BookBean> list = new ArrayList<>();
                        list.add(new BookBean(markdownContent,"",
                                "","",
                                "",""));
                        adapter.setNewData(list);

                    }
                });
            }
        }).start();
    }
}
