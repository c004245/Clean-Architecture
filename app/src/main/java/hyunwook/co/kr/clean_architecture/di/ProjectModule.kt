package hyunwook.co.kr.clean_architecture.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
object ProjectModule {

    val mainModule = module {
        factory {  }
    }

    private fun provideBeersApiService(retrofit: Retrofi)
}