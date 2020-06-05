package ru.zavanton.image.ui.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.math.MathUtils
import androidx.core.view.ViewCompat
import ru.zavanton.image.ui.view.BottomBar

class BottomBarBehavior : CoordinatorLayout.Behavior<BottomBar> {

    constructor() : super()
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: BottomBar,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        target: BottomBar,
        view: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        target.translationY = MathUtils.clamp(target.translationY + dy, 0f, target.minHeight.toFloat())
        super.onNestedPreScroll(coordinatorLayout, target, view, dx, dy, consumed, type)
    }
}