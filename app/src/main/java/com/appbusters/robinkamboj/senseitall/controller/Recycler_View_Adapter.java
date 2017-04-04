package com.appbusters.robinkamboj.senseitall.controller;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.Data;
import com.appbusters.robinkamboj.senseitall.model.View_Holder;
import com.appbusters.robinkamboj.senseitall.view.ListActivity;
import com.appbusters.robinkamboj.senseitall.view.MainActivity;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import static android.content.ContentValues.TAG;

public class Recycler_View_Adapter extends RecyclerView.Adapter<View_Holder> implements Filterable{

    List<Data> list = Collections.emptyList();
    SensorFilter filter;
    List<Data> filteredList;
    Context context, _context;
    int _position;
    View_Holder _holder;
    HashMap<Data,Integer> positionMap;


    public Recycler_View_Adapter(List<Data> list, Context context) {
        this.list = list;
        this.context = context;
        this.filteredList=list;
        positionMap=new HashMap<>();
        for(int i=0;i<this.list.size();i++){
            positionMap.put(this.list.get(i),i);
        }
        getFilter();
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
        holder.sensor_name.setText(filteredList.get(_position).getSensor_name());
        Glide.with(_context)
                .load(filteredList.get(_position).getDrawable())
                .into(holder.sensor_imageview);
//        holder.sensor_imageview.setImageResource(filteredList.get(_position).getDrawable());
        if(!filteredList.get(_position).isPresent){
//            holder.cardView.setClickable(false);
            Log.d(TAG, "onBindViewHolder: RED");

            Picasso.with(context).load("hoot")
                    .placeholder(R.drawable.disabled_background)
                    .resize(100,100)
                    .onlyScaleDown()
                    .into(holder.sensor_imageview);
        }else{
            Log.d(TAG, "onBindViewHolder: GREEN");
//            Picasso.with(context).load("http://www.fordesigner.com/imguploads/Image/cjbc/zcool/png20080526/1211771499.png")
//                    .fit()
//                    .into(holder.sensor_imageview);
        }

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onCLick(View v, int position, boolean isLongClick) {
                if(isLongClick){

                    if(filteredList.get(position).isPresent){
                        holder.intent(filteredList.get(position).sensor_name, position);
                    }
                    else {
//                        Toast.makeText(context, list.get(position).sensor_name + " is not present in your device", Toast.LENGTH_SHORT).show();
                        Snackbar.make(ListActivity.activity_list,filteredList.get(position).sensor_name + " is not present in your device", Snackbar.LENGTH_LONG )
                                .setAction("Okay", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        }).show();
                    }
                }
                else {
//                    int pos = positionMap.get(filteredList.get(position));
                    int pos = position;
                    Log.d(TAG, "onCLick: SENS"+positionMap);
                    if(filteredList.get(pos).isPresent){
                        holder.intent(filteredList.get(pos).sensor_name, pos);
//                        Log.e("ROBIN", filteredList.get(pos).sensor_name);
                    }
                    else {
                        Snackbar.make(ListActivity.activity_list,filteredList.get(position).sensor_name + " is not present in your device", Snackbar.LENGTH_LONG )
                                .setAction("Okay", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                }).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        if ( filter== null) {
            filter = new SensorFilter();
        }
        Log.d(TAG, "getFilter: "+filter);
        return filter;
    }

    private class SensorFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint!=null && constraint.length()>0) {
                List<Data> tempList = new ArrayList<>();

                // search content in friend list
                for (Data data : list) {
                    if (data.getSensor_name().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(data);
                    }
                }
                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = list.size();
                filterResults.values = list;
            }
            Log.d(TAG, "performFiltering: "+filterResults.count);
            Log.d(TAG, "performFiltering: "+filterResults.values.toString());
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (List<Data>) results.values;
            Log.d(TAG, "publishResults: "+results.values.toString());
            notifyDataSetChanged();
        }
    }
}
