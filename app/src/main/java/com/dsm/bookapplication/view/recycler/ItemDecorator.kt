package com.dsm.bookapplication.view.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecorator: RecyclerView.ItemDecoration {
    private var topSpacing: Int
    private var rightSpacing: Int
    private var bottomSpacing: Int
    private var leftSpacing: Int

    constructor(topSpacing: Int, rightSpacing: Int, bottomSpacing: Int, leftSpacing: Int){
        this.topSpacing = topSpacing
        this.rightSpacing = rightSpacing
        this.bottomSpacing = bottomSpacing
        this.leftSpacing = leftSpacing
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = bottomSpacing
        outRect.left = leftSpacing
        outRect.right = rightSpacing

        if(parent.getChildLayoutPosition(view) == 0)
            outRect.top = topSpacing
        else
            outRect.top = 0

    }

}