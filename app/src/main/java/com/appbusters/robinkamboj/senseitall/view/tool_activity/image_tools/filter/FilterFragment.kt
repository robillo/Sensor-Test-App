package com.appbusters.robinkamboj.senseitall.view.tool_activity.image_tools.filter


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.view.tool_activity.image_tools.filter.filter_adapter.FilterAdapter
import com.appbusters.robinkamboj.senseitall.view.tool_activity.image_tools.filter.filter_adapter.FilterList
import com.appbusters.robinkamboj.senseitall.view.tool_activity.image_tools.filter.filter_adapter.FilterListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_filter.view.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FilterFragment : Fragment(), FilterInterface {

    lateinit var bitmap: Bitmap
    lateinit var v: View
    lateinit var sourceUri: Uri
    var isSomeImageSelected = false
    val REQUEST_CODE_CAPTURE_IMAGE = 102
    val CHOOSER_INTENT_TITLE = "Select Image"
    val REQUEST_CODE_PICK_IMAGE = 101
    val IMAGE_CONTENT_TYPE = "image/*"
    private var mCurrentPhotoPath: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_filter, container, false)
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
    }

    @SuppressLint("MissingPermission")
    override fun setClickListeners() {
        v.rotate_left.setOnClickListener {
            if(!isSomeImageSelected) {
                showCoordinator("please select an image first")
                return@setOnClickListener
            }
            showCoordinatorPositive("rotated left")

            @Suppress("SENSELESS_COMPARISON")
            if (bitmap == null) {
                showCoordinator(getString(R.string.please_show_image))
                return@setOnClickListener
            }

            val matrix = Matrix()
            matrix.postRotate(-90f)
            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.width, bitmap.height, true)
            bitmap = Bitmap.createBitmap(
                    scaledBitmap,
                    0,
                    0,
                    scaledBitmap.width,
                    scaledBitmap.height,
                    matrix,
                    true
            )

            Glide.with(context!!).load(bitmap).into(v.filter_image_view)
        }
        v.rotate_right.setOnClickListener {
            if(!isSomeImageSelected) {
                showCoordinator("please select an image first")
                return@setOnClickListener
            }
            showCoordinatorPositive("rotated right")

            @Suppress("SENSELESS_COMPARISON")
            if (bitmap == null) {
                showCoordinator(getString(R.string.please_show_image))
                return@setOnClickListener
            }

            val matrix = Matrix()
            matrix.postRotate(90f)
            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.width, bitmap.height, true)
            bitmap = Bitmap.createBitmap(
                    scaledBitmap,
                    0,
                    0,
                    scaledBitmap.width,
                    scaledBitmap.height,
                    matrix,
                    true
            )

            Glide.with(context!!).load(bitmap).into(v.filter_image_view)
        }
        v.save_to_gallery.setOnClickListener {
            if(!isSomeImageSelected) {
                showCoordinator("please select an image first")
                return@setOnClickListener
            }
            val direct = File(Environment.getExternalStorageDirectory().toString() + "/Sense It All")
            if (!direct.exists()) {
                val wallpaperDirectory = File(Environment.getExternalStorageDirectory().path + "/Sense It All/")
                wallpaperDirectory.mkdirs()
            }

            val bitmap = (v.filter_image_view.drawable as BitmapDrawable).bitmap

            val file = File(
                    Environment.getExternalStorageDirectory().path + "/Sense It All/",
                    "img_draw_" + System.currentTimeMillis().toString() + ".jpg"
            )
            if (file.exists())
                file.delete()
            try {
                val out = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
                out.flush()
                out.close()
                showCoordinatorPositive("IMAGE SAVED SUCCESSFULLY")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        v.select_image_from_gallery.setOnClickListener { openGalleryForImageSelect() }
        v.capture_image.setOnClickListener { openCameraForImageSelect() }
    }

    override fun setup() {

        v.recycler.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL,
                false
        )

        setEnabledTint()
        setClickListeners()
    }

    override fun setFilterAdapter() {
        if(v.recycler.adapter != null) return

        v.recycler.adapter = FilterAdapter(FilterList.filterItemsList, context!!, FilterListener {
            if(!isSomeImageSelected) {
                showCoordinator("please select an image first")
            }
            else {
                if(it != null) {
                    Glide.with(this).load(bitmap)
                            .apply(RequestOptions.bitmapTransform(it))
                            .into(v.filter_image_view)
                }
                else {
                    Glide.with(this).load(bitmap)
                            .into(v.filter_image_view)
                }
            }
        })
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

            val photoURI = FileProvider.getUriForFile(
                    activity!!,
                    "com.appbusters.robinkamboj.senseitall.GenericFileProvider",
                    pictureFile
            )
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

    fun showCoordinatorSaved(coordinatorText: String, uri: Uri?) {
        val s = Snackbar.make(v.coordinator, coordinatorText, Snackbar.LENGTH_SHORT)
        val v = s.view
        v.setBackgroundColor(ContextCompat.getColor(activity!!, R.color.colorBlackShade))
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
                sourceUri = data.data ?: return

                try {
                    //get bitmap from selected uri
                    //set bitmap to image to work on
                    //hide placeholders

                    bitmap = BitmapFactory.decodeStream(
                            activity!!.contentResolver.openInputStream(sourceUri)
                    )

                    Glide.with(context!!).load(bitmap).into(v.filter_image_view)
                    isSomeImageSelected = true
                    setEnabledTint()
                    setFilterAdapter()

                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }

            } else {
                showCoordinator(getString(R.string.no_image_selected_oops))
            }
        } else if (requestCode == REQUEST_CODE_CAPTURE_IMAGE && resultCode == FragmentActivity.RESULT_OK) {
            sourceUri = Uri.fromFile(File(mCurrentPhotoPath))
            bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath)
            Glide.with(context!!).load(bitmap).into(v.filter_image_view)
            isSomeImageSelected = true
            setEnabledTint()
            setFilterAdapter()

//            val imageFile = File(mCurrentPhotoPath!!)
//            if (imageFile.exists()) {
//                isSomeImageSelected = true
//                setEnabledTint()
//                Glide.with(context!!).load(Uri.fromFile(imageFile)).into(v.filter_image_view)
//                setFilterAdapter()
//            } else {
//                showCoordinator(getString(R.string.null_photo_error))
//            }
        }
    }
}
