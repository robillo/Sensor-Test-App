package com.appbusters.robinkamboj.senseitall.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

public class BatteryActivity extends AppCompatActivity{

    ImageView imageView;
    AnimationDrawable ani;
    BroadcastReceiver receiver;
    TextView batteryperc;
    int batLevel;

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
        BatteryManager bm = (BatteryManager) getSystemService(BATTERY_SERVICE);
        batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
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
                    batteryperc.setText(batLevel+"%");


                }else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
                 //   tv.setText("POWER DISCONNECTED");

//                    if(ani.isRunning()){
//                        ani.stop();
//                    }
                    if(batLevel<=15){
                        imageView.setBackgroundResource(R.drawable.bless);
                    }else if(batLevel>=75&&batLevel<95){
                        imageView.setBackgroundResource(R.drawable.nninty);
                    }else if(batLevel>=50&&batLevel<75){
                        imageView.setBackgroundResource(R.drawable.nsevenfive);
                    }else if(batLevel>=95){
                        imageView.setBackgroundResource(R.drawable.bfull);
                    }
                    batteryperc.setText(batLevel+"%");
                    batteryperc.setVisibility(View.VISIBLE);
                }
            }
        };
        IntentFilter intentFilter  = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(receiver,intentFilter);


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
