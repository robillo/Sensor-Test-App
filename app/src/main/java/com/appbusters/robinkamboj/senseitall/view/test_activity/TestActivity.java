package com.appbusters.robinkamboj.senseitall.view.test_activity;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.appbusters.robinkamboj.senseitall.view.test_activity.accelerometer_test_fragment.AccelerometerTestFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.directions_fragment.DirectionsFragment;
import com.appbusters.robinkamboj.senseitall.view.test_activity.proximity_test_fragment.ProximityTestFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DATA_NAME;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DRAWABLE_ID;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IS_PRESENT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RECYCLER_NAME;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_ACCELEROMETER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_PROXIMITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE;

public class TestActivity extends AppCompatActivity implements TestInterface {

    public GenericData intentData = new GenericData();
    public String recyclerName;

    @BindView(R.id.test_name)
    TextView testName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        setup();
    }

    @Override
    public void initializeIntentData() {
        Bundle intentBundle = getIntent().getExtras();
        if(intentBundle != null) {
            intentData.setName(intentBundle.getString(DATA_NAME));
            intentData.setDrawableId(intentBundle.getInt(DRAWABLE_ID));
            intentData.setPresent(intentBundle.getBoolean(IS_PRESENT));
            intentData.setType(intentBundle.getInt(TYPE));
            recyclerName = intentBundle.getString(RECYCLER_NAME);
        }
        if(recyclerName != null)
            testName.setText(recyclerName.toUpperCase());
    }

    @Override
    public void setup() {
        ButterKnife.bind(this);

        initializeIntentData();
        setStatusBarColor();
        setDirectionsFragment();
    }

    @Override
    public void setStatusBarColor() {
        Window window = getWindow();
        View view = window.getDecorView();
        int flags = view.getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        view.setSystemUiVisibility(flags);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    @Override
    public void setDirectionsFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, new DirectionsFragment(), getString(R.string.tag_directions_fragment));
        transaction.commit();
    }

    @Override
    public void setTestFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(getString(R.string.tag_directions_fragment));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left_activity, R.anim.slide_out_left_activity);
        transaction.remove(fragment);
        transaction.commit();

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right_activity, R.anim.slide_out_right_activity);

        switch (intentData.getName()) {
            case SENSOR_PROXIMITY: {
                transaction.add(
                        R.id.container,
                        new ProximityTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
            case SENSOR_ACCELEROMETER: {
                transaction.add(
                        R.id.container,
                        new AccelerometerTestFragment(),
                        getString(R.string.tag_test_fragment)
                ).commit();
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity);
    }
}
