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
import com.s.ajrami.zoo.dp.DatabaseHelper
import com.s.ajrami.zoo.model.Animal
import kotlinx.android.synthetic.main.fragment_animal.view.*

class AnimalFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_animal, container, false)

        val db = DatabaseHelper(requireContext())
        val animal_List = db.getAllanimals()

        Log.e("animal", animal_List.size.toString())


        root.rv_animal.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = AnimalAdapter(requireActivity(), animal_List)

        root.rv_animal.adapter = adapter

        return root
    }



}
