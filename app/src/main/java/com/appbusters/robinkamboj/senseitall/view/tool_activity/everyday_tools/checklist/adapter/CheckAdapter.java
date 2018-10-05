package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.checklist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.checklist.db.Check;

import java.util.List;

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.CheckHolder> {

    private List<Check> list;
    private Context context;

    public CheckAdapter(List<Check> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CheckHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CheckHolder(LayoutInflater.from(context).inflate(R.layout.row_checklist, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CheckHolder checkHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return list == null? 0 : list.size();
    }

    class CheckHolder extends RecyclerView.ViewHolder {

        CheckHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
