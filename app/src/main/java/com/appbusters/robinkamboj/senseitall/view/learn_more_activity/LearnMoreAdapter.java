package com.appbusters.robinkamboj.senseitall.view.learn_more_activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.LearnMoreItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LearnMoreAdapter extends RecyclerView.Adapter<LearnMoreAdapter.LearnMoreHolder> {

    private List<LearnMoreItem> list;
    @SuppressWarnings("FieldCanBeLocal")
    private Context context;

    LearnMoreAdapter(List<LearnMoreItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public LearnMoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new LearnMoreHolder(LayoutInflater.from(context).inflate(R.layout.row_learn_more, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LearnMoreHolder holder, int position) {
        holder.heading.setText(list.get(position).getHeader());
        holder.drawable.setPadding(0, 0, 0, 0);
        Glide.with(context)
                .load(list.get(position).getDrawable())
                .apply(new RequestOptions().centerCrop())
                .into(holder.drawable);
        holder.content.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class LearnMoreHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.more_drawable)
        ImageView drawable;

        @BindView(R.id.heading)
        TextView heading;

        @BindView(R.id.content)
        TextView content;

        LearnMoreHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
