package com.ayanamirzhana.mypet.models

data class User(
        var id:String,
        var name:String,
        var announcements: ArrayList<String>
){
}