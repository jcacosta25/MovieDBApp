package io.jcal.theMovie.utils

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(context: Context, padding: Int) : RecyclerView.ItemDecoration() {
    private val spacing: Int

    init {
        val metrics = context.resources.displayMetrics
        spacing = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, padding.toFloat(), metrics)
            .toInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position != state.itemCount.dec()) {
            outRect.bottom = spacing
        }
    }
}
