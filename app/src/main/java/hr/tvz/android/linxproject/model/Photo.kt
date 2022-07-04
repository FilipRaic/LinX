package hr.tvz.android.linxproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo (
    @SerializedName("filename")
    var filename: String? = null
): Parcelable