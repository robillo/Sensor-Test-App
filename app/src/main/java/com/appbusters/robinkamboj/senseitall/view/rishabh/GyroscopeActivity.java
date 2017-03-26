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
    float currentRotVector[] =  { 1, 0, 0, 0 };
    private float RotAngle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);

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
                    axisX = currentRotVector[1];
                    axisY = currentRotVector[2];
                    axisZ = currentRotVector[3];

//                    x.setText("X:    " + gameRotationVectorValues[0]);
//                    y.setText("Y:    " + gameRotationVectorValues[1]);
//                    z.setText("Z:    " + gameRotationVectorValues[2]);
//                    cos.setText("Cos:    " + gameRotationVectorValues[3]);


                }
                timestamp = event.timestamp;

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
