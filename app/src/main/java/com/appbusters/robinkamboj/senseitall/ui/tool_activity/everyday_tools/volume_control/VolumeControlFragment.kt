package com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.volume_control


import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.volume_control.utils.VolumeObserver
import kotlinx.android.synthetic.main.fragment_volume_control.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class VolumeControlFragment : Fragment(), VolumeInterface {

    lateinit var lv: View
    lateinit var audioManager: AudioManager
    lateinit var volumeObserver: VolumeObserver

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_volume_control, container, false)
        lv = v
        setup()
        return v;
    }

    override fun setup() {
        audioManager = activity!!.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        volumeObserver = VolumeObserver(context, Handler())

        setInitialVolumes()
        setCrollerListeners()
    }

    override fun onResume() {
        super.onResume()

        if (activity != null) {
            activity!!.applicationContext.contentResolver.registerContentObserver(
                    android.provider.Settings.System.CONTENT_URI,
                    true,
                    volumeObserver
            )
        }
    }

    override fun setCrollerListeners() {
        lv.media_volume.setOnProgressChangedListener {
            setVolumeFromCroller(AudioManager.STREAM_MUSIC, it)
            lv.media_percent.text = "".plus(getPercentVolume(AudioManager.STREAM_MUSIC)).plus("%")
        }
        lv.call_volume.setOnProgressChangedListener {
            setVolumeFromCroller(AudioManager.STREAM_VOICE_CALL, it)
            lv.call_percent.text = "".plus(getPercentVolume(AudioManager.STREAM_VOICE_CALL)).plus("%")
        }
        lv.ring_volume.setOnProgressChangedListener {
            setVolumeFromCroller(AudioManager.STREAM_RING, it)
            lv.ring_percent.text = "".plus(getPercentVolume(AudioManager.STREAM_RING)).plus("%")
        }
        lv.alarm_volume.setOnProgressChangedListener {
            setVolumeFromCroller(AudioManager.STREAM_ALARM, it)
            lv.alarm_percent.text = "".plus(getPercentVolume(AudioManager.STREAM_ALARM)).plus("%")
        }
    }

    override fun setVolumeFromCroller(type: Int, progress: Int) {
        try {
            audioManager.setStreamVolume(type, progress, 0)
        }
        catch (ignored: SecurityException) {

        }
    }

    override fun setInitialVolumes() {
        lv.media_volume.max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        lv.media_volume.progress = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

        lv.call_volume.max = audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL)
        lv.call_volume.progress = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL)

        lv.ring_volume.max = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING)
        lv.ring_volume.progress = audioManager.getStreamVolume(AudioManager.STREAM_RING)

        lv.alarm_volume.max = audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM)
        lv.alarm_volume.progress = audioManager.getStreamVolume(AudioManager.STREAM_ALARM)
    }

    override fun getPercentVolume(type: Int): Int {
        return audioManager.getStreamVolume(type)*100/audioManager.getStreamMaxVolume(type)
    }
}
