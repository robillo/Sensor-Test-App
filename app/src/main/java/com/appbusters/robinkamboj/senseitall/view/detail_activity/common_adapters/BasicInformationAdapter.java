package com.appbusters.robinkamboj.senseitall.view.detail_activity.common_adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.SensorDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasicInformationAdapter extends RecyclerView.Adapter<BasicInformationAdapter.BasicInfoHolder> {

    private Context context;
    private List<SensorDetail> list;

    public BasicInformationAdapter(Context context, List<SensorDetail> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BasicInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = context;
        return new BasicInfoHolder(
                LayoutInflater.from(context)
                .inflate(R.layout.row_basic_information, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull BasicInfoHolder holder, int position) {
        holder.key.setText(list.get(position).getKey());
        holder.value.setText(list.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class BasicInfoHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.key)
        TextView key;

        @BindView(R.id.value)
        TextView value;

        BasicInfoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
