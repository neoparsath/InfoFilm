package id.ramawan.alpinisa

import android.util.Log
import id.ramawan.alpinisa.data.GetMoviesResponse
import id.ramawan.alpinisa.data.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/* Object yang akan digunakan untuk mengambil, menyimpan dan mengakses daftar film
 */
object MoviesRepository {
    private val api: Api

    //Membangun web service API dengan Url dasar https://api.themoviedb.org/3/ dengan mengambil data dalam format json menggunakan GSON converter
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }
    /* Membuat fungsi untuk mengakses daftar film dari web url yang sudah disediakan sebelumnya
    fungsi ini dapat menerima fungsi lain sebagai konstruktor.
    Akan dijalankan fungsi yang diberikan pada variable onSuccess jika respon dari web url
    diterima dan pengambilan data berhasil, Jika gagal atau tidak ada respon maka akan dijalankan
    fungsi yang diberikan pada variable onError.
    fungsi ini hanya akan mengambil halaman pertama dari daftar film popular
     */
    fun getPopularMovies(
        page: Int = 1,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit
    ) {
        api.getPopularMovies(page = page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}