package ai.arturxdroid.itunessearch.ui.track

import ai.arturxdroid.itunessearch.R
import ai.arturxdroid.itunessearch.data.Track
import ai.arturxdroid.itunessearch.databinding.PlayerBinding
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_track.*

const val TRACK_EXTRA = "track_extra"

class TrackActivity : AppCompatActivity() {

    private val track by lazy {
        intent.getParcelableExtra<Track>(TRACK_EXTRA)
    }

    private val trackViewModel: TrackViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<PlayerBinding>(this, R.layout.activity_track)


        binding.lifecycleOwner = this
        binding.track = track
        binding.viewModel = trackViewModel

        play_pause_button.setOnClickListener(trackViewModel)
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        trackViewModel.initPlayer(track.previewUrl)
    }

    override fun onDestroy() {
        super.onDestroy()
        trackViewModel.shutdownPlayer()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
