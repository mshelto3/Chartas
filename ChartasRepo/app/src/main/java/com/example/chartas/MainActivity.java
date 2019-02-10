package com.example.chartas;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.*;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static Boolean WeatherVsTime = false; //false = time, true = weather

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, WallPaperService.class));

        final Handler h = new Handler();
        final int delay = 100;

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setVisibility(View.INVISIBLE);

        h.postDelayed(new Runnable(){
            public void run(){
                if (WeatherVsTime == false) {
                    TextView textView = (TextView) findViewById(R.id.clock);
                    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                    textView.setText(currentDateTimeString);
                   h.postDelayed(this, delay);
                    textView.setVisibility(View.VISIBLE);
                    ImageView imageView = findViewById(R.id.imageView);
                    imageView.setVisibility(View.INVISIBLE);
                }
                else{
                    h.postDelayed(this, delay);
                    TextView textView = (TextView) findViewById(R.id.clock);
                    textView.setVisibility(View.INVISIBLE);
                    ImageView imageView = findViewById(R.id.imageView);
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        }, delay);


    }

    public void setting(View v){
        Button button = (Button) findViewById(R.id.time);
        WeatherVsTime = !WeatherVsTime;
        if(WeatherVsTime){
            button.setText("Weather");
        }
        else{
            button.setText("Time");
        }

    }
}
