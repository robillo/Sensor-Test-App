package com.appbusters.robinkamboj.senseitall.view.robin;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class GSMActivity extends AppCompatActivity {


    TelephonyManager tm;
    String sensor_name;
    TextView textView;
    TextView sim_operator_name, sim_country, sim_operator, sim_sn, sim_state, subscriber_id, mail_alpha_tag, mail_number,
    icc_card, network_roaming, network_operator_name, network_country, network_operator, phone_type, cell_id,
    location_area_code, call_state, line_one_number, device_id, software_version, data_activity, data_state, network_type;
    String[] results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsm);

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

        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

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
        results = new String[]{tm.getSimOperatorName(), tm.getSimCountryIso(),
                tm.getSimOperator(), tm.getSimSerialNumber(), String.valueOf(tm.getSimState()), tm.getSubscriberId(),
                tm.getVoiceMailAlphaTag(), tm.getVoiceMailNumber(), String.valueOf(tm.hasIccCard()),
                String.valueOf(tm.isNetworkRoaming()), tm.getNetworkOperatorName(), tm.getNetworkCountryIso(),
                tm.getNetworkOperator(), String.valueOf(tm.getPhoneType()), tm.getDeviceId(),
                String.valueOf(tm.getCellLocation()), String.valueOf(tm.getCallState()),
                tm.getLine1Number(), tm.getDeviceId(), tm.getDeviceSoftwareVersion(), String.valueOf(tm.getDataActivity()),
                String.valueOf(tm.getDataState()), String.valueOf(tm.getDataNetworkType())};
    }

    private void setTextviews(){
        if(results[0]!=null){
            sim_operator_name.setText(results[0]);
        } else {}
        if(results[1]!=null){
            sim_country.setText(results[1]);
        } else {}
        if(results[2]!=null){
            sim_operator.setText(results[2]);
        } else {}
        if(results[3]!=null){
            sim_sn.setText(results[3]);
        } else {}
        if(results[4]!=null){
            sim_state.setText(results[4]);
        } else {}
        if(results[5]!=null){
            subscriber_id.setText(results[5]);
        } else {}
        if(results[6]!=null){
            mail_alpha_tag.setText(results[6]);
        } else {}
        if(results[7]!=null){
            mail_number.setText(results[7]);
        } else {}
        if(results[8]!=null){
            icc_card.setText(results[8]);
        } else {}
        if(results[9]!=null){
            network_roaming.setText(results[9]);
        } else {}
        if(results[10]!=null){
            network_operator_name.setText(results[10]);
        } else {}
        if(results[11]!=null){
            network_country.setText(results[11]);
        } else {}
        if(results[12]!=null){
            network_operator.setText(results[12]);
        } else {}
        if(results[13]!=null){
            phone_type.setText(results[13]);
        } else {}
        if(results[14]!=null){
            cell_id.setText(results[14]);
        } else {}
        if(results[15]!=null){
            location_area_code.setText(results[15]);
        } else {}
        if(results[16]!=null){
            call_state.setText(results[16]);
        } else {}
        if(results[17]!=null){
            line_one_number.setText(results[17]);
        } else {}
        if(results[18]!=null){
            device_id.setText(results[18]);
        } else {}
        if(results[19]!=null){
            software_version.setText(results[19]);
        } else {}
        if(results[20]!=null){
            data_activity.setText(results[20]);
        } else {}
        if(results[21]!=null){
            data_state.setText(results[21]);
        } else {}
        if(results[22]!=null){
            network_type.setText(results[22]);
        } else {}
    }
}
