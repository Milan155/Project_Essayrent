package com.example.essayrent.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.essayrent.Activity.About_us
import com.example.essayrent.Activity.Property_tax
import com.example.essayrent.Activity.View_Cat
import com.example.essayrent.Activity.maintenance_req
import com.example.essayrent.Models.Dashboardmodel
import com.example.essayrent.R
import com.squareup.picasso.Picasso

class DashbaordAdapter(var context: Context, var list: MutableList<Dashboardmodel>):RecyclerView.Adapter<DashbaordAdapter.Myview1>()
{


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview1 {
      var view = LayoutInflater.from(context).inflate(R.layout.dashboard_design,parent,false)
        return Myview1(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Myview1, position: Int) {

        holder.textView.setText(list.get(position).category_name)
        Picasso.with(context).load(list.get(position).category_img).placeholder(R.mipmap.ic_launcher).into(holder.imageview)

        holder.itemView.setOnClickListener {
            if (position==0)
            {
                var i = Intent(context, View_Cat::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }
            if (position==1)
            {
                var i = Intent(context, Property_tax::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }
            if (position==2)
            {
                var i = Intent(context, maintenance_req::class.java)
                i.putExtra("mypos",list.get(position).id)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }

            if (position==3)
            {
                var i = Intent(context, About_us::class.java)
                i.putExtra("mypos",list.get(position).id)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }
        }
        }

    class Myview1(Itemview:View ):RecyclerView.ViewHolder(Itemview)
    {
        var textView: TextView = Itemview.findViewById(R.id.text)
        var imageview: ImageView = Itemview.findViewById(R.id.img)
    }
}
