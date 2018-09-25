package com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment.temp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.Category;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.main_activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<Category> mData;
    private float mBaseElevation;

    public CardPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(Category item) {
        mViews.add(null);
        mData.add(item);
    }

    public void addCardItems(List<Category> items) {
        for(Category c : items) {
            mViews.add(null);
        }
        mData.addAll(items);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.row_categories_pager, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = view.findViewById(R.id.card_view);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(final Category item, final View view) {
        LinearLayout parentCard = view.findViewById(R.id.parent_card);
        ImageView drawableImage = view.findViewById(R.id.image);
        TextView name = view.findViewById(R.id.name);
        TextView countDescription = view.findViewById(R.id.count_description);

        parentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCorrespondingActivity(view.getContext(), item.getName());
            }
        });

        drawableImage.setImageResource(item.getDrawable());
        name.setText(item.getName());
        countDescription.setText(item.getCountDescription());
    }

    private void startCorrespondingActivity(Context context, String name) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(AppConstants.CATEGORY, name);
        context.startActivity(intent);
        ((Activity) context)
                .overridePendingTransition(
                        R.anim.slide_in_right_activity,
                        R.anim.slide_out_left_activity
                );
    }
}