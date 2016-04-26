package com.rikachka.task1_1;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class LoaderModel {

    public JSONObject loadJson() {
        JSONObject json = new JSONObject();
        URL url;
        try {
            url = new URL("http://mobevo.ext.terrhq.ru/shr/j/ru/technology.js");
        } catch (MalformedURLException e) {
            Log.e("Exception", "LoaderModel: " + e.toString());
            return json;
        }
        HttpURLConnection urlConnection;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            Log.e("Exception", "LoaderModel: " + e.toString());
            return json;
        }
        try {
            urlConnection.setChunkedStreamingMode(0);
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                return readStream(in);
            } catch (IOException| JSONException e) {
                Log.e("Exception", "LoaderModel: " + e.toString());
                return json;
            }
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


    public JSONObject readStream(InputStream in) throws IOException, JSONException {
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            if (in != null) {
                in.close();
            }
        }

    }
}
