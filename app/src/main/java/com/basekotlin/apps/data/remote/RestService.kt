package com.basekotlin.apps.data.remote

import com.basekotlin.apps.BuildConfig
import com.basekotlin.apps.data.model.category.ResponseCategory
import com.basekotlin.apps.data.model.movie.ResponseMovie
import com.basekotlin.apps.data.model.movie.detail.ResponseDetailMovie
import com.basekotlin.apps.util.RxErrorHandlingCallAdapterFactory
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface RestService {
    companion object {
        val ENDPOINT = "https://api.themoviedb.org/3/"
    }

    @GET("categories")
    fun getCategory(): Observable<List<ResponseCategory>>


    //value == popular | top_rated
    @GET("movie/{value}")
    fun getMovie(@Path("value") value: String,
                 @Query("api_key") api_key: String): Observable<ResponseMovie>

    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") movie_id: Int,
                       @Query("api_key") api_key: String): Observable<ResponseDetailMovie>


    object Creator {
        fun newRestService(): RestService {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create()
            val httpClientBuilder = OkHttpClient().newBuilder()

            val logging = HttpLoggingInterceptor()
            logging.level =
                    if (BuildConfig.DEBUG)
                        HttpLoggingInterceptor.Level.BODY
                    else
                        HttpLoggingInterceptor.Level.NONE
            httpClientBuilder.addInterceptor(logging).build()

            httpClientBuilder.addInterceptor(Interceptor())
            httpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
            httpClientBuilder.readTimeout(30L, TimeUnit.SECONDS)
            httpClientBuilder.writeTimeout(30L, TimeUnit.SECONDS)

            val retrofit = Retrofit.Builder().baseUrl(RestService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClientBuilder.build())
                    .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                    .build()

            return retrofit.create(RestService::class.java)
        }
    }
}