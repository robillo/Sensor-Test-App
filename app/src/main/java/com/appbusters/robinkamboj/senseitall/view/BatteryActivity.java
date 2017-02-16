package com.appbusters.robinkamboj.senseitall.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class BatteryActivity extends AppCompatActivity{

    private static final String TAG = "Batteryinfo";
    String[] results;
    ImageView imageView;
    AnimationDrawable ani;
    BroadcastReceiver receiver;
    TextView batteryperc;
    int batLev;
    BatteryManager bm;

    TextView level,plugged,present,maxcl,status,tech,temp,vol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        batteryperc = (TextView) findViewById(R.id.batteryperc);
        Intent i = getIntent();
        imageView = (ImageView) findViewById(R.id.imagee);
        imageView.setBackgroundResource(R.drawable.gube);
        level = (TextView) findViewById(R.id.level);
        plugged = (TextView) findViewById(R.id.plugged);
        present = (TextView) findViewById(R.id.present);
        maxcl = (TextView) findViewById(R.id.maxcl);
        status = (TextView) findViewById(R.id.status);
        tech = (TextView) findViewById(R.id.tech);
        temp = (TextView) findViewById(R.id.temp);
        vol = (TextView) findViewById(R.id.vol);

        bm = (BatteryManager) getSystemService(BATTERY_SERVICE);
       // batLev= intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
        ani = (AnimationDrawable) imageView.getBackground();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
                    //tv.setText("POWER CONNECTED ");
                    imageView.setBackgroundResource(R.drawable.gube);
                    ani = (AnimationDrawable) imageView.getBackground();
                    ani.start();
                    //ani.run();
                    batteryperc.setText(intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0)+"%");




                }else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
                 //   tv.setText("POWER DISCONNECTED");

//                    if(ani.isRunning()){
//                        ani.stop();
//                    }
                    if(intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0)<=15){
                        imageView.setBackgroundResource(R.drawable.bless);
                    }else if(intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0)>=75&&intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0)<95){
                        imageView.setBackgroundResource(R.drawable.nninty);
                    }else if(intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0)>=50&&intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0)<75){
                        imageView.setBackgroundResource(R.drawable.nsevenfive);
                    }else if(intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0)>=95){
                        imageView.setBackgroundResource(R.drawable.bfull);
                    }
                    batteryperc.setText(intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0)+"%");
                    batteryperc.setVisibility(View.VISIBLE);


                }

                intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
                Log.d(TAG, "onReceive: "+intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0));
                Log.d(TAG, "onReceive: "+intent.getIntExtra(BatteryManager.ACTION_CHARGING,0));
                Log.d(TAG, "onReceive: "+intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0));
                Log.d(TAG, "onReceive: "+intent.getIntExtra(BatteryManager.ACTION_DISCHARGING,0));
                Log.d(TAG, "onReceive: "+intent.getIntExtra(BatteryManager.EXTRA_HEALTH,0));
                Log.d(TAG, "onReceive: "+intent.getIntExtra(BatteryManager.EXTRA_PRESENT,0));
                Log.d(TAG, "onReceive: "+intent.getIntExtra(BatteryManager.EXTRA_STATUS,0));
                Log.d(TAG, "onReceive: "+intent.getIntExtra(BatteryManager.EXTRA_SCALE,0));
                Log.d(TAG, "onReceive: "+intent.getIntExtra(BatteryManager.EXTRA_TECHNOLOGY,0));
                Log.d(TAG, "onReceive: "+intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0));
                Log.d(TAG, "onReceive: "+intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0));
                Log.d(TAG, "onReceive: "+intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL,0));
                Log.d(TAG, "onReceive: "+intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0));

                Log.d(TAG, "onReceive: "+intent.getStringExtra(BatteryManager.EXTRA_PLUGGED));
                Log.d(TAG, "onReceive: "+intent.getStringExtra(BatteryManager.ACTION_CHARGING));
                Log.d(TAG, "onReceive: "+intent.getStringExtra(BatteryManager.EXTRA_LEVEL));
                Log.d(TAG, "onReceive: "+intent.getStringExtra(BatteryManager.ACTION_DISCHARGING));
                Log.d(TAG, "onReceive: "+intent.getStringExtra(BatteryManager.EXTRA_HEALTH));
                Log.d(TAG, "onReceive: "+intent.getStringExtra(BatteryManager.EXTRA_PRESENT));
                Log.d(TAG, "onReceive: "+intent.getStringExtra(BatteryManager.EXTRA_STATUS));
                Log.d(TAG, "onReceive: "+intent.getStringExtra(BatteryManager.EXTRA_SCALE));
                Log.d(TAG, "onReceive: "+intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY));
                Log.d(TAG, "onReceive: "+intent.getStringExtra(BatteryManager.EXTRA_TEMPERATURE));
                Log.d(TAG, "onReceive: "+intent.getStringExtra(BatteryManager.EXTRA_PLUGGED));
                Log.d(TAG, "onReceive: "+intent.getStringExtra(BatteryManager.EXTRA_ICON_SMALL));
                Log.d(TAG, "onReceive: "+intent.getStringExtra(BatteryManager.EXTRA_VOLTAGE));

                if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
                    tech.setText(intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY));
//                   vol.setText(intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0));
//                int tempe  = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0);
//                                  int dec  = tempe%10;
//                                tempe/=10;
//                              temp.setText(tempe+"."+dec);
//                    level.setText(intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0));
//                    maxcl.setText(intent.getIntExtra(BatteryManager.EXTRA_SCALE,0));
                    present.setText("True");
                }
            }
        };
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
//                setResults();
//                for(String s:results){
//                    Log.d(TAG, "run: "+s);
//                }
            }
        });
        IntentFilter intentFilter  = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(receiver,intentFilter);


    }
    private void setResults(){
        results = new String[]{"%",BatteryManager.EXTRA_TEMPERATURE,
        BatteryManager.EXTRA_SCALE,BatteryManager.EXTRA_VOLTAGE,BatteryManager.ACTION_CHARGING,BatteryManager.ACTION_DISCHARGING,
        BatteryManager.EXTRA_HEALTH,BatteryManager.EXTRA_STATUS,BatteryManager.EXTRA_TECHNOLOGY,BatteryManager.EXTRA_LEVEL};

    }
    @Override
    protected void onPause() {
        unregisterReceiver(receiver);
        super.onPause();
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
