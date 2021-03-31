package com.oelrun.english.dictionary

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oelrun.english.R
import com.oelrun.english.Words

class DictionaryAdapter():
        RecyclerView.Adapter<DictionaryAdapter.ViewHolder>() {

    var data = listOf<Words.Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class ViewHolder private constructor (itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(item: Words.Item) {
            itemView.findViewById<ImageView>(R.id.img_word).setImageResource(item.source)
            itemView.findViewById<TextView>(R.id.word_rus).text = item.rus
            itemView.findViewById<TextView>(R.id.word_eng).text = item.eng

            itemView.setOnClickListener {
                val sound = MediaPlayer.create(it.context, item.sound)
                sound.start()
                sound.setOnCompletionListener {
                    sound.reset()
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                        .inflate(R.layout.list_item_dictionary, parent, false)

                return ViewHolder(view)
            }
        }
    }
}