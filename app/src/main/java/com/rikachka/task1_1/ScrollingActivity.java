package com.rikachka.task1_1;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrollingActivity extends ListActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_IMAGE = "image";
    final String ATTRIBUTE_NAME_LAYOUT = "layout";

    final int IMAGE = R.mipmap.ic_launcher;

    final int MIN_NUMBER = 1;
    final int MAX_NUMBER = 1000;
    private NumbersToTextClass numbersToTextClass = new NumbersToTextClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Map<String, Object>> data = new ArrayList<>(MAX_NUMBER);
        Map<String, Object> m;
        int img = 0;
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, i);
            m.put(ATTRIBUTE_NAME_IMAGE, IMAGE);
            m.put(ATTRIBUTE_NAME_LAYOUT, i);
            data.add(m);
        }

        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE, ATTRIBUTE_NAME_LAYOUT};
        int[] to = { R.id.number_text, R.id.list_image, R.id.list_item };

        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        sAdapter.setViewBinder(new MyViewBinder());
        setListAdapter(sAdapter);
    }

    class MyViewBinder implements SimpleAdapter.ViewBinder {

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            int i = 0;
            switch (view.getId()) {
                case R.id.list_item:
                    i = ((Integer) data).intValue();
                    if (i % 2 == 0) {
                        view.setBackgroundColor(Color.GRAY);
                    } else {
                        view.setBackgroundColor(Color.WHITE);
                    }
                    return true;
                case R.id.number_text:
                    i = ((Integer) data).intValue();
                    String text = numbersToTextClass.toText(i);
                    ((TextView)view).setText(text);
                    return true;
            }
            return false;
        }
    }
}
