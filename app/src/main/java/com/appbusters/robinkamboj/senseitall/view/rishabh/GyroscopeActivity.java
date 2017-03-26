package com.appbusters.robinkamboj.senseitall.view.rishabh;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.appbusters.robinkamboj.senseitall.R;

public class GyroscopeActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];
    private float timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                // This time step's delta rotation to be multiplied by the current rotation
                // after computing it from the gyro sample data.
                if (timestamp != 0) {
                    final float dT = (event.timestamp - timestamp) * NS2S;
                    // Axis of the rotation sample, not normalized yet.
                    float axisX = event.values[0];
                    float axisY = event.values[1];
                    float axisZ = event.values[2];

                    // Calculate the angular speed of the sample
                    float omegaMagnitude = (float) Math.sqrt(axisX*axisX + axisY*axisY + axisZ*axisZ);

                    // Normalize the rotation vector if it's big enough to get the axis
//                    if (omegaMagnitude > EPSILON) {
//                        axisX /= omegaMagnitude;
//                        axisY /= omegaMagnitude;
//                        axisZ /= omegaMagnitude;
//                    }

                    // Integrate around this axis with the angular speed by the time step
                    // in order to get a delta rotation from this sample over the time step
                    // We will convert this axis-angle representation of the delta rotation
                    // into a quaternion before turning it into the rotation matrix.
                    float thetaOverTwo = omegaMagnitude * dT / 2.0f;
                    float sinThetaOverTwo = (float) Math.sin(thetaOverTwo);
                    float cosThetaOverTwo = (float) Math.cos(thetaOverTwo);
                    deltaRotationVector[0] = sinThetaOverTwo * axisX;
                    deltaRotationVector[1] = sinThetaOverTwo * axisY;
                    deltaRotationVector[2] = sinThetaOverTwo * axisZ;
                    deltaRotationVector[3] = cosThetaOverTwo;
                }
                timestamp = event.timestamp;
                float[] deltaRotationMatrix = new float[9];
                SensorManager.getRotationMatrixFromVector(deltaRotationMatrix, deltaRotationVector);
                // User code should concatenate the delta rotation we computed with the current rotation
                // in order to get the updated rotation.
                // rotationCurrent = rotationCurrent * deltaRotationMatrix;
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

    }
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(sensorEventListener);
    }
}
