package hyunwook.co.kr.clean_architecture.repository.mapper

import hyunwook.co.kr.clean_architecture.commons.BaseMapper
import hyunwook.co.kr.clean_architecture.datasource.model.api.BeersApi
import hyunwook.co.kr.clean_architecture.domain.model.BeerEntity
import hyunwook.co.kr.clean_architecture.domain.model.BeersEntity

class RepositoryMapper {

    object ApiToEntityMappper: BaseMapper<BeersApi, BeersEntity> {
        override fun map(type: BeersApi?): BeersEntity {
            return BeersEntity(
                type?.beers?.map {
                    BeerEntity(
                        id = it.id ?: -1,
                        name = it.name ?: "",
                        tagline = it.tagline ?: "",
                        image = it.image ?: "",
                        abv = it.abv ?: -1.0
                    )
                } ?: listOf()
            )
        }
    }
}