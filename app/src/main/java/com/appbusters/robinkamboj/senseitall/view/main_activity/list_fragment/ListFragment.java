package com.appbusters.robinkamboj.senseitall.view.main_activity.list_fragment;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.appbusters.robinkamboj.senseitall.model.recycler.PermissionsItem;
import com.appbusters.robinkamboj.senseitall.preferences.AppPreferencesHelper;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.main_activity.MainActivity;
import com.appbusters.robinkamboj.senseitall.view.main_activity.list_fragment.adapter.GenericDataAdapter;
import com.appbusters.robinkamboj.senseitall.view.splash.helper_classes.MyTaskLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.CONSUMER_IR_SERVICE;
import static android.content.Context.SENSOR_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RATE_YOUR_EXPERIENCE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_DEVICE_TESTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_FEATURES_LIST;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_SENSORS_LIST;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_DIAGNOSTICS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_FEATURES;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_RATE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_SENSORS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.diagnosticsPointer;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.imageUrlMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements ListFragmentInterface,
        android.support.v4.app.LoaderManager.LoaderCallbacks<boolean[][]> {

    private AppPreferencesHelper helper;
    private List<GenericData> list;
    @SuppressWarnings("FieldCanBeLocal")
    private List<PermissionsItem> permissionsItems;
    private int rejectedCount;

    private List<String> sensorNames;
    private List<String> featureNames;
    private List<String> diagnosticsNames;

    private boolean[] sensorsPresent;
    private boolean[] featuresPresent;
    private boolean[] diagnosticsPresent;

    @BindView(R.id.rate_experience_screen)
    LinearLayout rateExperienceScreen;

    @BindView(R.id.list_screen)
    FrameLayout listScreen;

    @BindView(R.id.toolbar)
    LinearLayout toolbar;

    @BindView(R.id.card_permissions)
    CardView permissionsCard;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.header_text)
    TextSwitcher headerText;

    @BindView(R.id.highlight_tests)
    ImageView highlight_tests;

    @BindView(R.id.highlight_sensors)
    ImageView highlight_sensors;

    @BindView(R.id.highlight_features)
    ImageView highlight_features;

    @BindView(R.id.highlight_rate)
    ImageView highlight_rate;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        //noinspection ConstantConditions
        helper = new AppPreferencesHelper(getActivity());

        Animation in = AnimationUtils.loadAnimation(getActivity(), R.anim.top_down_enter_header);
        Animation out = AnimationUtils.loadAnimation(getActivity(), R.anim.top_down_exit_header);
        in.setDuration(200);
        out.setDuration(200);

        inflateData();
        checkIfAllPermissionsGiven();
        checkForPresentSensors();
        changeStatusBarColor();
    }

    @Override
    public void inflateData() {
        sensorNames = AppConstants.sensorNames;
        featureNames = AppConstants.featureNames;
        diagnosticsNames = AppConstants.diagnosticsNames;
    }

    @Override
    public void onResume() {
        super.onResume();
        checkIfAllPermissionsGiven();
    }

    @Override
    public void changeStatusBarColor() {
        @SuppressWarnings("ConstantConditions") Window window = getActivity().getWindow();
        View view = window.getDecorView();
        int flags = view.getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        view.setSystemUiVisibility(flags);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    @OnClick(R.id.button_tests_list)
    public void setShowTests() {
        if(helper.getHeaderText().equals(SHOWING_DEVICE_TESTS)) return;
        helper.setHeaderText(SHOWING_DEVICE_TESTS);
        setHeaderTextAndRv();

        if(listScreen.getVisibility() == View.GONE) {
            listScreen.setVisibility(View.VISIBLE);
            rateExperienceScreen.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.button_sensors_list)
    public void setShowSensors() {
        if(helper.getHeaderText().equals(SHOWING_SENSORS_LIST)) return;
        helper.setHeaderText(SHOWING_SENSORS_LIST);
        setHeaderTextAndRv();

        if(listScreen.getVisibility() == View.GONE) {
            listScreen.setVisibility(View.VISIBLE);
            rateExperienceScreen.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.button_features_list)
    public void setShowFeatures() {
        if(helper.getHeaderText().equals(SHOWING_FEATURES_LIST)) return;
        helper.setHeaderText(SHOWING_FEATURES_LIST);
        setHeaderTextAndRv();

        if(listScreen.getVisibility() == View.GONE) {
            listScreen.setVisibility(View.VISIBLE);
            rateExperienceScreen.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.button_rate_experience)
    public void setRateExperience() {
        if(helper.getHeaderText().equals(RATE_YOUR_EXPERIENCE)) return;
        helper.setHeaderText(RATE_YOUR_EXPERIENCE);
        setHeaderTextAndRv();

        if(rateExperienceScreen.getVisibility() == View.GONE) {
            listScreen.setVisibility(View.GONE);
            rateExperienceScreen.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setHeaderTextAndRv() {
        togglePermissionCardVisibility();

        String string = helper.getHeaderText();

        if(string.equals(RATE_YOUR_EXPERIENCE)) {
            if(headerText.getVisibility() == View.VISIBLE) headerText.setVisibility(View.GONE);
        }
        else {
            headerText.setText(string);
            if(headerText.getVisibility() == View.GONE) headerText.setVisibility(View.VISIBLE);
        }

        switch (string) {
            case SHOWING_DEVICE_TESTS: {
                turnOnHighlight(TYPE_DIAGNOSTICS);
                fillGenericDataForSelected(TYPE_DIAGNOSTICS);
                break;
            }
            case SHOWING_SENSORS_LIST: {
                turnOnHighlight(TYPE_SENSORS);
                fillGenericDataForSelected(TYPE_SENSORS);
                break;
            }
            case SHOWING_FEATURES_LIST: {
                turnOnHighlight(TYPE_FEATURES);
                fillGenericDataForSelected(TYPE_FEATURES);
                break;
            }
            case RATE_YOUR_EXPERIENCE: {
                turnOnHighlight(TYPE_RATE);
                if(rateExperienceScreen.getVisibility() == View.GONE) {
                    listScreen.setVisibility(View.GONE);
                    rateExperienceScreen.setVisibility(View.VISIBLE);
                }
                break;
            }
        }

        initializeAdapter();
    }

    @Override
    public void turnOnHighlight(int type) {
        toggleToolbarVisibility(type);
        switch (type) {
            case TYPE_DIAGNOSTICS: {
                highlight_tests.setBackgroundColor(getResources().getColor(R.color.green_shade_three));
                highlight_sensors.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_features.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_rate.setBackgroundColor(getResources().getColor(R.color.transparent));
                break;
            }
            case TYPE_SENSORS: {
                highlight_tests.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_sensors.setBackgroundColor(getResources().getColor(R.color.green_shade_three));
                highlight_features.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_rate.setBackgroundColor(getResources().getColor(R.color.transparent));
                break;
            }
            case TYPE_FEATURES: {
                highlight_tests.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_sensors.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_features.setBackgroundColor(getResources().getColor(R.color.green_shade_three));
                highlight_rate.setBackgroundColor(getResources().getColor(R.color.transparent));
                break;
            }
            case TYPE_RATE: {
                highlight_tests.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_sensors.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_features.setBackgroundColor(getResources().getColor(R.color.transparent));
                highlight_rate.setBackgroundColor(getResources().getColor(R.color.green_shade_three));
                break;
            }
        }
    }

    @Override
    public void checkIfAllPermissionsGiven() {
        List<String> permissionNames = AppConstants.dangerousPermissions;
        permissionsItems = new ArrayList<>();
        rejectedCount = 0;

        if(getActivity() != null)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            for(String p : permissionNames) {
                boolean b = getActivity().checkSelfPermission(p) == PERMISSION_GRANTED;
                permissionsItems.add(new PermissionsItem(p, b));
                if(!b) rejectedCount++;
            }

        if(rejectedCount == 0)
            permissionsCard.setVisibility(View.GONE);
        else
            permissionsCard.setVisibility(View.VISIBLE);
    }

    @Override
    public void checkForPresentSensors() {
        //noinspection ConstantConditions
        getActivity().getSupportLoaderManager().initLoader(AppConstants.LOADER_ID, null, this).forceLoad();
    }

    @Override
    public void togglePermissionCardVisibility() {
        if(helper.getHeaderText().equals(RATE_YOUR_EXPERIENCE))
            permissionsCard.setVisibility(View.GONE);
        else {
            if(rejectedCount > 0) permissionsCard.setVisibility(View.VISIBLE);
            else permissionsCard.setVisibility(View.GONE);
        }
    }

    @Override
    public List<PermissionsItem> getPermissionItemsList() {
        return permissionsItems;
    }

    @Override
    public void toggleToolbarVisibility(int type) {

        Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_top_activity);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                toolbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_top_activity);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                toolbar.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        switch (type) {
            case TYPE_DIAGNOSTICS: {
                recyclerView.setVisibility(View.VISIBLE);
                if(toolbar.getVisibility() == View.GONE) {
                    toolbar.startAnimation(fadeIn);
                }
                break;
            }
            case TYPE_FEATURES: {
                recyclerView.setVisibility(View.VISIBLE);
                if(toolbar.getVisibility() == View.GONE) {
                    toolbar.startAnimation(fadeIn);
                }
                break;
            }
            case TYPE_SENSORS: {
                recyclerView.setVisibility(View.VISIBLE);
                if(toolbar.getVisibility() == View.GONE) {
                    toolbar.startAnimation(fadeIn);
                }
                break;
            }
            case TYPE_RATE: {
                recyclerView.setVisibility(View.GONE);
                toolbar.startAnimation(fadeOut);
                break;
            }
        }
    }

    @Override
    public void initializeAdapter() {
        int span;
        switch (getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_PORTRAIT: {
                span = 3;
                break;
            }
            case Configuration.ORIENTATION_LANDSCAPE: {
                span = 5;
                break;
            }
            default: {
                span = 3;
            }
        }

        LayoutAnimationController animationController = new LayoutAnimationController(
                AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_left_recycler),
                0.05f
        );

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), span);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setLayoutAnimation(animationController);
        recyclerView.setAdapter(new GenericDataAdapter(list, getActivity()));

    }

    @Override
    public void fillGenericDataForSelected(int type) {
        list = new ArrayList<>();

        //first row null and invisible for title bar space
//        list.add(null);
//        list.add(null);
//        list.add(null);

        List<String> dataNames = new ArrayList<>();
        boolean[] dataPresent = new boolean[dataNames.size()];
        switch (type) {
            case TYPE_DIAGNOSTICS: {
                dataNames = diagnosticsNames;
                dataPresent = diagnosticsPresent;
                break;
            }
            case TYPE_SENSORS: {
                dataNames = sensorNames;
                dataPresent = sensorsPresent;
                break;
            }
            case TYPE_FEATURES: {
                dataNames = featureNames;
                dataPresent = featuresPresent;
                break;
            }
            case TYPE_RATE: {

                break;
            }
        }

        for(int i=0; i<dataNames.size(); i++)
            if(type == TYPE_DIAGNOSTICS)
                list.add(new GenericData(
                        dataNames.get(i),
                        imageUrlMap.get(diagnosticsPointer.get(dataNames.get(i))),
                        dataPresent[i],
                        type));
            else
                list.add(new GenericData(
                        dataNames.get(i),
                        imageUrlMap.get(dataNames.get(i)),
                        dataPresent[i],
                        type));

//        if(dataNames.size()%3 != 0) {
//            list.add(null);
//            list.add(null);
//            list.add(null);
//        }
    }

    @OnClick(R.id.card_permissions)
    public void askPermissions() {
        //noinspection ConstantConditions
        ((MainActivity) getActivity()).setRequestFragment();
    }

    @OnClick(R.id.image_five_stars)
    public void giveImageFiveStars() {
        giveFiveStars();
    }

    @OnClick(R.id.give_five_stars)
    public void giveFiveStars() {
        Toast.makeText(getActivity(), "Give five stars by clicking here", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.give_feedback)
    public void giveFeedback() {
        Toast.makeText(getActivity(), "Give feedback by clicking here", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    public Loader<boolean[][]> onCreateLoader(int id, @Nullable Bundle args) {
        Context context = getActivity();
        assert context != null;
        SensorManager sManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        List<String> sensors = AppConstants.sensorNames;
        HashMap<String, Integer> sMap = AppConstants.sensorManagerInts;

        PackageManager fManager = context.getPackageManager();
        List<String> features = AppConstants.featureNames;
        HashMap<String, String> fMap = AppConstants.packageManagerPaths;

        List<String> diagnostics = AppConstants.diagnosticsNames;

        Vibrator vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        ConsumerIrManager infrared = (ConsumerIrManager) context.getSystemService(CONSUMER_IR_SERVICE);

        boolean b;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            b = ActivityCompat
                    .checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) ==
                    PackageManager.PERMISSION_GRANTED &&
                    Objects.requireNonNull(context.getSystemService(FingerprintManager.class))
                            .isHardwareDetected();
        } else {
            b = FingerprintManagerCompat.from(context).isHardwareDetected();
        }

        return new MyTaskLoader(
                context,
                sManager,
                sensors,
                sMap,
                fManager,
                features,
                fMap,
                vibrator,
                infrared,
                b,
                diagnostics
        );
    }

    @Override
    public void onLoadFinished(@NonNull Loader<boolean[][]> loader, boolean[][] isPresent) {
        this.sensorsPresent = isPresent[0];
        this.featuresPresent = isPresent[1];
        this.diagnosticsPresent = isPresent[2];

        setHeaderTextAndRv();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<boolean[][]> loader) {

    }
}
