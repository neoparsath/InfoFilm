package id.ramawan.alpinisa.data

import com.google.gson.annotations.SerializedName
/* Membuat class untuk menyimpan data movie dengan constructor id, title, overview, poster_path dan release_date
 */
data class Movie(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String
)
