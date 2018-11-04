package com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.items_fragment.all_items_adapter

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
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.bumptech.glide.Glide

import com.appbusters.robinkamboj.senseitall.view.detail_activity.DetailActivity
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.TabMainActivity
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.helpers.CheckIfPresent
import com.appbusters.robinkamboj.senseitall.view.tab_dashboard_activity.sections.helpers.GetItemType
import kotlinx.android.synthetic.main.row_simple_item.view.*

class AllItemsAdapter(private val sensorsList: List<String>?, private val context: Context) : RecyclerView.Adapter<AllItemsAdapter.AllSensorsHolder>() {

    private val maxLengthPrint: Int = 12

    private val colorsList: List<Int> = AppConstants.simpleColors
    private var checkIfPresent: CheckIfPresent = CheckIfPresent(context)

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

        holder.itemView.setOnClickListener {
            if (checkIfPresent.returnPresence(sensorsList[position]))
                startDetailActivityForItem(context, sensorsList[position])
            else {
                var item: String = sensorsList.get(position)
                if(item.length > maxLengthPrint)
                    item = item.substring(0, maxLengthPrint)
                (context as TabMainActivity).showSnackBar(
                        "$item... not present in your device"
                )
            }
        }
    }

    private fun startDetailActivityForItem(context: Context, sensorName: String) {
        val intent = Intent(context, DetailActivity::class.java)

        val args = Bundle()

        args.putString(AppConstants.DATA_NAME, sensorName)
        args.putString(AppConstants.RECYCLER_NAME, sensorName)
        args.putInt(AppConstants.DRAWABLE_ID, AppConstants.imageUrlMap[sensorName]!!)
        args.putBoolean(AppConstants.IS_PRESENT, true)
        args.putInt(AppConstants.TYPE, GetItemType(sensorName).itemType)

        intent.putExtras(args)

        context.startActivity(intent)
        (context as Activity).overridePendingTransition(R.anim.slide_in_right_activity, R.anim.slide_out_left_activity)
    }

    override fun getItemCount(): Int {
        return sensorsList?.size ?: 0
    }

    inner class AllSensorsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var simpleItemCardView: CardView = itemView.simple_item_card_view
        var sensorImageView: ImageView = itemView.sensor_image_view
        var sensorTextView: TextView = itemView.sensor_text_view

    }
}
