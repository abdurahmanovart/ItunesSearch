package ai.arturxdroid.itunessearch.network

import ai.arturxdroid.itunessearch.data.TrackResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApi {

    @GET("search")
    fun getTracks(@Query("term") keyword: String?): Observable<TrackResponse?>?

}