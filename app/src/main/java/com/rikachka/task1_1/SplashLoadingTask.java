package com.rikachka.task1_1;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONObject;

public class SplashLoadingTask extends AsyncTask<Void, Void, Void> {
    private Activity splashLoadingActivity;
    JSONObject jsonObject;

    public SplashLoadingTask(Activity splashLoadingActivity) {
        this.splashLoadingActivity = splashLoadingActivity;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        Intent intent = new Intent(splashLoadingActivity.getApplicationContext(), SecondActivity.class);
        intent.putExtra("JSON_OBJECT", jsonObject.toString());
        splashLoadingActivity.startActivity(intent);
        splashLoadingActivity.finish();
    }

    @Override
    protected Void doInBackground(Void... params) {
        LoaderModel loaderModel = new LoaderModel();
        jsonObject = loaderModel.loadJson();
        return null;
    }
}
