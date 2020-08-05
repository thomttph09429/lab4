package com.poly.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.NetworkInterface;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {
    TextView tvlong, tvlat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvlong = findViewById(R.id.tvlong);
        tvlat = findViewById(R.id.tvlat);
    }

    public void gps(View view) {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // hỏi người dùng lệnh gps
        // hỏi người dùng đã cấp quyền chưa, nếu chưa thì thêm dòng để cấp quyền
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 999);
            return;
        }
        locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 100, 0, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Toast.makeText(MainActivity.this, location.getLatitude() + ":" + location.getLongitude(), Toast.LENGTH_LONG).show();

//                double lat = (int) location.getLatitude();
//                double lng = (int) location.getLongitude();
//                tvlat.setText(String.valueOf(lat));
//                tvlong.setText(String.valueOf(lng));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }
        });

    }

    public void checkinternet(View view) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo iswifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo is3g = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (is3g.isConnected()) {
            Toast.makeText(MainActivity.this, "thiết bị đã kết nối 3g", Toast.LENGTH_LONG).show();

        } else if (iswifi.isConnected()) {
            Toast.makeText(MainActivity.this, "thiết bị đã kết nối wifi", Toast.LENGTH_LONG).show();

        }
    }
}