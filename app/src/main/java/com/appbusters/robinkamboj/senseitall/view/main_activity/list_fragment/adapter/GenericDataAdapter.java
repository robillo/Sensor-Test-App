package com.appbusters.robinkamboj.senseitall.view.main_activity.list_fragment.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.DetailActivity;
import com.appbusters.robinkamboj.senseitall.view.main_activity.MainActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BACK_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FINGERPRINT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FLASH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FRONT_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GPS_LOCATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GSM_UMTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.HEART_RATE_ECG;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MICROPHONE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_HEART_RATE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_DIAGNOSTICS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WIFI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.diagnosticsPointer;

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
                if(list.get(pos).isPresent()) {

                    if(isPermissionGranted(context, list.get(pos).getName(), list.get(pos).getType())) {
                        context.startActivity(returnDetailActivityIntent(context, list.get(pos)));
                        ((Activity) context)
                                .overridePendingTransition(
                                        R.anim.slide_in_right_activity,
                                        R.anim.slide_out_left_activity
                                );
                    }
                    else {
//                        Toast.makeText(context, R.string.give_permissions_first, Toast.LENGTH_SHORT).show();
                        ((MainActivity) context).setRequestFragment();
                    }
                }
                else {
                    Toast.makeText(
                            context,
                            context.getString(R.string.sorry) + list.get(pos).getName() + context.getString(R.string.not_present),
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public boolean isPermissionGranted(Context context, String sensorName, int sensorType) {

        if(sensorType == TYPE_DIAGNOSTICS) {
            sensorName = diagnosticsPointer.get(sensorName);
        }

        boolean isGiven = true;
        switch (sensorName) {
            case BACK_CAMERA:
            case FRONT_CAMERA: {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    isGiven = false;
                }
                break;
            }
            case FINGERPRINT: {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                        isGiven = false;
                    }
                    else {
                        FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
                        if(fingerprintManager == null) {
                            Toast.makeText(context, "Error Retrieving Fingerprint Instance", Toast.LENGTH_SHORT).show();
                            isGiven = false;
                        }
                        else if (!fingerprintManager.isHardwareDetected()) {
                            Toast.makeText(context, "Device Doesn't Support Fingerprint Authentication", Toast.LENGTH_SHORT).show();
                            isGiven = false;
                        } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                            Toast.makeText(context, "Please Enroll Fingerprint Authentication First", Toast.LENGTH_SHORT).show();
                            isGiven = false;
                        }
                    }
                }
                else {
                    isGiven = false;
                }
                break;
            }
            case FLASH: {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    isGiven = false;
                break;
            }
            case GSM_UMTS: {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
                    isGiven = false;
                break;
            }
            case WIFI: {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                    isGiven = false;
                break;
            }
            case SENSOR_HEART_RATE:
            case HEART_RATE_ECG: {
                if(ActivityCompat.checkSelfPermission(context, Manifest.permission.BODY_SENSORS) != PackageManager.PERMISSION_GRANTED)
                    isGiven = false;
                break;
            }
            case GPS_LOCATION: {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)
                    isGiven = false;
                break;
            }
            case MICROPHONE: {
                if(ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
                    isGiven = false;
                break;
            }
        }
        return isGiven;
    }

    @Override
    public void filterList(List<GenericData> newList) {
        this.list = newList;
        notifyDataSetChanged();
    }

    @Override
    public Intent returnDetailActivityIntent(Context context, GenericData data) {
        Intent intent = new Intent(context, DetailActivity.class);

        Bundle args = new Bundle();

        if(data.getType() == AppConstants.TYPE_DIAGNOSTICS)
            args.putString(AppConstants.DATA_NAME, AppConstants.diagnosticsPointer.get(data.getName()));
        else
            args.putString(AppConstants.DATA_NAME, data.getName());

        args.putString(AppConstants.RECYCLER_NAME, data.getName());

        args.putInt(AppConstants.DRAWABLE_ID, data.getDrawableId());
        args.putBoolean(AppConstants.IS_PRESENT, data.isPresent());
        args.putInt(AppConstants.TYPE, data.getType());

        intent.putExtras(args);

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
