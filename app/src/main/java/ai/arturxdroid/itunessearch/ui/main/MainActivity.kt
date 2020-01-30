package ai.arturxdroid.itunessearch.ui.main


import ai.arturxdroid.itunessearch.R
import ai.arturxdroid.itunessearch.binding.TrackItem
import ai.arturxdroid.itunessearch.databinding.MainActivityBinding
import ai.arturxdroid.itunessearch.ui.track.TRACK_EXTRA
import ai.arturxdroid.itunessearch.ui.track.TrackActivity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initUI()
    }

    private fun initUI() {
        search_view.setOnQueryTextListener(viewModel)

        initRecycler()

        initErrorViews()
    }

    private fun initErrorViews() {
        viewModel.internetErrorVisible.observe(this, Observer {
            if (it == true) {
                showSnackbar(R.string.internet_error)
            }
        })
        viewModel.errorVisible.observe(this, Observer {
            if (it == true) {
                showSnackbar(R.string.short_query_error)
            }
        })
    }

    private fun initRecycler() {
        val adapter = GroupAdapter<GroupieViewHolder>()
        tracks_recycler_view.adapter = adapter

        viewModel.trackList.observe(this, Observer { list ->
            val trackList = list.map {
                TrackItem(it)
            }
            adapter.clear()
            adapter.addAll(trackList)
        })

        adapter.setOnItemClickListener { item, view ->
            val intent = Intent(this, TrackActivity::class.java)
            item as TrackItem
            intent.putExtra(TRACK_EXTRA, item.track)
            startActivity(intent)
        }
    }

    private fun showSnackbar(res: Int) {
        Snackbar.make(
            main_constraint_layout,
            res,
            Snackbar.LENGTH_LONG
        ).show()
    }
}
