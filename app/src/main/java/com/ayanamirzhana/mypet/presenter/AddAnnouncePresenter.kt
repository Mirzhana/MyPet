package com.ayanamirzhana.mypet.presenter

import com.ayanamirzhana.mypet.model.Announcement
import com.ayanamirzhana.mypet.view.AddAnnounceView
import com.google.firebase.database.FirebaseDatabase

class AddAnnouncePresenter(var view: AddAnnounceView) {

    fun uploadAnnouncement(announcement: Announcement) {
        val myRef = FirebaseDatabase.getInstance().getReference("announcements")
        val id = myRef.push().key
        myRef.child(id!!).setValue(announcement)

        // view.updateUI()
    }
}