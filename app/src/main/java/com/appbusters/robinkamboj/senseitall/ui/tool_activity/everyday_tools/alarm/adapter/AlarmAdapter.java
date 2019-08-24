package com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.alarm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.alarm.db.Alarm;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmHolder> {

    private List<Alarm> list;
    private Context context;

    public AlarmAdapter(List<Alarm> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AlarmHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new AlarmHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_alarm, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmHolder alarmHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class AlarmHolder extends RecyclerView.ViewHolder {

        public AlarmHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
