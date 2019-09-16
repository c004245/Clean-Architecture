package hyunwook.co.kr.clean_architecture.di

import hyunwook.co.kr.clean_architecture.datasource.BeersApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
object ProjectModule {

    val mainModule = module {
        factory { provideBeersApiService(get()) }
        factory {  }
    }

    private fun provideBeersApiService(retrofit: Retrofit): BeersApiService {
        return retrofit.create(BeersApiService::class.java)
    }
}