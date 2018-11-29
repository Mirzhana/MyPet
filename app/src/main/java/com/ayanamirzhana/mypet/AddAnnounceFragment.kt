package com.ayanamirzhana.mypet

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri

import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.ayanamirzhana.mypet.model.Announcement

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

import java.io.IOException
import java.util.*
=======
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ayanamirzhana.mypet.models.Announcement
import kotlinx.android.synthetic.main.fragment_add_announce.*
>>>>>>> f76ba2fc4abf109ca8c9a6955d897943cc3875ee

class AddAnnounceFragment : Fragment() {
    private var ctx: Context? = null
    private var self: View? = null
    private var url = ""
    var imgCode = 0

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        ctx = container?.context
        self = LayoutInflater.from(ctx).inflate(R.layout.fragment_add_announce, container, false)

        val bDaButton = self?.findViewById<Button>(R.id.btn_choose_file)
        val uploadBtn = self?.findViewById<Button>(R.id.btn_upload)
        val titleEdit = self?.findViewById<EditText>(R.id.title)
        val descriptionEdit = self?.findViewById<EditText>(R.id.description)
        val contactEdit = self?.findViewById<EditText>(R.id.contact)
        val spinner = self?.findViewById<Spinner>(R.id.spinner2)


        bDaButton?.setOnClickListener {
            /*Toast.makeText(ctx, "button works!", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, StorageActivity::class.java)
            startActivity(intent)*/

            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.setType("image/*")
            startActivityForResult(photoPickerIntent, imgCode)
        }

        uploadBtn?.setOnClickListener {
            val title = titleEdit?.text.toString()
            val description = descriptionEdit?.text.toString()
            val contact = contactEdit?.text.toString()
            val spinnerText = spinner?.selectedItem.toString()
            val downloadImageUrl = url

            if (!title.isEmpty() && !description.isEmpty() && !spinnerText.isEmpty() && !downloadImageUrl.isEmpty() && !contact.isEmpty()) {
                val announcement = Announcement(title,  spinnerText, description, contact, downloadImageUrl)
                uploadAnnouncement(announcement)
            }
        }



        return self

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == imgCode) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {

                    val uri = data.data

                    try {
                        uploadImage(uri)

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    fun uploadImage(uri: Uri) {

        val storage = FirebaseStorage.getInstance().reference
        val currentTime = Calendar.getInstance().time
        val imageRef =
                storage.child("images/" + FirebaseAuth.getInstance().currentUser?.uid + "_" + currentTime + ".jpg")

        imageRef.putFile(uri)
                .addOnSuccessListener {
                    val downloadUrl = imageRef.downloadUrl
                    url = downloadUrl.toString()
                }
                .addOnFailureListener {
                    Toast.makeText(ctx, "Failed to add image!", Toast.LENGTH_SHORT).show()
                }
    }

<<<<<<< HEAD
    fun uploadAnnouncement(announcement: Announcement) {
        val myRef = FirebaseDatabase.getInstance().getReference("announcements")
        val id = myRef.push().key
        myRef.child(id!!).setValue(announcement)
=======


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
>>>>>>> f76ba2fc4abf109ca8c9a6955d897943cc3875ee
    }

}

