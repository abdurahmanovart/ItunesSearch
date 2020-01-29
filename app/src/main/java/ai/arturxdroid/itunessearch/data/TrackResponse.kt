package ai.arturxdroid.itunessearch.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class TrackResponse(val resultCount: Int, val results: List<Track>)

@Parcelize
data class Track(
    val artistName: String,
    val trackName: String,
    val artistViewUrl: String,
    val previewUrl: String,
    val artworkUrl100: String
) : Parcelable