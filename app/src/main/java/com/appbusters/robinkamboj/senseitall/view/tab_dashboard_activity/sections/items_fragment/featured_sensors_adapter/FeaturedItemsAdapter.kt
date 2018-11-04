package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.items_fragment.featured_sensors_adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.model.recycler.GenericData
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.TabMainActivity
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.helpers.CheckIfPresent
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.helpers.GetItemType
import com.appbusters.robinkamboj.senseitall.view.test_activity.TestActivity
import com.appbusters.robinkamboj.senseitall.view.test_activity.helper.IsPresentHelper
import com.bumptech.glide.Glide

import java.util.ArrayList

import kotlinx.android.synthetic.main.row_featured_item.view.*

class FeaturedItemsAdapter(sensorsList: List<String>, private val context: Context) : RecyclerView.Adapter<FeaturedItemsAdapter.FeaturedSensorsHolder>() {

    private val sensorsList = ArrayList<String?>()
    private val colorsList: List<Int> = AppConstants.featuredColors
    private var isPresentHelper: IsPresentHelper
    private var checkIfPresent: CheckIfPresent

    init {
        this.sensorsList.add(null)
        this.sensorsList.addAll(sensorsList)
        isPresentHelper = IsPresentHelper(context)
        checkIfPresent = CheckIfPresent(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): FeaturedSensorsHolder {
        return FeaturedSensorsHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_featured_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FeaturedSensorsHolder, position: Int) {
        if (sensorsList[position] == null) {
            holder.itemView.visibility = View.GONE
        } else {
            holder.itemView.visibility = View.VISIBLE

            holder.featuredSensorText.text = oneWordName(sensorsList.get(position)!!)

            holder.featuredSensorCard.setCardBackgroundColor(
                    ContextCompat.getColor(
                            context,
                            colorsList[position]
                    )
            )
            Glide.with(context)
                    .load(AppConstants.imageUrlMap[sensorsList[position]])
                    .into(holder.sensorImageView)

            holder.itemView.setOnClickListener {
                if (checkIfPresent.returnPresence(sensorsList.get(position)))
                    startTest(sensorsList.get(position)!!, context)
                else
                    (context as TabMainActivity).showSnackBar("${sensorsList.get(position)} not present in your device")
            }
        }
    }

    private fun startTest(sensorName: String, context: Context) {
        val intentData = GenericData(
                sensorName,
                AppConstants.imageUrlMap.get(sensorName)!!,
                isPresentHelper.isPresent(sensorName),
                GetItemType(sensorName).itemType
        )

        val args = Bundle()

        args.putString(AppConstants.DATA_NAME, intentData.name)
        args.putString(AppConstants.RECYCLER_NAME, intentData.name)
        args.putInt(AppConstants.DRAWABLE_ID, intentData.drawableId)
        args.putBoolean(AppConstants.IS_PRESENT, intentData.isPresent)
        args.putInt(AppConstants.TYPE, intentData.type)

        val intent = Intent(context, TestActivity::class.java)
        intent.putExtras(args)

        context.startActivity(intent)
        (context as Activity).overridePendingTransition(R.anim.slide_in_right_activity, R.anim.slide_out_left_activity)
    }

    override fun getItemCount(): Int {
        return sensorsList.size
    }

    private fun oneWordName(text: String): String {

        for(i in 1..(text.length-1))
            if(text[i] == ' ')
                return text.substring(0, i)

        return text
    }

    inner class FeaturedSensorsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var sensorImageView: ImageView
        var featuredSensorCard: CardView
        var featuredSensorText: TextView

        init {
            sensorImageView = itemView.sensor_image_view
            featuredSensorCard = itemView.featured_sensor_card
            featuredSensorText = itemView.featured_sensor_text
        }
    }
}
