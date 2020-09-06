package com.dsm.bookapplication.model

import java.math.BigDecimal


class Ticker: ContentStatus, Cloneable {

    var volume: BigDecimal

    var bookName: String

    var dayHigh: BigDecimal
    var last: BigDecimal
    var dayLow: BigDecimal
    var vwap: BigDecimal
    var ask: BigDecimal
    var bid: BigDecimal

    var createdAt: String
    var change24: BigDecimal

    var spread: BigDecimal

    constructor(
        volume: BigDecimal,
        dayHigh: BigDecimal,
        last: BigDecimal,
        dayLow: BigDecimal,
        vwap: BigDecimal,
        ask: BigDecimal,
        bid: BigDecimal,
        createdAt: String,
        bookName: String,
        change24: BigDecimal
    ) {
        this.volume = volume
        this.dayHigh = dayHigh
        this.last = last
        this.dayLow = dayLow
        this.vwap = vwap
        this.ask = ask
        this.bid = bid
        this.createdAt = createdAt
        this.bookName = bookName
        this.change24 = change24
        this.spread = ask - bid
    }

    public override fun clone(): Any {
        return Ticker( volume = this.volume,
            dayHigh = this.dayHigh,
            last = this.last,
            dayLow = this.dayLow,
            vwap = this.vwap,
            ask = this.ask,
            bid = this.bid,
            createdAt = this.createdAt,
            bookName = this.bookName,
            change24 = this.change24)
    }

    override fun hashCode(): Int {
        return bookName.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return other is Ticker && bookName == other.bookName
    }

    override fun isContentSameAs(tickerToCompareTo: Any?): Boolean {

        if(this != tickerToCompareTo)
            return false

        tickerToCompareTo as Ticker

        if(this.volume != tickerToCompareTo.volume)
            return false
        if(this.dayHigh != tickerToCompareTo.dayHigh)
            return false
        if(this.last != tickerToCompareTo.last)
            return false
        if(this.dayLow != tickerToCompareTo.dayLow)
            return false
        if(this.vwap != tickerToCompareTo.vwap)
            return false
        if(this.ask != tickerToCompareTo.ask)
            return false
        if(this.bid != tickerToCompareTo.bid)
            return false
        if(this.createdAt != tickerToCompareTo.createdAt)
            return false
        if(this.change24 != tickerToCompareTo.change24)
            return false


        return true

    }
}