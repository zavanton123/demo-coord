package ru.zavanton.image.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.shape.MaterialShapeDrawable
import ru.zavanton.image.R

class CustomBottomBar : ConstraintLayout {

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context) : super(context)

    init {
        View.inflate(context, R.layout.layout_bottombar, this)
        val materialBackground = MaterialShapeDrawable.createWithElevationOverlay(context, 0F)
        materialBackground.elevation = this.elevation
        this.background = materialBackground
    }
}