package ru.zavanton.image.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import ru.zavanton.image.R
import ru.zavanton.image.extensions.gone
import ru.zavanton.image.extensions.init
import ru.zavanton.image.extensions.visible

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFabs()

        articleSubMenu.setOnClickListener {
            toggleCustomBottomBar()
        }

        customBottomBar.setOnClickListener {
            toggleArticleSubMenu()
        }
    }

    private fun setupFabs() {
        mini1.init(-96f, -96f)
        mini2.init(0f, -128f)
        mini3.init(96f, -96f)

        fab.setOnClickListener {
            if (mini1.isOrWillBeShown) mini1.hide() else mini1.show()
            if (mini2.isOrWillBeShown) mini2.hide() else mini2.show()
            if (mini3.isOrWillBeShown) mini3.hide() else mini3.show()
        }
    }

    private fun toggleArticleSubMenu() {
        if (articleSubMenu.isVisible) {
            articleSubMenu.gone()
        } else {
            articleSubMenu.visible()
        }
    }

    private fun toggleCustomBottomBar() {
        if (customBottomBar.isVisible) {
            customBottomBar.gone()
        } else {
            customBottomBar.visible()
        }
    }
}
