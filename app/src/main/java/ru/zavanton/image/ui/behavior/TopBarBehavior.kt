package ru.zavanton.image.ui.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.math.MathUtils
import androidx.core.view.ViewCompat

class TopBarBehavior : CoordinatorLayout.Behavior<TextView> {

    constructor() : super()
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: TextView,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        target: TextView,
        view: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        target.translationY = MathUtils.clamp(target.translationY - dy, -target.minHeight.toFloat(), 0F)
        Log.d("zavanton", "zavanton - translationY: ${target.translationY}")
        super.onNestedPreScroll(coordinatorLayout, target, view, dx, dy, consumed, type)
    }
}