package hyunwook.co.kr.clean_architecture.ui.model

import android.os.Parcel
import android.os.Parcelable

data class BeerAdapterModel(
    val id: Int,
    val name: String?,
    val tagline: String?,
    val image: String?,
    val abv: Double,
    val abvColor: Int
): Parcelable {
    constructor(parcel: Parcel) :this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt())

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel!!.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(tagline)
        parcel.writeString(image)
        parcel.writeDouble(abv)
        parcel.writeInt(abvColor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR: Parcelable.Creator<BeerAdapterModel> {
        override fun createFromParcel(parcel: Parcel): BeerAdapterModel {
            return BeerAdapterModel(parcel)
        }

        override fun newArray(size: Int): Array<BeerAdapterModel?> {
            return arrayOfNulls(size)
        }
    }

    }

