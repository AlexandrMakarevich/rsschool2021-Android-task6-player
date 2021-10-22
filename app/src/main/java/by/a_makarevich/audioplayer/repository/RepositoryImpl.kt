package by.a_makarevich.audioplayer.repository

import by.a_makarevich.audioplayer.data.Repository
import by.a_makarevich.audioplayer.model.Track
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val tracksListFromJson: TracksListFromJson,
    private val customExoPlayer: SimpleExoPlayer
) : Repository {

    override suspend fun getListOfTracksFromJson(): Flow<List<Track>> =
        tracksListFromJson.listOfTracks

    override fun getCustomExoPlayer(): SimpleExoPlayer = customExoPlayer

}