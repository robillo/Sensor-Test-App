package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.checklist


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.appbusters.robinkamboj.senseitall.R
import kotlinx.android.synthetic.main.fragment_checklist.view.*
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 *
 */
class ChecklistFragment : Fragment(), ChecklistInterface {

    lateinit var v: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_checklist, container, false)
        setup()
        return v
    }

    override fun setup() {
        setClickListeners()
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
                addItemToDb(v.new_item_text.text.toString())
                addItemToRv(v.new_item_text.text.toString())
                v.new_item_text.setText("")
            }
        }
    }

    override fun addItemToDb(text: String) {
    }

    override fun addItemToRv(text: String) {
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
