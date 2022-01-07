package com.example.kuismws

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Quotes {
    @SerializedName("text")
    @Expose
    var text: String? = null

    @SerializedName("author")
    @Expose
    var author: String? = null
}