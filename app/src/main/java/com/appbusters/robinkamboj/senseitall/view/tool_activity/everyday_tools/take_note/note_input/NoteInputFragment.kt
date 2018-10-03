package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note.note_input


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.view.tool_activity.ToolActivity
import kotlinx.android.synthetic.main.fragment_note_input.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class NoteInputFragment : Fragment(), NoteInputInterface {

    lateinit var v: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_note_input, container, false)
        setup()
        return v
    }

    override fun setup() {
        setClickListeners()
    }

    override fun setClickListeners() {
        v.add_note.setOnClickListener {
            //add note and hide this fragment
            val toolActivity = activity as ToolActivity

            val head = v.heading_text.text.toString()
            val desc = v.description_text.text.toString()

            if(head.isNotEmpty() && desc.isNotEmpty()) {
                toolActivity.saveNoteItem(head, desc)
                toolActivity.showCoordinator("note saved successfully")
                toolActivity.onBackPressed()
                return@setOnClickListener
            }
            else {
                toolActivity.showCoordinator("please input both parameters")
            }
        }
    }

}
