package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.sensors_fragment.all_sensors_adapter

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

import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.row_simple_item.view.*

class AllSensorsAdapter(private val sensorsList: List<String>?, private val context: Context) : RecyclerView.Adapter<AllSensorsAdapter.AllSensorsHolder>() {

    private val colorsList: List<Int> = AppConstants.simpleColors

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): AllSensorsHolder {
        return AllSensorsHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.row_simple_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AllSensorsHolder, position: Int) {

        if (position >= colorsList.size) {
            holder.simpleItemCardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                            context,
                            colorsList[position - colorsList.size]
                    )
            )
        } else {
            holder.simpleItemCardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                            context,
                            colorsList[position]
                    )
            )
        }

        Glide.with(context)
                .load(AppConstants.imageUrlMap[sensorsList!![position]])
                .into(holder.sensorImageView)

        holder.sensorTextView.text = sensorsList[position]
    }

    override fun getItemCount(): Int {
        return sensorsList?.size ?: 0
    }

    inner class AllSensorsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var simpleItemCardView: CardView
        var sensorImageView: ImageView
        var sensorTextView: TextView

        init {
            simpleItemCardView = itemView.simple_item_card_view
            sensorImageView = itemView.sensor_image_view
            sensorTextView = itemView.sensor_text_view
        }
    }
}
