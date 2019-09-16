package hyunwook.co.kr.clean_architecture.di

import hyunwook.co.kr.clean_architecture.datasource.retrofit.BeersApiService
import hyunwook.co.kr.clean_architecture.datasource.BeersNetworkDataSource
import hyunwook.co.kr.clean_architecture.domain.BeersRepository
import hyunwook.co.kr.clean_architecture.domain.usecase.GetBeersUseCase
import hyunwook.co.kr.clean_architecture.repository.HomeRepositoryImpl
import hyunwook.co.kr.clean_architecture.viewmodel.BeersViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
object ProjectModule {

    val mainModule = module {
        factory { provideBeersApiService(get()) }
        factory { BeersNetworkDataSource(beersApiService = get()) }
        factory { HomeRepositoryImpl(beersNetworkDataSource = get()) as BeersRepository }
        factory { GetBeersUseCase(beersRepository = get()) }
        viewModel { BeersViewModel(getBeersUseCase = get()) }
    }

    private fun provideBeersApiService(retrofit: Retrofit): BeersApiService {
        return retrofit.create(BeersApiService::class.java)
    }
}