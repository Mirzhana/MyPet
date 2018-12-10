package com.ayanamirzhana.mypet

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.ayanamirzhana.mypet.model.Announcement

@Dao
interface AnnouncementDAO {


        @Query("SELECT * FROM announcement")
        fun getAll(): List<Announcement>

        @Insert
        fun insert(announcement:Announcement)


}