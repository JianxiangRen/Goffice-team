package com.example.officebooking;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.officebooking.adapter.MarkDownAdapter;
import com.example.officebooking.bean.BookBean;

import java.util.ArrayList;
import java.util.List;

public class ActivityHistoryInfo extends AppCompatActivity {
    private Button bookingBack;
    private MarkDownAdapter md;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        md = new MarkDownAdapter(this);
        setContentView(R.layout.activity_booking_history_info);
        bookingBack = findViewById(R.id.bookingBack);
        bookingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
                String url = "https://file.goffice.fun/d/opt/Goffice/MD_File/activity_booking_history_info.md"; // Replace with your Markdown file URL
                String markdownContent = md.readMarkdownFromUrl(url);

                // Since you cannot update the UI from a background thread, use runOnUiThread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Update your UI here with the markdownContent

                        TextView textView = findViewById(R.id.bookingHistory);
                        textView.setText(markdownContent);

                    }
                });
            }
        }).start();
    }
}
