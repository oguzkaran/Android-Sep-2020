package org.csystem.samples.application.alertdialog

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onExitButtonClicked(view: View)
    {
        val dialog = AlertDialog.Builder(this)
                .setTitle("Dikkat")
                .setMessage("Kapatmak istediğinize emin misiniz?")
                .setPositiveButton("Evet") {_, _ -> this.finish()}
                .setNegativeButton("Hayır") {_, _ -> Toast.makeText(this, "Kapatmadığınız için teşekkürler", Toast.LENGTH_LONG).show()}
                .setNeutralButton("İptal") {_, _ -> }
                .create();
        dialog.show()
    }

    override fun onStop()
    {
        Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show()
        super.onStop()
    }
    override fun onPause()
    {
        Toast.makeText(this, "onPause", Toast.LENGTH_LONG).show()
        super.onPause()
    }
}