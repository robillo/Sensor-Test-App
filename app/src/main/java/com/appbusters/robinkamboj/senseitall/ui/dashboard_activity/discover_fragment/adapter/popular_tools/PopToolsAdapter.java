package com.appbusters.robinkamboj.senseitall.ui.dashboard_activity.discover_fragment.adapter.popular_tools;

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
import com.appbusters.robinkamboj.senseitall.model.recycler.Category;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.ui.helper_classes.CheckIfPresent;
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.ToolActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopToolsAdapter extends RecyclerView.Adapter<PopToolsAdapter.PopToolsHolder> {

    private List<Category> list;
    private Context context;
    private CheckIfPresent checkIfPresent;

    public PopToolsAdapter(List<Category> list, Context context) {
        this.list = list;
        this.context = context;
        checkIfPresent = new CheckIfPresent(context);
    }

    @NonNull
    @Override
    public PopToolsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PopToolsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tools, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopToolsHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.drawableImage.setImageDrawable(ContextCompat.getDrawable(context, list.get(position).getDrawable()));

        final int pos = position;

        holder.parentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkIfPresent.returnPresence(list.get(pos).getName())) {
                    Intent intent = new Intent(context, ToolActivity.class);

                    Bundle args = new Bundle();
                    args.putString(AppConstants.DATA_NAME, list.get(pos).getName());
                    args.putInt(AppConstants.DRAWABLE_ID, list.get(pos).getDrawable());
                    intent.putExtras(args);

                    context.startActivity(intent);
                    ((Activity) context)
                            .overridePendingTransition(R.anim.slide_in_right_activity, R.anim.slide_out_left_activity);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class PopToolsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.parent_card)
        LinearLayout parentCard;

        @BindView(R.id.image)
        ImageView drawableImage;

        @BindView(R.id.name)
        TextView name;

        PopToolsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
