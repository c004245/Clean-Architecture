package hyunwook.co.kr.clean_architecture.datasource.retrofit

import hyunwook.co.kr.clean_architecture.datasource.model.response.BeerResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface BeersApiService {

    @GET("beers?")
    fun getAllBeersAsync(@Query("page") page: String,
                         @Query("per_page") perPage: String): Deferred<List<BeerResponse>>
}