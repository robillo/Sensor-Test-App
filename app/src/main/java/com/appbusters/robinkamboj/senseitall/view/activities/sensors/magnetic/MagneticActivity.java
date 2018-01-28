package com.appbusters.robinkamboj.senseitall.view.activities.sensors.magnetic;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

public class MagneticActivity extends AppCompatActivity implements SensorEventListener{

    private static final String TAG = "MAGNETIC ACTIVITY";

    //MPAndroidChart
    private PieChart mChart;

    private static final int TEST_GRAV = Sensor.TYPE_ACCELEROMETER;
    private static final int TEST_MAG = Sensor.TYPE_MAGNETIC_FIELD;
    private final float alpha = (float) 0.8;
    private float gravity[] = new float[3];
    private float magnetic[] = new float[3];
    private SensorManager mSensorManager;
    /** Magnetometer spec. */
    private TextView vendor;
    private TextView resolution;
    private TextView maximumRange;

    /** Magnetic field coordinates measurements. */
    private TextView magneticXTextView, textView;
    private TextView magneticYTextView;
    private TextView magneticZTextView;
    private TextView calib;
    private TextView maxcl;
    private TextView present;


    /** Sensors. */
    private Sensor mAccelerometer;
    private Sensor mGeomagnetic;
    private float[] accelerometerValues;
    private float[] geomagneticValues;

    /** Flags. */
    private boolean specDefined = false;

    /** Rates. */
    private float nanoTtoGRate = 0.00001f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mGeomagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        //MPAndroidChart
//        tvX = (TextView) findViewById(R.id.tvXMax);
//        tvY = (TextView) findViewById(R.id.tvYMax);
//        mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
//        mSeekBarX.setOnSeekBarChangeListener(this);
//        mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);
//        mSeekBarY.setOnSeekBarChangeListener(this);

        mChart = findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);
//        mChart.setCenterTextTypeface(mTfLight);
        mChart.setCenterText(generateCenterSpannableText());
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);
        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);
        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);
        mChart.setDrawCenterText(true);
        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);
        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setEntryLabelTextSize(12f);

        vendor = (TextView) findViewById(R.id.vendorMag);
        resolution = (TextView) findViewById(R.id.resolutionMag);
        maximumRange = (TextView) findViewById(R.id.maximumRange);
        calib = (TextView) findViewById(R.id.calib);
        magneticXTextView = (TextView) findViewById(R.id.magneticX);
        magneticYTextView = (TextView) findViewById(R.id.magneticY);
        magneticZTextView = (TextView) findViewById(R.id.magneticZ);
        maxcl = (TextView) findViewById(R.id.maxcl);
        present = (TextView) findViewById(R.id.present);

        mSensorManager.registerListener(this, mGeomagnetic, SensorManager.SENSOR_DELAY_FASTEST);


    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("Sense It All\nDevice Test");
        s.setSpan(new RelativeSizeSpan(1.0f), 0, 12, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 10, 12, 0);

        s.setSpan(new ForegroundColorSpan(Color.GRAY), 12, s.length(), 0);
        s.setSpan(new RelativeSizeSpan(.8f), 12, s.length(), 0);

        return s;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
            return;
        }
        synchronized (this) {
                    if (!specDefined) {
                        Log.d(TAG, "onSensorChanged: LOG");
                        vendor.setText(sensorEvent.sensor.getVendor() + " " + sensorEvent.sensor.getName());
                        float resolutionValue = sensorEvent.sensor.getResolution() * nanoTtoGRate;
                        resolution.setText(String.valueOf(resolutionValue)+"μT");
                        calib.setText(sensorEvent.sensor.getName().toString());
                        float maximumRangeValue = sensorEvent.sensor.getMaximumRange() * nanoTtoGRate;
                        maximumRange.setText(String.valueOf(maximumRangeValue*1000000)+"μT");
                        magneticXTextView.setText(String.valueOf(sensorEvent.values[0])+"μT");
                        magneticYTextView.setText(String.valueOf(sensorEvent.values[1])+"μT");
                        magneticZTextView.setText(String.valueOf(sensorEvent.values[2])+"μT");
                        maxcl.setText(String.valueOf(sensorEvent.sensor.getPower())+" Amp");
//                        sensorEvent.sensor.
                        present.setText("Yes");
                        Log.d(TAG, "fifo........: "+  sensorEvent.sensor.getFifoReservedEventCount());
                    }
            geomagneticValues = sensorEvent.values.clone();
            }
        Sensor sensor = sensorEvent.sensor;

            magnetic[0] = sensorEvent.values[0];
            magnetic[1] = sensorEvent.values[1];
            magnetic[2] = sensorEvent.values[2];

            float[] R = new float[9];
            float[] I = new float[9];
            SensorManager.getRotationMatrix(R, I, gravity, magnetic);
            float [] A_D = sensorEvent.values.clone();
            float [] A_W = new float[3];
            A_W[0] = R[0] * A_D[0] + R[1] * A_D[1] + R[2] * A_D[2];
            A_W[1] = R[3] * A_D[0] + R[4] * A_D[1] + R[5] * A_D[2];
            A_W[2] = R[6] * A_D[0] + R[7] * A_D[1] + R[8] * A_D[2];

            Log.d("Field","\nX :"+A_W[0]+"\nY :"+A_W[1]+"\nZ :"+A_W[2]);

            float[] values = new float[] {magnetic[0], magnetic[1], magnetic[2]};
            setData(3, 100, values);
        }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    private void setData(int count, float range, float[] values) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        String[] mParties = new String[] {"Field X", "Field Y", "Field Z"};
        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            float percent = values[i]*100/(values[0] + values[1] + values[2]);
            entries.add(new PieEntry(percent, mParties[i]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Magnetic Field");

        dataSet.setSliceSpace(3f);
//        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        colors.add(getColor(R.color.colorPrimary));
        colors.add(getColor(R.color.colorPrimaryDark));
        colors.add(getColor(R.color.colorPrimaryLight));

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
//        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }
}
