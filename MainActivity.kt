package com.example.customsnackbar

import SelectedDatesDecorator
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_back)

        showCenteredSnackbarWithDimAndTextAligned("I am Snackbar with dim")
    }

    private fun showCenteredSnackbarWithDimAndTextAligned(message: String) {
        val rootView = findViewById<ViewGroup>(android.R.id.content)
        // Добавляем затемнение
        val dimView = View(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundResource(R.drawable.dim_background)
        }
        rootView.addView(dimView)
        // Показываем Snackbar
        val snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
        // Настраиваем Snackbar
        val snackbarView = snackbar.view
        val params = snackbarView.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.CENTER // Центрируем Snackbar
        snackbarView.layoutParams = params
        // Центрируем текст внутри Snackbar
        val textView = snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER // Выравнивание текста
        textView.gravity = Gravity.CENTER // Центрирование текста внутри TextView
        // Убираем затемнение после завершения
        snackbar.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                rootView.removeView(dimView) // Удаляем затемнение
            }
        })
        snackbar.show()
    }
}
