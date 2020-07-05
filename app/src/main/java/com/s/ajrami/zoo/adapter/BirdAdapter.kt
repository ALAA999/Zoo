package com.s.ajrami.zoo.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.s.ajrami.zoo.R
import com.s.ajrami.zoo.dp.DatabaseHelper
import com.s.ajrami.zoo.model.Bird
import com.s.ajrami.zoo.ui.UpdateItem
import kotlinx.android.synthetic.main.custom_item.view.*

class BirdAdapter(var activity: Activity, var data: ArrayList<Bird>) :
    RecyclerView.Adapter<BirdAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.custom_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.namebird.text = data[position].name_bird
        holder.imgbird.setImageURI(Uri.parse(data[position].image_bird.toString()))


        holder.update.setOnClickListener {
            var i = Intent(activity,UpdateItem::class.java)
            activity.startActivity(i)

            val db = DatabaseHelper(activity)
            db.updateBird(data[position].id_bird,data[position].name_bird,data[position].image_bird)

        }


        holder.delete.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Delete Bird")
            builder.setMessage("Are you sure ?")

            builder.setPositiveButton("Yes") { _ , which ->
                val db = DatabaseHelper(activity)

                val b = db.deletebird(data[position].id_bird)
                if (b) {
                    Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show()
                    data.removeAt(position)
                    notifyItemRemoved(position)
                } else {
                    Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setNegativeButton("No") { _, _ ->
            }

            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()

            false
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namebird = itemView.name
        val imgbird = itemView.img
        val update = itemView.update
        val delete=itemView.delete
    }


}

