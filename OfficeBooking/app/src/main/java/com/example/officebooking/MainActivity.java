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
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
    private MarkDownAdapter md;
//    public static String downloadFile(Context context, String fileName) {
//        String baseUrl = "https://file.goffice.fun/d/opt/Goffice/MD_File/";
//        // Use getExternalFilesDir to get a writable directory
//        File targetDirectory = new File(context.getExternalFilesDir(null), "md-file");
//
//        try {
//            URL fileUrl = new URL(baseUrl + fileName + ".md");
//            File targetFile = new File(targetDirectory, fileName + ".md");
//            targetDirectory.mkdirs(); // Make sure the directory exists
//            try (BufferedInputStream in = new BufferedInputStream(fileUrl.openStream());
//                 FileOutputStream fileOutputStream = new FileOutputStream(targetFile)) {
//                // Your code to write to the file
//            }
//            return targetFile.getAbsolutePath().toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Error downloading file";
//        }
//    }

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

        md = new MarkDownAdapter(this);
//        String path = downloadFile(this,"text");
//        String mdstring = md.readMarkdownFromUrl("https://file.goffice.fun/opt/Goffice/MD_File/activity_booking.md");

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
