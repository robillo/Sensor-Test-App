package com.appbusters.robinkamboj.senseitall.view.test_activity.graph_fragment_abstract;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.test_activity.TestActivity;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GraphFragment extends Fragment implements GraphInterface {

    private final Handler mHandler = new Handler();
    private Runnable mTimer;
    private LineGraphSeries<DataPoint> mSeriesX, mSeriesY, mSeriesZ;
    private double graph2LastXValue = 5d;
    protected DecimalFormat decimalFormat;
    private Snackbar snackbar;

    protected Sensor sensor;
    protected SensorManager sensorManager;
    protected SensorEventListener sensorEventListener;
    protected GenericData genericData;
    protected String sensorName;

    protected float valueX = 0;
    protected float valueY = 0;
    protected float valueZ = 0;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.graph_view)
    GraphView graphView;

    @BindView(R.id.units)
    TextView units;

    @BindView(R.id.x_val)
    TextView xValue;

    @BindView(R.id.y_val)
    TextView yValue;

    @BindView(R.id.z_val)
    TextView zValue;

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        if(getActivity() != null) genericData = ((TestActivity) getActivity()).intentData;
        if(genericData != null) sensorName = genericData.getName();

        mSeriesX = new LineGraphSeries<>();
        mSeriesY = new LineGraphSeries<>();
        mSeriesZ = new LineGraphSeries<>();
        mSeriesX.setColor(getResources().getColor(android.R.color.holo_red_dark));
        mSeriesY.setColor(getResources().getColor(android.R.color.holo_green_dark));
        mSeriesZ.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        graphView.addSeries(mSeriesX);
        graphView.addSeries(mSeriesY);
        graphView.addSeries(mSeriesZ);
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(40);

        snackbar = Snackbar.make(coordinatorLayout, "error displaying value", 400);
        View view = snackbar.getView();
        TextView textView = view.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        units.setText(Html.fromHtml(String.format("%s %s", getString(R.string.units), returnUnits(sensorName))));

        initialize();
    }

    @Override
    public void initialize() {

    }

    @Override
    public String returnUnits(String sensor) {
        switch (sensor) {
            case AppConstants.SENSOR_LINEAR_ACCELERATION:
            case AppConstants.SENSOR_ACCELEROMETER:
            case AppConstants.SENSOR_GRAVITY: {
                return " m/s<sup>2</sup>";
            }
            case AppConstants.SENSOR_GYROSCOPE: {
                return " rad/s";
            }
            case AppConstants.SENSOR_MAGNETIC_FIELD: {
                return " uT";
            }
            case AppConstants.SENSOR_ROTATION_VECTOR: {
                return " Magnitude x sin(Angle)";
            }
            default: {
                return "nil";
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(sensorManager != null && sensor != null && sensorEventListener != null)
            sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_UI);

        mTimer = new Runnable() {
            @Override
            public void run() {
                try {
                    graph2LastXValue += 1d;
                    //noinspection SuspiciousNameCombination
                    mSeriesX.appendData(new DataPoint(graph2LastXValue, valueX), true, 40);
                    mSeriesY.appendData(new DataPoint(graph2LastXValue, valueY), true, 40);
                    mSeriesZ.appendData(new DataPoint(graph2LastXValue, valueZ), true, 40);
                    try {
                        xValue.setText(
                                Html.fromHtml(
                                        getString(R.string.x) + " " + decimalFormat.format(valueX)
                                )
                        );
                        yValue.setText(
                                Html.fromHtml(
                                        getString(R.string.y) + " " + decimalFormat.format(valueY)
                                )
                        );
                        zValue.setText(
                                Html.fromHtml(
                                        getString(R.string.z) + " " + decimalFormat.format(valueZ)
                                )
                        );
                    }
                    catch (Exception e) {
                        if(!snackbar.isShown()) snackbar.show();
                    }
                } catch (Exception ignored) {
                    snackbar = Snackbar.make(coordinatorLayout, "seems like some error has occurred", 400);
                    View view = snackbar.getView();
                    TextView textView = view.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    if(!snackbar.isShown()) snackbar.show();
                }
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
