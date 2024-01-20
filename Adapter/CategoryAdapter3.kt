package com.example.essayrent.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.essayrent.Activity.maintenance_apply
import com.example.essayrent.Activity.maintenance_history
import com.example.essayrent.Models.Cat_model
import com.example.essayrent.R
import com.squareup.picasso.Picasso

class CategoryAdapter3(var context: Context, var list: List<Cat_model>):RecyclerView.Adapter<CategoryAdapter3.Myview5>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview5{

        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.maintenance_design, parent, false)
        return Myview5(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Myview5, position: Int) {
        holder.textView.setText(list.get(position).category_name)
        Picasso.with(context).load(list.get(position).category_img).into(holder.imageView)

        holder.itemView.setOnClickListener {
            if (position == 0) {
                var i = Intent(context, maintenance_apply::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }
            if (position == 1) {
                var i = Intent(context, maintenance_history::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }
            }
        }

        class Myview5(itemview: View) : RecyclerView.ViewHolder(itemview) {
            var textView: TextView = itemview.findViewById(R.id.textview)
            var imageView: ImageView = itemview.findViewById(R.id.imageview)
        }
}
