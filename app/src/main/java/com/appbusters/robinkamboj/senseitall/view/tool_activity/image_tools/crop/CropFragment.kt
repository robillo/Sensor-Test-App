package com.appbusters.robinkamboj.senseitall.view.tool_activity.image_tools.crop


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.appbusters.robinkamboj.senseitall.R
import com.isseiaoki.simplecropview.CropImageView
import com.isseiaoki.simplecropview.callback.LoadCallback
import kotlinx.android.synthetic.main.fragment_crop.view.*
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class CropFragment : Fragment(), CropInterface {

    var isSomeImageSelected = false
    val REQUEST_CODE_CAPTURE_IMAGE = 102
    val CHOOSER_INTENT_TITLE = "Select Image"
    val REQUEST_CODE_PICK_IMAGE = 101
    val IMAGE_CONTENT_TYPE = "image/*"
    private var mCurrentPhotoPath: String? = null

    lateinit var v: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_crop, container, false)
        setup()
        return v
    }

    override fun setEnabledTint() {
        val color: Int
        if(isSomeImageSelected) {
            color = ContextCompat.getColor(context!!, R.color.primary_new)
        }
        else {
            color = ContextCompat.getColor(context!!, R.color.colorTextThree)
        }
        v.rotate_left.setColorFilter(color)
        v.rotate_right.setColorFilter(color)
        v.save_to_gallery.setColorFilter(color)
        v.one_one.setTextColor(color)
        v.four_three.setTextColor(color)
        v.original_ratio.setTextColor(color)
        v.fit_image.setTextColor(color)
        v.sixteen_nine.setTextColor(color)
    }

    override fun setup() {
        v.crop_view.setHandleShowMode(CropImageView.ShowMode.SHOW_ALWAYS)
        v.crop_view.setGuideShowMode(CropImageView.ShowMode.SHOW_ON_TOUCH)
        v.crop_view.setFrameStrokeWeightInDp(3)
        v.crop_view.setHandleSizeInDp(5)

        setEnabledTint()
        setClickListeners()
    }

    override fun setClickListeners() {
        v.rotate_left.setOnClickListener {
            if(!isSomeImageSelected) {
                showCoordinator("please select an image first")
                return@setOnClickListener
            }
            showCoordinatorPositive("rotated left")
            v.crop_view.rotateImage(CropImageView.RotateDegrees.ROTATE_M90D)
        }
        v.rotate_right.setOnClickListener {
            if(!isSomeImageSelected) {
                showCoordinator("please select an image first")
                return@setOnClickListener
            }
            showCoordinatorPositive("rotated right")
            v.crop_view.rotateImage(CropImageView.RotateDegrees.ROTATE_90D)
        }
        v.save_to_gallery.setOnClickListener {
            if(!isSomeImageSelected) {
                showCoordinator("please select an image first")
                return@setOnClickListener
            }
        }
        v.one_one.setOnClickListener {
            if(!isSomeImageSelected) {
                showCoordinator("please select an image first")
                return@setOnClickListener
            }
            showCoordinatorPositive("1:1 ratio set")
            v.crop_view.setCropMode(CropImageView.CropMode.SQUARE)
        }
        v.four_three.setOnClickListener {
            if(!isSomeImageSelected) {
                showCoordinator("please select an image first")
                return@setOnClickListener
            }
            showCoordinatorPositive("4:3 ratio set")
            v.crop_view.setCropMode(CropImageView.CropMode.RATIO_4_3)
        }
        v.original_ratio.setOnClickListener {
            if(!isSomeImageSelected) {
                showCoordinator("please select an image first")
                return@setOnClickListener
            }
            showCoordinatorPositive("free size ratio set")
            v.crop_view.setCropMode(CropImageView.CropMode.FREE)
        }
        v.fit_image.setOnClickListener {
            if(!isSomeImageSelected) {
                showCoordinator("please select an image first")
                return@setOnClickListener
            }
            showCoordinatorPositive("original image ratio set")
            v.crop_view.setCropMode(CropImageView.CropMode.FIT_IMAGE)
        }
        v.sixteen_nine.setOnClickListener {
            if(!isSomeImageSelected) {
                showCoordinator("please select an image first")
                return@setOnClickListener
            }
            showCoordinatorPositive("16:9 ratio set")
            v.crop_view.setCropMode(CropImageView.CropMode.RATIO_16_9)
        }
        v.select_image_from_gallery.setOnClickListener { openGalleryForImageSelect() }
        v.capture_image.setOnClickListener { openCameraForImageSelect() }
    }

    override fun openGalleryForImageSelect() {
        val getIntent = Intent(Intent.ACTION_GET_CONTENT)
        getIntent.type = IMAGE_CONTENT_TYPE

        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickIntent.type = IMAGE_CONTENT_TYPE

        val chooserIntent = Intent.createChooser(getIntent, CHOOSER_INTENT_TITLE)
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))

        startActivityForResult(chooserIntent, REQUEST_CODE_PICK_IMAGE)
    }

    override fun openCameraForImageSelect() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(activity!!.packageManager) != null) {

            val pictureFile: File?
            try {
                pictureFile = createImageFile()
            } catch (ex: IOException) {
                showCoordinator(getString(R.string.couldnt_create_error))
                return
            }

            val photoURI = FileProvider.getUriForFile(activity!!,
                    "com.appbusters.robinkamboj.senseitall.GenericFileProvider", pictureFile)
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            startActivityForResult(cameraIntent, REQUEST_CODE_CAPTURE_IMAGE)
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        @SuppressLint("SimpleDateFormat")
        val timeStamp = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
        val pictureFile = "SENSE_IT_ALL_$timeStamp"
        val storageDir = activity!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(pictureFile, ".jpg", storageDir)
        mCurrentPhotoPath = image.absolutePath
        return image
    }

    override fun showCoordinator(coordinatorText: String) {
        val s = Snackbar.make(v.coordinator, coordinatorText, Snackbar.LENGTH_SHORT)
        val v = s.view
        v.setBackgroundColor(ContextCompat.getColor(activity!!, R.color.red_shade_three_less_vibrant))
        val t = v.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        t.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
        t.textAlignment = View.TEXT_ALIGNMENT_CENTER
        s.show()
    }

    fun showCoordinatorPositive(coordinatorText: String) {
        val s = Snackbar.make(v.coordinator, coordinatorText, Snackbar.LENGTH_SHORT)
        val v = s.view
        v.setBackgroundColor(ContextCompat.getColor(activity!!, R.color.primary_new))
        val t = v.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        t.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
        t.textAlignment = View.TEXT_ALIGNMENT_CENTER
        s.show()
    }

    override fun hidePlaceholderViews() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == FragmentActivity.RESULT_OK) {
            if (data != null) {
                val selectedImage = data.data ?: return

                try {
                    //get bitmap from selected uri
                    //set bitmap to image to work on
                    //hide placeholders

                    v.crop_view.load(selectedImage).execute(object: LoadCallback {
                        override fun onSuccess() {
                            v.crop_view.setInitialFrameScale(0.5f)
                            isSomeImageSelected = true
                            setEnabledTint()
                        }
                        override fun onError(e: Throwable?) {

                        }
                    });

//                    hidePlaceholderViews()
//                    image_to_work_on!!.setImageBitmap(bitmap)
//                    bitmap = BitmapFactory.decodeStream(
//                            contentResolver.openInputStream(selectedImage)
//                    )
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }

            } else {
                showCoordinator(getString(R.string.no_image_selected_oops))
            }
        } else if (requestCode == REQUEST_CODE_CAPTURE_IMAGE && resultCode == FragmentActivity.RESULT_OK) {
            //get bitmap from uri
            //set bitmap to image to work on
            //hide placeholder views

            v.crop_view.load(Uri.fromFile(File(mCurrentPhotoPath))).execute(object: LoadCallback {
                override fun onSuccess() {
                    v.crop_view.setInitialFrameScale(0.5f)
                    isSomeImageSelected = true
                    setEnabledTint()
                }
                override fun onError(e: Throwable?) {

                }
            });

//            bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath)
//            val imageFile = File(mCurrentPhotoPath!!)
//            if (imageFile.exists()) {
//                image_to_work_on!!.setImageURI(Uri.fromFile(imageFile))
//                hidePlaceholderViews()
//            } else {
//                showCoordinator(getString(R.string.null_photo_error))
//            }
        }
    }
}