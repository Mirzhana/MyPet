package com.ayanamirzhana.mypet

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import com.ayanamirzhana.mypet.model.UploadInfo
import com.ayanamirzhana.mypet.viewholder.ImgViewHolder
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_storage.*
import java.io.IOException

class StorageActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        val i =  v!!.id

        when (i) {
            R.id.btn_choose_file -> showChoosingFile()
            R.id.btn_upload_file -> uploadFile()

        }
    }

    private val CHOOSING_IMAGE_REQUEST = 1234

    private var fileUri: Uri? = null

    private var dataReference: DatabaseReference? = null
    private var imageReference: StorageReference? = null
    private var fileRef: StorageReference? = null

    private var mAdapter: FirebaseRecyclerAdapter<UploadInfo, ImgViewHolder>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)
        dataReference = FirebaseDatabase.getInstance().getReference("images")
        imageReference = FirebaseStorage.getInstance().reference.child("images")
        btn_choose_file.setOnClickListener(this)
        btn_upload_file.setOnClickListener(this)


        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = false
        rcvListImg.setHasFixedSize(true)
        rcvListImg.layoutManager = layoutManager
        val query = dataReference!!.limitToLast(3)

        /*mAdapter = object : FirebaseRecyclerAdapter<UploadInfo, ImgViewHolder>(
                UploadInfo::class.java, R.layout.item_image, ImgViewHolder::class.java, query) {


            override fun populateViewHolder(viewHolder: ImgViewHolder?, model: UploadInfo?, position: Int) {
                Picasso.with(this@StorageActivity)
                        .load(model.url)
                        .error(R.drawable.common_google_signin_btn_icon_dark)
                        .into(viewHolder.itemView.imgView)
            }
        }*/



    }

    private fun showChoosingFile() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Image"), CHOOSING_IMAGE_REQUEST)
    }

    private fun uploadFile() {

    }

}


