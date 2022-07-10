package edu.uchicago.gerber.favs.data.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Location {
    @SerializedName("address1")
    @Expose
    var address1: String? = null

    @SerializedName("address2")
    @Expose
    var address2: String? = null

    @SerializedName("address3")
    @Expose
    var address3: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("zip_code")
    @Expose
    var zipCode: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("state")
    @Expose
    var state: String? = null

    @SerializedName("display_address")
    @Expose
    var displayAddress: List<String>? = null
}