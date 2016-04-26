package com.rikachka.task1_1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataViewHolder> {
    public static final String IMAGE_URL_BEGIN = "http://mobevo.ext.terrhq.ru/";
    private static final String ROW_NAME = "technology";
    JSONObject jsonObject;
    Activity callingActivity;
    int itemCount;

    public RecyclerViewAdapter(Activity callingActivity, JSONObject jsonObject) {
        this.callingActivity = callingActivity;
        this.jsonObject = new JSONObject();
        try {
            this.jsonObject = jsonObject.getJSONObject(ROW_NAME);
        } catch (JSONException e) {
            Log.e("Exception", "RecyclerViewAdapter: " + e.toString());
        }
        itemCount = this.jsonObject.length();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public interface OnItemClickListener {
            void onItemClick(View view);
        }
        private OnItemClickListener listener;
        RelativeLayout rowLayout;
        TextView rowText;
        ImageView rowImage;

        DataViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            rowLayout = (RelativeLayout) itemView.findViewById(R.id.row_layout);
            rowText = (TextView) itemView.findViewById(R.id.row_text);
            rowImage = (ImageView) itemView.findViewById(R.id.row_image);
            rowImage.setOnClickListener(this);
            rowText.setOnClickListener(this);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        DataViewHolder(View itemView) {
            super(itemView);
            rowLayout = (RelativeLayout) itemView.findViewById(R.id.row_layout);
            rowText = (TextView) itemView.findViewById(R.id.row_text);
            rowImage = (ImageView) itemView.findViewById(R.id.row_image);
            rowImage.setOnClickListener(this);
            rowText.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(view);
        }
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);
        final DataViewHolder dataViewHolder = new DataViewHolder(view);
        dataViewHolder.setOnItemClickListener(new RecyclerViewAdapter.DataViewHolder.OnItemClickListener() {
            public void onItemClick(View view) {
                Intent intent = new Intent(callingActivity.getApplicationContext(), SliderFragmentActivity.class);
                intent.putExtra("JSON_OBJECT", jsonObject.toString());
                intent.putExtra("CURRENT_ITEM", dataViewHolder.getAdapterPosition());
                callingActivity.startActivity(intent);
                callingActivity.finish();
            }
        });
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(DataViewHolder viewHolder, int position) {
        if (position % 2 == 0) {
            viewHolder.rowLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            viewHolder.rowLayout.setBackgroundColor(Color.parseColor("#aaaaaa"));
        }

        try {
            JSONObject jsonObjectElement = jsonObject.getJSONObject(jsonObject.names().getString(position));
            new ImageLoadingTask(viewHolder.rowImage).execute(IMAGE_URL_BEGIN + jsonObjectElement.getString("picture"));
            viewHolder.rowText.setText(jsonObjectElement.getString("title"));
        } catch (JSONException e) {
            Log.e("Exception", "RecyclerViewAdapter: " + e.toString());
        }
    }
}
