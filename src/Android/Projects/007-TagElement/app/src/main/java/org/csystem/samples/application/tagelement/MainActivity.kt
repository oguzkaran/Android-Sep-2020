package org.csystem.samples.application.tagelement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.csystem.samples.application.tagelement.model.PlaceInfo

class MainActivity : AppCompatActivity() {
    private lateinit var mEditTextPlace: EditText
    private lateinit var mEditTextLatitude: EditText
    private lateinit var mEditTextLongitude: EditText
    private lateinit var mTextViewPlace: TextView

    private fun getPlaceInfo() : PlaceInfo
    {
        val name = mEditTextPlace.text.toString()
        val latitude = mEditTextLatitude.text.toString().toDouble()
        val longitude = mEditTextLongitude.text.toString().toDouble()

        return PlaceInfo(name, latitude, longitude)
    }

    private fun initViews()
    {
        mEditTextPlace = findViewById(R.id.mainActivityEditTextPlace)
        mEditTextLatitude = findViewById(R.id.mainActivityEditTextLatitude)
        mEditTextLongitude = findViewById(R.id.mainActivityEditTextLongitude)
        mTextViewPlace = findViewById(R.id.mainActivityTextViewPlace)
    }

    private fun initialize()
    {
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    fun onSaveButtonClicked(view: View)
    {
        try {
            getPlaceInfo().also {
                mTextViewPlace.tag = it
                mTextViewPlace.text = it.toString()
            }
        }
        catch (ex: Throwable) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }

    fun onExitButtonClicked(view: View) = finish()

    fun onPlaceTextViewClicked(view: View)
    {
        (mTextViewPlace.tag as PlaceInfo).apply { // Dikkat örnek için apply yapıldı also daha iyi olur
            val message = "${name}-${latitude}:${longitude}"
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
        }
    }
}