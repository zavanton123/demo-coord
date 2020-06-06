package ru.zavanton.image.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.util.TypedValue
import android.view.View
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun Context.dpToPx(dp: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        this.resources.displayMetrics
    )
}

fun Context.dpToIntPx(dp: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        this.resources.displayMetrics
    ).toInt()
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun FloatingActionButton.animateWithTranslation(trX: Float, trY: Float) {
    val animator = ObjectAnimator.ofPropertyValuesHolder(
        this,
        PropertyValuesHolder.ofFloat("translationX", this.translationX, trX),
        PropertyValuesHolder.ofFloat("translationY", this.translationY, trY)
    ).apply {
        duration = 300
        interpolator = FastOutSlowInInterpolator()
    }
    animator.start()
}

fun FloatingActionButton.init(trX: Float, trY: Float) {
    visibility = View.INVISIBLE
    addOnShowAnimationListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            animateWithTranslation(trX, trY)
            super.onAnimationStart(animation)
        }
    })
    addOnHideAnimationListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            animateWithTranslation(0f, 0f)
            super.onAnimationStart(animation)
        }
    })
}