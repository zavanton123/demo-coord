package ru.zavanton.image.ui.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.zavanton.image.ui.view.CustomFab

class HideFabBehavior : CoordinatorLayout.Behavior<FloatingActionButton> {

    constructor() : super()
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: FloatingActionButton,
        dependency: View
    ): Boolean {
        dependency as CustomFab
        return when {
            dependency.isChecked && dependency.isOrWillBeHidden -> {
                child.hide()
                true
            }
            dependency.isChecked -> {
                child.show()
                true
            }
            else -> false
        }
    }
}