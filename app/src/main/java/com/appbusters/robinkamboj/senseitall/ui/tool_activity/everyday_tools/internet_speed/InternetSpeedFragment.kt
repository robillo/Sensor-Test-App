package com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.internet_speed


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appbusters.robinkamboj.senseitall.R
import kotlinx.android.synthetic.main.include_result.view.*
import kotlinx.android.synthetic.main.include_speed.view.*
import kotlinx.android.synthetic.main.include_start.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class InternetSpeedFragment : Fragment(), InternetSpeedInterface {

    lateinit var v: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_internet_speed, container, false)
        setup()
        return v
    }

    override fun setup() {
        setClickListeners()

    }

    override fun setClickListeners() {
        v.start.setOnClickListener {
            v.speed_layout.visibility = View.VISIBLE
            startDownloadTest()
        }
    }

    override fun startDownloadTest() {
        //do download test computation
        startUploadTest()
    }

    override fun startUploadTest() {
        //do upload computation
        v.result_layout.visibility = View.VISIBLE
    }

//        SpeedTestTask().execute()
//
//    inner class SpeedTestTask : AsyncTask<Void, Void, String>() {
//
//        override fun doInBackground(vararg params: Void): String? {
//
//            val speedTestSocket = SpeedTestSocket()
//
//            // add a listener to wait for speedtest completion and progress
//            speedTestSocket.addSpeedTestListener(object : ISpeedTestListener {
//
//                override fun onCompletion(report: SpeedTestReport) {
//                    // called when download/upload is finished
//                    Log.e("speedtest", "[COMPLETED] rate in octet/s : " + report.transferRateOctet)
//                    Log.e("speedtest", "[COMPLETED] rate in bit/s   : " + report.transferRateBit)
//                }
//
//                override fun onError(speedTestError: SpeedTestError, errorMessage: String) {
//                    // called when a download/upload error occur
//                }
//
//                override fun onProgress(percent: Float, report: SpeedTestReport) {
//                    // called to notify download/upload progress
//                    Log.e("speedtest", "[PROGRESS] progress : $percent%")
//                    Log.e("speedtest", "[PROGRESS] rate in octet/s : " + report.transferRateOctet)
//                    Log.e("speedtest", "[PROGRESS] rate in bit/s   : " + report.transferRateBit)
//                }
//            })
//
//            speedTestSocket.startDownload("http://ipv4.ikoula.testdebit.info/1M.iso")
//
//            return null
//        }
//    }
}
