package com.s.ajrami.zoo.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.s.ajrami.zoo.R
import com.s.ajrami.zoo.common.Constants
import com.s.ajrami.zoo.common.Constants.ANIMAL_CATEGORY_NUM
import com.s.ajrami.zoo.common.Constants.BIRD_CATEGORY_NUM
import com.s.ajrami.zoo.dp.DatabaseHelper
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import com.vansuita.pickimage.listeners.IPickResult
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_update_item.*

class UpdateItem : AppCompatActivity(), IPickResult {

    var img: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_item)

        val db = DatabaseHelper(this)

        val id = intent.extras?.getInt(Constants.ID_KEY)
        val name = intent.extras?.getString(Constants.NAME_KEY)
        val image = intent.extras?.getString(Constants.IMAGE_KEY)
        val categoryNum = intent.extras?.getInt(Constants.CATEGORY_KEY)

        img = image
        current_name.setText(name)
        current_photo.setImageURI(Uri.parse(img.toString()))

        update_item.setOnClickListener {
            Log.e("categoryNum" , categoryNum.toString())
            val res = when (categoryNum) {
                ANIMAL_CATEGORY_NUM -> {
                    db.updateAnimal(id!!, current_name.text.toString(), img.toString())
                }
                BIRD_CATEGORY_NUM -> {
                    db.updateBird(id!!, current_name.text.toString(), img.toString())
                }
                else -> {
                    db.updateFish(id!!, current_name.text.toString(), img.toString())
                }
            }
            if (res) {
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }

        current_photo.setOnClickListener {
            PickImageDialog.build(PickSetup()).show(this)
        }
    }

    override fun onPickResult(r: PickResult?) {
        if (r!!.getError() == null) {
            current_photo.setImageURI(r!!.uri);
            img = r.uri.toString()
        } else {
            Toast.makeText(this, r.error.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
