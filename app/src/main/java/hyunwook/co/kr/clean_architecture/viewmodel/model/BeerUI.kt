package hyunwook.co.kr.clean_architecture.viewmodel.model

class BeerUI(
    val id: Int,
    val name: String,
    val tagline: String,
    val image: String,
    val abv: Double,
    val abvColorType: AbvColorType
)