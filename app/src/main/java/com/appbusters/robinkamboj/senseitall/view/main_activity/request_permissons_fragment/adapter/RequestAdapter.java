package com.appbusters.robinkamboj.senseitall.view.main_activity.request_permissons_fragment.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.PermissionsItem;
import com.appbusters.robinkamboj.senseitall.preferences.AppPreferencesHelper;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestHolder> {

    private Context context;
    private List<PermissionsItem> list;
    private List<String> rawPermissions;
    private AppPreferencesHelper helper;

    public RequestAdapter(Context context, List<PermissionsItem> list, List<String> rawPermissions, AppPreferencesHelper helper) {
        this.context = context;
        this.list = list;
        this.rawPermissions = rawPermissions;
        this.helper = helper;
    }

    @NonNull
    @Override
    public RequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new RequestHolder(LayoutInflater.from(context).inflate(R.layout.row_request , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RequestHolder holder, int position) {
        holder.permissionName.setText(list.get(position).getPermissionName());
        if(list.get(position).isGranted()) {
            holder.permissionIsAllowedIv
                    .setColorFilter(ContextCompat.getColor(context, R.color.green_shade_three));
            Glide.with(context)
                    .load(R.drawable.baseline_done_outline_black_48)
                    .into(holder.permissionIsAllowedIv);
        }
        else {
            holder.permissionIsAllowedIv
                    .setColorFilter(ContextCompat.getColor(context, R.color.red_shade_four));
            Glide.with(context)
                    .load(R.drawable.baseline_cancel_presentation_black_48)
                    .into(holder.permissionIsAllowedIv);
        }

        final int pos = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ActivityCompat
                        .checkSelfPermission(context, rawPermissions.get(pos)) != PackageManager.PERMISSION_GRANTED) {
                    if(ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, rawPermissions.get(pos))) {
                        ActivityCompat
                                .requestPermissions(
                                        (Activity) context,
                                        new String[] {rawPermissions.get(pos)},
                                        AppConstants.REQUEST_CODE);
                    }
                    else if(helper.isDontAsk(rawPermissions.get(pos))) {
                        Toast.makeText(
                                context,
                                R.string.ask_permissions_manually,
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                    else {
                        ActivityCompat
                                .requestPermissions(
                                        (Activity) context,
                                        new String[] {rawPermissions.get(pos)},
                                        AppConstants.REQUEST_CODE);
                    }
                }
                else {
                Toast.makeText(
                            context,
                            list.get(pos).getPermissionName() + " is already granted.",
                            Toast.LENGTH_SHORT
                    ).show();
                }

                helper.setIsDontAsk(rawPermissions.get(pos), true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class RequestHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.permission_name)
        TextView permissionName;

        @BindView(R.id.permission_is_allowed_iv)
        ImageView permissionIsAllowedIv;

        RequestHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
