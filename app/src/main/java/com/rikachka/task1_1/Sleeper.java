package com.rikachka.task1_1;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by BulatGaliev on 23.03.16.
 */
public class Sleeper extends AsyncTask<Void, Void, Void> {
    private static final int TIME_TO_SLEEP = 2000;
    private static final Class NEXT_ACTIVITY = ScrollingActivity.class;

    private Activity sleepingActivity;

    public Sleeper(Activity sleepingActivity) {
        this.sleepingActivity = sleepingActivity;
    }

    @Override
    public void onPostExecute(Void result) {
        super.onPostExecute(result);
        Intent intent = new Intent(sleepingActivity.getApplicationContext(), NEXT_ACTIVITY);
        sleepingActivity.startActivity(intent);
        sleepingActivity.finish();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Thread.sleep(TIME_TO_SLEEP);
        } catch (Exception e) {
        }
        return null;
    }
}
