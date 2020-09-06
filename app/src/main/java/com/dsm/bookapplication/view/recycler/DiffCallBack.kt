package com.dsm.bookapplication.view.recycler

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.dsm.bookapplication.model.Book


class DiffCallBack: DiffUtil.Callback {


    var oldBookList: List<Book>
    var newBookList: List<Book>

    constructor(oldBookList: List<Book>, newBookList: List<Book>) : super() {
        this.oldBookList = oldBookList
        this.newBookList = newBookList
    }


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldBookList[oldItemPosition] == newBookList[newItemPosition]
    }

    override fun getOldListSize(): Int {
        return oldBookList.size
    }

    override fun getNewListSize(): Int {
        return newBookList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldBookList[oldItemPosition].isContentSameAs(newBookList[newItemPosition])
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val diff = Bundle()

        diff.putSerializable("update", newBookList[newItemPosition])

        return diff
    }

}