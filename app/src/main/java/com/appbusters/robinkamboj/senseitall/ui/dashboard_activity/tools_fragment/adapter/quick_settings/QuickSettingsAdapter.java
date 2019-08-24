package com.appbusters.robinkamboj.senseitall.ui.dashboard_activity.tools_fragment.adapter.quick_settings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.SettingInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuickSettingsAdapter extends RecyclerView.Adapter<QuickSettingsAdapter.QuickHolder> {

    private List<SettingInfo> list;
    private Context context;
    private QuickSettingsListener settingsListener;

    public QuickSettingsAdapter(List<SettingInfo> list, Context context, QuickSettingsListener settingsListener) {
        this.list = list;
        this.context = context;
        this.settingsListener = settingsListener;
    }

    @NonNull
    @Override
    public QuickHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuickHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quick_settings, parent, false)
        );
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public void onBindViewHolder(@NonNull final QuickHolder holder, final int position) {
        if(list.get(position).isOn()) {
            holder.drawable.setImageResource(list.get(position).getDrawableOn());
            holder.drawable.setColorFilter(context.getResources().getColor(R.color.primary_new));
            holder.bg.setBackground(context.getResources().getDrawable(R.drawable.oval_quick_bg));
        }
        else {
            holder.drawable.setImageResource(list.get(position).getdrawableOff());
            holder.bg.setBackground(context.getResources().getDrawable(R.drawable.oval_bg_none));
            holder.drawable.setColorFilter(context.getResources().getColor(R.color.colorTextTwo));
        }

        final int pos = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setOnOff(holder, list.get(pos), pos);
                settingsListener.setSelectedSetting(list.get(pos));
            }
        });
    }

    private void setOnOff(QuickHolder holder, SettingInfo info, int pos) {
        if(info.isOn()) {
            list.get(pos).setOn(false);
            holder.drawable.setImageResource(info.getdrawableOff());
            holder.bg.setBackground(context.getResources().getDrawable(R.drawable.oval_bg_none));
            holder.drawable.setColorFilter(context.getResources().getColor(R.color.colorTextTwo));
        }
        else {
            list.get(pos).setOn(true);
            holder.drawable.setImageResource(info.getDrawableOn());
            holder.bg.setBackground(context.getResources().getDrawable(R.drawable.oval_quick_bg));
            holder.drawable.setColorFilter(context.getResources().getColor(R.color.primary_new));
        }
    }

    public void updateItemState(String item, boolean isOn) {
        try {
            for(int i=0; i<list.size(); i++) {
                if(list.get(i).getName().equals(item)) {
                    list.get(i).setOn(isOn);
                    notifyDataSetChanged();
                    break;
                }
            }
        }
        catch (Exception ignored) {}
    }

    public boolean getItemState(String item) {
        try {
            for(int i=0; i<list.size(); i++) {
                if(list.get(i).getName().equals(item)) {
                    return list.get(i).isOn();
                }
            }
        }
        catch (Exception ignored) {}
        return false;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class QuickHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_bg)
        FrameLayout bg;

        @BindView(R.id.card_view)
        CardView cardView;

        @BindView(R.id.drawable)
        ImageView drawable;

        QuickHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
