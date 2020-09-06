package com.dsm.bookapplication.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.dsm.bookapplication.R
import kotlinx.android.synthetic.main.ticker_data_view.view.*

class TickerDataView: ConstraintLayout {

    var fieldNameTextView: TextView
    var valueTextView: TextView

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        var view = LayoutInflater.from(context).inflate(R.layout.ticker_data_view, this, true)

        this.fieldNameTextView = view.fieldName
        this.valueTextView = view.value
    }


    fun setFieldName(fieldNameValue: String){
        fieldNameTextView.text = fieldNameValue
    }

    fun setFieldName(stringId: Int){
        fieldNameTextView.text = context.getString(stringId)
    }

    fun setValue(value: String){
        valueTextView.text = "$$value"
    }
}