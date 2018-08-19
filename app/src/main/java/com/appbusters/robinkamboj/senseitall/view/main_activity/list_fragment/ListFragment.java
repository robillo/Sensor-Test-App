package com.appbusters.robinkamboj.senseitall.view.main_activity.list_fragment;


import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
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
import android.support.v7.widget.CardView;
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
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.DIAGNOSTIC;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FEATURE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.INFORMATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RATE_APP;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.RATE_YOUR_EXPERIENCE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_DEVICE_TESTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_FEATURES_LIST;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SHOWING_SENSORS_LIST;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOFTWARE;
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

    private InputMethodManager inputMethodManager;
    private List<GenericData> list;
    private List<PermissionsItem> permissionsItems;
    private int rejectedCount;
    public boolean isSearching = false;
    private GenericDataAdapter adapter = null;
    private String headerTextString;

    private List<String> sensorNames;
    private List<String> featureNames;
    private List<String> diagnosticsNames;

    private boolean[] sensorsPresent;
    private boolean[] featuresPresent;
    private boolean[] diagnosticsPresent;

    @BindView(R.id.app_header_text)
    TextView appHeaderText;

    @BindView(R.id.edit_text_search)
    EditText searchEditText;

    @BindView(R.id.search)
    ImageView searchImage;

    @BindView(R.id.menu_settings)
    ImageView settingsMenuImage;

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

                break;
            }
            case SOFTWARE: {

                break;
            }
            case RATE_APP: {
                headerTextString = RATE_YOUR_EXPERIENCE;
                setRateExperience();
                break;
            }
        }
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
        if(getActivity() != null) {
            Window window = getActivity().getWindow();
            View view = window.getDecorView();
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

        if(listScreen.getVisibility() == GONE) {
            listScreen.setVisibility(VISIBLE);
            rateExperienceScreen.setVisibility(GONE);
        }
    }

    public void setShowSensors() {
        if(headerTextString.equals(SHOWING_SENSORS_LIST)) return;
        setHeaderTextAndRv();

        if(listScreen.getVisibility() == GONE) {
            listScreen.setVisibility(VISIBLE);
            rateExperienceScreen.setVisibility(GONE);
        }
    }

    public void setShowFeatures() {
        if(headerTextString.equals(SHOWING_FEATURES_LIST)) return;
        setHeaderTextAndRv();

        if(listScreen.getVisibility() == GONE) {
            listScreen.setVisibility(VISIBLE);
            rateExperienceScreen.setVisibility(GONE);
        }
    }

    public void setRateExperience() {
        if(headerTextString.equals(RATE_YOUR_EXPERIENCE)) return;
        setHeaderTextAndRv();

        if(rateExperienceScreen.getVisibility() == GONE) {
            listScreen.setVisibility(GONE);
            rateExperienceScreen.setVisibility(VISIBLE);
        }
    }

    @Override
    public void setHeaderTextAndRv() {
        togglePermissionCardVisibility();

        Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right_activity);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(toolbar.getVisibility() == VISIBLE) toolbar.setVisibility(GONE);
                rateExperienceScreen.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeIn.setDuration(100);

        switch (headerTextString) {
            case SHOWING_DEVICE_TESTS: {
                turnOnHighlight(TYPE_DIAGNOSTICS);
                fillGenericDataForSelected(TYPE_DIAGNOSTICS);
                resetSearchText();
                break;
            }
            case SHOWING_SENSORS_LIST: {
                turnOnHighlight(TYPE_SENSORS);
                fillGenericDataForSelected(TYPE_SENSORS);
                resetSearchText();
                break;
            }
            case SHOWING_FEATURES_LIST: {
                turnOnHighlight(TYPE_FEATURES);
                fillGenericDataForSelected(TYPE_FEATURES);
                resetSearchText();
                break;
            }
            case RATE_YOUR_EXPERIENCE: {
                turnOnHighlight(TYPE_RATE);
                if(rateExperienceScreen.getVisibility() == GONE) {
                    listScreen.setVisibility(GONE);
                    rateExperienceScreen.startAnimation(fadeIn);
                    if(isSearching) setSearch();
                }
                break;
            }
        }

        initializeAdapter();
    }

    @Override
    public void turnOnHighlight(int type) {
        toggleToolbarVisibility(type);
    }

    @Override
    public int checkIfAllPermissionsGiven() {
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
            permissionsCard.setVisibility(GONE);
        else
            permissionsCard.setVisibility(VISIBLE);

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
    public void togglePermissionCardVisibility() {
        if(headerTextString.equals(RATE_YOUR_EXPERIENCE))
            permissionsCard.setVisibility(GONE);
        else {
            if(rejectedCount > 0) permissionsCard.setVisibility(VISIBLE);
            else permissionsCard.setVisibility(GONE);
        }
    }

    @Override
    public List<PermissionsItem> getPermissionItemsList() {
        return permissionsItems;
    }

    @Override
    public void toggleToolbarVisibility(int type) {
        switch (type) {
            case TYPE_DIAGNOSTICS: {
                recyclerView.setVisibility(VISIBLE);
                if(toolbar.getVisibility() == GONE) {
                    toolbar.setVisibility(VISIBLE);
                }
                break;
            }
            case TYPE_FEATURES: {
                recyclerView.setVisibility(VISIBLE);
                if(toolbar.getVisibility() == GONE) {
                    toolbar.setVisibility(VISIBLE);
                }
                break;
            }
            case TYPE_SENSORS: {
                recyclerView.setVisibility(VISIBLE);
                if(toolbar.getVisibility() == GONE) {
                    toolbar.setVisibility(VISIBLE);
                }
                break;
            }
            case TYPE_RATE: {
                recyclerView.setVisibility(GONE);
                toolbar.setVisibility(GONE);
                break;
            }
        }
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
            case TYPE_RATE: {

                break;
            }
        }

        for(int i=0; i<dataNames.size(); i++) {
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
        }
    }

    @OnClick(R.id.card_permissions)
    public void askPermissions() {
        if(getActivity() != null)
            ((MainActivity) getActivity()).setRequestFragment();
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
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
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
//        Toast.makeText(getActivity(), "Settings Bottom Sheet Menu", Toast.LENGTH_SHORT).show();
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
