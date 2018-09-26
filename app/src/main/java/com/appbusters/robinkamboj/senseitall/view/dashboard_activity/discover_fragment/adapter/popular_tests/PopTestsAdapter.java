package com.appbusters.robinkamboj.senseitall.view.dashboard_activity.discover_fragment.adapter.popular_tests;

import android.content.Context;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BATTERY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.COMPASS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MULTI_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SCREEN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOUND;

public class PopTestsAdapter extends RecyclerView.Adapter<PopTestsAdapter.DashboardHolder>
    implements PopTestsInterface {

    private List<Category> list;
    private Context context;

    public PopTestsAdapter(List<Category> list, Context context) {
        this.list = list;
        this.context = context;
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

            }
        });
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
