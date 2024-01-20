package com.example.essayrent.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.essayrent.Activity.rent_details
import com.example.essayrent.ApiInterface
import com.example.essayrent.Models.On_house_model
import com.example.essayrent.R
import com.squareup.picasso.Picasso

class house_rentcat(var context: Context, var list: List<On_house_model>):RecyclerView.Adapter<house_rentcat.Myview3>()
{
    lateinit var apiinterface:ApiInterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview3 {
        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.category_design2, parent, false)
        return Myview3(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Myview3, position: Int) {
        holder.textView.setText(list.get(position).Price)
        holder.textView1.setText(list.get(position).Location)
        Picasso.with(context).load(list.get(position).Image).into(holder.image)

        holder.detaislbutton.setOnClickListener {
            var i=Intent(context,rent_details::class.java)
            i.putExtra("mypos",list.get(position).id)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }
    }
    class Myview3(itemview:View):RecyclerView.ViewHolder(itemview){
        var textView:TextView=itemview.findViewById(R.id.prictext)
        var textView1:TextView=itemview.findViewById(R.id.locationtext)
        var image:ImageView=itemview.findViewById(R.id.image)
        var detaislbutton:Button=itemview.findViewById(R.id.viewdetails)

    }
}
