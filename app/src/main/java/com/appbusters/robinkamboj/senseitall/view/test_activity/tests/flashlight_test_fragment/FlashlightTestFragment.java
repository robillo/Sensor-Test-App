package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.flashlight_test_fragment;


import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlashlightTestFragment extends Fragment implements FlashlightTestInterface {

    boolean isFlashOn = false;
    private CameraManager mCameraManager;
    private String mCameraId;

    @BindView(R.id.flash_text_status)
    TextView flashTextStatus;

    @BindView(R.id.flash_image)
    ImageView flashImage;

    public FlashlightTestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_flashlight_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        if(getActivity() != null) Glide.with(getActivity()).load(R.drawable.flash).into(flashImage);

        inflateImageAndText();

        initialize();
    }

    @Override
    public void inflateImageAndText() {
        if(getActivity() != null)
            if(isFlashOn) {
                flashTextStatus.setText(getActivity().getString(R.string.flash_is_turned_on));
                flashImage.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorFlashlightShade));
                turnOnFlashLight();
            }
            else {
                flashTextStatus.setText(getActivity().getString(R.string.flash_is_turned_off));
                flashImage.setColorFilter(ContextCompat.getColor(getActivity(), R.color.red_shade_four));
                turnOffFlashLight();
            }
    }

    @Override
    public void initialize() {
        if (getActivity() == null) return;
        mCameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);

        if(mCameraManager == null) return;
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void turnOnFlashLight() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mCameraId, true);
//                playOnOffSound();
//                mTorchOnOffButton.setImageResource(R.drawable.on);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void turnOffFlashLight() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mCameraId, false);
//                playOnOffSound();
//                mTorchOnOffButton.setImageResource(R.drawable.off);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void playOnOffSound(){
//        mp = MediaPlayer.create(FlashLightActivity.this, R.raw.flash_sound);
//        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                // TODO Auto-generated method stub
//                mp.release();
//            }
//        });
//        mp.start();
//    }

    @OnClick(R.id.flash_image)
    public void setFlashImage() {
        isFlashOn = !isFlashOn;
        inflateImageAndText();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(getActivity() == null) return;
        isFlashOn = false;
        inflateImageAndText();
    }
}
