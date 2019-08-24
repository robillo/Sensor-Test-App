package com.appbusters.robinkamboj.senseitall.ui.tool_activity.image_tools.draw


import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appbusters.robinkamboj.senseitall.R
import kotlinx.android.synthetic.main.fragment_draw.view.*
import android.support.design.widget.Snackbar
import android.widget.TextView
import java.io.*


/**
 * A simple [Fragment] subclass.
 *
 */
class DrawFragment : Fragment(), DrawInterface {

    lateinit var v: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_draw, container, false)
        setup()
        return v
    }

    override fun setup() {
        setClickListeners()
    }

    override fun setClickListeners() {

        v.draw_view.setStrokeWidth(2f)

        v.small_two.setOnClickListener { v.draw_view.setStrokeWidth(2f) }
        v.small_one.setOnClickListener { v.draw_view.setStrokeWidth(6f) }
        v.mid_size.setOnClickListener { v.draw_view.setStrokeWidth(10f) }
        v.large_one.setOnClickListener { v.draw_view.setStrokeWidth(14f) }
        v.large_two.setOnClickListener { v.draw_view.setStrokeWidth(18f) }
        v.black.setOnClickListener { v.draw_view.setColor(ContextCompat.getColor(context!!, R.color.black)) }
        v.red.setOnClickListener { v.draw_view.setColor(ContextCompat.getColor(context!!, R.color.red)) }
        v.yellow.setOnClickListener { v.draw_view.setColor(ContextCompat.getColor(context!!, R.color.yellow)) }
        v.green.setOnClickListener { v.draw_view.setColor(ContextCompat.getColor(context!!, R.color.green)) }
        v.blue.setOnClickListener { v.draw_view.setColor(ContextCompat.getColor(context!!, R.color.blue)) }
        v.pink.setOnClickListener { v.draw_view.setColor(ContextCompat.getColor(context!!, R.color.pink)) }
        v.brown.setOnClickListener { v.draw_view.setColor(ContextCompat.getColor(context!!, R.color.brown)) }
        v.save_to_gallery.setOnClickListener {
            val direct = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "Sense It All"
            )

            if (!direct.exists()) {
                direct.mkdirs()
            }

            val bitmap = v.draw_view.getBitmap()

            val file = File(
                    direct,
                    "img_draw_" + System.currentTimeMillis().toString() + ".jpg"
            )

            if (file.exists()) file.delete()

            try {
                val out = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
                out.flush()
                out.close()
                showCoordinatorPositive("IMAGE SAVED SUCCESSFULLY")
            } catch (e: Exception) {
                showCoordinatorPositive("SOME ERROR OCCURRED")
                e.printStackTrace()
            }
        }
        v.undo.setOnClickListener { v.draw_view.undo() }
        v.redo.setOnClickListener { v.draw_view.redo() }
        v.clear_canvas.setOnClickListener { v.draw_view.clearCanvas() }
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

    fun showCoordinatorNegative(coordinatorText: String) {
        val s = Snackbar.make(v.coordinator, coordinatorText, Snackbar.LENGTH_SHORT)
        val v = s.view
        v.setBackgroundColor(ContextCompat.getColor(activity!!, R.color.red_shade_two))
        val t = v.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        t.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
        t.textAlignment = View.TEXT_ALIGNMENT_CENTER
        s.show()
    }

}
