package com.s.ajrami.zoo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.s.ajrami.zoo.R
import com.s.ajrami.zoo.adapter.AnimalAdapter
import com.s.ajrami.zoo.adapter.BirdAdapter
import com.s.ajrami.zoo.dp.DatabaseHelper
import com.s.ajrami.zoo.model.Animal
import com.s.ajrami.zoo.model.Bird
import kotlinx.android.synthetic.main.fragment_animal.view.*
import kotlinx.android.synthetic.main.fragment_bird.view.*

class BirdFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_bird, container, false)

        val db = DatabaseHelper(requireContext())
        val bird_List = db.getAllbirds()

        Log.e("bird", bird_List.size.toString())

        root.rv_bird.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = BirdAdapter(requireActivity(), bird_List)

        root.rv_bird.adapter = adapter

        return root
    }
    }

