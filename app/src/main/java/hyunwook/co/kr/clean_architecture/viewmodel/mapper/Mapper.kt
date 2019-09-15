package hyunwook.co.kr.clean_architecture.viewmodel.mapper

import hyunwook.co.kr.clean_architecture.commons.BaseMapper
import hyunwook.co.kr.clean_architecture.domain.model.AbvRangeType
import hyunwook.co.kr.clean_architecture.domain.model.BeerEntity
import hyunwook.co.kr.clean_architecture.viewmodel.model.AbvColorType
import hyunwook.co.kr.clean_architecture.viewmodel.model.BeerUI

class ViewModelMapper {

    object EntityToUI : BaseMapper<List<BeerEntity>, List<BeerUI>> {
        override fun map(type: List<BeerEntity>?): List<BeerUI> {
            return type?.map {
                BeerUI(
                    id = it.id,
                    name = it.name,
                    tagline = it.tagline,
                    image = it.image,
                    abv = it.abv,
                    abvColorType = mapAbvType(it.getAbvRange(it.abv))
                )
            } ?: listOf()
        }
    }

    companion object {
        private fun mapAbvType(abvRangeType: AbvRangeType): AbvColorType {
            return when (abvRangeType) {
                AbvRangeType.LOW -> AbvColorType.GREEN
                AbvRangeType.NORMAL -> AbvColorType.ORANGE
                AbvRangeType.HIGH -> AbvColorType.RED
            }
        }
    }
}