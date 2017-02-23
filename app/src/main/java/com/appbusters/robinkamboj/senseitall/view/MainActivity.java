package com.appbusters.robinkamboj.senseitall.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.hardware.ConsumerIrManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MA";
    AVLoadingIndicatorView avi,avi_2;
    TextView textView1, textView2;
    Button button;
    boolean[] isPresent;
    PackageManager packageManager;
    Context context;
    private static final int ALL_MY_PERMISSIONS = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permissions={Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA};

        if(!hasPermissions(this, permissions)){
            ActivityCompat.requestPermissions(this, permissions, ALL_MY_PERMISSIONS);
        }

    context = getApplicationContext();
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi_2 = (AVLoadingIndicatorView) findViewById(R.id.avi_2);
        avi.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                List<Sensor> mySensonList = sensorManager.getSensorList(Sensor.TYPE_ALL);

                String[] sensors = getResources().getStringArray(R.array.sensors_list);
                isPresent = isSensorPresent(sensors,mySensonList);

                Log.e("ROBIN:" , Boolean.toString(isPresent[0]) + " " + Boolean.toString(isPresent[1]));

                button.setVisibility(View.VISIBLE);
                avi.hide();
                avi_2.show();
                textView1.setText("Done With Analyzing!");
                textView2.setText("Move To The Next Step?");

            }
        }, 5000);
    }

    //Permissions Helper Method
    public static boolean hasPermissions(Context context, String[] permissions){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && context!=null && permissions!=null){
            for(String permission : permissions){
                if(ActivityCompat.checkSelfPermission(context,permission)!=PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:{
                Intent i = new Intent(this, ListActivity.class);
                i.putExtra("sensors_present", isPresent);
                startActivity(i);
                break;
            }
        }
    }


    void startAnim(){
        avi.show();
        // or avi.smoothToShow();
    }

    void stopAnim(){
        avi.hide();
        // or avi.smoothToHide();
    }

    boolean[] isSensorPresent(String[] sensors, List<Sensor> sensorList){

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        packageManager = getPackageManager();
        boolean[] isPresent = new boolean[sensors.length];

        HashMap<String,Boolean> map  = new HashMap<>();
        List<ApplicationInfo> pm = getPackageManager().getInstalledApplications(PackageManager.GET_ACTIVITIES|PackageManager.GET_PROVIDERS);

        ConsumerIrManager infrared = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);

        for(ApplicationInfo ai:pm){
            Log.d(TAG, "INSTALLED APP: "+ai.toString());
        }
        for(Sensor sensor1: sensorList){
            map.put(sensor1.getName(),false);
            Log.d(TAG, "HashMap: "+sensor1.getName());
        }
        for(FeatureInfo featureInfo :packageManager.getSystemAvailableFeatures()){
            map.put(featureInfo.toString(),false);
            Log.d(TAG, "FEATURE INFO: "+featureInfo.toString());
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            isPresent[0] = true;
        }

        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)){
            isPresent[1] = true;
        }

        if(packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)){
            isPresent[2] = true;
        }

        if(packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI)){
            isPresent[3] = true;
        }

        if(packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)){
            isPresent[4] = true;
        }


        if(packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_GSM)){
            isPresent[5] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER)){
            isPresent[6] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS)){
            isPresent[7] = true;
        }
        if(Build.getRadioVersion()!="unknown"|| Build.getRadioVersion()!=null){
            isPresent[8] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN)){
            isPresent[9] = true;
        }
            isPresent[10] = true;
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CPU)){
            isPresent[11] = true;
//        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_MIDI)){
            isPresent[12] = true;
        }
        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        if(v.hasVibrator()){
            isPresent[13] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            isPresent[14] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_USB_ACCESSORY)){
            isPresent[15] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_AUDIO_OUTPUT)){
            isPresent[16] = true;
        }
        if(Build.VERSION.SDK_INT> Build.VERSION_CODES.DONUT){
            isPresent[17] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_LIGHT)){
            isPresent[18] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_PROXIMITY)){
            isPresent[19] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_AMBIENT_TEMPERATURE)){
            isPresent[20] = true;
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)!=null){
            isPresent[21] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_RELATIVE_HUMIDITY)){
            isPresent[22] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            isPresent[23] = true;
        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_ANT+)){
            isPresent[24] = false;
//        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE)){
            isPresent[25] = true;
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)!=null){
            isPresent[26] = true;
        }
//        Sensor acc = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        Sensor pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
//        Sensor grav = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
//        Log.d(TAG, "ACC: "+acc);
//        Log.d(TAG, "PRESSURE"+pressure);
//        Log.d(TAG, "GRAVITY: "+grav);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)!=null){
            isPresent[27] = true;
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)!=null){
            isPresent[28] = true;
        }

        if(infrared.hasIrEmitter()){
            isPresent[29] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR)){
            isPresent[30] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER)){
            isPresent[31] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_FAKETOUCH)){
            isPresent[32] = true;
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_MOTION_DETECT)!=null){
            isPresent[33] = true;
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STATIONARY_DETECT)!=null){
            isPresent[34] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH)){
            isPresent[35] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI_DIRECT)){
            isPresent[36] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_BAROMETER)){
            isPresent[37] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_HEART_RATE)){
            isPresent[38] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_HEART_RATE_ECG)){
            isPresent[39] = true;
        }
        // Check if we're running on Android 6.0 (M) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Fingerprint API only available on from Android 6.0 (M)
            FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
            if (!fingerprintManager.isHardwareDetected()) {
                // Device doesn't support fingerprint authentication
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                // User hasn't enrolled any fingerprints to authenticate with
                isPresent[40]=true;
            } else {
                // Everything is ready for fingerprint authentication
                isPresent[40]=true;
            }
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_NFC)){
            isPresent[41] = true;
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)!=null){
            isPresent[42] = true;
        }
        return isPresent;
    }
}
