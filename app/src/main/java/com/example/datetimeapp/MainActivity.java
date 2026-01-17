package com.example.datetimeapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView dateText;
    private TextView timeText;
    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Runnable ticker = new Runnable() {
        @Override
        public void run() {
            updateDateTime();
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateText = findViewById(R.id.dateText);
        timeText = findViewById(R.id.timeText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(ticker);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(ticker);
    }

    private void updateDateTime() {
        Date now = new Date();
        String date = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(now);
        String time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(now);
        dateText.setText(date);
        timeText.setText(time);
    }
}
