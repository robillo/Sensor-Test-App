package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class MagneticActivity extends AppCompatActivity implements SensorEventListener{

    private static final String TAG = "MAGNETIC ACTIVITY";

    String sensor_name;
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
        textView= (TextView) findViewById(R.id.tvMG);
        Intent i = getIntent();
        sensor_name = i.getStringExtra("sensorName");
//        textView = (TextView) findViewById(R.id.textView);
//        textView.setText(sensor_name);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mGeomagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

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
                        calib.setText(sensorEvent.sensor.getName().substring(0,17));
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
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

}
