package com.example.calculadorapdm20202

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.subtitle = "Configurações"

        settings = intent.getParcelableExtra(MainActivity.EXTRA_SETTINGS) ?: Settings(false)
        calculatorTypeRg.check(if (settings.advanced) R.id.advancedRb else R.id.basicRb)
    }

    fun saveSettings(view: View) {
        if (view.id == R.id.saveBt) {
            settings.advanced = advancedRb.isChecked
            val resultIntent = Intent()
            resultIntent.putExtra(MainActivity.EXTRA_SETTINGS, settings)
            setResult(RESULT_OK, resultIntent)

            finish()
        }
    }
}