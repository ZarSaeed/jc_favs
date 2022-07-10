package edu.uchicago.gerber.favs.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Region {
    @SerializedName("center")
    @Expose
    var center: Center? = null
}