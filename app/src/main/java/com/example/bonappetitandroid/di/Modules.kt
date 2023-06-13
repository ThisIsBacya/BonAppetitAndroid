package com.example.bonappetitandroid.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.context.startKoin
//import org.koin.ksp.generated.defaultModule

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KoinApp)
//            modules(defaultModule)
        }
    }
}

//
//val clientModule = module {
//    single { HttpClient(Android) }
//}



@Module
@ComponentScan
class AppModule

