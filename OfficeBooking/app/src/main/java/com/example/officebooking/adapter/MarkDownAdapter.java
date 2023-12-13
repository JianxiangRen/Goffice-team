package com.example.officebooking.adapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import android.content.Context;
import android.content.res.AssetManager;

public class MarkDownAdapter {

    private Context context;

    // Constructor to pass the context
    public MarkDownAdapter(Context context) {
        this.context = context;
    }

    // Method to read a Markdown file from the assets folder
    public String readMarkdownFile(String filename) {
        StringBuilder content = new StringBuilder();
        AssetManager assetManager = context.getAssets();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(assetManager.open(filename)))) {
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file";
        }

        return content.toString();
    }
}
