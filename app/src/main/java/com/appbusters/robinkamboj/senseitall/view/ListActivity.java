package com.appbusters.robinkamboj.senseitall.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.controller.Recycler_View_Adapter;
import com.appbusters.robinkamboj.senseitall.model.Data;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Data> data;
    String[] sensors_list;
    boolean[] isPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        isPresent = (boolean[]) getIntent().getSerializableExtra("sensors_present");

        Log.e("ROBIN:" , Boolean.toString(isPresent[0]) + " " + Boolean.toString(isPresent[1]));

        sensors_list = getResources().getStringArray(R.array.sensors_list);
        data = fillWithData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        Recycler_View_Adapter adapter = new Recycler_View_Adapter(data, getApplicationContext());
        recyclerView.setAdapter(adapter);

    }


    private List<Data> fillWithData(){
        List<Data> data = new ArrayList<>();

        for(int i = 1; i <= sensors_list.length; i++){
            data.add(new Data(sensors_list[i-1], R.drawable.android, isPresent[i-1]));
        }

        return data;
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
