package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.items_fragment.featured_sensors_adapter

import android.app.Activity
import android.content.Context
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

    private val START: Int = 1
    private val SPACE: Char = ' '

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
        sensorsList[position]?.let {
            holder.itemView.visibility = View.VISIBLE

            holder.featuredSensorText.text = oneWordName(sensorsList.get(position)!!)

            holder.featuredSensorCard.setCardBackgroundColor(
                    ContextCompat.getColor(
                            context,
                            colorsList[position]
                    )
            )

            Glide.with(context)
                    .load(AppConstants.toolImageUrlMap[sensorsList[position]])
                    .into(holder.sensorImageView)

            holder.itemView.setOnClickListener {
                if (checkIfPresent.returnPresence(sensorsList.get(position)))
                    startTest(sensorsList.get(position)!!, context)
                else
                    (context as TabMainActivity).showSnackBar("${sensorsList.get(position)} not present in your device")
            }
        } ?:

        kotlin.run {
            holder.itemView.visibility = View.GONE
        }
    }

    private fun startTest(sensorName: String, context: Context) {
        val intentData = GenericData(
                sensorName,
                AppConstants.toolImageUrlMap.get(sensorName)!!,
                isPresentHelper.isPresent(sensorName),
                GetItemType(sensorName).itemType
        )

        context.startActivity(
                TestActivity.newIntent(
                        context, intentData.name, intentData.name,
                        intentData.drawableId, intentData.isPresent,
                        intentData.type
                )
        )

        (context as Activity).overridePendingTransition(R.anim.slide_in_right_activity, R.anim.slide_out_left_activity)
    }

    override fun getItemCount(): Int {
        return sensorsList.size
    }

    private fun oneWordName(text: String): String {

        for(index in START..(text.length - 1))
            if(text[index] == SPACE) return stringBeforeIndex(text, 0, index)

        return text
    }

    private fun stringBeforeIndex(text: String, start: Int, end: Int): String {
        return text.substring(start, end)
    }

    inner class FeaturedSensorsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var sensorImageView: ImageView = itemView.sensor_image_view
        var featuredSensorCard: CardView = itemView.featured_sensor_card
        var featuredSensorText: TextView = itemView.featured_sensor_text

    }
}