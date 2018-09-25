package com.appbusters.robinkamboj.senseitall.view.main_activity.list_fragment;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.fingerprint.FingerprintManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.appbusters.robinkamboj.senseitall.model.recycler.PermissionsItem;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.main_activity.list_fragment.adapter.GenericDataAdapter;
import com.appbusters.robinkamboj.senseitall.view.splash.helper_classes.IsPresentLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.CONSUMER_IR_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.ANDROID;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DIAGNOSTIC;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FEATURE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.INFORMATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_ANDROID_FEATURE_LIST;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_DEVICE_TESTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_FEATURES_LIST;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_INFORMATION_LIST;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_SENSORS_LIST;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_SOFTWARE_LIST;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOFTWARE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_ANDROID;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_DIAGNOSTICS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_FEATURES;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_INFORMATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_SENSORS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TYPE_SOFTWARE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.imageUrlMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements ListFragmentInterface,
        android.support.v4.app.LoaderManager.LoaderCallbacks<boolean[][]> {

    private SensorManager sensorManager;
    private Vibrator vibrator;
    private ConsumerIrManager infrared;
    private PackageManager featureManager;
    private CameraManager cameraManager;
    private boolean isFingerprintSupported;

    private InputMethodManager inputMethodManager;
    private List<GenericData> list;
    private List<PermissionsItem> permissionsItems;
    public boolean isSearching = false;
    private GenericDataAdapter adapter = null;
    private String headerTextString;

    private List<String> diagnosticsNames;
    private List<String> sensorNames;
    private List<String> featureNames;
    private List<String> informationNames;
    private List<String> softwareNames;
    private List<String> androidNames;

    private boolean[] diagnosticsPresent;
    private boolean[] sensorsPresent;
    private boolean[] featuresPresent;
    private boolean[] informationsPresent;
    private boolean[] softwaresPresent;
    private boolean[] androidsPresent;

    @BindView(R.id.app_header_text)
    TextView appHeaderText;

    @BindView(R.id.edit_text_search)
    EditText searchEditText;

    @BindView(R.id.search)
    ImageView searchImage;

    @BindView(R.id.menu_settings)
    ImageView settingsMenuImage;

    @BindView(R.id.list_screen)
    FrameLayout listScreen;

    @BindView(R.id.toolbar)
    LinearLayout toolbar;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

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

        if(getActivity() == null) return;

        String intentCategory = getActivity().getIntent().getStringExtra(AppConstants.CATEGORY);
        appHeaderText.setText(intentCategory);
        setHeaderText(intentCategory);

        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        Animation in = AnimationUtils.loadAnimation(getActivity(), R.anim.top_down_enter_header);
        Animation out = AnimationUtils.loadAnimation(getActivity(), R.anim.top_down_exit_header);
        in.setDuration(200);
        out.setDuration(200);

        inflateData();
        checkIfAllPermissionsGiven();
        checkForPresentSensors();
        changeStatusBarColor();
        setEditTextSearchListener();
    }

    @Override
    public void setHeaderText(String intentCategory) {
        switch (intentCategory) {
            case DIAGNOSTIC: {
                headerTextString = SHOWING_DEVICE_TESTS;
                setShowTests();
                break;
            }
            case FEATURE: {
                headerTextString = SHOWING_FEATURES_LIST;
                setShowFeatures();
                break;
            }
            case SENSOR: {
                headerTextString = SHOWING_SENSORS_LIST;
                setShowSensors();
                break;
            }
            case INFORMATION: {
                headerTextString = SHOWING_INFORMATION_LIST;
                setShowInformation();
                break;
            }
            case SOFTWARE: {
                headerTextString = SHOWING_SOFTWARE_LIST;
                setShowSoftware();
                break;
            }
            case ANDROID: {
                headerTextString = SHOWING_ANDROID_FEATURE_LIST;
                setShowAndroidFeatures();
                break;
            }
        }
    }

    @Override
    public void inflateData() {
        diagnosticsNames = AppConstants.diagnosticsNames;
        sensorNames = AppConstants.sensorNames;
        featureNames = AppConstants.featureNames;
        informationNames = AppConstants.informationNames;
        softwareNames = AppConstants.softwareNames;
        androidNames = AppConstants.androidNames;

        if(getActivity() == null) return;

        Context context = getActivity();

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        infrared = (ConsumerIrManager) context.getSystemService(CONSUMER_IR_SERVICE);
        featureManager = context.getPackageManager();
        cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isFingerprintSupported = ActivityCompat
                    .checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) ==
                    PackageManager.PERMISSION_GRANTED &&
                    Objects.requireNonNull(context.getSystemService(FingerprintManager.class))
                            .isHardwareDetected();
        } else {
            isFingerprintSupported = FingerprintManagerCompat.from(context).isHardwareDetected();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkIfAllPermissionsGiven();
    }

    @Override
    public void changeStatusBarColor() {
        if(getActivity() != null) {
            Window window = getActivity().getWindow();
            if(window == null) return;

            View view = window.getDecorView();
            if(view == null) return;

            int flags = view.getSystemUiVisibility();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
    }

    public void setShowTests() {
        if(headerTextString.equals(SHOWING_DEVICE_TESTS)) return;
        setHeaderTextAndRv();
    }

    public void setShowSensors() {
        if(headerTextString.equals(SHOWING_SENSORS_LIST)) return;
        setHeaderTextAndRv();
    }

    public void setShowFeatures() {
        if(headerTextString.equals(SHOWING_FEATURES_LIST)) return;
        setHeaderTextAndRv();
    }

    private void setShowAndroidFeatures() {
        if(headerTextString.equals(SHOWING_ANDROID_FEATURE_LIST)) return;
        setHeaderTextAndRv();
    }

    private void setShowSoftware() {
        if(headerTextString.equals(SHOWING_SOFTWARE_LIST)) return;
        setHeaderTextAndRv();
    }

    private void setShowInformation() {
        if(headerTextString.equals(SHOWING_INFORMATION_LIST)) return;
        setHeaderTextAndRv();
    }

    @Override
    public void setHeaderTextAndRv() {

        switch (headerTextString) {
            case SHOWING_DEVICE_TESTS:
                fillGenericDataForSelected(TYPE_DIAGNOSTICS);
                break;
            case SHOWING_SENSORS_LIST:
                fillGenericDataForSelected(TYPE_SENSORS);
                break;
            case SHOWING_FEATURES_LIST:
                fillGenericDataForSelected(TYPE_FEATURES);
                break;
            case SHOWING_INFORMATION_LIST:
                fillGenericDataForSelected(TYPE_INFORMATION);
                break;
            case SHOWING_SOFTWARE_LIST:
                fillGenericDataForSelected(TYPE_SOFTWARE);
                break;
            case SHOWING_ANDROID_FEATURE_LIST:
                fillGenericDataForSelected(TYPE_ANDROID);
                break;
        }

        resetSearchText();
        initializeAdapter();
    }

    @Override
    public int checkIfAllPermissionsGiven() {
        List<String> permissionNames = AppConstants.dangerousPermissions;
        permissionsItems = new ArrayList<>();
        int rejectedCount = 0;

        if(getActivity() != null) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for(String p : permissionNames) {
                    boolean b = getActivity().checkSelfPermission(p) == PERMISSION_GRANTED;
                    permissionsItems.add(new PermissionsItem(p, b));
                    if(!b) rejectedCount++;
                }
            }
        }

        return rejectedCount;
    }

    @Override
    public void checkForPresentSensors() {
        if(getActivity() != null)
            getActivity()
                    .getSupportLoaderManager()
                    .initLoader(AppConstants.LOADER_ID, null, this).forceLoad();
    }

    @Override
    public List<PermissionsItem> getPermissionItemsList() {
        return permissionsItems;
    }

    @Override
    public void setEditTextSearchListener() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void filter(String searchText) {
        List<GenericData> newList = new ArrayList<>();
        if(list != null) {
            for(GenericData data : list) {
                if(data.getName().toLowerCase().contains(searchText.toLowerCase())) {
                    newList.add(data);
                }
            }
            if(adapter != null)
                adapter.filterList(newList);
        }
    }

    @Override
    public void hideOrShowSoftKeyboard(boolean haveToShow) {
        if(haveToShow) {
            searchEditText.requestFocus();
            if(inputMethodManager != null)
                inputMethodManager.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT);
        }
        else {
            if(inputMethodManager != null)
                inputMethodManager.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
        }
    }

    @Override
    public void resetSearchText() {
        searchEditText.setText("");
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
        adapter = new GenericDataAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void fillGenericDataForSelected(int type) {
        list = new ArrayList<>();

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
            case TYPE_INFORMATION: {
                dataNames = informationNames;
                dataPresent = informationsPresent;
                break;
            }
            case TYPE_SOFTWARE: {
                dataNames = softwareNames;
                dataPresent = softwaresPresent;
                break;
            }
            case TYPE_ANDROID: {
                dataNames = androidNames;
                dataPresent = androidsPresent;
                break;
            }
        }

        for(int i=0; i<dataNames.size(); i++) {
            list.add(new GenericData(
                    dataNames.get(i),
                    imageUrlMap.get(dataNames.get(i)),
                    dataPresent[i],
                    type));
        }
    }

    @OnClick(R.id.image_five_stars)
    public void giveImageFiveStars() {
        giveFiveStars();
    }

    @OnClick(R.id.give_five_stars)
    public void giveFiveStars() {
        if(getActivity() == null) return;

        Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {startActivity(goToMarket);}
        catch (ActivityNotFoundException e) {
            startActivity(
                    new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getActivity().getPackageName())
                    )
            );
        }
    }

    @OnClick(R.id.give_feedback)
    public void giveFeedback() {
        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.setType("text/email");
        Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "store.robinkamboj@gmail.com" });
        Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback For Sense It All! - Device Test");
        Email.putExtra(Intent.EXTRA_TEXT, "Hi Robin," + "\n");
        startActivity(Intent.createChooser(Email, "Send Feedback:"));
    }

    @OnClick(R.id.search)
    public void setSearch() {

        Animation fadeInAnimation = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
        fadeInAnimation.setDuration(300);

        if(!isSearching) {
            appHeaderText.setVisibility(GONE);
            searchEditText.setVisibility(VISIBLE);

            fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    searchImage.setClickable(false);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    searchImage.setClickable(true);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            searchEditText.startAnimation(fadeInAnimation);

            hideOrShowSoftKeyboard(true);

            searchImage.setImageResource(R.drawable.baseline_clear_black_48);
            if(getActivity() != null)
                searchImage.setColorFilter(ContextCompat.getColor(getActivity(), R.color.red_shade_four));
        }
        else {

            searchEditText.setText("");

            appHeaderText.setVisibility(VISIBLE);
            searchEditText.setVisibility(GONE);

            fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    searchImage.setClickable(false);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    searchImage.setClickable(true);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            appHeaderText.startAnimation(fadeInAnimation);

            hideOrShowSoftKeyboard(false);

            searchImage.setImageResource(R.drawable.baseline_search_black_48);
            if(getActivity() != null)
                searchImage.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorMajorDark));
        }
        isSearching = !isSearching;
    }

    @OnClick(R.id.menu_settings)
    public void setMenuSettings() {

    }

    @NonNull
    @Override
    public Loader<boolean[][]> onCreateLoader(int id, @Nullable Bundle args) {
        //noinspection ConstantConditions
        return new IsPresentLoader(getActivity(),
                sensorManager,
                vibrator,
                infrared,
                featureManager,
                cameraManager,
                isFingerprintSupported,
                AppConstants.diagnosticsNames,
                AppConstants.sensorNames,
                AppConstants.featureNames,
                AppConstants.informationNames,
                AppConstants.softwareNames,
                AppConstants.androidNames);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<boolean[][]> loader, boolean[][] isPresent) {
        this.sensorsPresent = isPresent[0];
        this.featuresPresent = isPresent[1];
        this.diagnosticsPresent = isPresent[2];

        this.informationsPresent = isPresent[3];
        this.softwaresPresent = isPresent[4];
        this.androidsPresent = isPresent[5];

        setHeaderTextAndRv();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<boolean[][]> loader) {

    }
}
