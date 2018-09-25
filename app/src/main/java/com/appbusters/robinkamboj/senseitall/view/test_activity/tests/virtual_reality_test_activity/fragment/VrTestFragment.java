package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.virtual_reality_test_activity.fragment;


import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("FieldCanBeLocal")
public class VrTestFragment extends Fragment {

    private Intent intent;
    private Intent fallbackIntent;
    private boolean isVrAppInstalled;

    private final String vr_package_name = "com.robillo.virtualrealitysample_senseitall";
    private final String vr_intent_filter = "com.robillo.virtualrealitysample_senseitall.VR_TESTS";

    @BindView(R.id.start_test)
    TextView startTest;

    public VrTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the lv for this fragment
        View v = inflater.inflate(R.layout.fragment_vr_test, container, false);
        ButterKnife.bind(this, v);

        fallbackIntent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=" + vr_package_name)
        );

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        isVrAppInstalled = appInstalledOrNot(vr_package_name);

        if(isVrAppInstalled)
            intent = new Intent(vr_intent_filter);
        else
            intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + vr_package_name)
            );
    }

    @OnClick(R.id.start_test)
    public void setStartTest() {
        isVrAppInstalled = appInstalledOrNot(vr_package_name);
        if(isVrAppInstalled) {
            //calling an activity using <intent-filter> action name
            startActivity(intent);
        }
        else {
            Toast.makeText(getActivity(), "installing vr samples", Toast.LENGTH_SHORT).show();
            try {
                startActivity(intent);
            } catch (android.content.ActivityNotFoundException e) {
                startActivity(fallbackIntent);
            }
        }
    }

    private boolean appInstalledOrNot(String uri) {
        if(getActivity() == null) return false;
        PackageManager pm = getActivity().getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException ignored) {

        }

        return false;
    }
}
