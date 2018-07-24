package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.bluetooth_test_fragment;


import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
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
import com.appbusters.robinkamboj.senseitall.model.others.BluetoothScanInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BluetoothTestFragment extends Fragment implements BluetoothTestInterface {

    private List<BluetoothScanInfo> bluetoothScanInfoItems = new ArrayList<>();

    @BindView(R.id.bluetooth_state_text)
    TextView bluetooth_state_text;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    public BluetoothTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bluetooth_test, container, false);
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
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(!bluetoothAdapter.isEnabled()) bluetoothAdapter.enable();
        Set<BluetoothDevice> bluetoothDevices = bluetoothAdapter.getBondedDevices();
        for(BluetoothDevice device : bluetoothDevices) {
            bluetoothScanInfoItems.add(new BluetoothScanInfo(device.getName(), device.getAddress()));
        }
    }

    @Override
    public void setAdapter() {
        if(getActivity() == null) return;

        if(bluetoothScanInfoItems.size() == 0) {
            Toast.makeText(getActivity(), "No bluetooth bonded devices found", Toast.LENGTH_SHORT).show();
            return;
        }

        BluetoothScanAdapter adapter = new BluetoothScanAdapter(getActivity(), bluetoothScanInfoItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    class BluetoothScanAdapter extends RecyclerView.Adapter<BluetoothScanAdapter.BluetoothScanHolder> {

        private Context context;
        private List<BluetoothScanInfo> list;

        public BluetoothScanAdapter(Context context, List<BluetoothScanInfo> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public BluetoothScanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            context = parent.getContext();
            return new BluetoothScanHolder(
                    LayoutInflater.from(context).inflate(R.layout.layout_view_wifi, parent, false)
            );
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull BluetoothScanHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        class BluetoothScanHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.lvName)
            TextView lvName;

            @BindView(R.id.lvAdd)
            TextView lvAdd;

            BluetoothScanHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
