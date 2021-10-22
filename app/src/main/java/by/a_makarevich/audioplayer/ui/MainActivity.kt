package by.a_makarevich.audioplayer.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import by.a_makarevich.audioplayer.App
import by.a_makarevich.audioplayer.databinding.ActivityMainBinding
import by.a_makarevich.audioplayer.model.Track
import coil.api.load
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer

class MainActivity : AppCompatActivity(), LifecycleObserver {


    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMembers(savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun initMembers(savedInstanceState: Bundle?) {

        (application as App).appComponent.injectMainViewModel(viewModel)
        viewModel.getTrack()

        if (savedInstanceState == null) {
            viewModel.initPlayer()
        }
    }

    private fun setListeners() {
        binding.buttonPrev.setOnClickListener {
            viewModel.prevTrack()
        }
        binding.buttonPlay.setOnClickListener {
            viewModel.play()
        }
        binding.buttonPause.setOnClickListener {
            viewModel.pause()
        }
        binding.buttonStop.setOnClickListener {
            viewModel.stop()
        }
        binding.buttonNext.setOnClickListener {
            viewModel.nextTrack()

        }
    }

    private fun setObservers() {
        viewModel.track.observe(this) {
            setViews(it)
            viewModel.play()
            Log.d(LOG, it.trackUri)
        }
    }

    private fun setViews(track: Track?) {
        binding.textViewTrackName.text = track?.title
        binding.textViewTrackAuthor.text = track?.artist
        binding.imageViewTrackImage.load(track?.bitmapUri)
    }

    override fun onDestroy() {
        viewModel.cancelPlayer()
        super.onDestroy()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun startService() {

    }


    companion object {
        const val LOG = "MyLog"
    }
}





