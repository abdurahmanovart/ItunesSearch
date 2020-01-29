package ai.arturxdroid.itunessearch.ui.main

import ai.arturxdroid.itunessearch.data.Track
import ai.arturxdroid.itunessearch.network.RetrofitService
import android.annotation.SuppressLint
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel(), SearchView.OnQueryTextListener {

    private val _trackList = MutableLiveData<List<Track>>()
    val trackList: LiveData<List<Track>> = _trackList

    private val _error_visible = MutableLiveData<Boolean>(false)
    val error_visible: LiveData<Boolean> = _error_visible

    private val _internet_error_visible = MutableLiveData<Boolean>(false)
    val internet_error_visible: LiveData<Boolean> = _internet_error_visible

    private val itunesApi = RetrofitService.getRetrofit()

    @SuppressLint("CheckResult")
    fun fetchTracks(query: String) {
        itunesApi.getTracks(query).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _internet_error_visible.value = false
                _trackList.value = it.results
            },
                {
                    Log.e("ERART", it.toString())
                    _internet_error_visible.value = true
                }
            )
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return if (query?.length!! >= 5) {
            fetchTracks(query)
            true
        } else {
            _error_visible.value = true
            false
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}