package hyunwook.co.kr.clean_architecture.ui.model

import android.os.Parcel
import android.os.Parcelable

data class BeersAdapterModel(
    val positionLastVisibleItem: Int = 0,
    val beers: List<BeerAdapterModel>
) : Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readInt(),
        mutableListOf<BeerAdapterModel>().apply {
            parcel.readTypedList(this, BeerAdapterModel)
        }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(positionLastVisibleItem)
        parcel.writeTypedList(beers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR: Parcelable.Creator<BeersAdapterModel> {
        override fun createFromParcel(parcel: Parcel): BeersAdapterModel {
            return BeersAdapterModel(parcel)
        }

        override fun newArray(size: Int): Array<BeersAdapterModel?> {
            return arrayOfNulls(size)
        }
    }
}