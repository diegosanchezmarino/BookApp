package com.dsm.bookapplication.view.recycler.viewholders

import android.view.View
import com.dsm.bookapplication.App
import com.dsm.bookapplication.R
import com.dsm.bookapplication.model.Book
import kotlinx.android.synthetic.main.cell_book.view.*

class MainBookViewHolder: BookViewHolder {


    private var bookViewHolderInteraction: BookViewHolderInteraction?
    var book: Book? = null

    constructor(itemView: View, bookViewHolderInteraction: BookViewHolderInteraction?) : super(itemView) {
        this.bookViewHolderInteraction = bookViewHolderInteraction

        itemView.setOnClickListener { bookViewHolderInteraction?.bookPressed(book) }
    }



    override fun bindView(book: Book){
        this.book = book

        itemView.title.text = book.name

        book.ticker?.let {
            itemView.lastPrice.text = "$${it.last}"
        } ?: run{
            itemView.lastPrice.text = App.instance.getString(
                R.string.unknown_value
            )
        }
    }
}