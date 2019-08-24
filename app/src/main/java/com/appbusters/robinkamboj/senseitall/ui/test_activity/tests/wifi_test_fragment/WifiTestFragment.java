package com.appbusters.robinkamboj.senseitall.ui.test_activity.tests.wifi_test_fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.others.WifiScanInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class WifiTestFragment extends Fragment implements WifiTestInterface {

    private WifiInfo wifiInfo;
    private WifiManager wifiManager;
    private List<WifiScanInfo> wifiScanInfoItems;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.wifi_state_text)
    TextView wifi_state_text;

    public WifiTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_wifi_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        initialize();

        setAdapter();
    }

    @Override
    public void initialize() {
        if(getActivity() == null) return;

        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if(wifiManager != null) {
            wifiInfo = wifiManager.getConnectionInfo();

            switch (wifiManager.getWifiState()) {
                case WifiManager.WIFI_STATE_DISABLED: {
                    wifi_state_text.setText(R.string.wifi_state_disabled);
                    break;
                }
                case WifiManager.WIFI_STATE_DISABLING: {
                    wifi_state_text.setText(R.string.wifi_state_disabling);
                    break;
                }
                case WifiManager.WIFI_STATE_ENABLED: {
                    wifi_state_text.setText(R.string.wifi_state_enabled);
                    break;
                }
                case WifiManager.WIFI_STATE_ENABLING: {
                    wifi_state_text.setText(R.string.wifi_state_enabling);
                    break;
                }
                case WifiManager.WIFI_STATE_UNKNOWN: {
                    wifi_state_text.setText(R.string.wifi_state_unknown);
                    break;
                }
            }

            wifiScanInfoItems = new ArrayList<>();

            List<ScanResult> tempList = wifiManager.getScanResults();
            if(tempList != null && tempList.size() > 0) {
                for(ScanResult res : tempList) {
                    wifiScanInfoItems.add(new WifiScanInfo(
                            res.SSID, res.capabilities, res.level, res.frequency, res.timestamp, res.BSSID
                            )
                    );
                }
            }
        }
    }

    @Override
    public void setAdapter() {
        if(getActivity() == null) return;

        if(wifiScanInfoItems.size() == 0) {
            Toast.makeText(getActivity(), "No WiFi bonded devices found", Toast.LENGTH_SHORT).show();
            return;
        }

        WifiScanAdapter adapter = new WifiScanAdapter(getActivity(), wifiScanInfoItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    class WifiScanAdapter extends RecyclerView.Adapter<WifiScanAdapter.WifiScanHolder> {

        private Context context;
        private List<WifiScanInfo> list;

        public WifiScanAdapter(Context context, List<WifiScanInfo> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public WifiScanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            context = parent.getContext();
            return new WifiScanHolder(
                    LayoutInflater.from(context).inflate(R.layout.layout_view_wifi, parent, false)
            );
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull WifiScanHolder holder, int position) {
            WifiScanInfo item = list.get(position);
            holder.name.setText(item.getName());
            holder.level.setText("Level:    " + item.getLevel() + " dB");
            holder.capabilities.setText("Capabilities:    " + item.getCapabilities());
            holder.frequency.setText("Frequency:    " + item.getFrequency()+" MHz");
            holder.timestamp.setText("Timestamp:    " + String.valueOf(item.getTimestamp()/1000000)+" secs");
            holder.mac.setText("MAC:    " + item.getMac());
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        class WifiScanHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.namesc)
            TextView name;

            @BindView(R.id.timestamp)
            TextView timestamp;

            @BindView(R.id.level)
            TextView level;

            @BindView(R.id.capabilities)
            TextView capabilities;

            @BindView(R.id.frequency)
            TextView frequency;

            @BindView(R.id.mac)
            TextView mac;

            WifiScanHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
