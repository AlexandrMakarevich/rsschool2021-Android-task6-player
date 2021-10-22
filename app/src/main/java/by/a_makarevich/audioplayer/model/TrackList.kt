package by.a_makarevich.audioplayer.model


data class TrackList (val tracks: List<Track>)

data class Track(
    val title: String,
    val artist: String,
    val bitmapUri: String,
    val trackUri: String,
    val duration: Int
)