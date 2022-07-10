package edu.uchicago.gerber.favs.data.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Center {
    @SerializedName("longitude")
    @Expose
    var longitude: Double? = null

    @SerializedName("latitude")
    @Expose
    var latitude: Double? = null
}