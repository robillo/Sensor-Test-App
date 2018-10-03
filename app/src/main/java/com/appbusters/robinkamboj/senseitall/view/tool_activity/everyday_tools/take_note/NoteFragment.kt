package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.appbusters.robinkamboj.senseitall.R
import com.appbusters.robinkamboj.senseitall.view.tool_activity.ToolActivity
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note.adapter.NoteAdapter
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note.db.Note
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note.db.NoteDao
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note.db.NoteDatabase
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note.db.NotePresenter
import kotlinx.android.synthetic.main.fragment_note.view.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class NoteFragment : Fragment(), NoteInterface {

    private lateinit var v: View
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var noteDao: NoteDao
    private lateinit var notePresenter: NotePresenter
    private lateinit var noteData: LiveData<MutableList<Note>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_note, container, false)
        setup()
        return v
    }

    override fun setup() {
        getDbInstances()
        initialize()
        displayAllNotes()
        setClickListeners()
    }

    override fun saveNoteToDb(heading: String, description: String) {
        notePresenter.insertNotes(
                Note(
                        heading,
                        description,
                        Date().toString(),
                        Calendar.getInstance().time.toString()
                )
        )
    }

    override fun getDbInstances() {
        noteDatabase = NoteDatabase.getDatabaseInstance(context)
        noteDao = noteDatabase.noteDao
        notePresenter = NotePresenter(noteDao)
    }

    override fun initialize() {
        v.recycler.layoutManager = LinearLayoutManager(activity)
    }

    override fun displayAllNotes() {
        noteData = notePresenter.allNotes

        noteData.observe(this, Observer {
            v.recycler.adapter = NoteAdapter(it, activity)
        })
    }

    override fun setClickListeners() {
        v.add_note.setOnClickListener {
            val toolActivity = activity as ToolActivity
            toolActivity.setNoteInputFragment()
        }
    }
}
