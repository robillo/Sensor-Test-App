package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.checklist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.tool_activity.ToolActivity;
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.checklist.db.Check;

import java.util.List;

import butterknife.BindView;

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
    public void onBindViewHolder(@NonNull final CheckHolder holder, final int position) {

        //noinspection UnnecessaryLocalVariable
        final int pos = position;

        holder.itemText.setText(list.get(position).getCheck_text());

        if(list.get(position).getDone()) {
            holder.markCheckAsDone.setText("MARK AS DONE");
            holder.markCheckAsDone.setTextColor(ContextCompat.getColor(context, R.color.green_shade_four));
        }
        else {
            holder.markCheckAsDone.setText("MARK AS NOT DONE");
            holder.markCheckAsDone.setTextColor(ContextCompat.getColor(context, R.color.red_shade_four));
        }

        holder.markCheckAsDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(pos).getDone()) {
                    holder.markCheckAsDone.setText("MARK AS NOT DONE");
                    holder.markCheckAsDone.setTextColor(ContextCompat.getColor(context, R.color.red_shade_four));
                }
                else {
                    holder.markCheckAsDone.setText("MARK AS DONE");
                    holder.markCheckAsDone.setTextColor(ContextCompat.getColor(context, R.color.green_shade_four));
                }
                list.get(pos).setDone(!list.get(pos).getDone());
                ((ToolActivity) context).markCheckAsDoneById(list.get(pos).getDone(), list.get(pos).getId());
                notifyDataSetChanged();
            }
        });

        holder.deleteCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ToolActivity) context).deleteCheckById(list.get(pos).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null? 0 : list.size();
    }

    class CheckHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_text)
        TextView itemText;

        @BindView(R.id.mark_as_done)
        TextView markCheckAsDone;

        @BindView(R.id.delete_check)
        TextView deleteCheck;

        CheckHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
