package ai.arturxdroid.itunessearch.ui.track

import android.media.MediaPlayer
import android.os.Handler
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrackViewModel : ViewModel(), View.OnClickListener {

    private val _isPlaying = MutableLiveData<Boolean>(false)
    val isPlaying: LiveData<Boolean> = _isPlaying

    private val _progress = MutableLiveData<Int>(0)
    val progress: LiveData<Int> = _progress

    val seekBarMax = MutableLiveData<Int>(0)

    private val player = MediaPlayer()
    private var handler = Handler()
    private var firstLaunch = true
    var startTime = 0
    private var updateSongTime = UpdateSongTime()
    private var audioUrl = ""
    private var finalTime = 0

    fun initPlayer(audioUrl: String) {
        this.audioUrl = audioUrl
        updateSongTime = UpdateSongTime()
        handler = Handler()
        firstLaunch = true
    }

    fun shutdownPlayer() {
        handler.removeCallbacks(updateSongTime)
        player?.release()
    }

    override fun onClick(v: View?) {
        if (_isPlaying.value!!) {
            _isPlaying.value = false
            player.pause()
            handler.removeCallbacks(updateSongTime)
        } else {
            _isPlaying.value = true
            if (firstLaunch) {
                player.setDataSource(audioUrl)
                player.prepare()
                finalTime = player.duration
                seekBarMax.value = finalTime
                firstLaunch = false
            }
            player.start()
            if (startTime == finalTime)
                startTime = 0
            else
                startTime = player.currentPosition

            _progress.value = startTime
            handler.postDelayed(updateSongTime, 100)
        }
    }

    private inner class UpdateSongTime : Runnable {

        override fun run() {
            startTime = player.currentPosition
            _progress.value = startTime
            handler.postDelayed(this, 100)
        }
    }

}