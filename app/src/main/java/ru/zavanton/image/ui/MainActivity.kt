package ru.zavanton.image.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import ru.zavanton.image.R
import ru.zavanton.image.extensions.gone
import ru.zavanton.image.extensions.visible

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        articleSubMenu.setOnClickListener {
            toggleCustomBottomBar()
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
