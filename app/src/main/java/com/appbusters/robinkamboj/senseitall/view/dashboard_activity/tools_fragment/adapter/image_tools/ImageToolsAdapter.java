package com.appbusters.robinkamboj.senseitall.view.dashboard_activity.tools_fragment.adapter.image_tools;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.ToolsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageToolsAdapter extends RecyclerView.Adapter<ImageToolsAdapter.ImageToolsHolder> {

    private List<ToolsItem> list;
    private Context context;

    public ImageToolsAdapter(List<ToolsItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageToolsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageToolsHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image_tools, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ImageToolsHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.drawableImage.setImageDrawable(ContextCompat.getDrawable(context, list.get(position).getDrawable()));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ImageToolsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.parent_card)
        LinearLayout parentCard;

        @BindView(R.id.image)
        ImageView drawableImage;

        @BindView(R.id.name)
        TextView name;

        ImageToolsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
