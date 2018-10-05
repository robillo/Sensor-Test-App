package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.checklist


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.appbusters.robinkamboj.senseitall.R
import kotlinx.android.synthetic.main.fragment_checklist.view.*
import android.widget.TextView
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.checklist.adapter.CheckAdapter
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.checklist.db.Check
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.checklist.db.CheckDao
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.checklist.db.CheckDatabase
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.checklist.db.CheckPresenter
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note.adapter.NoteAdapter
import kotlinx.android.synthetic.main.fragment_note.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ChecklistFragment : Fragment(), ChecklistInterface {

    lateinit var v: View
    private lateinit var checkDatabase: CheckDatabase
    private lateinit var checkDao: CheckDao
    private lateinit var checkPresenter: CheckPresenter
    private lateinit var checkData: LiveData<MutableList<Check>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_checklist, container, false)
        setup()
        return v
    }

    override fun setup() {
        setClickListeners()
        getDbInstances()
        initialize()
        displayAllNotes()
    }

    override fun getDbInstances() {
        checkDatabase = CheckDatabase.getDatabaseInstance(context)
        checkDao = checkDatabase.checkDao
        checkPresenter = CheckPresenter(checkDao)
    }

    override fun initialize() {
        v.check_recycler.layoutManager = LinearLayoutManager(activity)
    }

    override fun displayAllNotes() {
        checkData = checkPresenter.allChecks

        checkData.observe(this, Observer {
            v.check_recycler.adapter = CheckAdapter(it, activity)
        })
    }

    override fun setClickListeners() {
        v.new_item_text.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(s.length == 50) {
                    showToast("max 50 characters per item")
                }
            }
        })

        v.add_item.setOnClickListener {
            if(v.new_item_text.text.toString().isEmpty()) {
                showToast("please write item description")
            }
            else {
                addItemToDb(Check(v.new_item_text.text.toString(), false))
                v.new_item_text.setText("")
            }
        }
    }

    override fun addItemToDb(check: Check) {
        checkPresenter.insertChecks(check)
    }

    override fun showToast(text: String) {
        val toast = Toast.makeText(context, text, Toast.LENGTH_LONG)
        val view = toast.view
        view.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorBlackShade))
        val tv = view.findViewById(android.R.id.message) as TextView
        tv.setTextColor(ContextCompat.getColor(context!!, R.color.white))
        toast.show()
    }

}
