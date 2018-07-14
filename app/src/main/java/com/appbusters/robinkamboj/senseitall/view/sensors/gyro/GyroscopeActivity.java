package com.appbusters.robinkamboj.senseitall.view.sensors.gyro;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class GyroscopeActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];
    private float timestamp;
    private String results[];

    float currentRotVector[] =  { 1, 0, 0, 0 };
    private float RotAngle, xVal, yVal,zVal;
    private TextView name, vendor, version, maximum_range, power, minimum_delay, maximum_delay, resolution, x,y,z,cos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        name = (TextView) findViewById(R.id.name);
        vendor = (TextView) findViewById(R.id.vendor);
        version = (TextView) findViewById(R.id.version);
        maximum_range = (TextView) findViewById(R.id.maximum_range);
        power = (TextView) findViewById(R.id.power);
        minimum_delay = (TextView) findViewById(R.id.minimum_delay);
        maximum_delay = (TextView) findViewById(R.id.maximum_delay);
        resolution = (TextView) findViewById(R.id.resolution);
        x = (TextView) findViewById(R.id.xVal);
        y = (TextView) findViewById(R.id.yVal);
        z = (TextView) findViewById(R.id.zVal);
        cos = (TextView) findViewById(R.id.cos);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                if (timestamp != 0) {
                    final float dT = (event.timestamp - timestamp) * NS2S;
                    float axisX = event.values[0];
                    float axisY = event.values[1];
                    float axisZ = event.values[2];

                    float omegaMagnitude = (float) Math.sqrt(axisX*axisX + axisY*axisY + axisZ*axisZ);

                    // Normalize the rotation vector if it's big enough to get the axis
//                    if (omegaMagnitude > EPSILON) {
//                        axisX /= omegaMagnitude;
//                        axisY /= omegaMagnitude;
//                        axisZ /= omegaMagnitude;
//                    }

                    float thetaOverTwo = omegaMagnitude * dT / 2.0f;
                    float sinThetaOverTwo = (float) Math.sin(thetaOverTwo);
                    float cosThetaOverTwo = (float) Math.cos(thetaOverTwo);

                    deltaRotationVector[0] = sinThetaOverTwo * axisX;
                    deltaRotationVector[1] = sinThetaOverTwo * axisY;
                    deltaRotationVector[2] = sinThetaOverTwo * axisZ;
                    deltaRotationVector[3] = cosThetaOverTwo;


                    currentRotVector[0] = deltaRotationVector[0] * currentRotVector[0] -
                            deltaRotationVector[1] * currentRotVector[1] -
                            deltaRotationVector[2] * currentRotVector[2] -
                            deltaRotationVector[3] * currentRotVector[3];

                    currentRotVector[1] = deltaRotationVector[0] * currentRotVector[1] +
                            deltaRotationVector[1] * currentRotVector[0] +
                            deltaRotationVector[2] * currentRotVector[3] -
                            deltaRotationVector[3] * currentRotVector[2];

                    currentRotVector[2] = deltaRotationVector[0] * currentRotVector[2] -
                            deltaRotationVector[1] * currentRotVector[3] +
                            deltaRotationVector[2] * currentRotVector[0] +
                            deltaRotationVector[3] * currentRotVector[1];

                    currentRotVector[3] = deltaRotationVector[0] * currentRotVector[3] +
                            deltaRotationVector[1] * currentRotVector[2] -
                            deltaRotationVector[2] * currentRotVector[1] +
                            deltaRotationVector[3] * currentRotVector[0];
                    final float rad2deg = (float) (180.0f / Math.PI);
                    RotAngle = currentRotVector[0] * rad2deg;
                     xVal = currentRotVector[1];
                     yVal = currentRotVector[2];
                     zVal = currentRotVector[3];

                    String a, b, c;
                    a = "X Axis:    " + xVal;
                    b = "Y Axis:    " + yVal;
                    c = "Z Axis:    " + zVal;
                    x.setText(a);
                    y.setText(b);
                    z.setText(c);
                    cos.setText("Angle:    " + RotAngle+" rad/s");
                }
                timestamp = event.timestamp;
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

        };

        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_UI);

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                setResults();
                setTextviews();
            }
        });

    }


    private void setResults(){
        if(sensor!=null){
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
    }

    private void setTextviews(){
        if(sensor!=null){
            name.setText(results[0]);
            vendor.setText(results[1]);
            version.setText(results[2]);
            maximum_range.setText(results[3]);
            power.setText(results[4]);
            minimum_delay.setText(results[5]);
            maximum_delay.setText(results[6]);
            resolution.setText(results[7]);
        }
    }



    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(sensorEventListener);
    }
}
