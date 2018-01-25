package com.appbusters.robinkamboj.senseitall.view.activities.main;

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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.activities.list.ListActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MA";
    AVLoadingIndicatorView avi;
//    AVLoadingIndicatorView avi_2;
    ImageView imageView;
    TextView textView1, textView2;
    Button button;
    boolean[] isPresent;
    PackageManager packageManager;
    Context context;
    String[] sensors;

//    private static final int ALL_MY_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        imageView = findViewById(R.id.icon);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        avi = findViewById(R.id.avi);
        avi.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                List<Sensor> mySensonList = sensorManager != null ? sensorManager.getSensorList(Sensor.TYPE_ALL) : null;

                sensors = getResources().getStringArray(R.array.sensors_list);
                isPresent = isSensorPresent(sensors, mySensonList);

                Log.e("ROBIN:" , Boolean.toString(isPresent[0]) + " " + Boolean.toString(isPresent[1]));

                button.setVisibility(View.VISIBLE);
                avi.hide();
                imageView.setVisibility(View.VISIBLE);
//                avi_2.show();
                textView1.setText("Done With Analyzing!");
                textView2.setText("Move To The Next Step?");

            }
        }, 3000);
    }

//    //Permissions Helper Method
//    public static boolean hasPermissions(Context context, String[] permissions){
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && context!=null && permissions!=null){
//            for(String permission : permissions){
//                if(ActivityCompat.checkSelfPermission(context,permission)!=PackageManager.PERMISSION_GRANTED){
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

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

    boolean[] isSensorPresent(String[] sensors, List<Sensor> sensorList){

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        packageManager = getPackageManager();
        boolean[] isPresent = new boolean[sensors.length];

        HashMap<String,Boolean> map  = new HashMap<>();
        List<ApplicationInfo> pm = getPackageManager().getInstalledApplications(PackageManager.GET_ACTIVITIES|PackageManager.GET_PROVIDERS);

        ConsumerIrManager infrared = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            infrared = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);
        }

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
        if(Build.getRadioVersion().equals("unknown")|| Build.getRadioVersion()!=null){
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

        if(v != null && v.hasVibrator()){
            isPresent[13] = true;
        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
//            isPresent[14] = true;
//        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_USB_ACCESSORY)){
//            isPresent[15] = true;
//        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_AUDIO_OUTPUT)){
            isPresent[14] = true;
        }
        isPresent[15] = true;
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_LIGHT)){
            isPresent[16] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_PROXIMITY)){
            isPresent[17] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_AMBIENT_TEMPERATURE)){
            isPresent[18] = true;
        }
        if((sensorManager != null ? sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) : null) !=null){
            isPresent[19] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_RELATIVE_HUMIDITY)){
            isPresent[20] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            isPresent[21] = true;
        }
////        if(packageManager.hasSystemFeature(PackageManager.FEATURE_ANT+)){
//            isPresent[24] = false;
////        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE)){
            isPresent[22] = true;
        }
        if((sensorManager != null ? sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) : null) !=null){
            isPresent[23] = true;
        }
//        Sensor acc = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        Sensor pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
//        Sensor grav = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
//        Log.d(TAG, "ACC: "+acc);
//        Log.d(TAG, "PRESSURE"+pressure);
//        Log.d(TAG, "GRAVITY: "+grav);
        if((sensorManager != null ? sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) : null) !=null){
            isPresent[24] = true;
        }
        if((sensorManager != null ? sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) : null) !=null){
            isPresent[25] = true;
        }

        if(infrared != null && infrared.hasIrEmitter()){
            isPresent[26] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR)){
            isPresent[27] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER)){
            isPresent[28] = true;
        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_FAKETOUCH)){
//            isPresent[31] = true;
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if((sensorManager != null ? sensorManager.getDefaultSensor(Sensor.TYPE_MOTION_DETECT) : null) !=null){
                isPresent[29] = true;
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if((sensorManager != null ? sensorManager.getDefaultSensor(Sensor.TYPE_STATIONARY_DETECT) : null) !=null){
                isPresent[30] = true;
            }
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH)){
            isPresent[31] = true;
        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI_DIRECT)){
//            isPresent[34] = true;
//        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_BAROMETER)){
//            isPresent[35] = true;
//        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_HEART_RATE)){
            isPresent[32] = true;
        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_HEART_RATE_ECG)){
//            isPresent[35] = true;
//        }
        // Check if we're running on Android 6.0 (M) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Fingerprint API only available on from Android 6.0 (M)
            FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
            if (!(fingerprintManager != null && fingerprintManager.isHardwareDetected())) {
                // Device doesn't support fingerprint authentication
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                // User hasn't enrolled any fingerprints to authenticate with
                isPresent[33]=true;
            } else {
                // Everything is ready for fingerprint authentication
                isPresent[33]=true;
            }
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_NFC)){
            isPresent[34] = true;
        }
        if((sensorManager != null ? sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) : null) !=null){
            isPresent[35] = true;
        }
        return isPresent;
    }


}
