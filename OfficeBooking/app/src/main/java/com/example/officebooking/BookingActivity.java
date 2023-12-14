package com.example.officebooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.officebooking.adapter.BookAdapter;
import com.example.officebooking.adapter.MDhelper;
import com.example.officebooking.adapter.MarkDownAdapter;
import com.example.officebooking.bean.BookBean;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.net.URL;

public class BookingActivity extends AppCompatActivity {

        public static String downloadFile(String fileName) {
            String baseUrl = "https://file.goffice.fun/d/opt/Goffice/MD_File/";
            String targetDirectory = "md-file/";

            try {
                URL fileUrl = new URL(baseUrl + fileName + ".md");
                Path targetPath = Paths.get(targetDirectory + fileName + ".md");
                Files.createDirectories(targetPath.getParent());
                Files.copy(fileUrl.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

                return targetPath.toAbsolutePath().toString();
            } catch (IOException e) {
                e.printStackTrace();
                return "Error downloading file";
            }
        }
/*
    public static void main(String[] args) {
        String localFilePath = downloadFile("text");
        System.out.println("Downloaded file path: " + localFilePath);
    }
*/
    private Button back,bookingNew,bookingManage, seemore;
    private BookAdapter adapter;
    private RecyclerView re;
    private MarkDownAdapter md;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        back=findViewById(R.id.bookingBack);
        bookingNew = findViewById(R.id.bookingNew);
        bookingManage = findViewById(R.id.bookingManage);
        seemore = findViewById(R.id.see);
        re = findViewById(R.id.statusList);
        adapter = new BookAdapter(R.layout.adapter_book);
        re.setLayoutManager(new LinearLayoutManager(this));
        re.setAdapter(adapter);

        md = new MarkDownAdapter(this);

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
        seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingActivity.this,ActivityBookHistory.class));
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
                String url = "https://file.goffice.fun/d/opt/Goffice/MD_File/activity_main.md"; // Replace with your Markdown file URL
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
