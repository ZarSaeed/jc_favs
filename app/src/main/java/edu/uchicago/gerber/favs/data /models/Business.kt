package edu.uchicago.gerber.favs.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Business() : Parcelable {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("alias")
    @Expose
    var alias: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null

    @SerializedName("is_closed")
    @Expose
    var isClosed: Boolean? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("review_count")
    @Expose
    var reviewCount: Int? = null

    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null

    @SerializedName("rating")
    @Expose
    var rating: Double? = null

    @SerializedName("coordinates")
    @Expose
    var coordinates: Coordinates? = null

    @SerializedName("transactions")
    @Expose
    var transactions: List<String>? = null

    @SerializedName("location")
    @Expose
    var location: Location? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null

    @SerializedName("display_phone")
    @Expose
    var displayPhone: String? = null

    @SerializedName("distance")
    @Expose
    var distance: Double? = null

    @SerializedName("price")
    @Expose
    var price: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        alias = parcel.readString()
        name = parcel.readString()
        imageUrl = parcel.readString()
        isClosed = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        url = parcel.readString()
        reviewCount = parcel.readValue(Int::class.java.classLoader) as? Int
        rating = parcel.readValue(Double::class.java.classLoader) as? Double
        transactions = parcel.createStringArrayList()
        phone = parcel.readString()
        displayPhone = parcel.readString()
        distance = parcel.readValue(Double::class.java.classLoader) as? Double
        price = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(alias)
        parcel.writeString(name)
        parcel.writeString(imageUrl)
        parcel.writeValue(isClosed)
        parcel.writeString(url)
        parcel.writeValue(reviewCount)
        parcel.writeValue(rating)
        parcel.writeStringList(transactions)
        parcel.writeString(phone)
        parcel.writeString(displayPhone)
        parcel.writeValue(distance)
        parcel.writeString(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Business> {
        override fun createFromParcel(parcel: Parcel): Business {
            return Business(parcel)
        }

        override fun newArray(size: Int): Array<Business?> {
            return arrayOfNulls(size)
        }
    }
}