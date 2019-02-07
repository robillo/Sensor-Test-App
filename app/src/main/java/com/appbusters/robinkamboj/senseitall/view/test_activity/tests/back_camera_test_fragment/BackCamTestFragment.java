package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.back_camera_test_fragment;


import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Size;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BackCamTestFragment extends Fragment implements BackCamTestInterface {

    private CameraManager cameraManager;
    private int cameraFacing;
    private CameraDevice cameraDevice;
    private CaptureRequest.Builder captureRequestBuilder;
    private CaptureRequest captureRequest;
    private CameraCaptureSession cameraCaptureSession;
    private String cameraId;
    private Handler backgroundHandler;
    private HandlerThread backgroundThread;
    private Size previewSize;
    private int width, height;

    @BindView(R.id.texture_view)
    TextureView textureView;

    public BackCamTestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the parentView for this fragment
        View v = inflater.inflate(R.layout.fragment_back_cam_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        Display display = null;
        if(getActivity() != null)
            display = getActivity().getWindowManager().getDefaultDisplay();

        if(display != null) {
            Point size = new Point();
            display.getSize(size);
            width = size.x;
            height = size.y;
        }

        cameraFacing = CameraCharacteristics.LENS_FACING_BACK;
        if(getActivity() != null)
            cameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);
    }

    private void openCamera() {
        try {
            if(getActivity() != null)
                if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {
                    cameraManager.openCamera(cameraId, callback, backgroundHandler);
                }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void openBackgroundThread() {
        backgroundThread = new HandlerThread("camera_background_thread");
        backgroundThread.start();
        backgroundHandler = new Handler(backgroundThread.getLooper());
    }

    private void setUpCamera() {
        try {
            for (String cameraId : cameraManager.getCameraIdList()) {
                CameraCharacteristics cameraCharacteristics =
                        cameraManager.getCameraCharacteristics(cameraId);
                //noinspection ConstantConditions
                if (cameraCharacteristics.get(CameraCharacteristics.LENS_FACING) != null &&
                        cameraCharacteristics.get(CameraCharacteristics.LENS_FACING) ==
                        cameraFacing) {
                    StreamConfigurationMap streamConfigurationMap = cameraCharacteristics.get(
                            CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                    if (streamConfigurationMap != null)
                        previewSize = chooseOptimalSize(
                                streamConfigurationMap.getOutputSizes(SurfaceTexture.class),
                                width,
                                height);
                    this.cameraId = cameraId;
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            setUpCamera();
            openCamera();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };

    CameraDevice.StateCallback callback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            BackCamTestFragment.this.cameraDevice = camera;
            createPreviewSession();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            cameraDevice.close();
            BackCamTestFragment.this.cameraDevice = null;
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            cameraDevice.close();
            BackCamTestFragment.this.cameraDevice = null;
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        openBackgroundThread();
        if (textureView.isAvailable()) {
            setUpCamera();
            openCamera();
        } else {
            textureView.setSurfaceTextureListener(textureListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        closeCamera();
        closeBackgroundThread();
    }

    private void closeCamera() {
        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
            cameraCaptureSession = null;
        }

        if (cameraDevice != null) {
            cameraDevice.close();
            cameraDevice = null;
        }
    }

    private void closeBackgroundThread() {
        if (backgroundHandler != null) {
            backgroundThread.quitSafely();
            backgroundThread = null;
            backgroundHandler = null;
        }
    }

    private void createPreviewSession() {
        try {
            SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
            surfaceTexture.setDefaultBufferSize(previewSize.getWidth(), previewSize.getHeight());
            Surface previewSurface = new Surface(surfaceTexture);
            if(cameraDevice != null)
                captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);

            if(captureRequestBuilder != null)
                captureRequestBuilder.addTarget(previewSurface);

            if(cameraDevice != null)
                cameraDevice.createCaptureSession(Collections.singletonList(previewSurface),
                        new CameraCaptureSession.StateCallback() {

                            @Override
                            public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                                if (cameraDevice == null) {
                                    return;
                                }

                                try {
                                    captureRequest = captureRequestBuilder.build();
                                    BackCamTestFragment.this.cameraCaptureSession = cameraCaptureSession;
                                    BackCamTestFragment.this.cameraCaptureSession.setRepeatingRequest(captureRequest,
                                            null, backgroundHandler);
                                } catch (CameraAccessException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {

                            }
                        }, backgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private Size chooseOptimalSize(Size[] outputSizes, int width, int height) {
        double preferredRatio = height / (double) width;
        Size currentOptimalSize = outputSizes[0];
        double currentOptimalRatio = currentOptimalSize.getWidth() / (double) currentOptimalSize.getHeight();
        for (Size currentSize : outputSizes) {
            double currentRatio = currentSize.getWidth() / (double) currentSize.getHeight();
            if (Math.abs(preferredRatio - currentRatio) <
                    Math.abs(preferredRatio - currentOptimalRatio)) {
                currentOptimalSize = currentSize;
                currentOptimalRatio = currentRatio;
            }
        }
        return currentOptimalSize;
    }
}
