package com.s.ajrami.zoo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.s.ajrami.zoo.R
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.listeners.IPickResult

class UpdateItem : AppCompatActivity(), IPickResult {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_item)
    }

    override fun onPickResult(r: PickResult?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
