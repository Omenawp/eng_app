package com.oelrun.english.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oelrun.english.HelpData
import com.oelrun.english.R
import com.oelrun.english.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentResultBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_result, container, false )

        val arguments = ResultFragmentArgs.fromBundle(requireArguments())
        val score = arguments.score
        val unitId = arguments.unitId
        val mistakes = arguments.mistakes.trim()

        if(mistakes.isEmpty()) {
            binding.learnText.text = getString(R.string.congrats)
        }
        else {
            val list = mistakes.split(" ")
            if(list.size <= 5) {
                val mistakesString = mistakesFormat(list)
                binding.learnText.text = String.format(getString(R.string.learn_words), mistakesString)
            } else {
                binding.learnText.text = ""
            }
        }


        val newResult = score*arguments.gameId
        binding.resultImage.setImageResource(
                when(newResult) {
                    in 0..4 -> R.drawable.cat_sad
                    in 5..9 -> R.drawable.cat_good
                    else -> R.drawable.cat_exell
                }
        )

        binding.scoreNumber.text = String.format(getString(R.string.result_number), score, arguments.total)

        val helpData = HelpData(unitId)

        (activity as AppCompatActivity).supportActionBar?.title = "${helpData.unitName}"

        binding.playAgain.setOnClickListener {
            if(arguments.gameId == 1) {
                this.findNavController().navigate(
                        ResultFragmentDirections.actionResultFragmentToWordsGameFragment(unitId))
            } else {
                this.findNavController().navigate(
                        ResultFragmentDirections.actionResultFragmentToLettersGameFragment(unitId))
            }
        }

        binding.home.setOnClickListener {
            this.findNavController().navigate(
                    ResultFragmentDirections.actionResultFragmentToUnitFragment(unitId))
        }

        return binding.root
    }

    private fun mistakesFormat(arr: List<String>):String {
        var newString = ""

        if(arr.size == 1) {
            newString = arr[0]
        }
        else {
            for(i in 0..(arr.size - 2)) {
                newString += if(i == arr.size - 2) {
                    "${arr[i]} and ${arr[i+1]}"
                }
                else{
                    "${arr[i]}, "
                }
            }
        }

        return newString
    }
}