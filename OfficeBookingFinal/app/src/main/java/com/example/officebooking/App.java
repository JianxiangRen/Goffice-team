package com.example.officebooking;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    public  List<String> list;
    @Override
    public void onCreate() {
        super.onCreate();
        list = new ArrayList<String>();
    }
}
