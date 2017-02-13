package com.appbusters.robinkamboj.senseitall.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.Data;
import com.appbusters.robinkamboj.senseitall.model.View_Holder;

import java.util.Collections;
import java.util.List;

public class Recycler_View_Adapter extends RecyclerView.Adapter<View_Holder>{

    List<Data> list = Collections.emptyList();
    Context context, _context;
    int _position;
    View_Holder _holder;
    public Boolean isPresent[];

    public Recycler_View_Adapter(List<Data> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        _context = parent.getContext();
        View v = LayoutInflater.from(_context).inflate(R.layout.row_layout, parent, false);
        return new View_Holder(v);
    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {
        _position = position;
        _holder = holder;
        holder.sensor_name.setText(list.get(_position).getSensor_name());
        holder.sensor_imageview.setImageResource(list.get(_position).getDrawable());
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onCLick(View v, int position, boolean isLongClick) {
                if(isLongClick){

                }
                else {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
