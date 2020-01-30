package ai.arturxdroid.itunessearch.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface ItunesApiService {
    fun getApi(): ItunesApi
}

class ItunesApiServiceImpl : ItunesApiService {

    private val BASE_URL = "https://itunes.apple.com/"

    private lateinit var itunesApi: ItunesApi

    override fun getApi(): ItunesApi {

        val okHttpClient = OkHttpClient()
        itunesApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ItunesApi::class.java)

        return itunesApi
    }
}