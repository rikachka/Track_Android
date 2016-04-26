package com.rikachka.task1_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PageFragment extends Fragment {
    public static final String ARGUMENT_IMAGE = "arg_image";
    public static final String ARGUMENT_TITLE = "arg_title";
    public static final String ARGUMENT_INFO = "arg_info";
    private String image;
    private String title;
    private String info;
    static PageFragment init(String fragmentImageUrl, String fragmentTitle, String fragmentInfo) {
        PageFragment pageFragment = new PageFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_IMAGE, fragmentImageUrl);
        args.putString(ARGUMENT_TITLE, fragmentTitle);
        args.putString(ARGUMENT_INFO, fragmentInfo);
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        image = getArguments() != null ? getArguments().getString(ARGUMENT_IMAGE) : "";
        title = getArguments() != null ? getArguments().getString(ARGUMENT_TITLE) : "";
        info = getArguments() != null ? getArguments().getString(ARGUMENT_INFO) : "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment, container, false);
        ImageView fragmentImage = (ImageView) rootView.findViewById(R.id.fragmentImage);
        TextView fragmentTitle = (TextView) rootView.findViewById(R.id.fragmentTitle);
        TextView fragmentInfo = (TextView) rootView.findViewById(R.id.fragmentInfo);
        fragmentTitle.setText(title);
        fragmentInfo.setText(info);
        new ImageLoadingTask(fragmentImage).execute(RecyclerViewAdapter.IMAGE_URL_BEGIN + image);
        return rootView;
    }
}
