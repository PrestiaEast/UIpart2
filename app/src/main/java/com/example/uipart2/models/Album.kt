package com.example.uipart2.models

class Album(var id: Int = 0, var title: String, var releaseDate: Double){
    override fun toString(): String {
        return "Title: ${title}, Release Date: ${releaseDate}"
    }
}