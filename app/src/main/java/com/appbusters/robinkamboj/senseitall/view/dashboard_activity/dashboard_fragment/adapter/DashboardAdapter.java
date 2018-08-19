package com.appbusters.robinkamboj.senseitall.view.dashboard_activity.dashboard_fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardHolder> {

    private List<Category> list;
    private Context context;

    public DashboardAdapter(List<Category> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public DashboardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new DashboardHolder(LayoutInflater.from(context).inflate(R.layout.row_dashboard, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.countDescription.setText(list.get(position).getCountDescription());
        holder.drawableImage.setImageDrawable(ContextCompat.getDrawable(context, list.get(position).getDrawable()));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class DashboardHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView drawableImage;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.count_description)
        TextView countDescription;

        public DashboardHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
