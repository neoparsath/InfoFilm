package id.ramawan.alpinisa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.fitCenter
import id.ramawan.alpinisa.R
import id.ramawan.alpinisa.data.Movie

/* Membuat class yang akan digunakan sebagai itemAdapter
Class ini akan menerima dan mengolah data daftar film(List<Movie>
 */

class ItemAdapter(val context: Context, var items: List<Movie>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    /* Inflate view yang akan digunakan untuk mmenampilkan detail film
    view yang digunakan kali ini yaitu item_custom_row.xml
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_custom_row,
                parent,
                false
            )
        )
    }

    /* Melakukan bind terhadap tiap item didalam list movies ke masing - masing view yang sesuai
    dengan yang dibutuhkan
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Melakukan bind item[position] ke view
        val item = items.get(position)

        holder.bind(item)
        //Menampilkan tiap data dari item pada view sesuai kebutuhan
        holder.mvTitle.text = item.title
        holder.mvDate.text = item.releaseDate
        holder.mvDescription.text = item.overview

        // Mengubah warna background dari view sesuai posisi dari tiap item
        if (position % 2 == 0) {
            holder.cardViewItem.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.lightGray
                )
            )
        } else {
            holder.cardViewItem.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.white
                )
            )
        }
    }

    /*Memberi notifikasi ke observers bahwa ada data yang diubah (data daftar film)
     dan setiap view yang menampilkan dataset tersebut harus merefresh outputnya
     */
    fun updateMovies(movies:List<Movie>){
        this.items = movies
        notifyDataSetChanged()
    }

    //Menghitung jumlah item dalam dataset
    override fun getItemCount(): Int {
        return items.size
    }

    /* Viewholder yang akan menyimpan metada dari tiap view dalam hal ini lokasi dari view tersebut
     */
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val mvTitle = view.findViewById<TextView>(R.id.movie_title)
        val mvDate = view.findViewById<TextView>(R.id.movie_release_date)
        val mvDescription = view.findViewById<TextView>(R.id.movie_description)
        val mvPoster = view.findViewById<ImageView>(R.id.poster)
        val cardViewItem = view.findViewById<CardView>(R.id.card_view_item)

        /*melakukan bind gambar poster yang telah diambil dari url kedalam imageview "poster"
        lalu di resize sesuai besar imageview*/
        fun bind(movie: Movie) {
            Glide.with(view).load("https://image.tmdb.org/t/p/w342${movie.posterPath}").fitCenter().into(mvPoster)
        }
    }
}