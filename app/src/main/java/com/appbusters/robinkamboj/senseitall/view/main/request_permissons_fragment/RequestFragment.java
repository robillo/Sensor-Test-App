package com.appbusters.robinkamboj.senseitall.view.main.request_permissons_fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.PermissionsItem;
import com.appbusters.robinkamboj.senseitall.preferences.AppPreferencesHelper;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.main.MainActivity;
import com.appbusters.robinkamboj.senseitall.view.main.request_permissons_fragment.adapter.RequestAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment implements RequestFragmentInterface {

    List<PermissionsItem> permissionsItems;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    public RequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_request_permissions, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        //noinspection ConstantConditions
        permissionsItems = ((MainActivity) getActivity()).getPermissionItemsList();

        showRecycler(permissionsItems);
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

        @SuppressWarnings("ConstantConditions") RequestAdapter adapter = new RequestAdapter(
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

    @OnClick(R.id.go_back)
    public void setGoBack() {
        if(getActivity() != null) getActivity().onBackPressed();
    }
}
