package com.appbusters.robinkamboj.senseitall.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class GSMActivity extends AppCompatActivity {

    String sensor_name;
    TextView textView;
    TextView sim_operator_name, sim_country, sim_operator, sim_sn, sim_state, subscriber_id, mail_alpha_tag, mail_number,
    icc_card, network_roaming, network_operator_name, network_country, network_operator, phone_type, cell_id,
    location_area_code, call_state, line_one_number, device_id, software_version, data_activity, data_state, network_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsm);

        Intent i = getIntent();
        sensor_name = i.getStringExtra("sensorName");
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(sensor_name);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sim_operator_name = (TextView) findViewById(R.id.operator_name);
        sim_country = (TextView) findViewById(R.id.country);
        sim_operator = (TextView) findViewById(R.id.operator);
        sim_sn = (TextView) findViewById(R.id.sn);
        sim_state = (TextView) findViewById(R.id.state);
        subscriber_id = (TextView) findViewById(R.id.subscriber_id);
        mail_alpha_tag = (TextView) findViewById(R.id.mail_alpha_tag);
        mail_number = (TextView) findViewById(R.id.mail_number);
        icc_card = (TextView) findViewById(R.id.icc_card);
        network_roaming = (TextView) findViewById(R.id.network_roaming);
        network_operator_name = (TextView) findViewById(R.id.network_operator_name);
        network_country = (TextView) findViewById(R.id.network_country);
        network_operator = (TextView) findViewById(R.id.network_operator);
        phone_type = (TextView) findViewById(R.id.phone_type);
        cell_id = (TextView) findViewById(R.id.cell_id);
        location_area_code = (TextView) findViewById(R.id.location_area_code);
        call_state = (TextView) findViewById(R.id.call_state);
        line_one_number = (TextView) findViewById(R.id.line_one_number);
        device_id = (TextView) findViewById(R.id.device_id);
        software_version = (TextView) findViewById(R.id.software_version);
        data_activity = (TextView) findViewById(R.id.data_activity);
        data_state = (TextView) findViewById(R.id.data_state);
        network_type = (TextView) findViewById(R.id.network_type);
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
