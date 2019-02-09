package com.example.chartas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.WallpaperManager;
import android.view.View;
import android.widget.Toast;
import java.io.IOException;
import java.util.Calendar


public class MainActivity extends AppCompatActivity {

    private WallpaperManager wallpaperManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wallpaperManager = WallpaperManager.getInstance(this);
    }

    public void changeWall(View view) {
        try {
        switch (view.getId()){
            case R.id.clockWallpaper:
                Bitmap wall = BitmapFactory.decodeResource(getResources(), R.drawable.wallpaper1);
                wallpaperManager.setBitmap(wall);
                Toast.makeText(this,"clock Wallpaper", Toast.LENGTH_SHORT).show();
                break;
        }

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void
}
