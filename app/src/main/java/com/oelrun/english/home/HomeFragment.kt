package com.oelrun.english.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.oelrun.english.R
import com.oelrun.english.database.UnitProgressDatabase
import com.oelrun.english.databinding.FragmentHomeBinding


class HomeFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home, container, false
        )

        val manager = LinearLayoutManager(activity)
        binding.unitList.layoutManager = manager

        val application = requireNotNull(this.activity).application
        val dataBase = UnitProgressDatabase.getInstance(application).unitProgressDatabaseDao

        val viewModelFactory = HomeViewModelFactory(dataBase)
        val homeViewModel = ViewModelProvider(this, viewModelFactory)
            .get(HomeViewModel::class.java)

        binding.lifecycleOwner = this

        val adapter = HomeUnitAdapter(UnitListener { unitId ->
            homeViewModel.onUnitClicked(unitId)
        })

        binding.unitList.adapter = adapter

        homeViewModel.unitProgress.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it.isEmpty()) {
                    homeViewModel.createBd()
                }
                else{
                    adapter.submitList(it)
                    binding.loading.visibility = View.GONE
                }
            }
        })

        homeViewModel.navigateToUnitFragment.observe(viewLifecycleOwner, Observer { unitId ->
            unitId?.let {
                this.findNavController().navigate(HomeFragmentDirections
                        .actionHomeFragmentToUnitFragment(unitId))
                homeViewModel.toUnitFragmentNavigated()
            }
        })

        binding.headerText.text = getString(R.string.greetings)

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        return binding.root
    }
}