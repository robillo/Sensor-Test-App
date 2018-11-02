package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.sensors_fragment.featured_sensors_adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.bumptech.glide.Glide

import java.util.ArrayList

import kotlinx.android.synthetic.main.row_featured_item.view.*

class FeaturedSensorsAdapter(sensorsList: List<String>, private val context: Context) : RecyclerView.Adapter<FeaturedSensorsAdapter.FeaturedSensorsHolder>() {

    private val sensorsList = ArrayList<String?>()
    private val colorsList: List<Int>

    init {
        colorsList = AppConstants.featuredColors
        this.sensorsList.add(null)
        this.sensorsList.addAll(sensorsList)
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
        }
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
