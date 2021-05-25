package tech.notzero.people.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class People(
    var name:String? = null,
    var height:String? = null,
    var mass:String? = null,
    @SerializedName("hair_color")
    @Expose
    var hairColor:String? = null,
    @SerializedName("skin_color")
    @Expose
    var skinColor:String? = null,
    @SerializedName("eye_color")
    @Expose
    var eyeColor:String? = null,
    @SerializedName("birth_year")
    @Expose
    var birthYear:String? = null,
    var gender:String? = null,
    var homeworld:String? = null
):Parcelable