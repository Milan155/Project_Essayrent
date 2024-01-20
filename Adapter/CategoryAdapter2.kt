package com.example.essayrent.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.essayrent.Models.Cat_model
import com.example.essayrent.R
import com.squareup.picasso.Picasso

class CategoryAdapter2(var context: Context, var list: List<Cat_model>): RecyclerView.Adapter<CategoryAdapter2.Myview8>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview8 {
        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.property_category_design, parent, false)
        return Myview8(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Myview8, position: Int) {
        holder.textView.setText(list.get(position).category_name)
        Picasso.with(context).load(list.get(position).category_img).into(holder.imageView)

        holder.itemView.setOnClickListener {
            if (position==0)
            {
                var url = "https://www.incometax.gov.in/"
                var i = Intent(Intent.ACTION_VIEW)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                i.setData(Uri.parse(url))
                context.startActivity(i)
            }
        }

    }
    class Myview8(itemview: View) : RecyclerView.ViewHolder(itemview){
        var textView: TextView = itemview.findViewById(R.id.text1)
        var imageView: ImageView = itemview.findViewById(R.id.image1)
    }
}