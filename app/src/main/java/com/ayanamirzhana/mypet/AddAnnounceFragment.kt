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
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.ayanamirzhana.mypet.model.Announcement
import com.ayanamirzhana.mypet.presenter.AddAnnouncePresenter
import com.ayanamirzhana.mypet.view.AddAnnounceView
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask

import java.io.IOException
import java.util.*

class AddAnnounceFragment : Fragment(), AddAnnounceView {
    private var ctx: Context? = null
    private var self: View? = null
    private var url = ""
    var imgCode = 0
    private lateinit var presenter: AddAnnouncePresenter

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

        presenter = AddAnnouncePresenter(this)

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
                presenter.uploadAnnouncement(announcement)
                Toast.makeText(ctx, "Announcement is added!", Toast.LENGTH_SHORT).show()
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

        val uploadTask = imageRef.putFile(uri)

        val urlTask = uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            return@Continuation imageRef.downloadUrl
        }).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                url = downloadUri.toString()
            } else {
                // Handle failures
                // ...
            }
        }


        /*val durl = imageRef.downloadUrl

        imageRef.putFile(uri)
                .addOnSuccessListener {
                    val downloadUrl = imageRef.downloadUrl
                    url = downloadUrl.toString()
                }
                .addOnFailureListener {
                    Toast.makeText(ctx, "Failed to add image!", Toast.LENGTH_SHORT).show()
                }*/
    }

//    fun uploadAnnouncement(announcement: Announcement) {
//        val myRef = FirebaseDatabase.getInstance().getReference("announcements")
//        val id = myRef.push().key
//        myRef.child(id!!).setValue(announcement)
//    }
}