package com.ayanamirzhana.mypet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ayanamirzhana.mypet.models.Announcement
import kotlinx.android.synthetic.main.fragment_add_announce.*

class AddAnnounceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_add_announce, container, false)



    fun getValues(view: View) {
        // get category
        val adapter = ArrayAdapter.createFromResource(activity,
                R.array.categories_list, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter
        val category = spinner2.selectedItem.toString()
        val details = description.text

    }

    companion object {
        fun newInstance(): AddAnnounceFragment = AddAnnounceFragment()
    }

}

