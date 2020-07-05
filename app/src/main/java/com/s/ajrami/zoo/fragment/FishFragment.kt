package com.s.ajrami.zoo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.s.ajrami.zoo.R
import com.s.ajrami.zoo.adapter.BirdAdapter
import com.s.ajrami.zoo.adapter.FishAdapter
import com.s.ajrami.zoo.dp.DatabaseHelper
import com.s.ajrami.zoo.model.Bird
import com.s.ajrami.zoo.model.Fish
import kotlinx.android.synthetic.main.fragment_bird.view.*
import kotlinx.android.synthetic.main.fragment_fish.view.*

class FishFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_fish, container, false)

        val db = DatabaseHelper(requireContext())
        val fish_List = db.getAllfish()

        Log.e("bird", fish_List.size.toString())


        root.rv_fish.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = FishAdapter(requireActivity(), fish_List)

        root.rv_fish.adapter = adapter

        return root
    }
}

