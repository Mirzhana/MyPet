package com.ayanamirzhana.mypet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.ayanamirzhana.mypet.model.Announcement
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_add_announce.*

class HomeFragment : Fragment() {

    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        recycler = root.findViewById<RecyclerView>(R.id.homeRecycler)

        val myRef = FirebaseDatabase.getInstance().getReference("announcements")
        val dataset = ArrayList<Announcement>()

        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    dataset.add(Announcement(dataSnapshot.child("title").value.toString(),
                                            dataSnapshot.child("category").value.toString(),
                                            dataSnapshot.child("description").value.toString(),
                                            dataSnapshot.child("contact").value.toString(),

                                            dataSnapshot.child("imageUrl").value.toString()
                            ))
                }

                loadDataset(dataset)
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })

        return root
    }

    fun loadDataset(dataset: ArrayList<Announcement>) {
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = HomeAdapter(activity!!, dataset)
    }



}