package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.gravity_test_fragment;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Html;
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
public class GravityTestFragment extends Fragment implements GravityTestInterface {

    private final Handler mHandler = new Handler();
    private Runnable mTimer;
    private LineGraphSeries<DataPoint> mSeriesX, mSeriesY, mSeriesZ;
    private double graph2LastXValue = 5d;
    private DecimalFormat decimalFormat;
//    private double graph2LastYValue = 5d;
//    private double graph2LastZValue = 5d;

    private Sensor sensor;
    private SensorManager sensorManager;
    SensorEventListener sensorEventListener;
//
//    private Runnable mTimer2;
//    private double graph2LastXValue = 5d;
//    private final Handler mHandler = new Handler();
//    private LineGraphSeries<DataPoint> graphSeries = new LineGraphSeries<>();
//    private int counter = 1;
    private float valueX = 0;
    private float valueY = 0;
    private float valueZ = 0;

    @BindView(R.id.graph_view)
    GraphView graph2;

    @BindView(R.id.x_val)
    TextView xValue;

    @BindView(R.id.y_val)
    TextView yValue;

    @BindView(R.id.z_val)
    TextView zValue;

    public GravityTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gravity_test, container, false);
        setup(v);

        mSeriesX = new LineGraphSeries<>();
        mSeriesY = new LineGraphSeries<>();
        mSeriesZ = new LineGraphSeries<>();
        mSeriesX.setColor(getResources().getColor(android.R.color.holo_red_dark));
        mSeriesY.setColor(getResources().getColor(android.R.color.holo_green_dark));
        mSeriesZ.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        graph2.addSeries(mSeriesX);
        graph2.addSeries(mSeriesY);
        graph2.addSeries(mSeriesZ);
        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setMinX(0);
        graph2.getViewport().setMaxX(40);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        initialize();
    }

    @Override
    public void initialize() {
        decimalFormat = new DecimalFormat("#.##");

        if(getActivity() == null) return;
        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);

        if(sensorManager == null) return;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event != null) {
                    valueX = event.values[0];
                    valueY = event.values[1];
                    valueZ = event.values[2];
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
                mSeriesX.appendData(new DataPoint(graph2LastXValue, valueX), true, 40);
                mSeriesY.appendData(new DataPoint(graph2LastXValue, valueY), true, 40);
                mSeriesZ.appendData(new DataPoint(graph2LastXValue, valueZ), true, 40);
                xValue.setText(
                        Html.fromHtml(
                                getString(R.string.x) + " " + decimalFormat.format(valueX) + " " +  "m/s<sup>2</sup>"
                        )
                );
                yValue.setText(
                        Html.fromHtml(
                                getString(R.string.y) + " " + decimalFormat.format(valueY) + " " + "m/s<sup>2</sup>"
                        )
                );
                zValue.setText(
                        Html.fromHtml(
                                getString(R.string.z) + " " + decimalFormat.format(valueZ) + " " + "m/s<sup>2</sup>"
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
