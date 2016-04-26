package com.rikachka.task1_1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageLoadingTask extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;

    ImageLoadingTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String urlString = params[0];
        Bitmap bitmap = null;
        URL url;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e("Exception", "LoaderModel: " + e.toString());
            return bitmap;
        }
        HttpURLConnection urlConnection;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            Log.e("Exception", "LoaderModel: " + e.toString());
            return bitmap;
        }
        try {
            urlConnection.setChunkedStreamingMode(0);
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                return readStream(in);
            } catch (IOException| JSONException e) {
                Log.e("Exception", "LoaderModel: " + e.toString());
                return bitmap;
            }
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    private Bitmap readStream(InputStream in) throws IOException, JSONException {
        try {
            return BitmapFactory.decodeStream(in);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
