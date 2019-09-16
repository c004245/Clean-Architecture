package hyunwook.co.kr.clean_architecture.ui.mapper

import hyunwook.co.kr.clean_architecture.R
import hyunwook.co.kr.clean_architecture.commons.BaseMapper
import hyunwook.co.kr.clean_architecture.ui.model.BeerAdapterModel
import hyunwook.co.kr.clean_architecture.viewmodel.model.AbvColorType
import hyunwook.co.kr.clean_architecture.viewmodel.model.BeerUI

object BeerUIMapper : BaseMapper<List<BeerUI>, List<BeerAdapterModel>> {
    override fun map(type: List<BeerUI>?): List<BeerAdapterModel> {
        return type?.map {
            BeerAdapterModel(
                id = it.id,
                name = it.name,
                tagline = it.tagline,
                image = it.image,
                abv = it.abv,
                abvColor = getColor(it.abvColorType)
            )
        } ?: listOf()
    }

    private fun getColor(abvType: AbvColorType): Int {
        return when (abvType) {
            AbvColorType.GREEN -> R.color.green
            AbvColorType.ORANGE -> R.color.orange
            else -> R.color.red
        }

    }


}