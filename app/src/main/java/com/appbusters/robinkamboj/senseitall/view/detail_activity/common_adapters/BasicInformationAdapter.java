package com.appbusters.robinkamboj.senseitall.view.detail_activity.common_adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.SensorDetail;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasicInformationAdapter extends RecyclerView.Adapter<BasicInformationAdapter.BasicInfoHolder> {

    private Context context;
    private List<SensorDetail> list;
    private String fromSensorName;

    public BasicInformationAdapter(Context context, List<SensorDetail> list) {
        this.context = context;
        this.list = list;
    }

    public BasicInformationAdapter(Context context, List<SensorDetail> list, String fromSensorName) {
        this.context = context;
        this.list = list;
        this.fromSensorName = fromSensorName;
    }

    @NonNull
    @Override
    public BasicInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new BasicInfoHolder(
                LayoutInflater.from(context)
                .inflate(R.layout.row_basic_information, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull BasicInfoHolder holder, int position) {
        if(list.get(position).getKey() != null) {
            if(list.get(position).getKey().length() == 0) holder.key.setVisibility(View.GONE);
            else holder.key.setVisibility(View.VISIBLE);

            holder.key.setText(list.get(position).getKey());
        }

        if(list.get(position) != null && list.get(position).getValue() != null) {
            if(fromSensorName != null && fromSensorName.equals(AppConstants.CPU)) {
                holder.value.setText(list.get(position).getValue());
            }
            else {
                holder.value.setText(Html.fromHtml(list.get(position).getValue()));
            }
        }
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
