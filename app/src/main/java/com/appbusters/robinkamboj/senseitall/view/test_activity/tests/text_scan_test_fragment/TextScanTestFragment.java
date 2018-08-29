package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.text_scan_test_fragment;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.CHOOSER_INTENT_TITLE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.IMAGE_CONTENT_TYPE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.REQUEST_CODE_PICK_IMAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TextScanTestFragment extends Fragment implements TextScanTestInterface {

    private Bitmap bitmap;

    @BindView(R.id.image_to_work_on)
    ImageView imageToWorkOn;

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

    @OnClick(R.id.done_button)
    public void startProcessingImage() {
        //disable button till image is processed
        //process image
        //enable button
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
    }
}
