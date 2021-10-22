package by.a_makarevich.audioplayer.ui

import android.util.Log
import androidx.lifecycle.*
import by.a_makarevich.audioplayer.data.Repository
import by.a_makarevich.audioplayer.model.Track
import by.a_makarevich.audioplayer.ui.MainActivity.Companion.LOG
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel : ViewModel() {

    @Inject
    lateinit var repository: Repository

    lateinit var player: SimpleExoPlayer

    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L


    private val _track = MutableLiveData<Track>()
    val track: LiveData<Track> get() = _track

    private var tracks: List<Track>? = listOf<Track>()
    private var currentTrack = 0

    fun initPlayer() {
        Log.d(LOG, playWhenReady.toString())
        player = repository.getCustomExoPlayer()
    }


    fun play() {
        Log.d(LOG, playWhenReady.toString())
        initilatePlayer()
    }

    fun pause() {
        Log.d(LOG, playWhenReady.toString())
        releasePlayer()
    }

    fun stop() {
        Log.d(LOG, playWhenReady.toString())
        releasePlayer()
    }

    fun initilatePlayer() {
        player.also {
            val mediaItem = MediaItem.fromUri(_track.value!!.trackUri)
            it.setMediaItem(mediaItem)
            it.playWhenReady = playWhenReady
            it.seekTo(currentWindow, playbackPosition)
            it.prepare()

        }
    }

    fun releasePlayer() {
        player.also {
            playbackPosition = it.currentPosition
            currentWindow = it.currentWindowIndex
            playWhenReady = it.playWhenReady
            it.stop()
        }
    }


    fun getTrack() {
        viewModelScope.launch {
            repository.getListOfTracksFromJson().collect { list ->
                list.forEach { Log.d(MainActivity.LOG, it.artist) }
                _track.postValue(list[currentTrack])
                tracks = list
            }
        }
    }

    fun nextTrack() {
        if (currentTrack < tracks?.size!! - 1) {
            currentTrack++
            _track.postValue(tracks!![currentTrack])
        }
    }

    fun prevTrack() {
        if (currentTrack > 0) {
            currentTrack--
            _track.postValue(tracks!![currentTrack])
        }
    }

    fun cancelPlayer() {
        player.release()

    }

}


