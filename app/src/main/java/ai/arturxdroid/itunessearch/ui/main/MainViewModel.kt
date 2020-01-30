package ai.arturxdroid.itunessearch.ui.main

import ai.arturxdroid.itunessearch.data.Track
import ai.arturxdroid.itunessearch.network.ItunesApiService
import android.annotation.SuppressLint
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(val api: ItunesApiService) : ViewModel(), SearchView.OnQueryTextListener {

    private val _trackList = MutableLiveData<List<Track>>()
    val trackList: LiveData<List<Track>> = _trackList

    private val _errorVisible = MutableLiveData<Boolean>(false)
    val errorVisible: LiveData<Boolean> = _errorVisible

    private val _internetErrorVisible = MutableLiveData<Boolean>(false)
    val internetErrorVisible: LiveData<Boolean> = _internetErrorVisible

    private val apiService = api.getApi()

    @SuppressLint("CheckResult")
    fun fetchTracks(query: String) {
        apiService.getTracks(query).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _internetErrorVisible.value = false
                _trackList.value = it.results
            },
                {
                    Log.e("ERART", it.toString())
                    _internetErrorVisible.value = true
                }
            )
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return if (query?.length!! >= 5) {
            fetchTracks(query)
            true
        } else {
            _errorVisible.value = true
            false
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}