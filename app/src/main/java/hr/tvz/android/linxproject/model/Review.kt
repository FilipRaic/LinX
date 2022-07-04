package hr.tvz.android.linxproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review (
    @SerializedName("title")
    var title: String? = null,

    @SerializedName("rating")
    var rating: Float? = null
): Parcelable