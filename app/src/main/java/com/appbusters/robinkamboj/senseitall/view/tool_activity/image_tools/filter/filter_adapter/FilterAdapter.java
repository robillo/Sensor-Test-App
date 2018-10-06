package com.appbusters.robinkamboj.senseitall.view.tool_activity.image_tools.filter.filter_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterHolder> {

    private List<FilterItem> list;
    private Context context;
    private FilterListener filterListener;

    public FilterAdapter(List<FilterItem> list, Context context, FilterListener filterListener) {
        this.list = list;
        this.context = context;
        this.filterListener = filterListener;
    }

    @NonNull
    @Override
    public FilterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FilterHolder(LayoutInflater.from(context).inflate(R.layout.row_filter, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FilterHolder holder, final int position) {
        //noinspection UnnecessaryLocalVariable
        final int pos = position;
        if(list.get(pos).getTransformation() != null) {
            Glide.with(context)
                    .load(R.drawable.anime)
                    .apply(RequestOptions.bitmapTransform(list.get(pos).getTransformation()))
                    .into(holder.holderImage);
        }
        else {
            Glide.with(context)
                    .load(R.drawable.anime)
                    .into(holder.holderImage);
        }
        holder.holderText.setText(list.get(position).getTransName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterListener.onFilterSelected(list.get(pos).getTransformation());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class FilterHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.holder_image)
        ImageView holderImage;

        @BindView(R.id.holder_text)
        TextView holderText;

        FilterHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
