package ai.arturxdroid.itunessearch

import ai.arturxdroid.itunessearch.di.KoinModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ItunesSearchApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ItunesSearchApp)
            modules(KoinModule.appModule)
        }
    }

}