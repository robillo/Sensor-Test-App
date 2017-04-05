package com.appbusters.robinkamboj.senseitall.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.appbusters.robinkamboj.senseitall.R;
import com.bumptech.glide.Glide;

public class AboutActivity extends AppCompatActivity {

    private ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        Glide.with(getApplicationContext())
                .load("http://4.bp.blogspot.com/-djSLq2o8FtM/WOUfuN2cfYI/AAAAAAAAAHY/y41hBqg9Vyc1LaN3UyG4VYXM2nt0-HHOQCK4B/s1600/IMG_20170401_150958.jpg")
                .centerCrop()
                .into(imageView1);
    }
}
