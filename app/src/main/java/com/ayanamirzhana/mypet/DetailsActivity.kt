package com.ayanamirzhana.mypet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ayanamirzhana.mypet.model.Announcement
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_announcement.view.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val announcement = intent.getParcelableExtra<Announcement>("announcement")
        Picasso.get().load(announcement.imageUrl).into(image_details_iv)
        title_text.text = announcement.title
        category_text.text = announcement.category
        description_text.text = announcement.description
        contact_text.text = announcement.contact
    }

}
