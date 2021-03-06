package com.example.chartas;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class WallPaperService extends Service {

    private int set = 0;
    private int set2 = 0;

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onCreate(){
        Toast.makeText(getApplicationContext(), "Service created!", Toast.LENGTH_LONG).show();


        find_weather();


        final Handler h = new Handler();
        final int delay = 60000;

        h.postDelayed(new Runnable(){
            public void run(){
                find_weather();
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
        if((check >= 18 || check <= 5)){
            if(set != 1) {
                WallpaperManager wallpaperManager =
                        WallpaperManager.getInstance(getApplicationContext());
                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.starrynight);
                try {
                    wallpaperManager.setBitmap(icon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                set = 1;
            }
        }
        else if((check >= 6 && check <= 7)){
            if(set != 2) {
                WallpaperManager wallpaperManager =
                        WallpaperManager.getInstance(getApplicationContext());
                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.sunrise);
                try {
                    wallpaperManager.setBitmap(icon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                set = 2;
            }
        }
        else if((check >= 8 && check <= 15)){
            if(set != 3) {
                WallpaperManager wallpaperManager =
                        WallpaperManager.getInstance(getApplicationContext());
                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.sunnyday);
                try {
                    wallpaperManager.setBitmap(icon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                set = 3;
            }
        }
        else if((check >= 16 && check <= 17)){
            if(set != 4) {
                WallpaperManager wallpaperManager =
                        WallpaperManager.getInstance(getApplicationContext());
                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.sunset);
                try {
                    wallpaperManager.setBitmap(icon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                set = 4;
            }
        }
    }

    public void find_weather(){
        String url = "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=28011474bd62663987d97ebe379f687e";
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){
                try {
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    change_weather(object.getString("description"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);
    }

    public void change_weather(String des){
        if(des.contains("rain")){
            if(set2 != 1) {
                WallpaperManager wallpaperManager =
                        WallpaperManager.getInstance(getApplicationContext());
                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.rain);
                try {
                    wallpaperManager.setBitmap(icon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                set2 = 1;
            }
        }
        else if(des.contains("cloudy")){
            if(set2 != 2) {
                WallpaperManager wallpaperManager =
                        WallpaperManager.getInstance(getApplicationContext());
                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.cloudyday);
                try {
                    wallpaperManager.setBitmap(icon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                set2 = 2;
            }
        }
        else if(des.contains("snow")){
            if(set2 != 3) {
                WallpaperManager wallpaperManager =
                        WallpaperManager.getInstance(getApplicationContext());
                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.winternight);
                try {
                    wallpaperManager.setBitmap(icon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                set2 = 3;
            }
        }
        else if(des.contains("clear")){
            checkTime();
        }
    }
}

