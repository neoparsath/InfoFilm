package id.isaalpin.infofilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.isaalpin.infofilm.adapter.ItemAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler_view = findViewById<RecyclerView>(R.id.recycler_view_items)
        // Set the LayoutManager that this RecyclerView will use.
        recycler_view.layoutManager = LinearLayoutManager(this)

        // Adapter class is initialized and list is passed in the param.
        val itemAdapter = ItemAdapter(this, getItemsList())

        // adapter instance is set to the recyclerview to inflate the items.
        recycler_view.adapter = itemAdapter
    }

    private fun getItemsList(): ArrayList<String> {
        val list = ArrayList<String>()

        for(i in 1..15){
            list.add("Item $i")
        }

        return list
    }
}