package com.dsm.bookapplication.model

import java.io.Serializable
import java.math.BigDecimal

class Book : ContentStatus, Serializable, Cloneable {

    var name: String
    
    
    var minimunAmount: BigDecimal

    var maximumAmount: BigDecimal

    var minimumPrice: BigDecimal

    var maximumPrice: BigDecimal

    var minimumValue: BigDecimal

    var maximumValue: BigDecimal

    var ticker: Ticker?


    constructor(name: String, minimumAmount: BigDecimal, maximumAmount: BigDecimal, minimumPrice: BigDecimal, maximumPrice: BigDecimal, minimumValue: BigDecimal, maximumValue: BigDecimal, ticker: Ticker? = null) {
        this.name = name
        this.minimunAmount = minimumAmount
        this.maximumAmount = maximumAmount
        this.minimumPrice = minimumPrice
        this.maximumPrice = maximumPrice
        this.minimumValue = minimumValue
        this.maximumValue = maximumValue
        this.ticker = ticker
    }


    override fun hashCode(): Int {
        return name.hashCode()
    }

    public override fun clone(): Any {

        return Book(this.name, this.minimunAmount,
            this.maximumAmount, this.minimumPrice,
            this.maximumPrice, this.minimumValue,
            this.maximumValue, this.ticker?.let { it.clone() as Ticker } ?: run { null })
    }



    override fun isContentSameAs(bookToCompareTo: Any?): Boolean {

        if(this != bookToCompareTo)
            return false

        bookToCompareTo as Book

        if(this.ticker == null && bookToCompareTo.ticker != null)
            return false

        if(this.ticker != null && bookToCompareTo.ticker == null)
            return false

        if(this.ticker != null && bookToCompareTo.ticker != null)
            return this.ticker!!.isContentSameAs(bookToCompareTo.ticker)

        return true

    }

    override fun equals(other: Any?): Boolean {
        return other is Book && name == other.name
    }



}