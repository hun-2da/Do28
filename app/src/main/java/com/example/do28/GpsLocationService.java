package com.example.do28;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GpsLocationService extends Service implements LocationListener {
    public double latitude,longitude;
    public GpsLocationService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Location location = null;
        LocationManager manager =(LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        try {
            location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }catch(SecurityException e) {
            Log.e("location", "getLastKnownLoaction오류" );
        }
        if(location!=null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
         location_widget();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void location_widget(){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.getDefault().sendTextMessage(
                "010",
                null,
                "("+latitude+","+longitude+")",
                null,
                null
        );
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}