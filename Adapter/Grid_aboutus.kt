package com.example.essayrent.Adapter
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.essayrent.R

class Grid_aboutus(var datalist: List<String>):RecyclerView.Adapter<Grid_aboutus.Myview7>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview7 {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_girds, parent, false)
        return Myview7(view)
    }

    override fun onBindViewHolder(holder: Myview7, position: Int) {
        holder.Button.setOnClickListener {
            var url = "https://api.whatsapp.com/send?phone=9099972756"
            var i = Intent(Intent.ACTION_VIEW)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.setData(Uri.parse(url))
        }

    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    class Myview7(Itemview:View):RecyclerView.ViewHolder(Itemview)
    {
        var Button: ImageButton = Itemview.findViewById(R.id.btn1)
        var button1: ImageButton = Itemview.findViewById(R.id.btn2)
        var Button2: ImageButton = Itemview.findViewById(R.id.btn3)
        var Button3: ImageButton = Itemview.findViewById(R.id.btn4)

    }

}