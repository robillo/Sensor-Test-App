package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.pressure_sensor_test;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.SENSOR_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class PressureTestFragment extends Fragment implements PressureTestInterface {


    private final Handler mHandler = new Handler();
    private Runnable mTimer;
    private LineGraphSeries<DataPoint> mSeriesX;
    private Sensor sensor;
    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;
    private float atmPressure = 0;
    private double graph2LastXValue = 5d;
    private DecimalFormat decimalFormat;

    public PressureTestFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.graph_view)
    GraphView graphView;

    @BindView(R.id.x_val)
    TextView xValue;

    @BindView(R.id.y_val)
    TextView yValue;

    @BindView(R.id.z_val)
    TextView zValue;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the lv for this fragment
        View v = inflater.inflate(R.layout.fragment_pressure_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        yValue.setVisibility(View.GONE);
        zValue.setVisibility(View.GONE);

        decimalFormat = new DecimalFormat("#.##");

        mSeriesX = new LineGraphSeries<>();
        mSeriesX.setColor(getResources().getColor(android.R.color.holo_red_dark));
        graphView.addSeries(mSeriesX);
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(40);

        if(getActivity() == null) return;
        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);

        if(sensorManager == null) return;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event != null) {
                    atmPressure = event.values[0];
                    Log.e("TAG", " " + atmPressure);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(sensorManager != null && sensor != null && sensorEventListener != null)
            sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_UI);

        mTimer = new Runnable() {
            @Override
            public void run() {
                graph2LastXValue += 1d;
                //noinspection SuspiciousNameCombination
                mSeriesX.appendData(new DataPoint(graph2LastXValue, atmPressure), true, 40);
                xValue.setText(
                        Html.fromHtml(
                                "Atmospheric Pressure Is " + decimalFormat.format(atmPressure) + " mbar"
                        )
                );
                mHandler.postDelayed(this, 200);
            }
        };
        mHandler.postDelayed(mTimer, 1000);
    }

    @Override
    public void onPause() {
        if(sensorManager != null && sensorEventListener != null)
            sensorManager.unregisterListener(sensorEventListener);

        mHandler.removeCallbacks(mTimer);
        super.onPause();
    }
}
