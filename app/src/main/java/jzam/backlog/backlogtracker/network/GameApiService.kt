package jzam.backlog.backlogtracker.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val GAME_BASE_URL = "https://www.giantbomb.com/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(GAME_BASE_URL)
    .build()

interface GameApiService {
    @GET("game/{guid}")
    suspend fun getGame(@Path ("guid") guid: String, @Query("api_key") key: String, @Query("format") format: String, @Query("field_list") fields: String): GBResponseSingle

    @GET("search")
    suspend fun searchGame(@Query("query") searchText: String, @Query("api_key") key: String,
                           @Query("format") format: String, @Query("field_list") fields: String,
                           @Query("resources") resources:String, @Query("limit") limit: Int): GBResponse
}


object GameApi {
    val retrofitService: GameApiService by lazy {
        retrofit.create(GameApiService::class.java) }
}
