package com.dsm.bookapplication.view.recycler.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dsm.bookapplication.model.Book

open class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    open fun bindView(book: Book){

    }
}