package com.example.essayrent.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.essayrent.Adapter.Grid_aboutus
import com.example.essayrent.R

class About_us : AppCompatActivity() {

    lateinit var scrollableLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)


        scrollableLayout = findViewById(R.id.scrollableLayout)

        val data: List<String> = arrayListOf("1",)
        val chunkedData = data.chunked(4) // Split the list into chunks of 10

        chunkedData.forEach { chunkList ->
            val recyclerView = RecyclerView(applicationContext)
            recyclerView.layoutManager = GridLayoutManager(this, 1) // 5 columns in grid
            recyclerView.adapter = Grid_aboutus(chunkList)

            scrollableLayout.addView(recyclerView)
        }
    }
}