package by.a_makarevich.audioplayer.data

import by.a_makarevich.audioplayer.model.Track
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getListOfTracksFromJson(): Flow<List<Track>>

    fun getCustomExoPlayer(): SimpleExoPlayer

}