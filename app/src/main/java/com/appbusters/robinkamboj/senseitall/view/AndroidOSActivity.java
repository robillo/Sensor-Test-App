package com.appbusters.robinkamboj.senseitall.view;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL10Ext;

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

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                setResults();
                setTextviews();
            }
        });
    }

    private void setResults(){

        results = new String[]{
                Build.MANUFACTURER, Build.MODEL, Build.PRODUCT ,Build.VERSION.RELEASE, Build.VERSION.BASE_OS,
                Build.BOARD, Build.BOOTLOADER, Build.BRAND, Build.CPU_ABI, Build.CPU_ABI2, Build.DEVICE, Build.DISPLAY, Build.FINGERPRINT,
                Build.HARDWARE, Build.HOST, Build.ID, Build.RADIO, Build.TAGS, String.valueOf(Build.TIME), Build.TYPE, Build.USER, Build.VERSION.CODENAME, Build.VERSION.INCREMENTAL,
                Build.HARDWARE, Build.HOST, Build.VERSION.RELEASE, "ANDROID", System.getProperty("os.arch"), Build.VERSION.BASE_OS,
                String.valueOf(GL10.GL_VERSION), String.valueOf(GL10.GL_VERSION), String.valueOf(GL10.GL_VENDOR), String.valueOf(GL10.GL_VERSION), String.valueOf(GL10.GL_EXTENSIONS)
        };
    }

    private void setTextviews(){
        manufacturer.setText("Manufacturer:" + results[0]);
        model.setText("Model:" + results[1]);
        product.setText("Product:" + results[2]);
        release.setText("Release:" + results[3]);
        version.setText("Version:" + results[4]);
        board.setText("Board:" + results[5]);
        bootloader.setText("Bootloader:" + results[6]);
        brand.setText("Brand:" + results[7]);
        instruction_set.setText("Instruction Set 1:" + results[8]);
        instruction_set2.setText("Instruction Set 2:" + results[9]);
        device.setText("Device:" + results[10]);
        display.setText("Display:" + results[11]);
        fingerprint.setText("Fingerprint:" + results[12]);
        hardware.setText("Hardware:" + results[13]);
        host.setText("Host:" + results[14]);
        device_id.setText("Device ID:" + results[15]);
        radio.setText("Radio:" + results[16]);
        tags.setText("Tags:" + results[17]);
        time.setText("Time:" + results[18]);
        type.setText("Type:" + results[19]);
        user.setText("User:" + results[20]);
        codename.setText("Codename:" + results[21]);
        incremental.setText("Incremental:" + results[22]);
        machine_type.setText("Machine Type:" + results[23]);
        host_name.setText("Host Name:" + results[24]);
        os_release.setText("OS Release:" + results[25]);
        os_name.setText("OS Name:" + results[26]);
        processor_type.setText("Processor Type:" + results[27]);
        os_version.setText("OS Version:" + results[28]);
        gles_detected_version.setText("Detected Version:" + results[29]);
        gles_version.setText("Version:" + results[30]);
        gles_vendor.setText("Vendor:" + results[31]);
        gles_graphic_chip.setText("Graphic Chip:" + results[32]);
        gles_extensions.setText("Extensions:" + results[33]);
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
