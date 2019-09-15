package hyunwook.co.kr.clean_architecture.datasource.model

import com.google.gson.annotations.SerializedName

data class BeerResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("tagline") val tagline: String? = null,
    @SerializedName("image_url") val image: String? = null,
    @SerializedName("abv") val abv: Double? = null
)