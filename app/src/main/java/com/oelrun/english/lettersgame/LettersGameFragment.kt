package com.oelrun.english.lettersgame

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.oelrun.english.HelpData
import com.oelrun.english.R
import com.oelrun.english.Words
import com.oelrun.english.database.UnitProgressDatabase
import com.oelrun.english.databinding.FragmentGameLettersBinding
import kotlinx.coroutines.launch

class LettersGameFragment: Fragment() {
    private lateinit var binding: FragmentGameLettersBinding
    private lateinit var data: MutableList<Words.Item>
    private  lateinit var adapter: LettersAdapter
    var positions = mutableListOf<Int>()
    private var level: Int = 0
    private var score: Int = 0
    private var mistakes: String = ""
    private lateinit var helpData: HelpData

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_letters, container, false)

        val application = requireNotNull(this.activity).application
        val dataBase = UnitProgressDatabase.getInstance(application).unitProgressDatabaseDao

        val arguments = LettersGameFragmentArgs.fromBundle(requireArguments())

        data = Words(arguments.unitId).data
        data.shuffle()
        data = data.subList(0, 5)

        helpData = HelpData(arguments.unitId)

        binding.undo.setOnClickListener { undo() }

        binding.check.setOnClickListener {
            binding.check.visibility = View.GONE
            binding.undo.visibility = View.INVISIBLE
            //word check
            if (binding.textLettersPack.text == data[level].eng) {
                score++
                binding.textLettersPack.setTextColor(Color.parseColor("#BBDF39"))
            } else {
                mistakes += "${data[level].eng} "
                binding.textLettersPack.setTextColor(Color.parseColor("#FF6886"))
                binding.textRight.text = data[level].eng
                binding.textRight.visibility = View.VISIBLE
            }

            val sound = MediaPlayer.create(it.context, data[level].sound)
            sound.start()
            sound.setOnCompletionListener {
                binding.next.visibility = View.VISIBLE
                it.reset()
            }

        }

        binding.next.setOnClickListener {
            //continue or end game
            if (level < data.size - 1) {
                level++
                startGame()
            } else {
                if(score == data.size) {
                    lifecycleScope.launch {
                        dataBase.updateGameLetters(arguments.unitId)
                    }
                }
                this.findNavController().navigate(LettersGameFragmentDirections
                        .actionLettersGameFragmentToResultFragment(
                                score, mistakes, data.size, 2, arguments.unitId))
            }

            binding.textRight.visibility = View.GONE
            binding.next.visibility = View.GONE
            binding.textLettersPack.setTextColor(Color.parseColor("#585858"))
        }

        adapter = LettersAdapter(binding.lettersGrid, LetterListener {position, item ->
            onClickListener(position, item)
        })

        startGame()

        return binding.root
    }

    private fun undo() {
        if(positions.isNotEmpty()) {
            val newText = binding.textLettersPack.text.replaceFirst("[a-z](?=[_]|$)".toRegex(), "_")
            binding.textLettersPack.text = newText

            val position = positions.last()
            positions.remove(position)

            adapter.changeVisibility(position, true)
        }
        if(positions.isEmpty()) {
            binding.undo.visibility = View.INVISIBLE
        }

        if(binding.check.visibility == View.VISIBLE ) {
            binding.check.visibility = View.GONE
        }
    }

    private fun onClickListener(position: Int, letter: Char) {
        positions.add(position)
        adapter.changeVisibility(position, false)
        val newText = binding.textLettersPack.text.replaceFirst("[_]".toRegex(), letter.toString())
        binding.textLettersPack.text = newText
        binding.undo.visibility = View.VISIBLE

        if(positions.size == data[level].eng.length) {
            binding.check.visibility = View.VISIBLE
        }
    }

    private fun startGame() {
        val item = data[level]
        positions = mutableListOf()
        binding.imageWordLetters.setImageResource(item.source)
        binding.textWordRus.text = item.rus
        var str = ""
        repeat(item.eng.length) {
            str += "_"
        }
        binding.textLettersPack.text = str

        val len = item.eng.length - 1
        val randomList = (0..len).shuffled()
        var shuffledWord = ""
        randomList.forEach {
            shuffledWord += item.eng[it]
        }

        adapter.word = shuffledWord

        (activity as AppCompatActivity).supportActionBar?.title = "${helpData.unitName} ${level+1}/5"
    }
}