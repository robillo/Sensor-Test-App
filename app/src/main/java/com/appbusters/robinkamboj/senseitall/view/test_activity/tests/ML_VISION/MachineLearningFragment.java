package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.ML_VISION;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.test_activity.TestActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CHOOSER_INTENT_TITLE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IMAGE_CONTENT_TYPE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.REQUEST_CODE_CAPTURE_IMAGE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.REQUEST_CODE_PICK_IMAGE;

abstract public class MachineLearningFragment extends Fragment implements MachineLearningInterface {

    protected String HEADER_TEXT_SCAN;
    protected Bitmap bitmap;
    private String mCurrentPhotoPath;

    @BindView(R.id.place_holder_banner)
    ImageView placeHolderBanner;

    @BindView(R.id.place_holder_gallery)
    ImageView placeHolderGallery;

    @BindView(R.id.place_holder_frame)
    FrameLayout placeHolderFrame;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.rotate_left)
    ImageView rotateLeft;

    @BindView(R.id.rotate_right)
    ImageView rotateRight;

    @BindView(R.id.crop_image)
    ImageView cropImage;

    @BindView(R.id.image_to_work_on)
    protected
    ImageView imageToWorkOn;

    @BindView(R.id.select_image_from_gallery)
    ImageView selectImageFromGallery;

    @BindView(R.id.capture_image)
    ImageView captureImage;

    @BindView(R.id.done_button)
    protected
    Button doneButton;

    @Override
    public void setup(View v) {
        if(getActivity() == null) return;
        ButterKnife.bind(this, v);
        HEADER_TEXT_SCAN = ((TestActivity) getActivity()).recyclerName;
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
        processImage();
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
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (getActivity() != null && cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {

            File pictureFile;
            try {
                pictureFile = createImageFile();
            } catch (IOException ex) {
                showCoordinator(getString(R.string.couldnt_create_error));
                return;
            }
            if (pictureFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "com.appbusters.robinkamboj.senseitall.view.test_activity.other_files.GenericFileProvider",
                        pictureFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, REQUEST_CODE_CAPTURE_IMAGE);
            }
            else {
                showCoordinator(getString(R.string.null_photo_error));
            }
        }
    }

    @Override
    public void showCoordinator(String coordinatorText) {
        if(getActivity() == null) return;
        Snackbar s = Snackbar.make(coordinatorLayout, coordinatorText, Snackbar.LENGTH_SHORT);
        View v = s.getView();
        v.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.red_shade_three_less_vibrant));
        TextView t = v.findViewById(android.support.design.R.id.snackbar_text);
        t.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
        t.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        s.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK) {
            if(data != null) {
                Uri selectedImage = data.getData();

                if(getActivity() == null || selectedImage == null) return;

                try {
                    bitmap = BitmapFactory.decodeStream(
                            getActivity().getContentResolver().openInputStream(selectedImage)
                    );
                    imageToWorkOn.setImageBitmap(bitmap);
                    hidePlaceholderViews();
                    setDominantColorBackground();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else {
                showCoordinator(getString(R.string.no_image_selected_oops));
            }
        }
        else if(requestCode == REQUEST_CODE_CAPTURE_IMAGE && resultCode == RESULT_OK) {
            bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
            File imageFile = new File(mCurrentPhotoPath);
            if(imageFile.exists()) {
                imageToWorkOn.setImageURI(Uri.fromFile(imageFile));
                hidePlaceholderViews();
                setDominantColorBackground();
            }
            else {
                showCoordinator(getString(R.string.null_photo_error));
            }
        }
    }

    private File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String pictureFile = "SENSE_IT_ALL_" + timeStamp;
        if(getActivity() == null) return null;
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(pictureFile,  ".jpg", storageDir);
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @OnClick(R.id.rotate_left)
    public void setRotateLeft() {
        if(bitmap == null) {
            showCoordinator(getString(R.string.please_show_image));
            return;
        }

        Matrix matrix = new Matrix();
        matrix.postRotate(-90);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
        bitmap = Bitmap.createBitmap(
                scaledBitmap,
                0,
                0,
                scaledBitmap.getWidth(),
                scaledBitmap.getHeight(),
                matrix,
                true
        );

        imageToWorkOn.setImageBitmap(bitmap);
        hidePlaceholderViews();
    }

    @OnClick(R.id.rotate_right)
    public void setRotateRight() {
        if(bitmap == null) {
            showCoordinator(getString(R.string.please_show_image));
            return;
        }

        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
        bitmap = Bitmap.createBitmap(
                scaledBitmap,
                0,
                0,
                scaledBitmap.getWidth(),
                scaledBitmap.getHeight(),
                matrix,
                true
        );

        imageToWorkOn.setImageBitmap(bitmap);
        hidePlaceholderViews();
    }

    @OnClick(R.id.crop_image)
    public void setCropImage() {
        if(bitmap == null) showCoordinator(getString(R.string.please_show_image));
    }

    @Override
    public void hidePlaceholderViews() {
        placeHolderBanner.setVisibility(View.GONE);
        placeHolderGallery.setVisibility(View.GONE);
    }

    @Override
    public void setDominantColorBackground() {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@NonNull Palette palette) {
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                if (vibrantSwatch != null) {
                    placeHolderFrame.setBackgroundColor(vibrantSwatch.getRgb());
                }
            }
        });
    }
}
