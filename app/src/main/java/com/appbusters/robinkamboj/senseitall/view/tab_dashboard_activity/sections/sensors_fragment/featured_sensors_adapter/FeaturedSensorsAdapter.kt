package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.sensors_fragment.featured_sensors_adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.bumptech.glide.Glide

import java.util.ArrayList
import java.util.Random

import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.row_featured_item.view.*

class FeaturedSensorsAdapter(sensorsList: List<String>, private val context: Context) : RecyclerView.Adapter<FeaturedSensorsAdapter.FeaturedSensorsHolder>() {

    private val sensorsList = ArrayList<String?>()
    private val colorsList: List<Int>
    private val random: Random = Random()

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
            holder.featuredSensorCard.setCardBackgroundColor(
                    ContextCompat.getColor(
                            context,
                            colorsList[random.nextInt(colorsList.size)]
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

    inner class FeaturedSensorsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var sensorImageView: ImageView
        var featuredSensorCard: CardView

        init {
            sensorImageView = itemView.sensor_image_view
            featuredSensorCard = itemView.featured_sensor_card
        }
    }
}
