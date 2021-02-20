package jzam.backlog.backlogtracker.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class GBResponse(
    @Json(name = "status_code") val statusCode: Int,
    val error: String,
    @Json(name = "number_of_total_results") val numResults: Int,
    @Json(name = "number_of_page_results") val numPageResults: Int,
    val limit: Int,
    val offset: Int,
    val results: List<GameItem>
)

data class GBResponseSingle(
    @Json(name = "status_code") val statusCode: Int,
    val error: String,
    @Json(name = "number_of_total_results") val numResults: Int,
    @Json(name = "number_of_page_results") val numPageResults: Int,
    val limit: Int,
    val offset: Int,
    val results: GameItem
)

@Parcelize
data class GameItem(
    val guid: String,
    val deck: String?,
    val name: String?,
    val image: GameImages?,
    val genres: List<Genre>?,
    val platforms: List<Platform>?
) : Parcelable

@Parcelize
data class GameImages(
    @Json(name = "icon_url") val iconUrl: String,
    @Json(name = "medium_url") val mediumUrl: String,
    @Json(name = "screen_url") val screenUrl: String,
    @Json(name = "screen_large_url") val screenLargeUrl: String,
    @Json(name = "small_url") val smallUrl: String,
    @Json(name = "thumb_url") val thumbUrl: String,
    @Json(name = "tiny_url") val tinyUrl: String,
    @Json(name = "original_url") val originalUrl: String,
    @Json(name = "image_tags") val imageTags: String
) : Parcelable

@Parcelize
data class Genre(
    @Json(name = "api_detail_url") val apiDetailUrl: String,
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "site_detail_url") val site_detail_url: String
    ) : Parcelable

@Parcelize
data class Platform(
    @Json(name = "abbreviation") val abbreviation: String,
    @Json(name = "api_detail_url") val apiDetailUrl: String,
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "site_detail_url") val site_detail_url: String
) : Parcelable