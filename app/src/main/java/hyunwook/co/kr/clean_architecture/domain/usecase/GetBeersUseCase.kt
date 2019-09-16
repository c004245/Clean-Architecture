package hyunwook.co.kr.clean_architecture.domain.usecase

import hyunwook.co.kr.clean_architecture.commons.datatype.Result
import hyunwook.co.kr.clean_architecture.commons.datatype.ResultType
import hyunwook.co.kr.clean_architecture.domain.BeersRepository
import hyunwook.co.kr.clean_architecture.domain.model.BeersEntity

class GetBeersUseCase(private val beersRepository: BeersRepository) {

    suspend fun execute(): Result<BeersEntity> {
        var beers: Result<BeersEntity> = Result.success(BeersEntity(listOf()))

        beersRepository.getAllBeers()?.let { beersEntity ->
            val resultType = beersEntity.resultType

            if (resultType == ResultType.SUCCESS) {
                beersEntity.data?.let {
                    val sortedBeers = getSortedAscendingBeers(beersEntity.data)
                    beers = Result.success(sortedBeers)
                }
            } else {
                beers = Result.error(beersEntity.error)
            }
        }
        return beers
    }

    private fun getSortedAscendingBeers(beersEntity: BeersEntity): BeersEntity {
        return BeersEntity(
            beersEntity.beers.sortedBy { it.abv }
        )
    }
}