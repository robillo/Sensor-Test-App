package com.appbusters.robinkamboj.senseitall.view.main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.PermissionsItem;
import com.appbusters.robinkamboj.senseitall.view.main.list_fragment.ListFragment;
import com.appbusters.robinkamboj.senseitall.view.main.request_permissons_fragment.RequestFragment;

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
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, TAG).commit();
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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
