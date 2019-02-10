package com.example.chartas;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;
import android.app.Notification;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class WallPaperService extends Service {
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onCreate(){
        Toast.makeText(getApplicationContext(), "Service created!", Toast.LENGTH_LONG).show();


        final Handler h = new Handler();
        final int delay = 60000;

        checkTime();

        h.postDelayed(new Runnable(){
            public void run(){
                checkTime();
                h.postDelayed(this, delay);
            }
        }, delay);
    }

    public void onDestroy(){
        Toast.makeText(getApplicationContext(), "Service destroyed!", Toast.LENGTH_LONG).show();
        super.onDestroy();
        sendBroadcast(new Intent("YouWillNeverKillMe"));
    }

    public void checkTime(){
        Calendar time = Calendar.getInstance();
        Date now = time.getTime();
        DateFormat formatting = new SimpleDateFormat("HH");
        String format = formatting.format(now);
        int check = Integer.parseInt(format);
        if(check >= 18 || check <= 5){
            WallpaperManager wallpaperManager =
                    WallpaperManager.getInstance(getApplicationContext());
            Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.starrynight);
            try {
                wallpaperManager.setBitmap(icon);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(check >= 6 && check <= 7){
            WallpaperManager wallpaperManager =
                    WallpaperManager.getInstance(getApplicationContext());
            Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.sunrise);
            try {
                wallpaperManager.setBitmap(icon);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(check <= 8 && check <= 15){
            WallpaperManager wallpaperManager =
                    WallpaperManager.getInstance(getApplicationContext());
            Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.sunnyday);
            try {
                wallpaperManager.setBitmap(icon);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(check <= 16 && check <= 17){
            WallpaperManager wallpaperManager =
                    WallpaperManager.getInstance(getApplicationContext());
            Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.sunrise);
            try {
                wallpaperManager.setBitmap(icon);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
