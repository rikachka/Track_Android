package com.rikachka.task1_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class SliderFragmentActivity extends FragmentActivity {
    private ViewPager mPager;
    private JSONObject jsonObject;
    private PagerAdapter mPagerAdapter;
    private Integer itemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jsonObject = new JSONObject();

        try {
            jsonObject = new JSONObject(getIntent().getStringExtra("JSON_OBJECT"));
        } catch (JSONException e) {
            Log.e("Exception", "SecondActivity: " + e.toString());
        }
        itemCount = jsonObject.length();
        setContentView(R.layout.view_pager);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new SliderFragmentStatePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }
    private class SliderFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
        public SliderFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            try {
                JSONObject jsonObjectElement = jsonObject.getJSONObject(jsonObject.names().getString(position));
                String fragmentImageUrl = jsonObjectElement.getString("picture");
                String fragmentTitle = jsonObjectElement.getString("title");
                String fragmentInfo = "";
                if (jsonObjectElement.has("info")) {
                    fragmentInfo = jsonObjectElement.getString("info");
                }
                return PageFragment.init(fragmentImageUrl, fragmentTitle, fragmentInfo);
            } catch (JSONException e) {
                Log.e("Exception", "RecyclerViewAdapter: " + e.toString());
            }
            return null;
        }

        @Override
        public int getCount() {
            return itemCount;
        }
    }
}
