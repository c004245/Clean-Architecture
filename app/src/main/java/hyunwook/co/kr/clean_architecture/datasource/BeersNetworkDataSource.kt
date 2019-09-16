package hyunwook.co.kr.clean_architecture.datasource

import hyunwook.co.kr.clean_architecture.commons.datatype.Result
import hyunwook.co.kr.clean_architecture.commons.exception.CancelledFetchDataException
import hyunwook.co.kr.clean_architecture.datasource.mapper.NetworkMapper
import hyunwook.co.kr.clean_architecture.datasource.model.BeerResponse
import hyunwook.co.kr.clean_architecture.datasource.model.api.BeersApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
class BeersNetworkDataSource(private val beersApiService: BeersApiService) {

    companion object {
        const val MAX_RESULTS_PER_PAGE: Int = 80
    }

    suspend fun getAllBeers(page: String): Result<BeersApi> {
        var result: Result<BeersApi> = Result.success(BeersApi(listOf()))

        withContext(Dispatchers.IO) {
            try {
                val request = beersApiService.getAllBeersAsync(page, MAX_RESULTS_PER_PAGE.toString())

                val response: List<BeerResponse>? = request.await()

                request.let {
                    if (it.isCompleted) result = Result.success(NetworkMapper.ResponseToApiMapper.map(response))
                    else if (it.isCancelled) result = Result.error(CancelledFetchDataException())
                }
            } catch (ex: Exception) {
                result = Result.error(NetworkMapper.ExceptionToErrorMapper.map(ex))
            }
        }
        return result
    }
}