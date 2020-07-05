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
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.s.ajrami.zoo.R
import com.s.ajrami.zoo.dp.DatabaseHelper
import com.s.ajrami.zoo.model.Fish
import com.s.ajrami.zoo.ui.UpdateItem
import kotlinx.android.synthetic.main.custom_item.view.*


class FishAdapter(var activity: Activity, var data: ArrayList<Fish>) :
        RecyclerView.Adapter<FishAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(activity).inflate(R.layout.custom_item, parent, false)
            return MyViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.namefish.text = data[position].name_fish.toString()
            holder.imgfish.setImageURI(Uri.parse(data[position].image_fish.toString()))


            holder.update.setOnClickListener {

                var i = Intent(activity,UpdateItem::class.java)
                activity.startActivity(i)

                val db = DatabaseHelper(activity)
                db.updateFish(data[position].id_fish,data[position].name_fish!!,data[position].image_fish)

            }
            holder.delete.setOnClickListener {
                val builder = AlertDialog.Builder(activity)
                builder.setTitle("Delete Fish")
                builder.setMessage("Are you sure ?")

                builder.setPositiveButton("Yes") { dialogInterface, which ->
                    val db = DatabaseHelper(activity)

                    val b = db.deletefish(data[position].id_fish)
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
            val namefish = itemView.name
            val imgfish = itemView.img
            val update = itemView.update
            val delete=itemView.delete
        }


    }