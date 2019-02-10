package com.example.chartas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    static Boolean WeatherVsTime = false; //false = time, true = weather

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, WallPaperService.class));

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

    public static Boolean getSetting(){
        return WeatherVsTime;
    }

}
