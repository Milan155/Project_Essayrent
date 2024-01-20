package com.example.essayrent.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.essayrent.Activity.Rent_Payment
import com.example.essayrent.Activity.maintenance_apply
import com.example.essayrent.Models.On_house_model
import com.example.essayrent.R
import com.squareup.picasso.Picasso

class house_rentview(var context: Context, var list: List<On_house_model>):RecyclerView.Adapter<house_rentview.Myview4>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview4 {
        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.rentdetails_design, parent, false)
        return Myview4(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Myview4, position: Int) {

        holder.text1.setText(list.get(position).Price)
        holder.text2.setText(list.get(position).Description)
        holder.text3.setText(list.get(position).Location)
        Picasso.with(context).load(list.get(position).Image).into(holder.image1)

        holder.whatappbuttomn.setOnClickListener {
            var url = "https://api.whatsapp.com/send?phone=9099972756"
            var i = Intent(Intent.ACTION_VIEW)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.setData(Uri.parse(url))
            context.startActivity(i)
        }
        holder.contectsbutton.setOnClickListener {
            var i = Intent(context, Rent_Payment::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("id", list.get(position).id)
            i.putExtra("price", list.get(position).Price)
            i.putExtra("description", list.get(position).Description)
            i.putExtra("image", list.get(position).Image)
            context.startActivity(i)
        }

    }


    class Myview4(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var text1: TextView = itemview.findViewById(R.id.Pricetext)
        var text2: TextView = itemview.findViewById(R.id.Desctext)
        var text3: TextView = itemview.findViewById(R.id.Locationtext)
        var image1: ImageView = itemview.findViewById(R.id.viewimage)
        var whatappbuttomn: Button = itemview.findViewById(R.id.whatsappid)
        var contectsbutton: Button = itemview.findViewById(R.id.contectsid)
    }
}


