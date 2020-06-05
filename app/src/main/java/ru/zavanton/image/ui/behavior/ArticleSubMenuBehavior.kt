package ru.zavanton.image.ui.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.marginEnd
import ru.zavanton.image.ui.view.ArticleSubMenu
import ru.zavanton.image.ui.view.BottomBar

class ArticleSubMenuBehavior : CoordinatorLayout.Behavior<ArticleSubMenu> {

    constructor() : super()
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun layoutDependsOn(parent: CoordinatorLayout, child: ArticleSubMenu, dependency: View): Boolean {
        return dependency is BottomBar
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: ArticleSubMenu, dependency: View): Boolean {
        return if (dependency is BottomBar && dependency.translationY >= 0) {
            val fraction = dependency.translationY / dependency.minHeight
            val translationX = (child.width + child.marginEnd) * fraction
            child.translationX = translationX
            true
        } else {
            false
        }
    }
}