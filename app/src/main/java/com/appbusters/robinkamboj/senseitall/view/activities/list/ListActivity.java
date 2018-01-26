package com.appbusters.robinkamboj.senseitall.view.activities.list;

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
import com.appbusters.robinkamboj.senseitall.view.activities.about.AboutActivity;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "LIST";
    RecyclerView recyclerView;
    List<Data> data;
    String[] sensors_list;
    boolean[] isPresent;
    Recycler_View_Adapter adapter;
    public LinearLayout activity_list;
    private int[] drawables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activity_list = findViewById(R.id.activity_list);

        isPresent = (boolean[]) getIntent().getSerializableExtra("sensors_present");

        drawables = new int[]{
                R.drawable.camera,
                R.drawable.camera,
                R.drawable.gps,
                R.drawable.wifi,
                R.drawable.bluetooth,
                R.drawable.gsm,
                R.drawable.accelerometer,
                R.drawable.compass,
                R.drawable.radio,
                R.drawable.screen,
                R.drawable.battery_,
                R.drawable.cpu,
                R.drawable.sound,
                R.drawable.vibrator,
//                R.drawable.mic,
//                R.drawable.usb,
                R.drawable.aud_vid,
                R.drawable.android,
                R.drawable.light,
                R.drawable.proximity,
                R.drawable.temperature,
                R.drawable.light,
                R.drawable.humidity,
                R.drawable.flash,
//                R.drawable.ant,
                R.drawable.gyroscope,
                R.drawable.apple,
                R.drawable.accelerometer,
                R.drawable.rotation,
                R.drawable.proximity,
                R.drawable.step_detector,
                R.drawable.step_detector,
//                R.drawable.multitouch,
                R.drawable.motion_detector,
                R.drawable.motion_detector,
                R.drawable.multitouch,
//                R.drawable.wifi,
//                R.drawable.rotation,
                R.drawable.heart,
//                R.drawable.rotation,
                R.drawable.fingerprint,
                R.drawable.nfc,
                R.drawable.mag
        };

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
        ArrayList<String> listStr = new ArrayList<>();
        for(int i = 1; i <= sensors_list.length; i++){
            data.add(new Data(sensors_list[i-1], drawables[i-1], isPresent[i-1]));
            Log.d(TAG, "fillWithData: "+ data.get(i - 1).getSensor_name());
            listStr.add(data.get(i - 1).getSensor_name());
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
                Log.d(TAG, "onQueryTextChange: "+newText);
                Log.d(TAG, "onQueryTextChange: "+adapter);
                adapter.getFilter().filter(newText);
                return true;
            }
        });

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
            case R.id.about:{
                Intent i = new Intent(this, AboutActivity.class);
                startActivity(i);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

}
