package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

import static android.R.attr.data;

public class RotationActivity extends AppCompatActivity {


    private static final String TAG = "ROTN";
    private SensorManager mSensorManager;
    private String results[];
    private Sensor sensor;
    TriggerEventListener mTriggerEventListener;
    private TextView name, vendor, version, maximum_range, power, minimum_delay, maximum_delay, resolution;
    private float[] gameRotationVectorValues = null;

//    private Cube mCube;

    private final float[] mRotationMatrix = new float[16];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        mTriggerEventListener = new TriggerEventListener() {
            @Override
            public void onTrigger(TriggerEvent event) {
                // Do work
            }
        };
        name = (TextView) findViewById(R.id.name);
        vendor = (TextView) findViewById(R.id.vendor);
        version = (TextView) findViewById(R.id.version);
        maximum_range = (TextView) findViewById(R.id.maximum_range);
        power = (TextView) findViewById(R.id.power);
        minimum_delay = (TextView) findViewById(R.id.minimum_delay);
        maximum_delay = (TextView) findViewById(R.id.maximum_delay);
        resolution = (TextView) findViewById(R.id.resolution);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);


        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                gameRotationVectorValues = event.values.clone();
                Log.d(TAG, "x " + gameRotationVectorValues[0]);
                Log.d(TAG, "y " + gameRotationVectorValues[1]);
                Log.d(TAG, "z " + gameRotationVectorValues[2]);
                Log.d(TAG, "cos " + gameRotationVectorValues[3]);
//                data.put("y", gameRotationVectorValues[1]);
//                data.put("z", gameRotationVectorValues[2]);
//                data.put("cos", gameRotationVectorValues[3]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        mSensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_GAME);

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                setResults();
                setTextviews();
            }
        });
        mSensorManager.requestTriggerSensor(mTriggerEventListener, sensor);
    }

    private void setResults(){
        results = new String[]{
                "Name:    " + sensor.getName(), "Vendor:    " + sensor.getVendor(),
                "Version:    " + String.valueOf(sensor.getVersion()),
                "Maximum Range:    " + String.valueOf(sensor.getMaximumRange()),
                "Power:    " + String.valueOf(sensor.getPower()),
                "Minimum Delay:    " + String.valueOf(sensor.getMinDelay()),
                "Maximum Delay:    " + String.valueOf(sensor.getMaxDelay()),
                "Resolution:    " + String.valueOf(sensor.getResolution())
        };
    }

    private void setTextviews(){
        name.setText(results[0]);
        vendor.setText(results[1]);
        version.setText(results[2]);
        maximum_range.setText(results[3]);
        power.setText(results[4]);
        minimum_delay.setText(results[5]);
        maximum_delay.setText(results[6]);
        resolution.setText(results[7]);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.requestTriggerSensor(mTriggerEventListener, sensor);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.cancelTriggerSensor(mTriggerEventListener, sensor);
    }

}
