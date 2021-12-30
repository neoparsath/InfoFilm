package id.ramawan.alpinisa.data

import com.google.gson.annotations.SerializedName
/* membuat class untuk menyimpan data daftar movie yang diterima
data yang disimpan berupa halaman dan daftar film
 */
data class GetMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val pages: Int
)
