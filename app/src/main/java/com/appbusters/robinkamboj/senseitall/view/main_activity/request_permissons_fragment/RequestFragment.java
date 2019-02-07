package com.appbusters.robinkamboj.senseitall.view.main_activity.request_permissons_fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.PermissionsItem;
import com.appbusters.robinkamboj.senseitall.preferences.AppPreferencesHelper;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.dashboard_activity.DashboardActivity;
import com.appbusters.robinkamboj.senseitall.view.main_activity.MainActivity;
import com.appbusters.robinkamboj.senseitall.view.main_activity.request_permissons_fragment.adapter.RequestAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment implements RequestFragmentInterface {

    public static final int FROM_DASHBOARD = 0;
    public static final int FROM_MAIN = 1;
    private int FROM_ARG;

    List<PermissionsItem> permissionsItems;

    @BindView(R.id.go_back)
    FrameLayout goBack;

    @BindView(R.id.load_progress)
    ProgressBar loadProgress;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.pending_text)
    TextView pendingText;

    public RequestFragment() {
        // Required empty public constructor
    }

    public static RequestFragment newInstance() {
        Bundle args = new Bundle();
        args.putInt(AppConstants.FROM_ARG_IN_REQUEST, FROM_MAIN);
        RequestFragment fragment = new RequestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_request_permissions, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        if(getActivity() == null) return;
        if(getArguments() == null) return;

        FROM_ARG = getArguments().getInt(AppConstants.FROM_ARG_IN_REQUEST);

        if(FROM_ARG == FROM_DASHBOARD) {
            ((DashboardActivity) getActivity()).refreshPermissionsRecycler();
        }
        else {
            ((MainActivity) getActivity()).refreshPermissionsRecycler();
        }
    }

    @Override
    public void setStatusBarColor() {
        @SuppressWarnings("ConstantConditions") Window window = getActivity().getWindow();
        View view = window.getDecorView();
        int flags = view.getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        view.setSystemUiVisibility(flags);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    @Override
    public void showRecycler(List<PermissionsItem> permissionsItems) {

        List<PermissionsItem> items = new ArrayList<>();
        List<String> rawPermissions = new ArrayList<>();
        for(PermissionsItem item : permissionsItems) {
            rawPermissions.add(item.getPermissionName());
            int index = item.getPermissionName().lastIndexOf('.');
            String tempItem = item.getPermissionName().substring(++index);
            tempItem = tempItem.replaceAll("_", " ");
            items.add(new PermissionsItem(tempItem, item.isGranted()));
        }

        if(getActivity() == null) return;

        RequestAdapter adapter = new RequestAdapter(
                getActivity(),
                items,
                rawPermissions,
                new AppPreferencesHelper(getActivity())
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updatePermissionsListAndRecycler(List<PermissionsItem> permissionsItems) {
        this.permissionsItems = permissionsItems;
        showRecycler(permissionsItems);
    }

    @Override
    public void updatePendingCount(int pendingCount) {
        loadProgress.setProgress(
                (AppConstants.dangerousPermissions.size() - pendingCount)*100/AppConstants.dangerousPermissions.size()
        );
        if(pendingCount != 0) {
            pendingText.setText(
                    String.format(
                            "%s out of %s permissions given",
                            AppConstants.dangerousPermissions.size() - pendingCount,
                            AppConstants.dangerousPermissions.size()
                    )
            );
        }
        else {
            pendingText.setText(R.string.press_back_to_get_started);
            goBack.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.go_back)
    public void setGoBack() {
        if(getActivity() != null) getActivity().onBackPressed();
    }
}
