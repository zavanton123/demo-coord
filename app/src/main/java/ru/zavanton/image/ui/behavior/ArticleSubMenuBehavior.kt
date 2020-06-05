package ru.zavanton.image.ui.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import ru.zavanton.image.ui.view.ArticleSubMenu
import ru.zavanton.image.ui.view.BottomBar

class ArticleSubMenuBehavior : CoordinatorLayout.Behavior<ArticleSubMenu> {

    constructor() : super()
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun layoutDependsOn(parent: CoordinatorLayout, child: ArticleSubMenu, dependency: View): Boolean {
        Log.d("zavanton", "zavanton - layoutDependsOn")
        return dependency is BottomBar
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: ArticleSubMenu, dependency: View): Boolean {
        Log.d("zavanton", "zavanton - onDependentViewChanged: translationY -> ${(dependency as BottomBar).translationY}")

        child.translationY = dependency.translationY - dependency.height


        return true
    }

    override fun onDependentViewRemoved(parent: CoordinatorLayout, child: ArticleSubMenu, dependency: View) {
        Log.d("zavanton", "zavanton - onDependentViewRemoved")
        child.translationY = dependency.translationY - dependency.height
        super.onDependentViewRemoved(parent, child, dependency)
    }
}