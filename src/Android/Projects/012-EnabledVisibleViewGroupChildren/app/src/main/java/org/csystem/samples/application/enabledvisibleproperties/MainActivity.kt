package org.csystem.samples.application.enabledvisibleproperties

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import org.csystem.samples.application.enabledvisibleproperties.common.Visibility

class MainActivity : AppCompatActivity() {
    private lateinit var mLinearLayoutPersonInfo: LinearLayout
    private fun clearPersonInfo()
    {
        mLinearLayoutPersonInfo.children
            .filter { it !is Button && it is TextView }
            .map { it as TextView }
            .forEach {it.text = ""}
    }

    private fun initPersonInfoLayout()
    {
        mLinearLayoutPersonInfo = findViewById(R.id.mainActivityLinearLayoutPersonInfo)
    }

    private fun initialize()
    {
        initPersonInfoLayout()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    fun onOKButtonClicked(view: View)
    {
        //...
    }

    fun onClearButtonClicked(view: View)
    {
        clearPersonInfo()
    }

    fun onEnableDisableButtonClicked(view: View)
    {
        if (mLinearLayoutPersonInfo.visibility != View.VISIBLE)
            return

        mLinearLayoutPersonInfo.children
            //.filter { it is EditText || it is Button }
            .forEach { it.isEnabled = !it.isEnabled }
    }

    fun onVisibleInvisibleGoneButtonClicked(view: View)
    {
        mLinearLayoutPersonInfo.visibility = Visibility.visibility
    }
}