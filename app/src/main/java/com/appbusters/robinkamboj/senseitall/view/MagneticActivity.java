package com.appbusters.robinkamboj.senseitall.view;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class MagneticActivity extends AppCompatActivity implements SensorEventListener{

    public static TextView level,plugged,present,maxcl,status,tech,temp,vol;

    String sensor_name;
    TextView textView;
    int bt;
    private SensorManager mSensorManager;
    /** Magnetometer spec. */
    private TextView vendor;
    private TextView resolution;
    private TextView maximumRange;

    /** Magnetic field coordinates measurements. */
    private TextView magneticXTextView;
    private TextView magneticYTextView;
    private TextView magneticZTextView;

    /** Sensors. */
    private Sensor mAccelerometer;
    private Sensor mGeomagnetic;
    private float[] accelerometerValues;
    private float[] geomagneticValues;

    /** Flags. */
    private boolean specDefined = false;
    private boolean kalmanFiletring = false;

    /** Rates. */
    private float nanoTtoGRate = 0.00001f;
    private final int gToCountRate = 1000000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        sensor_name = i.getStringExtra("sensorName");
//        textView = (TextView) findViewById(R.id.textView);
//        textView.setText(sensor_name);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGeomagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        vendor = (TextView) findViewById(R.id.vendorMag);
        resolution = (TextView) findViewById(R.id.resolutionMag);
        maximumRange = (TextView) findViewById(R.id.maximumRange);

        magneticXTextView = (TextView) findViewById(R.id.magneticX);
        magneticYTextView = (TextView) findViewById(R.id.magneticY);
        magneticZTextView = (TextView) findViewById(R.id.magneticZ);

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mGeomagnetic, SensorManager.SENSOR_DELAY_FASTEST);


    }
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
            return;
        }
        synchronized (this) {
            switch(sensorEvent.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                    accelerometerValues = sensorEvent.values.clone();
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    if (!specDefined) {
                        vendor.setText("Vendor: " + sensorEvent.sensor.getVendor() + " " + sensorEvent.sensor.getName());
                        float resolutionValue = sensorEvent.sensor.getResolution() * nanoTtoGRate;
                        resolution.setText("Resolution: " + resolutionValue);
                        float maximumRangeValue = sensorEvent.sensor.getMaximumRange() * nanoTtoGRate;
                        maximumRange.setText("Maximum range: " + maximumRangeValue);
                    }
                    geomagneticValues = sensorEvent.values.clone();
                    break;
            }
            if (accelerometerValues != null && geomagneticValues != null) {
                float[] Rs = new float[16];
                float[] I = new float[16];

                if (SensorManager.getRotationMatrix(Rs, I, accelerometerValues, geomagneticValues)) {

                    float[] RsInv = new float[16];
                    Matrix.invertM(RsInv, 0, Rs, 0);

                    float resultVec[] = new float[4];
                    float[] geomagneticValuesAdjusted = new float[4];
                    geomagneticValuesAdjusted[0] = geomagneticValues[0];
                    geomagneticValuesAdjusted[1] = geomagneticValues[1];
                    geomagneticValuesAdjusted[2] = geomagneticValues[2];
                    geomagneticValuesAdjusted[3] = 0;
                    Matrix.multiplyMV(resultVec, 0, RsInv, 0, geomagneticValuesAdjusted, 0);

                    for (int i = 0; i < resultVec.length; i++) {
                        resultVec[i] = resultVec[i] * nanoTtoGRate * gToCountRate;
                    }

                    if (kalmanFiletring) {



                    } else {
                        magneticXTextView.setText("x: " + resultVec[0]);
                        magneticYTextView.setText("y: " + resultVec[1]);
                        magneticZTextView.setText("z: " + resultVec[2]);
                    }
                }
            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //nonspection SimplifiableIfStatement

        switch (id){
            case R.id.action_settings:{
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

}
