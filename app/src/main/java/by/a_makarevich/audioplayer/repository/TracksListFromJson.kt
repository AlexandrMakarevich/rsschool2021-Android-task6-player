package by.a_makarevich.audioplayer.repository

import android.content.Context
import by.a_makarevich.audioplayer.model.Track
import by.a_makarevich.audioplayer.model.TrackList
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TracksListFromJson @Inject constructor(private val appcontext: Context) {

    val listOfTracks: Flow<List<Track>> = flow {

        val jsonFileString = getJsonFromAssets(appcontext, "playlist.json")
        val json = Gson()
        val location = json.fromJson(jsonFileString, TrackList::class.java)
        emit(location.tracks)
    }
}