package com.appbusters.robinkamboj.senseitall.view;

import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    boolean isPresent[];
    PackageManager packageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                button.setVisibility(View.VISIBLE);
                avi.hide();
                avi_2.show();
                textView1.setText("Done With Analyzing!");
                textView2.setText("Move To The Next Step?");

            }
        }, 5000);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:{
                Intent i = new Intent(this, ListActivity.class);
                startActivity(i);
                break;
            }
        }
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



    void startAnim(){
        avi.show();
        // or avi.smoothToShow();
    }

    void stopAnim(){
        avi.hide();
        // or avi.smoothToHide();
    }

    boolean[] isSensorPresent(String[] sensors, List<Sensor> sensorList){

        packageManager = getPackageManager();
        boolean[] isPresent = new boolean[sensors.length];

        HashMap<String,Boolean> map  = new HashMap<>();

//        for(Sensor sensor1: sensorList){
//            map.put(sensor1.getName(),false);
//            Log.d(TAG, "HashMap: "+sensor1.getName());
//        }
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
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_RADIO)){
//            isPresent[8] = true;
//        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN)){
            isPresent[9] = true;
        }
            isPresent[10] = true;
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CPU)){
//            isPresent[11] = true;
//        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SOUND)){
//            isPresent[12] = true;
//        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_VIBRATOR)){
//            isPresent[13] = true;
//        }
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
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_PRESSURE)){
//            isPresent[21] = true;
//        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_RELATIVE_HUMIDITY)){
            isPresent[22] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            isPresent[23] = true;
        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_ANT+)){
//            isPresent[24] = true;
//        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE)){
            isPresent[25] = true;
        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_GRAVITY)){
//            isPresent[26] = true;
//        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_LINEARACC)){
//            isPresent[27] = true;
//        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_ROTATION)){
//            isPresent[28] = true;
//        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_INFRARED)){
//            isPresent[29] = true;
//        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR)){
            isPresent[30] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER)){
            isPresent[31] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_FAKETOUCH)){
            isPresent[32] = true;
        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_STORAGEOPT)){
//            isPresent[33] = true;
//        }
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_VOICERECOG)){
//            isPresent[34] = true;
//        }
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
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){
            isPresent[40] = true;
        }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_NFC)){
            isPresent[41] = true;
        }



        return isPresent;
    }
}
