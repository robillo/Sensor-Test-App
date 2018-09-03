package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.virtual_reality_test_activity.activity;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.appbusters.robinkamboj.senseitall.R;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VirtualRealityTestActivity extends AppCompatActivity implements VirtualRealityTestInterface {

    private ImageLoaderTask backgroundImageLoaderTask;

    @BindView(R.id.pano_view)
    VrPanoramaView panoramaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_virtual_reality_test);

        setup();
    }

    @Override
    public void setup() {
        ButterKnife.bind(this);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUi();
            }
        }, 500);
    }

    public void runOnUi() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(panoramaView != null) {
                    panoramaView.loadImageFromBitmap(
                            BitmapFactory.decodeResource(getResources(), R.drawable.working_pano_one),
                            new VrPanoramaView.Options()
                    );
                }
            }
        });
    }

    @Override
    public synchronized void loadPanoImage() {
        ImageLoaderTask task = backgroundImageLoaderTask;
        if (task != null && !task.isCancelled()) {
            // Cancel any task from a previous loading.
            task.cancel(true);
        }

        // pass in the name of the image to load from assets.
        VrPanoramaView.Options viewOptions = new VrPanoramaView.Options();
        viewOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;

        // use the name of the image in the assets/ directory.
        String panoImageName = "converted.jpg";

        // create the task passing the widget view and call execute to start.
        task = new ImageLoaderTask(panoramaView, viewOptions, panoImageName);
        task.execute(this.getAssets());
        backgroundImageLoaderTask = task;
    }

    @Override
    protected void onPause() {
        if(panoramaView != null) panoramaView.pauseRendering();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(panoramaView != null) panoramaView.resumeRendering();
    }

    @Override
    protected void onDestroy() {
        if(panoramaView != null) panoramaView.shutdown();
        super.onDestroy();
    }
}
