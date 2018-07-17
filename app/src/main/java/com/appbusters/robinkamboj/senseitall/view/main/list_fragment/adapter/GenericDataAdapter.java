package com.appbusters.robinkamboj.senseitall.view.main.list_fragment.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.DetailActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenericDataAdapter extends RecyclerView.Adapter<GenericDataAdapter.GenericViewHolder>
        implements GenericAdapterInterface {

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
    public void onBindViewHolder(@NonNull final GenericViewHolder holder, final int position) {
        @SuppressWarnings("UnnecessaryLocalVariable") final int pos = position;

        if(list.get(pos).isPresent()) {
            holder.dataDrawable.setColorFilter(ContextCompat.getColor(context, R.color.colorMajorDark));
        }
        else {
            holder.dataDrawable.setColorFilter(ContextCompat.getColor(context, R.color.red_shade_four));
        }
        holder.dataName.setText(list.get(pos).getName());
        Glide.with(context)
                .load(list.get(pos).getDrawableId())
                .into(holder.dataDrawable);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(pos).isPresent())
                    switch (list.get(pos).getName()) {
                        default:
                            context.startActivity(returnDetailActivityIntent(context, list.get(pos)));
                            ((Activity) context)
                                    .overridePendingTransition(
                                            R.anim.slide_in_right_activity,
                                            R.anim.slide_out_left_activity
                                    );
                    }
                else
                    Toast.makeText(
                                    context,
                                    "Sorry, " + list.get(pos).getName() + " is not present in your device.",
                                    Toast.LENGTH_SHORT)
                            .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Intent returnDetailActivityIntent(Context context, GenericData data) {
        Intent intent = new Intent(context, DetailActivity.class);

        Bundle args = new Bundle();

        if(data.getType() == AppConstants.TYPE_DIAGNOSTICS)
            args.putString(AppConstants.DATA_NAME, AppConstants.diagnosticsPointer.get(data.getName()));
        else
            args.putString(AppConstants.DATA_NAME, data.getName());

        args.putInt(AppConstants.DRAWABLE_ID, data.getDrawableId());
        args.putBoolean(AppConstants.IS_PRESENT, data.isPresent());
        args.putInt(AppConstants.TYPE, data.getType());

        return intent;
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
