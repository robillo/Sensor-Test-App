package com.appbusters.robinkamboj.senseitall.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.controller.ItemClickListener;

public class View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public TextView sensor_name;
    public ImageView sensor_imageview;
    ItemClickListener clickListener;
    Context context;


    public View_Holder(View itemView) {
        super(itemView);

        sensor_name = (TextView) itemView.findViewById(R.id.textView);
        sensor_imageview = (ImageView) itemView.findViewById(R.id.imageView);

        context = itemView.getContext();
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setClickListener(ItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public void onClick(View view) {
        clickListener.onCLick(itemView, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View view) {
        clickListener.onCLick(itemView, getAdapterPosition(), true);
        return false;
    }
}
