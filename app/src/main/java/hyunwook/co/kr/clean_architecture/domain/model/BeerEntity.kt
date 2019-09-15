package hyunwook.co.kr.clean_architecture.domain.model

//각 맥주 관련 Entity
class BeerEntity (
    val id: Int,
    val name: String,
    val tagline: String,
    val image: String,
    val abv: Double
) {
    fun getAbvRange(abv: Double): AbvRangeType {
        return when {
            abv < 5 -> AbvRangeType.LOW
            abv >= 5 && abv < 8 -> AbvRangeType.NORMAL
            else -> AbvRangeType.HIGH
        }
    }
}