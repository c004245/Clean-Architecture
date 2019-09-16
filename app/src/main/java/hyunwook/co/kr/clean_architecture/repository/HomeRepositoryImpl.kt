package hyunwook.co.kr.clean_architecture.repository

import hyunwook.co.kr.clean_architecture.commons.Result
import hyunwook.co.kr.clean_architecture.commons.ResultType
import hyunwook.co.kr.clean_architecture.commons.exception.BadRequestException
import hyunwook.co.kr.clean_architecture.commons.exception.NetworkConnectionException
import hyunwook.co.kr.clean_architecture.datasource.BeersNetworkDataSource
import hyunwook.co.kr.clean_architecture.datasource.model.api.BeersApi
import hyunwook.co.kr.clean_architecture.domain.BeersRepository
import hyunwook.co.kr.clean_architecture.domain.model.BeersEntity
import hyunwook.co.kr.clean_architecture.repository.mapper.RepositoryMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeRepositoryImpl constructor(
    private val beersNetworkDataSource: BeersNetworkDataSource): BeersRepository {

    private val beers = mutableListOf<BeersEntity>()

    @ExperimentalCoroutinesApi
    override suspend fun getAllBeers(): Result<BeersEntity>? {
        var page = -1

        var result: Result<BeersEntity>?

        do {
            page = getPageToCheckBeers(page)

            beersNetworkDataSource.getAllBeers(page.toString()).let { resultListBeerResponse ->
                addAllBeersUntilLastPage(resultListBeerResponse)
                result = initResult(resultListBeerResponse)
            }
        } while (result?.resultType != Result.error<Error>().resultType && beers.size == 0)

        return result
    }

    private fun getPageToCheckBeers(currentPage: Int): Int {
        var page: Int = currentPage

        if (hasBeers()) {
            if (isNecessaryFetchMoreBeers(currentPage)) page++ else page = -1
        } else {
            page = 1
        }
        return page
    }

    private fun hasBeers() = beers.size > 0

    private fun isNecessaryFetchMoreBeers(page: Int): Boolean {
        return (beers.size / page) == BeersNetworkDataSource.MAX_RESULTS_PER_PAGE
    }

    private fun addAllBeersUntilLastPage(beersApiResult: Result<BeersApi>) {
        RepositoryMapper.ApiToEntityMappper.map(beersApiResult.data).let { beersEntity ->
            beersEntity.beers.forEach { beerEntity ->
                beers.add(beersEntity)
            }
        }
    }

    private fun initResult(beersApiResult: Result<BeersApi>): Result<BeersEntity> {
        return if (beersApiResult.resultType == ResultType.SUCCESS) {
            Result.success(BeersEntity(beers))
        } else {
            if (hasNotMoreBeers(beersApiResult.error)) {
                Result.success(BeersEntity(beers))
            } else {
                Result.error(NetworkConnectionException())
            }
        }
    }

    private fun hasNotMoreBeers(error: Exception?): Boolean {
        return beers.isNotEmpty() && error == BadRequestException()
    }
}
