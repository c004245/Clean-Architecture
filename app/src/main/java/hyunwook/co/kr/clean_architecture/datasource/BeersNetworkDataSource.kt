package hyunwook.co.kr.clean_architecture.datasource

import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class BeersNetworkDataSource(private val beersApiService: BeersApiService) {

    companion object {
        const val MAX_RESULTS_PER_PAGE: Int = 80
    }

    suspend fun getAllBeers(page: String): Result<BeersApi>



}