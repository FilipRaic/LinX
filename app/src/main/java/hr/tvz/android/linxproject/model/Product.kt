package hr.tvz.android.linxproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("category")
    var category: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("price")
    var price: Float? = 0f,

    @SerializedName("photo")
    var photo: Photo? = null,

    @SerializedName("reviews")
    var reviews: ArrayList<Review> = arrayListOf()
): Parcelable