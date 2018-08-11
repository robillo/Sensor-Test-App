package com.appbusters.robinkamboj.senseitall.view.test_activity.graph_fragment_abstract;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
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

    protected Sensor sensor;
    protected SensorManager sensorManager;
    protected SensorEventListener sensorEventListener;

    protected float valueX = 0;
    protected float valueY = 0;
    protected float valueZ = 0;

    @BindView(R.id.graph_view)
    GraphView graphView;

    @BindView(R.id.x_val)
    TextView xValue;

    @BindView(R.id.y_val)
    TextView yValue;

    @BindView(R.id.z_val)
    TextView zValue;

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

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

        initialize();
    }

    @Override
    public void initialize() {

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
