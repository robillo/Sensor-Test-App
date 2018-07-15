package com.appbusters.robinkamboj.senseitall.view.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenericDataAdapter extends RecyclerView.Adapter<GenericDataAdapter.GenericViewHolder> {

    private List<GenericData> list;
    private Context context;

    public GenericDataAdapter(List<GenericData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false);
        return new GenericViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.dataName.setText(list.get(position).getName());
        Glide.with(context)
                .load(list.get(position).getDrawableId())
                .into(holder.dataDrawable);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class GenericViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.data_card)
        CardView dataCard;

        @BindView(R.id.data_drawable)
        ImageView dataDrawable;

        @BindView(R.id.data_name)
        TextView dataName;

        GenericViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
