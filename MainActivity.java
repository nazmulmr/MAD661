package com.example.ch04_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Number of seconds displayed on the stopwatch.
    TextView tvLifecycle;
    String lifecycle = "";
    private int seconds = 0;
   // private int mseconds = 0;
   private static final String HOME_ACTIVITY_TAG = MainActivity.class.getSimpleName();
    //Is the stopwatch running?
    private boolean running;
    private boolean wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // lifecycle = lifecycle + "onCreate\n";
       // tvLifecycle.setText(lifecycle);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();

    }

    public void onClickStart(View view) {
        running = true;
        Toast.makeText(MainActivity.this, "You Clicked on Start Button..", Toast.LENGTH_SHORT).show();
        Log.d("lifecycle","onStart invoked");
    }

    public void onClickStop(View view) {
        running = false;
        Toast.makeText(MainActivity.this, "You Clicked on Stop Button..", Toast.LENGTH_SHORT).show();
       /* super.onPause();
        lifecycle = lifecycle + "onPause\n";
        tvLifecycle.setText(lifecycle);*/
        Log.d("lifecycle","onStop invoked");
    }

    public void onClickReset(View view) {
        running = false;
        Toast.makeText(MainActivity.this, "You Clicked on Reset Button..", Toast.LENGTH_SHORT).show();
        seconds = 0;
        Log.d("lifecycle","onResume invoked");
        showLog("Activity restarted");
    }

    private void showLog(String activity_restarted) {
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    //Sets the number of seconds on the timer.
    private void runTimer() {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
              //  int msecs = (seconds%1000);
                String time = String.format(" %02d:%02d %02d", hours,  minutes, secs);
                timeView.setText(time);
                timeView.setTextSize(20);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1);
            }
        });
    }

}