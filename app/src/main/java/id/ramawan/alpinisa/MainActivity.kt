package id.ramawan.alpinisa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ramawan.alpinisa.adapter.ItemAdapter
import id.ramawan.alpinisa.data.Movie

class MainActivity : AppCompatActivity() {
    private lateinit var recycler_view: RecyclerView
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //menginisialisasi recycler view yang akan digunakan untuk menampung daftar film
        recycler_view = findViewById<RecyclerView>(R.id.recycler_view_items)
        //Mengeset layoutmanager yang akan digunakan oleh recycler view diatas
        recycler_view.layoutManager = LinearLayoutManager(this)
        //Menginisialisasi class Adapter and Memasukkan daftar sebagai parameter
        itemAdapter = ItemAdapter(this, listOf())
        //Instance dari adapter diset untuk menginflate item yang diberikan
        recycler_view.adapter = itemAdapter
        /*Menjalankan fungsi getPopularMovies dari object MoviesRepository dengan parameter
        fungsi onPopularMOviesFetched dan onError yang telah dibuat dalam class ini*/
        MoviesRepository.getPopularMovies(
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError
        )
    }

    //fungsi yang akan Menjalankan fungsi updateMovies
    private fun onPopularMoviesFetched(movies: List<Movie>) {
        itemAdapter.updateMovies(movies)
    }

    //fungsi yang akan menampilkan pesan eror
    private fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }
}