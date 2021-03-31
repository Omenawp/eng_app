package com.oelrun.english.lettersgame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.children
import com.oelrun.english.R


class LettersAdapter(private var container: ViewGroup,
                     private var onClickListener: LetterListener) {

    var word = String()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private fun notifyDataSetChanged() {
        container.removeAllViews()
        val length = word.length - 1
        for(i in 0..length) { createView(i) }
        createLayouts()
    }

    private fun createView(i: Int) {
        val layoutInflater = LayoutInflater.from(container.context)
        val view = layoutInflater.inflate(R.layout.list_item_letters, container, false)
        view.findViewById<TextView>(R.id.letter).text = word[i].toString()
        view.id = View.generateViewId()
        view.setOnClickListener { onClickListener.onClick(i, word[i]) }

        container.addView(view)
    }

    fun changeVisibility(position: Int, visibility: Boolean) {
        val view = container.getChildAt(position)
        view.visibility = if(visibility) View.VISIBLE else View.INVISIBLE
    }

    private fun createLayouts() {
        val views = container.children
        val count = container.children.count() - 1
        for(i in 0..count) {
            val view = views.elementAt(i)
            val layoutParams = view.layoutParams as ConstraintLayout.LayoutParams

            if(i == count || i % 4 == 3) {
                layoutParams.endToEnd = container.id
            }
            else {
                layoutParams.endToStart = views.elementAt(i+1).id
            }

            if(i % 4 == 0) {
                layoutParams.startToStart = container.id
                layoutParams.horizontalChainStyle = ConstraintSet.CHAIN_PACKED
                if(i == 0) {
                    layoutParams.topToTop = container.id
                }
                else {
                    layoutParams.topToBottom = views.elementAt(i - 4).id
                }
            }
            else {
                layoutParams.topToTop = views.elementAt(i-1).id
                layoutParams.startToEnd = views.elementAt(i-1).id
            }

            view.layoutParams = layoutParams
        }
    }
}

class LetterListener(val clickListener: (position: Int, item: Char) -> Unit) {
    fun onClick(position: Int, item: Char) = clickListener(position, item)
}