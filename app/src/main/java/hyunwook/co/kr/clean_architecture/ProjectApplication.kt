package hyunwook.co.kr.clean_architecture

import android.app.Application
import hyunwook.co.kr.clean_architecture.di.NetworkModule
import hyunwook.co.kr.clean_architecture.di.ProjectModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@ExperimentalCoroutinesApi
class ProjectApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@ProjectApplication)
            modules(
                listOf(
                    ProjectModule.mainModule,
                    NetworkModule.retrofitModule
                )
            )
        }
    }
}