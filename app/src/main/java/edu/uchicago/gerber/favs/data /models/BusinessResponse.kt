package edu.uchicago.gerber.favs.data.models

import android.graphics.Region
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

class BusinessResponse @Inject constructor() {
    @SerializedName("businesses")
    @Expose
    var businesses: List<Business>? = null

    @SerializedName("total")
    @Expose
    var total: Int? = null

    @SerializedName("region")
    @Expose
    var region: Region? = null
}