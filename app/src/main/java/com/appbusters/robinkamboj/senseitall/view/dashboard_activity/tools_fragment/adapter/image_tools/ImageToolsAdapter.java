package com.appbusters.robinkamboj.senseitall.view.dashboard_activity.tools_fragment.adapter.image_tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.ToolsItem;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.tool_activity.ToolActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageToolsAdapter extends RecyclerView.Adapter<ImageToolsAdapter.ImageToolsHolder> {

    private List<ToolsItem> list;
    private Context context;

    //0 for blue
    //1 for purple
    private int color = 0;

    public ImageToolsAdapter(List<ToolsItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public ImageToolsAdapter(List<ToolsItem> list, Context context, int color) {
        this.list = list;
        this.context = context;
        this.color = color;
    }

    @NonNull
    @Override
    public ImageToolsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(color == 0)
            return new ImageToolsHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image_tools, parent, false)
            );
        else
            return new ImageToolsHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.row_popular_tests, parent, false)
            );
    }

    @Override
    public void onBindViewHolder(@NonNull ImageToolsHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.drawableImage.setImageDrawable(ContextCompat.getDrawable(context, list.get(position).getDrawable()));

        final int pos = position;

        holder.parentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ToolActivity.class);

                Bundle args = new Bundle();
                args.putString(AppConstants.DATA_NAME, list.get(pos).getName());
                args.putInt(AppConstants.DRAWABLE_ID, list.get(pos).getDrawable());
                intent.putExtras(args);

                context.startActivity(intent);
                ((Activity) context)
                        .overridePendingTransition(R.anim.slide_in_right_activity, R.anim.slide_out_left_activity);
            }
        });
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
