package com.oelrun.english.dictionary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.oelrun.english.R
import com.oelrun.english.Words
import com.oelrun.english.databinding.FragmentDictionaryBinding


class DictionaryFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentDictionaryBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_dictionary, container, false
        )

        val arguments = DictionaryFragmentArgs.fromBundle(requireArguments())

        val manager = GridLayoutManager(activity, 2)
        binding.dictionaryList.layoutManager = manager

        val adapter = DictionaryAdapter()
        adapter.data = Words(arguments.unitId).data
        binding.dictionaryList.adapter = adapter

        return binding.root
    }
}
