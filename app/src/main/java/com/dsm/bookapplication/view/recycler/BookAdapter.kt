package com.dsm.bookapplication.view.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dsm.bookapplication.R
import com.dsm.bookapplication.model.Book
import com.dsm.bookapplication.view.recycler.viewholders.BookViewHolderInteraction
import com.dsm.bookapplication.view.recycler.viewholders.BookViewHolder
import com.dsm.bookapplication.view.recycler.viewholders.MainBookViewHolder
import com.dsm.bookapplication.view.recycler.viewholders.TickerViewHolder


class BookAdapter : RecyclerView.Adapter<BookViewHolder> {

    var bookList: ArrayList<Book> = ArrayList()

    var bookViewHolderInteraction: BookViewHolderInteraction? = null

    var style: Style

    constructor(
        bookViewHolderInteraction: BookViewHolderInteraction? = null,
        style: Style
    ) : super() {
        this.bookViewHolderInteraction = bookViewHolderInteraction
        this.style = style
    }


    override fun getItemViewType(position: Int): Int {
        return when (style) {
            Style.Book -> R.layout.cell_book
            Style.Ticker -> R.layout.cell_ticker_detail
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return when (style) {

            Style.Book -> MainBookViewHolder(view, bookViewHolderInteraction)

            Style.Ticker -> TickerViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindView(bookList[position])
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int, payloads: MutableList<Any>) {


        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }
        else {
            val o = payloads[0] as Bundle
            for (key in o.keySet()) {
                if (key == "update") {
                    var updatedBoook = o.getSerializable(key) as Book
                    holder.bindView(book = updatedBoook)
                }
            }
        }


    }

    fun updateData(newBooks: List<Book>) {

        val diffResult = DiffUtil.calculateDiff(
            DiffCallBack(
                bookList,
                newBooks
            )
        )
        diffResult.dispatchUpdatesTo(this)
        bookList.clear()
        newBooks.forEach {
            bookList.add(it.clone() as Book)
        }
    }


    sealed class Style {
        object Book : Style()
        object Ticker : Style()
    }


}