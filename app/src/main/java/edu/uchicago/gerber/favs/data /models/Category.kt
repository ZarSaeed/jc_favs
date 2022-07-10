package edu.uchicago.gerber.favs.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Category {
    @SerializedName("alias")
    @Expose
    var alias: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null
}