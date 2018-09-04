package com.appbusters.robinkamboj.senseitall.view.detail_activity.android.version_pie;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.LearnMoreItem;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.DetailActivity;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.android.AndroidFragment;
import com.appbusters.robinkamboj.senseitall.view.learn_more_activity.LearnMoreAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PieFragment extends AndroidFragment implements PieInterface {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    private String[] headers, descriptions, images;
    private List<LearnMoreItem> list = new ArrayList<>();

    public PieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pie, container, false);
        setupView(v);
        return v;
    }

    @Override
    public void setupView(View v) {
        ButterKnife.bind(this, v);

        initializeData();

        setAdapter();
    }

    @Override
    public void initializeData() {
        DetailActivity activity = (DetailActivity) getActivity();
        if(activity == null) return;
        String recyclerName = activity.recyclerName;

        switch (recyclerName) {
            case AppConstants.PIE: {
                headers = getResources().getStringArray(R.array.pie_headers);
                descriptions = getResources().getStringArray(R.array.pie_descriptions);
                images = getResources().getStringArray(R.array.pie_images);
                break;
            }
        }

        for(int i=0; i<headers.length; i++) list.add(new LearnMoreItem(images[i], headers[i], descriptions[i]));
    }

    @Override
    public void setAdapter() {
        LearnMoreAdapter adapter = new LearnMoreAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}
