package com.appbusters.robinkamboj.senseitall.ui.dashboard_activity.discover_fragment.adapter.popular_tests;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.Category;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.ui.helper_classes.CheckIfPresent;
import com.appbusters.robinkamboj.senseitall.ui.test_activity.TestActivity;
import com.appbusters.robinkamboj.senseitall.ui.test_activity.helper.IsPresentHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BATTERY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.COMPASS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MULTI_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SCREEN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOUND;

public class PopTestsAdapter extends RecyclerView.Adapter<PopTestsAdapter.DashboardHolder>
    implements PopTestsInterface {

    private List<Category> list;
    private Context context;
    private IsPresentHelper isPresent;
    private CheckIfPresent checkIfPresent;

    public PopTestsAdapter(List<Category> list, Context context) {
        this.list = list;
        this.context = context;
        isPresent = new IsPresentHelper(context);
        checkIfPresent = new CheckIfPresent(context);
    }

    @NonNull
    @Override
    public DashboardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new DashboardHolder(
                LayoutInflater.from(context)
                        .inflate(
                                R.layout.row_popular_tests,
                                parent,
                                false
                        )
        );
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public void onBindViewHolder(@NonNull DashboardHolder holder, int position) {
        holder.name.setText(getName(list.get(position).getName()));
        holder.drawableImage.setImageDrawable(ContextCompat.getDrawable(context, list.get(position).getDrawable()));

        final int pos = position;

        holder.parentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkIfPresent.returnPresence(list.get(pos).getName())) {
                    startTest(list.get(pos), context);
                }
            }
        });
    }

    private void startTest(Category category, Context context) {
        GenericData intentData =
                new GenericData(
                        category.getName(),
                        category.getDrawable(),
                        isPresent.isPresent(category.getName()),
                        category.getType()
                );

        Bundle args = new Bundle();

        args.putString(AppConstants.DATA_NAME, intentData.getName());
        args.putString(AppConstants.RECYCLER_NAME, intentData.getName());
        args.putInt(AppConstants.DRAWABLE_ID, intentData.getDrawableId());
        args.putBoolean(AppConstants.IS_PRESENT, intentData.isPresent());
        args.putInt(AppConstants.TYPE, intentData.getType());

        Intent intent = new Intent(context, TestActivity.class);
        intent.putExtras(args);

        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.slide_in_right_activity, R.anim.slide_out_left_activity);
    }

    private String getName(String name) {
        switch (name) {
            case SOUND:
            case SCREEN:
            case COMPASS:
            case BATTERY:
            case MULTI_TOUCH: {
                return String.format("%s %s", name, "Test");
            }
        }
        return name;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void startCorrespondingActivity(Context context, String name) {

    }

    class DashboardHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.parent_card)
        LinearLayout parentCard;

        @BindView(R.id.image)
        ImageView drawableImage;

        @BindView(R.id.name)
        TextView name;

        DashboardHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
