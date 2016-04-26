package com.rikachka.task1_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        JSONObject jsonObject = new JSONObject();
        super.onCreate(savedInstanceState);
        try {
            jsonObject = new JSONObject(getIntent().getStringExtra("JSON_OBJECT"));

        } catch (JSONException e) {
            Log.e("Exception", "SecondActivity: " + e.toString());
        }
        setContentView(R.layout.activity_second);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, jsonObject);
        recyclerView.setAdapter(adapter);
    }
}
