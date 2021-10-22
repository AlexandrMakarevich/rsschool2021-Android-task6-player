package by.a_makarevich.audioplayer

import android.app.Application


class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .jsonModule(JsonModule(this))
            .customExoPlayerModule(CustomExoPlayerModule(this))
            .repositoryImplModule(RepositoryImplModule(this))
            .build()
    }
}