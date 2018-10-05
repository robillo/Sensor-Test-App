package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note.note_input


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.utils.AppConstants
import com.appbusters.robinkamboj.senseitall.view.tool_activity.ToolActivity
import kotlinx.android.synthetic.main.fragment_note_input.*
import kotlinx.android.synthetic.main.fragment_note_input.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class NoteInputFragment : Fragment(), NoteInputInterface {

    lateinit var v: View
    var isEdit: Boolean = false
    var noteId: Int? = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_note_input, container, false)
        setup()
        return v
    }

    override fun getArgumentStrings() {
        val head: String? = arguments?.getString(AppConstants.ARG_HEADING_NOTE)
        val desc: String? = arguments?.getString(AppConstants.ARG_DESCRIPTION_NOTE)
        noteId = arguments?.getInt(AppConstants.ARG_ID_NOTE)

        if(head != null && desc != null && noteId != -1) {
            isEdit = true
            v.add_note.text = getString(R.string.save_edited_note)
        }

        if(head != null) v.heading_text.setText(head)
        if(desc != null) v.description_text.setText(desc)
    }

    override fun setup() {
        getArgumentStrings()
        setClickListeners()
    }

    override fun setClickListeners() {
        v.add_note.setOnClickListener {
            //add note and hide this fragment
            val toolActivity = activity as ToolActivity

            val head = v.heading_text.text.toString()
            val desc = v.description_text.text.toString()

            if(head.isNotEmpty() && desc.isNotEmpty()) {

                if(isEdit) {
                    toolActivity.saveEditedNote(head, desc, noteId!!)
                    toolActivity.showCoordinator("note edited successfully")
                    toolActivity.onBackPressed()
                    return@setOnClickListener
                }
                else {
                    toolActivity.saveNoteItem(head, desc)
                    toolActivity.showCoordinator("note saved successfully")
                    toolActivity.onBackPressed()
                    return@setOnClickListener
                }
            }
            else {
                toolActivity.showCoordinator("please input both parameters")
            }
        }
    }

}
