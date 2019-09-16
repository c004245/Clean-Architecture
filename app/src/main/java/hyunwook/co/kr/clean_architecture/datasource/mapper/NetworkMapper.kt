package hyunwook.co.kr.clean_architecture.datasource.mapper

import hyunwook.co.kr.clean_architecture.commons.BaseMapper
import hyunwook.co.kr.clean_architecture.commons.exception.BadRequestException
import hyunwook.co.kr.clean_architecture.commons.exception.GenericNetworkException
import hyunwook.co.kr.clean_architecture.commons.exception.NetworkConnectionException
import hyunwook.co.kr.clean_architecture.datasource.model.BeerResponse
import hyunwook.co.kr.clean_architecture.datasource.model.api.BeerApi
import hyunwook.co.kr.clean_architecture.datasource.model.api.BeersApi
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

class NetworkMapper {

    object ResponseToApiMapper: BaseMapper<List<BeerResponse>, BeersApi> {
        override fun map(type: List<BeerResponse>?): BeersApi {
            return BeersApi(type?.map {
                BeerApi(
                    id = it.id ?: -1,
                    name = it.name ?: "",
                    tagline = it.tagline ?: "",
                    image = it.image ?: "",
                    abv = it.abv ?: -1.0
                )
            } ?: listOf())
        }
    }

    object ExceptionToErrorMapper: BaseMapper<Exception, Exception> {
        override fun map(type: Exception?): Exception {
            return when (type) {
                is IOException -> NetworkConnectionException()
                is UnknownHostException -> NetworkConnectionException()
                is HttpException -> apiErrorFromCodeException(type.code())
                else -> GenericNetworkException()
            }
        }
    }

    companion object {
        private fun apiErrorFromCodeException(code: Int): Exception {
            return if (code == 400) {
                BadRequestException()
            } else {
                GenericNetworkException()
            }
        }
    }
}