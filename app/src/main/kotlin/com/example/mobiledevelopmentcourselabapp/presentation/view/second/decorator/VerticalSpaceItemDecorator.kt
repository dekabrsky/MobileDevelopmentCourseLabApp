package com.example.mobiledevelopmentcourselabapp.presentation.view.second.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.adapter.PlayersAdapter.Companion.AD_ID
import com.example.mobiledevelopmentcourselabapp.utils.orZero

class VerticalSpaceItemDecorator(private val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position != parent.adapter?.itemCount.orZero() - 1
            && parent.adapter?.getItemViewType(position) == AD_ID
        ) {
            outRect.top = verticalSpaceHeight
            outRect.bottom = verticalSpaceHeight
        }
    }
    // статья про полный арсенал ItemDecorator https://habr.com/ru/articles/532552/
}