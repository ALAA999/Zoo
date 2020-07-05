package com.s.ajrami.zoo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.s.ajrami.zoo.R
import com.s.ajrami.zoo.dp.DatabaseHelper
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import com.vansuita.pickimage.listeners.IPickResult
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.custom_item.*

class Add : AppCompatActivity(), IPickResult {
    var img: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val db = DatabaseHelper(this)


        btn_add.setOnClickListener {
            var name: String = name_item.text.toString()

            if (name.isEmpty()){
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
            } else {
                val res = db.addanimal(name,img.toString())
                if(res){
                    Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

            /*  var sp = sp_type.selectedItem.toString()

            if (name.isEmpty()){
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()}
           if (sp.equals("Animal")){
                db.addanimal(name,img.toString())

                } else if (sp.equals("Bird")){
                db.addbird(name, img.toString())
                }
                else
                db.addfish(name,img.toString())
                }*/


        add.setOnClickListener {
            PickImageDialog.build(PickSetup()).show(this)
        }}

        override fun onPickResult(r: PickResult?) {
            if (r!!.getError() == null) {
                add.setImageURI(r!!.uri);
                img = r.uri.toString()
            } else {
                Toast.makeText(this, r.error.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

