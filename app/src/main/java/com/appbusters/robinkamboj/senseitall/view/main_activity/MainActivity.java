package com.appbusters.robinkamboj.senseitall.view.main_activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.PermissionsItem;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.main_activity.list_fragment.ListFragment;
import com.appbusters.robinkamboj.senseitall.view.main_activity.request_permissons_fragment.RequestFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity
        implements MainActivityInterface {

    @BindView(R.id.container)
    FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
    }

    @Override
    public void setup() {
        ButterKnife.bind(this);

        setListFragment();
    }

    @Override
    public void setListFragment() {
        String TAG = getString(R.string.tag_list_fragment);
        if(getSupportFragmentManager().findFragmentByTag(TAG) != null) return;
        ListFragment fragment = new ListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, TAG).commit();
    }

    @Override
    public void setRequestFragment() {
        String TAG = getString(R.string.tag_request_fragment);
        if(getSupportFragmentManager().findFragmentByTag(TAG) != null) return;
        RequestFragment fragment = new RequestFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_bottom_activity, R.anim.slide_out_right_activity);
        transaction.add(R.id.container, fragment, TAG);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Fragment reqFragment = getSupportFragmentManager().findFragmentByTag(getString(R.string.tag_request_fragment));
        ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.tag_list_fragment));
        if(reqFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_bottom_activity, R.anim.slide_out_right_activity);
            transaction.remove(reqFragment);
            transaction.commit();
        }
        else if(listFragment != null && listFragment.isSearching) {
            listFragment.setSearch();
        }
        else {
            super.onBackPressed();
            overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity);
        }
    }

    @Override
    public List<PermissionsItem> getPermissionItemsList() {
        ListFragment fragment =
                (ListFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.tag_list_fragment));

        if(fragment != null)
            return fragment.getPermissionItemsList();
        else
            return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshPermissionsRecycler();
    }

    @Override
    public void refreshPermissionsRecycler() {
        ListFragment lFragment =
                (ListFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.tag_list_fragment));

        RequestFragment rFragment =
                (RequestFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.tag_request_fragment));

        if(lFragment == null || rFragment == null) return;

        int rejectedCount = lFragment.checkIfAllPermissionsGiven();
        rFragment.showRecycler(lFragment.getPermissionItemsList());
        rFragment.updatePendingCount(rejectedCount);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        refreshPermissionsRecycler();

        if(requestCode == AppConstants.REQUEST_CODE) {
            boolean b = true;
            for(int result : grantResults) {
                b = b && result == PackageManager.PERMISSION_GRANTED;
            }
            if(b) {
                Toast.makeText(this, "Permission(s) granted.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Permission(s) denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
