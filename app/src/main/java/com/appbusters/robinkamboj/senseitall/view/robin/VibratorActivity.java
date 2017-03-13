package com.appbusters.robinkamboj.senseitall.view.robin;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;

public class VibratorActivity extends AppCompatActivity {


    String sensor_name;
    TextView textView;
    Context context;
    Vibrator vib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrator);

        Intent i = getIntent();
        sensor_name = i.getStringExtra("sensorName");
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(sensor_name);

        context = getApplicationContext();

        vib = (Vibrator) this.context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.card:{
                vib.vibrate(500);
            }
        }
    }
}
