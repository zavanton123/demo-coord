package ru.zavanton.image.ui.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.widget.Checkable
import androidx.core.content.ContextCompat
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.zavanton.image.R

class CustomFab : FloatingActionButton, Checkable {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var isChecked = false
    private val animation: AnimatorSet

    init {
        val ca = ContextCompat.getColor(context, R.color.colorAccent)
        val cw = ContextCompat.getColor(context, R.color.white)

        val rotateAnim = ObjectAnimator.ofFloat(this, "rotation", 135f)
        val iconAnim = ValueAnimator.ofArgb(cw, ca)
        iconAnim.addUpdateListener { imageTintList = ColorStateList.valueOf(it.animatedValue as Int) }

        val bgAnim = ValueAnimator.ofArgb(ca, cw)
        bgAnim.addUpdateListener { backgroundTintList = ColorStateList.valueOf(it.animatedValue as Int) }

        animation = AnimatorSet().apply {
            interpolator = FastOutSlowInInterpolator()
            playTogether(rotateAnim, iconAnim, bgAnim)
        }
    }

    override fun performClick(): Boolean {
        toggle()
        return super.performClick()
    }

    override fun isChecked(): Boolean {
        return isChecked
    }

    override fun toggle() {
        isChecked = !isChecked
        playAnimation()
    }

    override fun setChecked(checked: Boolean) {
        isChecked = checked
        playAnimation()
    }

    private fun playAnimation() {
        if (isChecked) {
            animation.start()
        } else {
            animation.reverse()
        }
    }
}