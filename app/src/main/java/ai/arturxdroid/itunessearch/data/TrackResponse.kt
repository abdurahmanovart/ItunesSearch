package ai.arturxdroid.itunessearch.data

data class TrackResponse(val resultCount: Int, val results: List<Track>)

data class Track(
    val artistName: String,
    val trackName: String,
    val artistViewUrl: String,
    val previewUrl: String,
    val artworkUrl100: String
)