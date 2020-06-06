package ru.zavanton.image.ui.behavior

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.graphics.ColorUtils
import androidx.core.math.MathUtils
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import ru.zavanton.image.R
import java.lang.Math.max
import java.lang.Math.min

class OverScrollBehavior : AppBarLayout.Behavior {

    constructor() : super()
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private lateinit var targetView: View
    private lateinit var collapsingView: CollapsingToolbarLayout
    private var targetHeight: Int = 0
    private var parentHeight: Int = 0
    private var totalDy: Int = 0
    private var lastScale: Float = 0f
    private var lastBottom: Int = 0
    private var isStopped: Boolean = false

    override fun onLayoutChild(parent: CoordinatorLayout, appBarLayout: AppBarLayout, layoutDirection: Int): Boolean {
        val layout = super.onLayoutChild(parent, appBarLayout, layoutDirection)
        if (!::targetView.isInitialized) {
            initialize(appBarLayout)
        }
        return layout
    }

    private fun initialize(appBarLayout: AppBarLayout) {
        targetView = appBarLayout.findViewById(R.id.ivProduct)
        collapsingView = appBarLayout.findViewById(R.id.collapsingToolbarLayout)
        parentHeight = appBarLayout.height
        targetHeight = targetView.height
    }

    override fun onStartNestedScroll(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int,
        type: Int
    ): Boolean {
        isStopped = false
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, appBarLayout: AppBarLayout, target: View, type: Int) {
        isStopped = true
        restore(appBarLayout)
        super.onStopNestedScroll(coordinatorLayout, appBarLayout, target, type)
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        val appBarLayoutBottom = child.bottom
        if ((dy < 0 && appBarLayoutBottom >= parentHeight) || (dy > 0 && appBarLayoutBottom > parentHeight)) {
            scale(child, dy)
        } else {
            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        }

    }

    private fun restore(appBarLayout: AppBarLayout) {
        if (totalDy > 0) {
            totalDy = 0
            val anim = ValueAnimator.ofFloat(lastScale, 1f)
            anim.addUpdateListener {
                val value = it.animatedValue as Float
                targetView.scaleX = value
                targetView.scaleY = value
                val bottomValue = (lastBottom - (lastBottom - parentHeight) * it.animatedFraction).toInt()
                appBarLayout.bottom = bottomValue
                collapsingView.bottom = bottomValue
                animateTitleColor(it.animatedFraction)
            }
            anim.start()
        }
    }

    private fun scale(appBarLayout: AppBarLayout, dY: Int) {
        if (isStopped) return
        totalDy += -dY
        totalDy = min(totalDy, targetHeight)
        lastScale = max(1f, 1f + totalDy.toFloat() / targetHeight)
        targetView.scaleX = lastScale
        targetView.scaleY = lastScale

        lastBottom = parentHeight + (targetHeight / 2 * (lastScale - 1)).toInt()
        appBarLayout.bottom = lastBottom
        collapsingView.bottom = lastBottom
        animateTitleColor(1f - totalDy.toFloat() / targetHeight)
    }

    private fun animateTitleColor(fraction: Float) {
        val alpha = MathUtils.clamp(255 * fraction, 0f, 255f).toInt()
        val color = ColorUtils.setAlphaComponent(Color.WHITE, alpha)
        collapsingView.setExpandedTitleColor(color)
    }
}