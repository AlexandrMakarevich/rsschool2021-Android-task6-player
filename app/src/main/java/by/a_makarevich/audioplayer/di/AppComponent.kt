package by.a_makarevich.audioplayer

import android.app.Application
import android.content.Context
import by.a_makarevich.audioplayer.data.Repository
import by.a_makarevich.audioplayer.repository.RepositoryImpl
import by.a_makarevich.audioplayer.repository.TracksListFromJson
import by.a_makarevich.audioplayer.ui.MainViewModel
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Component(modules = [JsonModule::class, CustomExoPlayerModule::class, RepositoryModule::class, RepositoryImplModule::class])
interface AppComponent {

    fun injectMainViewModel(mainViewModel: MainViewModel)

}

@Module
class JsonModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideTracksListFromJson(): TracksListFromJson = TracksListFromJson(application)

}

@Module
class CustomExoPlayerModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application

    @Singleton
    @Provides
    fun provideCustomExoPlayerNew(): SimpleExoPlayer = SimpleExoPlayer.Builder(application).build()
}

@Module
class RepositoryImplModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application

    @Provides
    fun bindsRepository(): RepositoryImpl = RepositoryImpl(
        TracksListFromJson(application),
        SimpleExoPlayer.Builder(application).build()
    )
}

@Module
interface RepositoryModule {
    @Binds
    fun bindRepository(rep: RepositoryImpl): Repository
}










