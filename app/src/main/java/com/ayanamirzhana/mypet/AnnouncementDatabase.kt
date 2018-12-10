package com.ayanamirzhana.mypet

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.ayanamirzhana.mypet.model.Announcement

@Database(entities = arrayOf(Announcement::class), version = 1)
abstract class AnnouncementDatabase : RoomDatabase() {

    abstract fun announcementDao():AnnouncementDAO

    companion object {
        private var INSTANCE : AnnouncementDatabase? = null

        fun getInstance(context: Context):AnnouncementDatabase? {

            if(INSTANCE == null){
                synchronized(AnnouncementDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AnnouncementDatabase::class.java, "announcement.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }


    }

}