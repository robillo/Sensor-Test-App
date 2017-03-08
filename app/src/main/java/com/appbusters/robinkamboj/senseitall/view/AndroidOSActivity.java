package com.appbusters.robinkamboj.senseitall.view;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class AndroidOSActivity extends AppCompatActivity {

    String sensor_name, results[];
    TextView textView;

    TextView manufacturer, model, product, release, version,
            board, bootloader, brand, instruction_set, instruction_set2, device, display, fingerprint, hardware, host, device_id, radio, tags, time, type, user, codename, incremental,
            machine_type, host_name, os_release, os_name, processor_type, os_version,
            gles_detected_version, gles_version, gles_vendor, gles_graphic_chip, gles_extensions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_os);

        Intent i = getIntent();
        sensor_name = i.getStringExtra("sensorName");
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(sensor_name);

        manufacturer = (TextView) findViewById(R.id.manufacturer);
        product = (TextView) findViewById(R.id.product);
        model = (TextView) findViewById(R.id.model);
        release = (TextView) findViewById(R.id.release);
        version = (TextView) findViewById(R.id.version);
        board = (TextView) findViewById(R.id.board);
        bootloader = (TextView) findViewById(R.id.bootloader);
        brand = (TextView) findViewById(R.id.brand);
        instruction_set = (TextView) findViewById(R.id.instruction_Set);
        instruction_set2 = (TextView) findViewById(R.id.instruction_set_2);
        device = (TextView) findViewById(R.id.device);
        display = (TextView) findViewById(R.id.display);
        fingerprint = (TextView) findViewById(R.id.fingerprint);
        hardware = (TextView) findViewById(R.id.hardware);
        host = (TextView) findViewById(R.id.host);
        device_id = (TextView) findViewById(R.id.id);
        radio = (TextView) findViewById(R.id.radio);
        tags = (TextView) findViewById(R.id.tags);
        time = (TextView) findViewById(R.id.time);
        type = (TextView) findViewById(R.id.type);
        user = (TextView) findViewById(R.id.user);
        codename = (TextView) findViewById(R.id.codename);
        incremental = (TextView) findViewById(R.id.incremental);
        machine_type = (TextView) findViewById(R.id.machine_type);
        host_name = (TextView) findViewById(R.id.host_name);
        os_release = (TextView) findViewById(R.id.os_release);
        os_name = (TextView) findViewById(R.id.os_name);
        processor_type = (TextView) findViewById(R.id.processor_type);
        os_version = (TextView) findViewById(R.id.os_version);
        gles_detected_version = (TextView) findViewById(R.id.gles_detected_version);
        gles_version = (TextView) findViewById(R.id.gles_version);
        gles_vendor = (TextView) findViewById(R.id.gles_vendor);
        gles_graphic_chip = (TextView) findViewById(R.id.gles_graphic_chip);
        gles_extensions = (TextView) findViewById(R.id.extensions);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setResults(){
        results = new String[]{
                Build.MANUFACTURER, Build.MODEL, Build.PRODUCT ,Build.VERSION.RELEASE, Build.VERSION.BASE_OS,
                Build.BOARD, Build.BOOTLOADER, Build.BRAND, Build.CPU_ABI, Build.CPU_ABI2, Build.DEVICE, Build.DISPLAY, Build.FINGERPRINT,
                Build.HARDWARE, Build.HOST, Build.ID, Build.RADIO, Build.TAGS, String.valueOf(Build.TIME), Build.TYPE, Build.USER, Build.VERSION.CODENAME, Build.VERSION.INCREMENTAL






        };
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
