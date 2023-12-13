package com.example.officebooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.officebooking.adapter.BookAdapter;
import com.example.officebooking.adapter.MarkDownAdapter;
import com.example.officebooking.bean.BookBean;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button booking,check,account,seemore;
    private BookAdapter adapter;
    private RecyclerView re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        booking=findViewById(R.id.mainBooking);
        check=findViewById(R.id.mainCheck);
        account=findViewById(R.id.mainAccount);
        seemore = findViewById(R.id.see);

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

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,BookingActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AccountActivity.class);
                startActivity(intent);
            }
        });
        seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ActivityBookHistory.class);
                startActivity(intent);
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Just a second, please.",Toast.LENGTH_SHORT).show();
                if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                } else {

                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

                    if (!isGPSEnabled) {

                    } else {
                        getCurrentLocationAndCalculateDistance();
                    }
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            check.performClick();
        }
    }
    private void getCurrentLocationAndCalculateDistance() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                float[] results = new float[1];
                Location.distanceBetween(location.getLatitude(), location.getLongitude(), 43.07149805883468, -89.40677319207389, results);
                float distanceInMeters = results[0];
                locationManager.removeUpdates(this);
                int distanceInMetersInt = Math.round(distanceInMeters);

                Intent intent;
                if (distanceInMeters > 500) {
                    intent = new Intent(MainActivity.this, CheckActivity.class);
                } else {
                    intent = new Intent(MainActivity.this, CheckActivity2.class);
                }
                intent.putExtra("distance", distanceInMetersInt);
                startActivity(intent);;
            }
        };

        try {
            locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener, null);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

}
