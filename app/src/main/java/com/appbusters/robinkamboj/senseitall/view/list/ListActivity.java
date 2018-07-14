package com.appbusters.robinkamboj.senseitall.view.list;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.controller.Recycler_View_Adapter;
import com.appbusters.robinkamboj.senseitall.model.Data;
import com.appbusters.robinkamboj.senseitall.view.about.AboutActivity;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "LIST";
    RecyclerView recyclerView;
    List<Data> data;
    String[] sensors_list;
    boolean[] isPresent;
    Recycler_View_Adapter adapter;
    public static LinearLayout activity_list;
    private int[] drawables;
    private String[] sensorDrawableUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activity_list = findViewById(R.id.activity_list);

        isPresent = (boolean[]) getIntent().getSerializableExtra("sensors_present");

        sensorDrawableUrls = getResources().getStringArray(R.array.sensor_drawable_urls);

//        drawables = new int[]{
//                R.drawable.camera,      //0
//                R.drawable.camera,      //1
//                R.drawable.gps,      //2
//                R.drawable.wifi,      //3
//                R.drawable.bluetooth,      //4
//                R.drawable.gsm,      //5
//                R.drawable.accelerometer,      //6
//                R.drawable.compass,      //7
//                R.drawable.radio,      //8
//                R.drawable.screen,      //9
//                R.drawable.battery_,      //10
//                R.drawable.cpu,      //11
//                R.drawable.sound,      //12
//                R.drawable.vibrator,      //13
//                R.drawable.aud_vid,      //14
//                R.drawable.android,      //15
//                R.drawable.light,      //16
//                R.drawable.proximity,      //17
//                R.drawable.temperature,      //18
//                R.drawable.light,      //19
//                R.drawable.humidity,      //20
//                R.drawable.flash,      //21
//                R.drawable.gyroscope,      //22
//                R.drawable.apple,      //23
//                R.drawable.accelerometer,      //24
//                R.drawable.rotation,      //25
//                R.drawable.proximity,      //26
//                R.drawable.step_detector,      //27
//                R.drawable.step_detector,      //28
//                R.drawable.motion_detector,      //29
//                R.drawable.motion_detector,      //30
//                R.drawable.multitouch,      //31
//                R.drawable.heart,      //32
//                R.drawable.fingerprint,      //33
//                R.drawable.nfc,      //34
//                R.drawable.mag      //35
////                R.drawable.mic,      //36
////                R.drawable.usb,      //37
////                R.drawable.ant,      //38
////                R.drawable.faketouch,      //39
////                R.drawable.wifi_direct,      //40
////                R.drawable.barometer,      //41
////                R.drawable.heart_rate_Ecg,      //42
//        };

        sensors_list = getResources().getStringArray(R.array.sensors_list);
        data = fillWithData();
        recyclerView = findViewById(R.id.recyclerView);

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
            recyclerView.setLayoutManager(gridLayoutManager);
            adapter = new Recycler_View_Adapter(data, getApplicationContext());
            recyclerView.setAdapter(adapter);
        }
        else if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 5);
            recyclerView.setLayoutManager(gridLayoutManager);
            adapter = new Recycler_View_Adapter(data, getApplicationContext());
            recyclerView.setAdapter(adapter);
        }
    }

    private List<Data> fillWithData(){
        List<Data> data = new ArrayList<>();
        for(int i = 1; i <= sensors_list.length; i++){
            data.add(new Data(sensors_list[i-1], isPresent[i-1], sensorDrawableUrls[i-1]));
        }
        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //For SearchView
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);

        if(searchView!=null) {
            searchView.setSearchableInfo(searchManager != null ? searchManager.getSearchableInfo(getComponentName()) : null);
            searchView.setSubmitButtonEnabled(true);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // Here is where we are going to implement the filter logic

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return true;
                }
            });
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noispection SimplifiableIfStatement

        switch (id){
            case R.id.about:{
                Intent i = new Intent(this, AboutActivity.class);
                startActivity(i);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

}
