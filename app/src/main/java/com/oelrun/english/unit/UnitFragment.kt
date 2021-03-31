package com.oelrun.english.unit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.oelrun.english.HelpData
import com.oelrun.english.R
import com.oelrun.english.database.UnitProgressDatabase
import com.oelrun.english.databinding.FragmentUnitBinding

class UnitFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentUnitBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_unit, container, false
        )

        val application = requireNotNull(this.activity).application
        val arguments = UnitFragmentArgs.fromBundle(requireArguments())
        val dataSource = UnitProgressDatabase.getInstance(application).unitProgressDatabaseDao

        val viewModelFactory = UnitViewModelFactory(arguments.unitId, dataSource)
        val unitViewModel = ViewModelProvider(this, viewModelFactory)
                .get(UnitViewModel::class.java)

        binding.dictionaryBtn.setOnClickListener {
            this.findNavController().navigate(UnitFragmentDirections
                    .actionUnitFragmentToDictionaryFragment(arguments.unitId))
        }

        binding.game1Btn.setOnClickListener {
            this.findNavController().navigate(UnitFragmentDirections
                    .actionUnitFragmentToWordsGameFragment(arguments.unitId))
        }
        binding.game2Btn.setOnClickListener {
            this.findNavController().navigate(UnitFragmentDirections
                    .actionUnitFragmentToLettersGameFragment(arguments.unitId))
        }

        binding.unitViewModel = unitViewModel
        binding.lifecycleOwner = this

        val helpData = HelpData(arguments.unitId)
        binding.textUnit.text = helpData.unitText

        (activity as AppCompatActivity).supportActionBar?.title = helpData.unitName
        return binding.root
    }
}