package com.s.ajrami.zoo.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.s.ajrami.zoo.R
import com.s.ajrami.zoo.dp.DatabaseHelper
import com.s.ajrami.zoo.model.Animal
import com.s.ajrami.zoo.ui.MainActivity
import com.s.ajrami.zoo.ui.UpdateItem
import kotlinx.android.synthetic.main.custom_item.view.*

class AnimalAdapter(var activity: Activity, var data: ArrayList<Animal>) :
    RecyclerView.Adapter<AnimalAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.custom_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameanimal.text = data[position].name_animal
        holder.imganimal.setImageURI(Uri.parse(data[position].image_animal.toString()))




        holder.update.setOnClickListener {

            val db = DatabaseHelper(activity)

            var i = Intent(activity,UpdateItem::class.java)
             activity.startActivity(i)



            db.updateAnimal(
                data[position].id_animal,
                data[position].name_animal,
                data[position].image_animal
            )


        }
        holder.delete.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Delete Animal")
            builder.setMessage("Are you sure ?")

            builder.setPositiveButton("Yes") { _, which ->
                val db = DatabaseHelper(activity)

                val b = db.deleteanimal(data[position].id_animal)
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
        val nameanimal = itemView.name
        val imganimal = itemView.img
        val update = itemView.update
        val delete=itemView.delete
    }}

