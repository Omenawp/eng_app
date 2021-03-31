package com.oelrun.english.wordsgame

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.oelrun.english.HelpData
import com.oelrun.english.R
import com.oelrun.english.Words
import com.oelrun.english.database.UnitProgressDatabase
import com.oelrun.english.databinding.FragmentGameWordsBinding
import kotlinx.coroutines.launch


class WordsGameFragment : Fragment() {

    private lateinit var binding: FragmentGameWordsBinding
    private lateinit var data: MutableList<Words.Item>
    private lateinit var options: List<Button>
    private var level: Int = 0
    private lateinit var rightButton: View
    private var score: Int = 0
    private var mistakes: String = ""
    private lateinit var helpData: HelpData


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_words, container, false)

        val arguments = WordsGameFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application
        val dataBase = UnitProgressDatabase.getInstance(application).unitProgressDatabaseDao

        data = Words(arguments.unitId).data
        data.shuffle()

        helpData = HelpData(arguments.unitId)

        options = listOf(binding.option1, binding.option2, binding.option3, binding.option4)

        binding.nextButton.setOnClickListener {
            if(level < data.size - 1) {
                level++
                play()
            }
            else {
                if(score == 10) {
                    lifecycleScope.launch {
                        dataBase.updateGameWords(arguments.unitId)
                    }
                }
                this.findNavController().navigate(WordsGameFragmentDirections
                        .actionWordsGameFragmentToResultFragment(
                                score, mistakes, data.size, 1, arguments.unitId))
            }
            binding.nextButton.visibility = View.INVISIBLE
        }

        play()
        return binding.root
    }

    private fun play() {
        val item = data[level]

        options.forEach {button ->
            button.setOnClickListener {
                checkAnswer(it)
            }
        }

        binding.wordImage.setImageResource(item.source)
        binding.wordText.text = item.rus
        setOptions()

        (activity as AppCompatActivity).supportActionBar?.title = "${helpData.unitName} ${level+1}/10"
    }

    private fun setOptions() {
        val numbers = makeRandomList(level)

        for (i in 0..3) {
            val position = numbers[i]
            options[i].text = data[position].eng
            options[i].setBackgroundResource(R.drawable.game_item_base)

            if(position == level) {
                rightButton = options[i]
            }
        }
    }

    private fun checkAnswer(it:View) {
        options.forEach {button ->
            button.setOnClickListener {}
        }

        if(it.id == rightButton.id)  {
            score++
            it.setBackgroundResource(R.drawable.game_item_right)
        }
        else {
            mistakes += "${data[level].eng} "
            it.setBackgroundResource(R.drawable.game_item_wrong)
            rightButton.setBackgroundResource(R.drawable.game_item_right)
        }

        val sound = MediaPlayer.create(this.requireContext(), data[level].sound)
        sound.start()

        sound.setOnCompletionListener {
            binding.nextButton.visibility = View.VISIBLE
            it.reset()
        }

    }

    private fun makeRandomList(position: Int): MutableList<Int> {
        val randomList = (0..9).toMutableList()
        randomList.remove(position)
        val numbers = randomList.shuffled().take(3).toMutableList()
        numbers.add(position)

        numbers.shuffle()
        return numbers
    }
}