package com.rikachka.task1_1;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        SystemClock.sleep(TIME_TO_SLEEP);
//        Intent intent = new Intent(this.getApplicationContext(), ScrollingActivity.class);
//        this.startActivity(intent)
        Sleeper sleeper = new Sleeper(this);
        sleeper.execute();
    }
}
