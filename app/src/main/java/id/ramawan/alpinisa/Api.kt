package id.ramawan.alpinisa

import id.ramawan.alpinisa.data.GetMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/*Mendeklarasikan api yang akan digunakan untuk mengambil data movies, menggunakan api themoviedb
Dalam api ini akan diambil daftar film dari halaman popular
*/
interface Api {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "b7cd3340a794e5a2f35e3abb820b497f",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>
}