package com.example.essayrent.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.essayrent.Activity.On_Rent_House
import com.example.essayrent.Activity.Pay_history
import com.example.essayrent.Models.Cat_model
import com.example.essayrent.R
import com.squareup.picasso.Picasso

class CategoryAdapter(var context: Context,var list: List<Cat_model>):RecyclerView.Adapter<CategoryAdapter.Myview2>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview2 {

        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.category_design, parent, false)
        return Myview2(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Myview2, position: Int) {
        holder.textView.setText(list.get(position).category_name)
        Picasso.with(context).load(list.get(position).category_img).into(holder.imageView)
        holder.itemView.setOnClickListener {
            if (position == 0) {
                var i = Intent(context, On_Rent_House::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }
            if (position == 1) {
                var i = Intent(context, Pay_history::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }
            }
        }

        class Myview2(itemview: View) : RecyclerView.ViewHolder(itemview) {
            var textView: TextView = itemview.findViewById(R.id.text)
            var imageView: ImageView = itemview.findViewById(R.id.image)
        }
}
