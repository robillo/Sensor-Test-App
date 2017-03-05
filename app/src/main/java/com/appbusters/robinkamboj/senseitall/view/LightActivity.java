package com.appbusters.robinkamboj.senseitall.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.github.yongjhih.mismeter.MisMeter;

public class LightActivity extends AppCompatActivity {

    String sensor_name, results[];
    TextView textView;
    private Sensor sensor;
    private SensorManager sensorManager;
    MisMeter meter;
    TextView maximum, current;
    float max, percentage;
    private TextView vendor, minimum_delay, version, power, resolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        Intent i = getIntent();
        sensor_name = i.getStringExtra("sensorName");
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(sensor_name);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        vendor = (TextView) findViewById(R.id.vendor);
        minimum_delay = (TextView) findViewById(R.id.minimum_delay);
        version = (TextView) findViewById(R.id.version);
        power = (TextView) findViewById(R.id.power);
        resolution = (TextView) findViewById(R.id.resolution);

        maximum = (TextView) findViewById(R.id.maximum);
        current = (TextView) findViewById(R.id.current);
        meter = (MisMeter) findViewById(R.id.meter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                setResults();
                setTextViews();
            }
        });


        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        max = lightSensor.getMaximumRange();
        maximum.setText(String.valueOf(max));

        sensorManager.registerListener(lightSensorEventListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void setResults(){
        results = new String[]{sensor.getVendor(),
                String.valueOf(sensor.getMinDelay()), String.valueOf(sensor.getVersion()),
                String.valueOf(sensor.getPower()), String.valueOf(sensor.getResolution())};
    }

    private void setTextViews(){
        vendor.setText(results[0]);
        minimum_delay.setText(results[1]);
        version.setText(results[2]);
        power.setText(results[3]);
        resolution.setText(results[4]);
    }


    SensorEventListener lightSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if(sensorEvent.sensor.getType()==Sensor.TYPE_LIGHT){
                float currentReading = sensorEvent.values[0];
                current.setText(String.valueOf(currentReading));
                String set = String.valueOf(String.format("%.2f",(currentReading/max)*10000));
                percentage = (currentReading/max);
                meter.setProgress(percentage);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

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
