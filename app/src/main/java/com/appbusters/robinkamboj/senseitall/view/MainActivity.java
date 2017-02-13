package com.appbusters.robinkamboj.senseitall.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.wang.avi.AVLoadingIndicatorView;

public class MainActivity extends AppCompatActivity {

    AVLoadingIndicatorView avi,avi_2;
    TextView textView1, textView2;
    Button button;
    boolean isPresent[];

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
                String[] sensors = getResources().getStringArray(R.array.sensors_list);
                isPresent = new boolean[33];

                for(int i=0;i<sensors.length;i++){
                    if(isSensorPresent(sensors[i])){
                        isPresent[i] = true;
                    }
                }
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

    boolean isSensorPresent(String sensor){
        //TODO: I will be adding the code for this soon
        return false;
    }
}
