package com.s.ajrami.zoo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.s.ajrami.zoo.R
import com.s.ajrami.zoo.common.Constants.*
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

            if (name.isEmpty()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
            } else {
                val res: Boolean = when (sp_type.selectedItemPosition) {
                    ANIMAL_CATEGORY_NUM -> {
                        db.addanimal(name, img.toString())
                    }
                    BIRD_CATEGORY_NUM -> {
                        db.addbird(name, img.toString())
                    }
                    FISH_CATEGORY_NUM -> {
                        db.addfish(name, img.toString())
                    }
                    else -> false
                }
                if (res) {
                    Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
        add.setOnClickListener {
            PickImageDialog.build(PickSetup()).show(this)
        }
    }

    override fun onPickResult(r: PickResult?) {
        if (r!!.getError() == null) {
            add.setImageURI(r!!.uri);
            img = r.uri.toString()
        } else {
            Toast.makeText(this, r.error.toString(), Toast.LENGTH_LONG).show();
        }
    }
}

