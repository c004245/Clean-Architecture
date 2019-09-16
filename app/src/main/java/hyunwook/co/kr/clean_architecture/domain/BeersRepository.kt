package hyunwook.co.kr.clean_architecture.domain

import hyunwook.co.kr.clean_architecture.commons.datatype.Result
import hyunwook.co.kr.clean_architecture.domain.model.BeersEntity

interface BeersRepository {

    suspend fun getAllBeers(): Result<BeersEntity>?
}