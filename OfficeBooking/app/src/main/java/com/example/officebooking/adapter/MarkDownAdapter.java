package com.example.officebooking.adapter;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MarkDownAdapter {

    private Context context;

    // Constructor to pass the context
    public MarkDownAdapter(Context context) {
        this.context = context;
    }

    // Method to read a Markdown file from a URL
    public String readMarkdownFromUrl(String urlString) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (InputStream inputStream = connection.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }

            } finally {
                connection.disconnect();
            }

        } catch (IOException e) {
            Log.e("MarkDownAdapter", "Error reading Markdown from URL", e);
            return "Error reading file: " + e.getMessage();
        }

        return content.toString();
    }
}