package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.text_scan_test_fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.test_activity.TestActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.constraint.Constraints.TAG;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CHOOSER_INTENT_TITLE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IMAGE_CONTENT_TYPE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.REQUEST_CODE_CAPTURE_IMAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.REQUEST_CODE_PICK_IMAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TextScanTestFragment extends Fragment implements TextScanTestInterface {

    private Bitmap bitmap;
    private String mCurrentPhotoPath;
    private FirebaseVisionTextRecognizer detector;
    private FirebaseVisionImage visionImage;

    @BindView(R.id.image_to_work_on)
    ImageView imageToWorkOn;

    @BindView(R.id.capture_image)
    Button captureImage;

    @BindView(R.id.done_button)
    Button doneButton;

    public TextScanTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_text_scan_test, container, false);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        initialize();
    }

    @OnClick(R.id.select_image_from_gallery)
    public void setImageFromGallery() {
        openGalleryForImageSelect();
    }

    @OnClick(R.id.capture_image)
    public void captureImage() {
        openCameraForImageSelect();
    }

    @OnClick(R.id.done_button)
    public void startProcessingImage() {
        //disable button till image is processed
        doneButton.setEnabled(false);
        //process image
        if(bitmap == null) {
            Toast.makeText(getActivity(), "No Image Selected", Toast.LENGTH_SHORT).show();
            doneButton.setEnabled(true);
            return;
        }

        if(getActivity() != null) ((TestActivity) getActivity()).showBottomSheetResults();

        visionImage = FirebaseVisionImage.fromBitmap(bitmap);
        detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        detector.processImage(visionImage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                processTextRecognitionResult(firebaseVisionText.getTextBlocks());
                doneButton.setEnabled(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "An Error Occurred", Toast.LENGTH_SHORT).show();
                doneButton.setEnabled(true);
            }
        });
        //enable button
    }

    private void processTextRecognitionResult(List<FirebaseVisionText.TextBlock> texts) {
        StringBuilder builder = new StringBuilder();
        for(FirebaseVisionText.TextBlock t : texts) {
            builder.append(t.getText()).append(" ... ");
        }
        if(getActivity() != null) {
            ((TestActivity) getActivity()).setResultsToBottomSheet(builder.toString());
        }
    }

    @Override
    public void openGalleryForImageSelect() {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType(IMAGE_CONTENT_TYPE);

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType(IMAGE_CONTENT_TYPE);

        Intent chooserIntent = Intent.createChooser(getIntent, CHOOSER_INTENT_TITLE);
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(chooserIntent, REQUEST_CODE_PICK_IMAGE);
    }

    @Override
    public void openCameraForImageSelect() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (getActivity()!= null && cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.i(TAG, "IOException");
            }
            if (photoFile != null) {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(cameraIntent, REQUEST_CODE_CAPTURE_IMAGE);
            }
        }
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_PICK_IMAGE) {
            if(data != null) {
                Uri selectedImage = data.getData();

                if(getActivity() == null || selectedImage == null) return;

                try {
                    bitmap = BitmapFactory.decodeStream(
                            getActivity().getContentResolver().openInputStream(selectedImage)
                    );
                    imageToWorkOn.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else {
                Toast.makeText(getActivity(), "Seems Like No Image Was Selected", Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode == REQUEST_CODE_CAPTURE_IMAGE) {
            try {
                if(getActivity() == null) return;
                bitmap = MediaStore.Images.Media.getBitmap(
                        getActivity().getContentResolver(), Uri.parse(mCurrentPhotoPath)
                );
                imageToWorkOn.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        @SuppressLint("SimpleDateFormat")
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
}
