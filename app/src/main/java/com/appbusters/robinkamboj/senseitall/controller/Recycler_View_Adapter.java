package com.appbusters.robinkamboj.senseitall.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.Data;
import com.appbusters.robinkamboj.senseitall.model.View_Holder;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Recycler_View_Adapter extends RecyclerView.Adapter<View_Holder>{

    List<Data> list = Collections.emptyList();
    Context context, _context;
    int _position;
    View_Holder _holder;
    public Boolean[] isPresent;

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
    public void onBindViewHolder(final View_Holder holder, int position) {
//        if(holder.sensor_imageview.i)
        _position = position;
        _holder = holder;
        holder.sensor_name.setText(list.get(_position).getSensor_name());
        holder.sensor_imageview.setImageResource(list.get(_position).getDrawable());
        if(!list.get(_position).isPresent){
//            holder.sensor_imageview.setBackgroundColor(_context.getResources().getColor(R.color.colorBlackShade));
//            holder.cardView.setClickable(false);
            Log.d(TAG, "onBindViewHolder: RED");

            Picasso.with(context).load("http://www.fordesigner.com/imguploads/Image/cjbc/zcool/png20080526/1211771503.png")
                    .fit()
                    .into(holder.sensor_imageview);
        }else{
            Log.d(TAG, "onBindViewHolder: GREEN");
            Picasso.with(context).load("http://www.fordesigner.com/imguploads/Image/cjbc/zcool/png20080526/1211771499.png")
                    .fit()
                    .into(holder.sensor_imageview);
        }

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onCLick(View v, int position, boolean isLongClick) {
                if(isLongClick){
                    if(list.get(position).isPresent){
                        holder.intent(list.get(position).sensor_name);
                        Log.e("ROBIN", list.get(position).sensor_name);
                    }
                    else {
                        Toast.makeText(context, list.get(position).sensor_name + " is not present in your device", Toast.LENGTH_SHORT).show();                    }
                }
                else {
                    if(list.get(position).isPresent){
                        holder.intent(list.get(position).sensor_name);
                        Log.e("ROBIN", list.get(position).sensor_name);
                    }
                    else {
                        Toast.makeText(context, list.get(position).sensor_name + " is not present in your device", Toast.LENGTH_SHORT).show();                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
