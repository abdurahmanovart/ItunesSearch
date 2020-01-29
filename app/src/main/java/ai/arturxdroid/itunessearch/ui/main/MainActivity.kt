package ai.arturxdroid.itunessearch.ui.main

import ai.arturxdroid.itunessearch.R
import ai.arturxdroid.itunessearch.binding.TrackItem
import ai.arturxdroid.itunessearch.databinding.MainActivityBinding
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

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

        val adapter = GroupAdapter<GroupieViewHolder>()
        tracks_recycler_view.adapter = adapter

        viewModel.trackList.observe(this, Observer { list ->
            val trackList = list.map {
                TrackItem(it)
            }
            adapter.clear()
            adapter.addAll(trackList)
        })

        viewModel.internet_error_visible.observe(this, Observer {
            if (it == true) {
                Snackbar.make(
                    main_constraint_layout,
                    R.string.internet_error,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })
        viewModel.error_visible.observe(this, Observer {
            if (it == true) {
                Snackbar.make(
                    main_constraint_layout,
                    R.string.short_query_error,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })
    }
}
