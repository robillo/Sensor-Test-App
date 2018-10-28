package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.sensors_fragment.all_sensors_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllSensorsAdapter extends RecyclerView.Adapter<AllSensorsAdapter.AllSensorsHolder> {

    private List<String> sensorsList;
    private Context context;
    private List<Integer> colorsList;

    public AllSensorsAdapter(List<String> sensorsList, Context context) {
        this.sensorsList = sensorsList;
        this.context = context;
        this.colorsList = AppConstants.simpleColors;
    }

    @NonNull
    @Override
    public AllSensorsHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        return new AllSensorsHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_simple_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AllSensorsHolder holder, int position) {

        if(position >= colorsList.size()) {
            holder.simpleItemCardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                            context,
                            colorsList.get(position - colorsList.size())
                    )
            );
        }
        else {
            holder.simpleItemCardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                            context,
                            colorsList.get(position)
                    )
            );
        }

        Glide.with(context)
                .load(AppConstants.imageUrlMap.get(sensorsList.get(position)))
                .into(holder.sensorImageView);

        holder.sensorTextView.setText(sensorsList.get(position));
    }

    @Override
    public int getItemCount() {
        return sensorsList == null ? 0 : sensorsList.size();
    }

    class AllSensorsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.simple_item_card_view)
        CardView simpleItemCardView;

        @BindView(R.id.sensor_image_view)
        ImageView sensorImageView;

        @BindView(R.id.sensor_text_view)
        TextView sensorTextView;

        AllSensorsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
