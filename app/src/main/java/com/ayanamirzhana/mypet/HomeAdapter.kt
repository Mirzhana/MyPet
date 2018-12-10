package com.ayanamirzhana.mypet

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ayanamirzhana.mypet.model.Announcement
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_add_announce.view.*
import kotlinx.android.synthetic.main.item_announcement.view.*

class HomeAdapter(var context: Context, var dataset: ArrayList<Announcement>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return AnnouncementViewHolder(LayoutInflater.from(context).inflate(R.layout.item_announcement, p0, false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val announcement = dataset[p1]

        Picasso.get().load(announcement.imageUrl).resize(100,100).centerCrop().into(p0.itemView.image_pet)
        p0.itemView.text_title_pet.text = announcement.title
        p0.itemView.text_category_pet.text = announcement.category
        p0.itemView.text_desc_pet.text = announcement.description
        p0.itemView.text_contact_pet.text = announcement.contact

    }

    inner class AnnouncementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                val act = context as Activity

                act.startActivityForResult(Intent(context, DetailsActivity::class.java).putExtra("announcement", dataset[adapterPosition]), 1)


            }
        }
    }
}