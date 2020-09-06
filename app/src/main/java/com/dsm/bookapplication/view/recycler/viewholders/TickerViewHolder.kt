package com.dsm.bookapplication.view.recycler.viewholders

import android.view.View
import com.dsm.bookapplication.R
import com.dsm.bookapplication.model.Book
import kotlinx.android.synthetic.main.cell_ticker_detail.view.*

class TickerViewHolder(itemView: View): BookViewHolder(itemView) {



    override fun bindView(book: Book){

        itemView.title.text = book.name

        book.ticker?.let {

            itemView.volume24.setFieldName(R.string.volume24)
            itemView.volume24.setValue(it.volume.toString())

            itemView.dayHigh.setFieldName(R.string.day_high)
            itemView.dayHigh.setValue(it.dayHigh.toString())

            itemView.dayLow.setFieldName(R.string.day_low)
            itemView.dayLow.setValue(it.dayLow.toString())

            itemView.spread.setFieldName(R.string.spread)
            itemView.spread.setValue(it.spread.toString())

            itemView.bidPrice.setFieldName(R.string.bid_price)
            itemView.bidPrice.setValue(it.bid.toString())

            itemView.askPrice.setFieldName(R.string.ask_price)
            itemView.askPrice.setValue(it.ask.toString())

        }
    }
}